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
 * Same implementation as a scoping function run however limited to collections with
 * a more descriptive function name.
 */
fun <T : Collection<Any>, R> T.use(block: T.() -> R): R {
	return block()
}

/**
 * Generally it isn't necessary to convert a range to a list but it did come
 * handy this one time in the use extension function test case
 */
fun listOf(range: IntRange): List<Int> {
	return range.mapTo(ArrayList(), { x -> x })
}