package com.eduanbekker.kec

import com.natpryce.Failure
import com.natpryce.Result
import com.natpryce.Success

inline fun <T, reified S, reified F> Iterable<T>.mapAsResult(noinline predicate: (T) -> Boolean, block: MBPBuilder<T, S, F>.() -> Unit): List<Result<S, F>> {
	val newBlock = MBPBuilder<T, S, F>().apply(block).build(predicate)

	return mapTo(ArrayList(), newBlock)
}

class MBPBuilder<T, S, F> {
	private lateinit var successBlock: (T.() -> S)

	private lateinit var failureBlock: (T.() -> F)


	fun mapSuccess(transform: (T) -> S) {
		successBlock = transform
	}

	fun mapFailure(transform: (T) -> F) {
		failureBlock = transform
	}

	fun build(predicate: (T) -> Boolean): T.() -> Result<S, F> = {
		if (predicate(this)) Success(successBlock(this)) else Failure(failureBlock(this))
	}
}
