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

	@Test
	fun `test use case`() {
		val list = (1..4).map { listOf(0..it) }.use {
			flatten()
		}
		assertEquals(listOf(0, 1, 0, 1, 2, 0, 1, 2, 3, 0, 1, 2, 3, 4), list)

		list.filter { it % 2 == 0 }.distinct().use {
			assertEquals(listOf(0, 2, 4), this)
		}
	}
}
