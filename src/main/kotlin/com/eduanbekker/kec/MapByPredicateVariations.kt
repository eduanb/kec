package com.eduanbekker.kec

inline class MapResult<L,R>(val pair: Pair<List<L>, List<R>>) {
	fun failedList(): List<L> = pair.first

	fun successList(): List<R> = pair.second
}


/**
 * Only applies the mapping to an element in the collection if a passed in predicate returns true. The key benefit of
 * doing so is that it keeps all the other elements in the list. This becomes impossible to do when using a filter operation
 * One thing to note is that the collection must remain the same type so the mapping must return the same element stored
 * in the list.
 *
 */
fun <T> Iterable<T>.mapByPredicate(predicate: (T) -> Boolean, transform: (T) -> T): Iterable<T> {
	val newTransform: T.() -> T = {
		if (predicate(this)) transform(this) else this
	}

	return mapTo(ArrayList(), newTransform)
}


/**
 * The limitation of the previous mapByPredicate is solved here. This function provides mappings for both elements that
 * pass the predicate and elements that fail. This can then be thought of as a mapping for a simple if else case
 * if true should return x else return y. Objects returned can be of different types.
 *
 * @return MapResult is a simple wrapper class for class Pair. Adds no functionality but makes accessing a failed list
 * and success list a bit more readable.
 *
 */
fun <T, L, R> Iterable<T>.mapByPredicate(predicate: T.() -> Boolean, passedTransform: (T) -> R, failedTransform: (T) -> L): MapResult<L,R> {
	val leftList = mutableListOf<L>()
	val rightList = mutableListOf<R>()

	for (item in this) {
		if(predicate(item)) rightList.add(passedTransform(item)) else leftList.add(failedTransform(item))
	}

	return MapResult(leftList to rightList)
}
