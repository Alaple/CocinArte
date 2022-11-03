package com.bifrost.cocinarte.entities

class UserRecipe {
    var name: String? = null
    var description: String? = null

    constructor() {}

    constructor(name: String,description: String) {
        this.name = name
        this.description = description
    }
}