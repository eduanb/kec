package com.eduanbekker.kec

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.contracts.ExperimentalContracts

class MapByPredicateTest {

	@Test
	fun testSimpleMap() {
		val list = listOf("Paul", "Eduan", "Bob")

		val newMap = list.mapByPredicate({it == "Bob"}) {
			"Steven"
		}

		assertEquals(listOf("Paul", "Eduan", "Steven"), newMap)
	}

	@Test
	@ExperimentalContracts
	fun testSimpleMapDsl() {
		val list = listOf("Paul", "Eduan", "Bob")

		val newMap = list.mapByPredicate {

			predicate {
				it == "Bob"
			}

			map {
				"Steven"
			}
		}

		assertEquals(listOf("Paul", "Eduan", "Steven"), newMap)
	}

	@Test
	@ExperimentalContracts
	fun testSimpleMapDslInvalid() {
		// missing both values should throw exception
		 assertThrows<IllegalArgumentException> {
			 listOf("Paul", "Eduan", "Bob").mapByPredicate {

			 }
		 }

		// missing map should throw an exception
		assertThrows<IllegalArgumentException> {
			listOf("Paul", "Eduan", "Bob").mapByPredicate {
				predicate {
					it == "Bob"
				}
			}
		}

		// missing predicate should throw an exception
		assertThrows<IllegalArgumentException> {
			listOf("Paul", "Eduan", "Bob").mapByPredicate {
				map {
					"Bob"
				}
			}
		}
	}

}
