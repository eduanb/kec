package com.eduanbekker.kec

/**
 * A receiver version of filter
 * Returns a list containing only elements matching the given [predicate].
 */
fun <T> List<T>.filterOn(predicate: T.() -> Boolean): List<T> {
    return this.filter { predicate.invoke(it) }
}

/**
 * Returns null if the list is empty, otherwise a nullable List
 * Useful for libraries like Jackson which by default renders a empty list of JSON
 * instead of skipping that property. Often used in conjunction with listOfNotNull()
 */
fun <T> List<T>.nullIfEmpty(): List<T>? {
    return if (this.isEmpty()) null else this
}

/**
 * Use the entire collection. Useful for sending the result of a stream of mappings
 * or filters etc etc, or initializing objects that require collections as parameters.
 * Same implementation as the scoping function `run` however limited to collections with
 * a more descriptive function name.
 */
fun <T : Collection<Any>, R> T.use(block: T.() -> R): R {
    return block()
}



/**
 * Map List to Index Map takes the index of an item in a list and stores the item with that index
 * in a map, useful for translating simple lists to ordered pairs for consistencies through transactions
 */

fun <V, T : List<V>> T.toIndexedMap(): Map<Int, V> {
    return mapIndexed {index, v -> index to v }.toMap()
}

/**
 * Map List to Index MutableMap takes the index of an item in a list and stores the item with that index
 * in a map, useful for translating simple lists to ordered pairs for consistencies through transactions,
 * but returns a Mutable Map
 */
fun <V, T : List<V>> T.toIndexedMutableMap(): MutableMap<Int, V> {
    return toIndexedMap().toMutableMap()
}
