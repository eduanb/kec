package com.eduanbekker.kec

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@ExperimentalContracts
fun <T> Iterable<T>.mapByPredicate(block: MBPBuilder<T>.() -> Unit): Iterable<T> {
	val newBlock = MBPBuilder<T>().apply(block).build()

	return mapTo(ArrayList<T>(), newBlock)
}

class MBPBuilder<T> {
	private var pred: (T.() -> Boolean)? = null
	private var trans: (T.() -> T)? = null


	fun predicate(predicate: (T) -> Boolean) {
		pred = predicate
	}

	fun map(transform: (T) -> T) {
		trans = transform
	}


	@ExperimentalContracts
	fun build(): T.() -> T {

	 	if(pred.requireNotNull() && trans.requireNotNull()) {

			val passedPred: (T.() -> Boolean) = pred as T.() -> Boolean
			val passedMap: (T.() -> T) = trans as T.() -> T

			return { if(passedPred()) passedMap() else this }
		}
		error("Map and Predicate are required to create a map by predicate")
	}
}

@ExperimentalContracts
fun <T> T?.requireNotNull(): Boolean {

	contract {
		returns(true) implies (this@requireNotNull != null)
	}

	require(this != null) {
		"Map and Predicate are required to create a map by predicate"
	}
	return true
}
