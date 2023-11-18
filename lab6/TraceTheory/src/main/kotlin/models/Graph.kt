package models

data class Graph(val nodes: List<Node>) {

    override fun toString(): String {
        return nodes.joinToString("\n") { node ->
            "Node ${node.name}, ID: ${node.id}, Forward: ${node.forward.joinToString { "(" + it?.name.toString() + ", " + it?.id.toString() +")"}}"
        }
    }
}
