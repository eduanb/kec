package com.eduanbekker.kec

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.*

class NullableExtensionTests {

	@Suppress("SENSELESS_COMPARISON")
	@Test
	fun `test nullable`() {
		val name = Optional.ofNullable(null)
		assertTrue(name.asNullable() == null) //Even the compiler knows it
		assertEquals(name.asNullable() ?: "Hello", "Hello")
		assertEquals(name.asNullable() ?: 100, 100)
	}
}
