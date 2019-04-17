package com.sa.kotlin_cleanarch.sample.utils.validations

import java.util.regex.Pattern


class ValidationHelper {

    private val EMAIL_PATTERNS =
        "^[\\w!#$%&'*+/=?`{|}~^'\"-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^'\"-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"
    private val PASSWORD_MIN_LENGTH = 6
    private val PASSWORD_MAX_LENGTH = 12

    fun isEmptyField(text: String): Boolean {
        return text.trim().isEmpty()
    }

    fun isValidEmail(email: String): Boolean {
        val p = Pattern.compile(EMAIL_PATTERNS, Pattern.CASE_INSENSITIVE)
        val m = p.matcher(email)
        return m.matches() && email.trim { it <= ' ' }.isNotEmpty()
    }

    fun isValid4Digit(email: String): Boolean {
        return (email.trim().length < 4)
    }

    fun isValid6Digit(email: String): Boolean {
        return (email.trim().length < 6)
    }

    fun isValidPassword(password: String?): Boolean {
        return password != null && password.length >= PASSWORD_MIN_LENGTH
    }

    fun isValidPasswordMinLength(password: String?): Boolean {
        return password != null && password.length >= PASSWORD_MIN_LENGTH
    }

    fun isValidPasswordMaxLength(password: String?): Boolean {
        return password != null && password.length <= PASSWORD_MAX_LENGTH
    }

}