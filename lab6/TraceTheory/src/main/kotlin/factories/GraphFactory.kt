package factories

import guru.nidi.graphviz.attribute.Arrow
import guru.nidi.graphviz.attribute.Color
import guru.nidi.graphviz.attribute.Label
import guru.nidi.graphviz.attribute.Rank
import guru.nidi.graphviz.attribute.Rank.RankDir.*
import guru.nidi.graphviz.graph
import guru.nidi.graphviz.toGraphviz
import guru.nidi.graphviz.engine.Format.*
import guru.nidi.graphviz.model.Factory.node
import models.Graph
import java.io.File

class GraphFactory {

    companion object {

        /***
         * Method for generating visualization of a graph. Uses Graphviz with Kotlin API. You need to install Graphviz to generate graphs with this program.
         * Docs can be found here: https://github.com/nidi3/graphviz-java#kotlin-dsl
         * @param graph Graph
         * @param path Name or path where the generated graph should be saved.
         * @return Visualized graph as a png saved in the graphs directory + provided path.
         */
        fun printGraph(g: Graph, path: String) {
            graph(directed = true) {
                edge["color" eq "black", Arrow.VEE]
                node[Color.BLACK]
                graph[Rank.dir(LEFT_TO_RIGHT)]

                g.nodes.forEach{ node(it.id.toString()) }

                g.nodes.forEach {main ->
                    main.forward.forEach {next ->
                        main.id.toString()[Label.of(main.name.toString())] - next?.id.toString()[Label.of(next?.name.toString())]
//                        println(main.id.toString() + "-" + next?.id.toString())
                    }
                }

            }.toGraphviz().render(PNG).toFile(File("graphs/$path.png"))
        }
    }
}