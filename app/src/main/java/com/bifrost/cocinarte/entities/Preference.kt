package com.bifrost.cocinarte.entities

class Preference(appTheme: Boolean,pushNotif: Boolean,emailNotif:Boolean) {
    var appTheme: Boolean = appTheme
    var pushNotif: Boolean = pushNotif
    var emailNotif: Boolean = emailNotif

    init {
        this.appTheme = appTheme
        this.pushNotif = pushNotif
        this.emailNotif = emailNotif
    }
}