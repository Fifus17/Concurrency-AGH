package parsers

import main.kotlin.models.Input
import models.Graph
import models.Node
import models.Relations

class GraphParser {

    companion object {

        // TODO test
        /**
         * Method creates a Graph object for given relations and word.
         * @param relations Relations object
         * @param word String
         * @return Graph of the word
         */
        fun parseToGraph(relations: Relations, word: String): Graph {
            val nodes: MutableList<Node> = mutableListOf()

            // word is processed in reverse, so all the nodes are already created while being added to Node's forward field
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

        /**
         * Method for minimizing graph to its minimized form.
         * @param graph Graph
         * @return Minimized graph
         */
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

        /**
         * Method for finding starting nodes in a graph. Method is used for finding first nodes in a BFS algorithm.
         * @param relations Relations object
         * @param word String
         * @return Mutable list of Int - indexes of letters in word that no other letters go to.
         */
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

        /**
         * Method for finding nodes' depths in a minimized graph.
         * It performs simple BFS algorithm where every Node has a corresponding "parent" from which the path to the node is the longest.
         * @param graph Graph
         * @param relations Relations object
         * @param input Input object
         * @return Mutable map where every Node is a key with its value being its depth
         */
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

        /**
         * Method for printing FNF for the given input.
         * @param graph minimized Graph object
         * @param relations Relations object
         * @param input Input object
         * @return Unit
         */
        fun printFNF(graph: Graph, relations: Relations, input: Input) {

            val depths = getDepthsInGraph(graph, relations, input)

            val depthToNodes = depths.entries.groupBy({ it.value }, { it.key.name })

            println("FNF([w]) = " + depthToNodes.entries.sortedBy { it.key }
                .joinToString("") { entry ->
                    entry.value.joinToString("", "(", ")")
                })
            println()
        }

        /**
         * Method for printing graphs - both normal and minimized versions.
         * @param relations Relations object
         * @param input Input object
         * @return minimized Graph
         */
        fun printGraphs(relations: Relations, input: Input): Graph {
            println("Graph: ")
            var graph = parseToGraph(relations, input.word)
            println(graph)
            println()

            println("Minimized graph: ")
            graph = minimizeGraph(graph, relations, input)
            println(graph)
            println()

            printFNF(graph, relations, input)
            println()

            return graph
        }
    }
}