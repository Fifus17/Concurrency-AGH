package parsers

import main.kotlin.models.DependencyMatrix
import main.kotlin.models.Input
import main.kotlin.parsers.InputParser
import models.IndependecyMatrix
import models.Relations

class RelationsParser {

    companion object {
        fun parseRelations(input: Input): Relations {
            var relations: MutableMap<Char, MutableSet<Char>> = HashMap()
            input.transactions.forEach {
                val variable = it.formula[0]

                val values = input.transactions
                    .filter { variable in InputParser.parseFormulaToVars(it.formula.substring(2)) }
                    .map { it.name }

                relations.computeIfAbsent(it.name) { mutableSetOf() }.addAll(values)

                // adding two-way dependency (if i.e. b is dependent of a then a is dependent of b, which is not the case in our values
                values.forEach { value ->
                    relations.computeIfAbsent(value) { mutableSetOf() }.add(it.name)
                }
            }

            // sorting
            relations = relations.mapValues { entry ->
                entry.value.sorted().toMutableSet()
            }.toMutableMap()

            val result = Relations(relations)
            println(result)
            println()

            return result
        }

        fun parseVarsRelations(input: Input): Relations {
            val relations: MutableMap<Char, MutableSet<Char>> = HashMap()
            input.transactions.forEach {
                val key = it.formula[0]
                val values = InputParser.parseFormulaToVars(it.formula.substring(2))
                relations.computeIfAbsent(key) { mutableSetOf() }.addAll(values.sorted().filter { it != key })
            }
            return Relations(relations)
        }

        fun parseRelationsToMatrix(relations: Relations): ArrayList<ArrayList<Boolean>> {
            val names = getRelationsNames(relations)
            return ArrayList(names.map { name ->
                ArrayList(names.map { otherName ->
                    relations.relations[otherName]?.contains(name) == true
                })
            })
        }

        fun getRelationsNames(relations: Relations): List<Char> {
            return relations.relations.map { it.key }
        }

        fun printDependencyMatrices(relations: Relations) {

            val names = RelationsParser.getRelationsNames(relations)
            val relationsBinary = RelationsParser.parseRelationsToMatrix(relations)

            println(DependencyMatrix(names, relationsBinary).toString())
            println(IndependecyMatrix(names, relationsBinary).toString())
        }
    }
}