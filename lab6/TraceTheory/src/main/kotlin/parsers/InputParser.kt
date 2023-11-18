package main.kotlin.parsers

import main.kotlin.models.Input
import main.kotlin.models.Transaction

class InputParser {

    companion object {

        private val transaction = Regex("\\([a-z]\\)[a-zA-Z]:=*.+")
        private val word = Regex("w=.+")
        private val path = Regex("n=.+")
        fun parseInput(input: Array<String>): Input {
            val transactions: MutableList<Transaction> = mutableListOf()
            var wordSeq = ""
            var fileName = "graph"
            val vars: MutableSet<Char> = mutableSetOf()
            input.forEach {
                when {
                    transaction.matches(it) -> {
                        val formula = it.substring(3)
                        transactions.add(Transaction(it[1], formula))
                        parseFormulaToVars(formula).forEach {vars.add(it)}
                    }
                    word.matches(it) -> wordSeq = it.substring(2)
                    path.matches(it) -> fileName = it.substring(2)
                    else -> {}
                }
            }


            val result =  Input(transactions, wordSeq, vars, fileName)
            println(result)
            println()

            return result
        }

        fun parseFormulaToVars(input: String): List<Char> {
            return input.toCharArray().asList().filter { it.isLetter() }
        }
    }
}