package com.eduanbekker.kec

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class SecretStringTest {

	@Test
	fun testToString() {
		data class Credentials(val ip: String, val secretString: SecretString)

		val credentials = Credentials("192.168.1.1", SecretString("my-secret"))

		assertEquals("Credentials(ip=192.168.1.1, password=********)", credentials.toString())
	}
}