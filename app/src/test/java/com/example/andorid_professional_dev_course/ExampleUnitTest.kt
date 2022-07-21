package com.example.andorid_professional_dev_course

import com.example.andorid_professional_dev_course.ui.MainActivity
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun string_isNotEmpty() {
        val string = "string"
        assertTrue(string.isNotEmpty())
    }

    @Test
    fun string_isEquals() {
        val string = "Hello World"
        assertEquals("Hello World", string)
    }

    @Test
    fun number_isInt(){
        val number = 1
        assertNotEquals(4,number + 4)
    }
}