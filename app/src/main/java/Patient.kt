package com.gfg.recyclerview_kotlin

import java.time.LocalDate

enum class Gender {
    MALE, FEMALE
}

data class Patient(
    val firstName: String,
    val lastName: String,
    val birthDate: LocalDate,
    val gender: Gender,
    val phoneNumber: String
) {
    fun getFullName(): String =
        listOf(firstName, lastName)
            .filter { it.isNotBlank() }
            .joinToString(" ")

    override fun toString(): String = getFullName()
}
