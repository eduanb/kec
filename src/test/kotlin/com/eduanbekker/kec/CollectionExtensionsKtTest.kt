package com.eduanbekker.kec

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CollectionExtensionsKtTest {

    @Test
    fun testIndexedMap() {

        val simpleList = listOf("Alice", "Bob", "Charlie")
        val expectedMap = mapOf(0 to "Alice", 1 to "Bob", 2 to "Charlie")
        assertEquals(expectedMap, simpleList.toIndexedMap())

    }

    @Test
    fun mutableIndexedMap() {

        val simpleList = listOf(12, 15, 4, 6, 7)
        val expectedMap = mutableMapOf(0 to 12, 1 to 15, 2 to 4, 3 to 6, 4 to 7)
        assertEquals(expectedMap, simpleList.toIndexedMutableMap())
    }

}
