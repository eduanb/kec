package com.eduanbekker.kec

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CollectionExtensionTests {

	data class User(val name: String, val isDeveloper: Boolean)

	@Test
	fun `test filter on`() {
		val list = listOf(User("Peter", true), User("John", false))

		assertEquals(list.filter { user -> user.isDeveloper }, list.filterOn { isDeveloper })
		assertEquals(list.filter { user -> user.name == "Peter" }, list.filterOn { name == "Peter" })
	}

	@Test
	fun `test null if empty`() {

		assertEquals(null, listOf<Any>().nullIfEmpty())

		val o1: String? = null
		val o2: String? = null
		assertEquals(null, listOfNotNull(o1, o2).nullIfEmpty())
	}
}
