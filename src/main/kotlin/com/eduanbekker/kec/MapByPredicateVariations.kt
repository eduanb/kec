package com.eduanbekker.kec

import com.natpryce.Failure
import com.natpryce.Result
import com.natpryce.Success


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
 * @return Result is a simple wrapper class for class Success and failure created by natpryce. The library comes standard
 * with built in mapSuccess and map failure functions so it is ideal for our use case.
 *
 */
fun <T, S, F> Iterable<T>.mapByPredicate(predicate: T.() -> Boolean, passedTransform: (T) -> S, failedTransform: (T) -> F): Iterable<Result<S, F>> {
	val newTransform: T.() -> Result<S, F> = {
		if (predicate(this)) Success(passedTransform(this)) else Failure(failedTransform(this))
	}

	return mapTo(ArrayList(), newTransform)
}
