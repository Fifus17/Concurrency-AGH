package main.kotlin.models

data class Input(val transactions : List<Transaction>, var word: String, var vars: MutableSet<Char>, val fileName: String)
