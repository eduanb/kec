package com.eduanbekker.kec

import com.natpryce.Failure
import com.natpryce.Result
import com.natpryce.Success
import com.natpryce.mapFailure
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class MapByPredicateTest {


	@Test
	fun testSimpleMap() {
		val list = listOf("Paul", "Eduan", "Bob")

		val newMap = list.mapByPredicate({ it == "Bob" }) {
			"Steven"
		}

		assertEquals(listOf("Paul", "Eduan", "Steven"), newMap)
	}

	@Test
	fun `test dsl returns mappings with different types`() {
		val list = listOf("Paul", "Eduan", "Bob")

		val newMap: List<Result<Int, String>> = list.mapAsResult({ it == "Paul" }) {
			mapSuccess {
				23
			}
			mapFailure {
				it
			}
		}
		// notice different types
		assertEquals(listOf(Success(23), Failure("Eduan"), Failure("Bob")), newMap)
	}

	@Test
	fun `test dsl returns same types but marks successess and failures`() {
		val list = listOf("Paul", "Eduan", "Bob")

		val newMap: List<Result<String, String>> = list.mapAsResult({ it == "Paul" }) {
			mapSuccess {
				it
			}
			mapFailure {
				it
			}
		}

		assertEquals(listOf(Success("Paul"), Failure("Eduan"), Failure("Bob")), newMap)
	}

	@Test
	fun `test dsl returns different values for each`() {
		val list = listOf("Paul", "Eduan", "Bob")

		val newMap: List<Result<Double, Int>> = list.mapAsResult({ it == "Paul" }) {
			mapSuccess {
				4.0
			}
			mapFailure {
				4
			}
		}

		assertEquals(listOf(Success(4.0), Failure(4), Failure(4)), newMap)
	}

	@Test
	fun `test do something with our failed results`() {
		val list = listOf("Paul", "Eduan", "Bob")

		val newMap: List<Result<String, String>> = list.mapAsResult({ it == "Paul" }) {
			mapSuccess {
				it
			}
			mapFailure {
				it
			}
		}

		assertEquals(listOf(Success("Paul"), Failure("Eduan"), Failure("Bob")), newMap)

		val failedMap = newMap.map { it.mapFailure { "So many Paul's" } }

		assertEquals(listOf(Success("Paul"), Failure("So many Paul's"), Failure("So many Paul's")), failedMap)
	}

}
