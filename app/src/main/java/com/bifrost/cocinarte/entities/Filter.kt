package com.bifrost.cocinarte.entities

class Filter {
    var name: String = ""
    var filterName: String = ""
    var default: Boolean = false

    constructor(name: String, filterName: String) {
        this.name = name
        this.filterName = filterName
    }

    constructor(name: String, filterName: String, default: Boolean) {
        this.name = name
        this.filterName = filterName
        this.default = default;
    }
}