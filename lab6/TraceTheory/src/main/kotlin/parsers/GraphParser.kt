package parsers

import main.kotlin.models.Input
import models.Graph
import models.Node
import models.Relations

class GraphParser {

    companion object {

        // TODO test
        fun parseToGraph(relations: Relations, word: String): Graph {
            val nodes: MutableList<Node> = mutableListOf()
            word.toCharArray().reversed().forEachIndexed { index, char ->
                nodes.add(
                    Node(
                        char,
                        word.length - index - 1,
                        nodes.filter { relations.relations.get(char)!!.contains(it.name) }
                    )
                )
            }

            return Graph(nodes.reversed())
        }

        fun minimizeGraph(graph: Graph, relations: Relations, input: Input): Graph {

            val depths = getDepthsInGraph(graph, relations, input)

            // creating minimized graph
            val nodes: MutableList<Node> = mutableListOf()
            graph.nodes.forEach {
                val minDepth = it.forward.takeIf { it.isNotEmpty() }
                    ?.minOf { m -> depths.getOrDefault(m, Int.MAX_VALUE) }
                    ?: Int.MAX_VALUE
                val forwards = it.forward.filter { f -> depths.getOrDefault(f, Int.MAX_VALUE) == minDepth }
                nodes.add(Node(it.name, it.id, forwards))
            }

            return Graph(nodes)
        }

        fun findGraphStartingNodes(relations: Relations, word: String) : MutableList<Int> {
            val starts = mutableListOf<Int>() // indexes

            word.toCharArray().forEachIndexed() { index, char ->
                var flag = true
                word.substring(0, index).toCharArray().forEach {
                    if (relations.relations.get(it)!!.contains(char)) flag = false
                }
                if (flag) starts.add(index)
            }

            return starts
        }

        fun getDepthsInGraph(graph: Graph, relations: Relations, input: Input): MutableMap<Node, Int> {
            val depths = mutableMapOf<Node, Int>() // ParentNode: depth
            val nodeQ = mutableListOf<Node>()
            val parentQ = mutableListOf<Node>()

            findGraphStartingNodes(relations, input.word).forEach {
                nodeQ.add(graph.nodes[it])
                parentQ.add(graph.nodes[it])
            }

            // getting depths - "baguettes"
            while (nodeQ.isNotEmpty()) {
                val currentNode = nodeQ.removeAt(0)
                val currentParent = parentQ.removeAt(0)

                val depth = depths.getOrDefault(currentParent, -1) + 1
                depths.put(currentNode, depth)

                currentNode.forward.forEach { it?.let { node ->
                    nodeQ.add(node)
                    parentQ.add(currentNode)
                }}
            }

            return depths
        }

        fun printFoat(graph: Graph, relations: Relations, input: Input) {

            val depths = getDepthsInGraph(graph, relations, input)

            val depthToNodes = depths.entries.groupBy({ it.value }, { it.key.name })

            println("FNF([w]) = " + depthToNodes.entries.sortedBy { it.key }
                .joinToString("") { entry ->
                    entry.value.joinToString("", "(", ")")
                })
            println()
        }

        fun printGraphs(relations: Relations, input: Input): Graph {
            println("Graph: ")
            var graph = parseToGraph(relations, input.word)
            println(graph)
            println()

            println("Minimized graph: ")
            graph = minimizeGraph(graph, relations, input)
            println(graph)
            println()

            printFoat(graph, relations, input)
            println()

            return graph
        }
    }
}