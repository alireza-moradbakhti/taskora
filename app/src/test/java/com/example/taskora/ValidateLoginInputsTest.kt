package com.example.taskora

import com.example.taskora.feature_auth.domain.usecase.ValidateLoginInputs
import com.example.taskora.feature_auth.domain.util.EmptyEmailException
import com.example.taskora.feature_auth.domain.util.EmptyPasswordException
import com.example.taskora.feature_auth.domain.util.InvalidEmailException
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class ValidateLoginInputsTest {


    private lateinit var validateLoginInputs: ValidateLoginInputs

    @Before
    fun setup() {
        validateLoginInputs = ValidateLoginInputs()
    }

    @Test
    fun `empty email throws EmptyEmailException`() {
        val exception = assertThrows(EmptyEmailException::class.java) {
            validateLoginInputs("", "password123")
        }
        assertEquals("Email cannot be empty", exception.message)
    }

    @Test
    fun `invalid email format throws InvalidEmailException`() {
        val exception = assertThrows(InvalidEmailException::class.java) {
            validateLoginInputs("bademail", "password123")
        }
        assertEquals("Invalid email format", exception.message)
    }

    @Test
    fun `empty password throws EmptyPasswordException`() {
        val exception = assertThrows(EmptyPasswordException::class.java) {
            validateLoginInputs("test@example.com", "")
        }
        assertEquals("Password cannot be empty", exception.message)
    }

//    @Test
//    fun `valid input passes with no exception`() {
//        assertDoesNotThrow {
//            validateLoginInputs("user@mail.com", "securePass123")
//        }
//    }

}