package com.bifrost.cocinarte.entities

class Filter {
    var name: String = ""
    var filterName: String = ""
    var category: Category = Category.NONE
    var filterDefault: Boolean = false

    constructor(name: String, filterName: String, category: Category) {
        this.name = name
        this.filterName = filterName
        this.category = category
    }

    constructor(name: String, filterName: String, category: Category, filterDefault: Boolean) {
        this.name = name
        this.filterName = filterName
        this.category = category
        this.filterDefault = filterDefault
    }
}