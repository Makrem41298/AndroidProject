package com.gfg.recyclerview_kotlin

import java.time.LocalDate
import java.time.LocalDateTime
enum class Status {
    cancel,
    accept,
    suspend

}


class Appointment(
    var id: Long = 0,
    var PatientName: String = "",
    var dateTime: LocalDateTime? = null,
    var status: Status? = null
)

