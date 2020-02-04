package com.eduanbekker.kec

/**
 * Used to hide sensitive Strings like user names and passwords. It overrides the toString()
 * so you don't need to worry that the secret will be in any log. Being an inline class
 * means you can use it anywhere a normal string was used.
 */
inline class SecretString(val value: String) {
	override fun toString() = "********"
}