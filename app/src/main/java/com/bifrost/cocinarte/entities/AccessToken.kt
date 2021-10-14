package com.bifrost.cocinarte.entities

import java.util.*

class AccessToken(id:Int,user_id: Int, created: Date) {
    var id: Int = id
    var user_id :Int = user_id
    var created: Date = created

    init {
        this.id = id
        this.user_id = user_id
        this.created = created
    }
}