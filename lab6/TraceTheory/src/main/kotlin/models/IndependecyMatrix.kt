package models

data class IndependecyMatrix(val names: List<Char>, val relations: ArrayList<ArrayList<Boolean>>) {

    override fun toString(): String {
        val matrix = StringBuilder()

        matrix.append("Independency Matrix\n\n")
        matrix.append("| |${names.joinToString("|")}|\n")

        for (i in 0 until relations.size) {
            matrix.append("|${names[i]}|")
            for (j in 0 until relations[i].size) {
                val value = if (relations[i][j]) 0 else 1
                matrix.append("$value|")
            }
            matrix.append("\n")
        }

        return matrix.toString()
    }
}
