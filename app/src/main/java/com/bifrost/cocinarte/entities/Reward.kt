package com.bifrost.cocinarte.entities

class Reward(name:String,image_url:String,description:String) {
    var name: String = name
    var image_url : String = image_url
    var description : String = description

    init {
        this.name = name
        this.image_url = image_url
        this.description = description
    }
}