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