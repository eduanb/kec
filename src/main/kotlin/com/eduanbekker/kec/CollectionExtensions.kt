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