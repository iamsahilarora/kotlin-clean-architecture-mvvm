package com.sa.kotlin_cleanarch.sample.utils.validations


class Validator constructor(var validationHelper: ValidationHelper) {


    fun validateEmail(email: String): ValidationResult {
        return if (!validationHelper.isEmptyField(email))
            if (!validationHelper.isValid6Digit(email))
                if (validationHelper.isValidEmail(email))
                    ValidationResult.SUCCESS
                else ValidationResult.ERROR_EMAIL
            else ValidationResult.ERROR_EMAIL_6
        else ValidationResult.EMPTY_EMAIL
    }


    fun validatePassword(password: String): ValidationResult {
        return if (!validationHelper.isEmptyField(password))
            if (validationHelper.isValidPassword(password))
                ValidationResult.SUCCESS
            else ValidationResult.ERROR_PASSWORD
        else ValidationResult.EMPTY_PASSWORD
    }

    fun validatePasswordSignup(password: String): ValidationResult {
        return if (!validationHelper.isEmptyField(password))
            if (validationHelper.isValidPasswordMinLength(password))
                if (validationHelper.isValidPasswordMaxLength(password))
                    ValidationResult.SUCCESS
                else ValidationResult.ERROR_PASSWORD_TOO_LONG
            else ValidationResult.ERROR_PASSWORD_TOO_SMALL
        else ValidationResult.EMPTY_PASSWORD
    }



}