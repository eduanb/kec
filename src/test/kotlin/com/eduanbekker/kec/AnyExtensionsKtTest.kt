package com.eduanbekker.kec

import org.junit.jupiter.api.Test

class AnyExtensionsKtTest {

	@Test
	fun testExhaustive() {
		val n = 3

		//Note: Compiles without else
		when (n % 3) {
			0 -> println("$n % 3 == 0")
			1 -> println("$n % 3 == 1")
			2 -> println("$n % 3 == 2")
		}

		//Note: Needs the else
		when (n % 3) {
			0 -> println("$n % 3 == 0")
			1 -> println("$n % 3 == 1")
			2 -> println("$n % 3 == 2")
			else -> println("else")
		}.exhaustive()

	}
}