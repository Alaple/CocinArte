package com.bifrost.cocinarte.entities

class Preference {
    var appTheme: Boolean? = null
    var pushNotif: Boolean? = null
    var emailNotif: Boolean? = null

    constructor() {}

    constructor(appTheme: Boolean,pushNotif: Boolean,emailNotif:Boolean) {
        this.appTheme = appTheme
        this.pushNotif = pushNotif
        this.emailNotif = emailNotif
    }
}