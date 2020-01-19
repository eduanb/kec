package com.eduanbekker.kec

import java.util.*

/**
 * Returns a nullable of the object which allows for safe calls and elvis operators.
 *
 * Slight improvement over "myOptional.orElse("defaultValue")" as this requires you to define the
 * type like this: val myOptional: Optional<String> = Optional.ofNullable(null)
 */
fun <T> Optional<T>.asNullable(): T? {
	return this.orElse(null)
}