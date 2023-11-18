package main.kotlin.models

data class DependencyMatrix(val names: List<Char>, val relations: ArrayList<ArrayList<Boolean>>) {

    override fun toString(): String {
        val matrix = StringBuilder()

        matrix.append("Dependency Matrix\n\n")
        matrix.append("| |${names.joinToString("|")}|\n")

        for (i in 0 until relations.size) {
            matrix.append("|${names[i]}|")
            for (j in 0 until relations[i].size) {
                val value = if (relations[i][j]) 1 else 0
                matrix.append("$value|")
            }
            matrix.append("\n")
        }

        return matrix.toString()
    }
}

