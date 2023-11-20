package main.kotlin.parsers

import main.kotlin.models.Input
import main.kotlin.models.Transaction

class InputParser {

    companion object {

        private val transaction = Regex("\\([a-z]\\)[a-zA-Z]:=*.+")
        private val word = Regex("w=.+")
        private val path = Regex("n=.+")

        /**
         * Function for parsing input given as an Array<String> that matches strings with regexes for finding transactions, words, and file paths.
         * Returns an Input object with parsed data.
         *
         * @param inputArray An array of strings representing the input to be parsed.
         * @return Input An object containing the parsed data.
         */
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

        /**
         * Function for getting letters out of formula. It filters out all mathematical symbols and numbers from the formula.
         * @param Formula given as a String ie. 2x+3c-4q
         * @return List of chars from the formula i.e. (x, c, q)
         */
        fun parseFormulaToVars(input: String): List<Char> {
            return input.toCharArray().asList().filter { it.isLetter() }
        }
    }
}