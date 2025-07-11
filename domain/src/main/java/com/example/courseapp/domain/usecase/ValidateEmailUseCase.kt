package com.example.courseapp.domain.usecase

class ValidateEmailUseCase {
    private companion object {
        const val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$"
    }

    operator fun invoke(email: String): Boolean {
        val regex = EMAIL_REGEX.toRegex()
        return regex.matches(email)
    }
}