package com.gfg.recyclerview_kotlin

import java.time.LocalDate

enum class Gender {
    MALE,
    FEMALE
}
class Patient(
    var firstName: String? = null,
    var lastName: String? = null,
    var numero:String? = null,
    var birthDate: LocalDate? = null,
    var gender: Gender = Gender.MALE
) {
    // Suppression du constructeur inutile
    // Utilisation d'un constructeur principal avec paramètres optionnels

    fun getFullName(): String {
        return "$firstName $lastName".trim()
    }

    override fun toString(): String {
        return getFullName()
    }
}