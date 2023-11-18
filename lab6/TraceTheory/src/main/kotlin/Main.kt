import factories.GraphFactory
import main.kotlin.parsers.InputParser
import parsers.GraphParser
import parsers.RelationsParser

/**
 * Solves tasks related to concurrency assignment.
 *
 * The program accomplishes the following tasks:
 * 1. Determine the dependency relation D.
 * 2. Determine the independence relation I.
 * 3. Determine the Foata Normal Form FNF([w]) of trace [w].
 * 4. Draw the dependency graph in minimal form for the word w.
 *
 * @author Filip Dziurdzia
 * @version 1.0
 */
fun main(args: Array<String>) {

    /**
     * Main function that executes the program. You can run the program with two examples (uncomment whichever you prefer).
     *
     * To run the program with your own arguments simply run
     *
     * ```
     * mvn exec:java -Dexec.mainClass="main.kotlin.Main" -Dexec.args=*your args*
     * ```
     *
     * i.e.
     *
     * ```
     * mvn exec:java -Dexec.mainClass="main.kotlin.Main" -Dexec.args="(a)x:=x+1 (b)y:=y+2z (c)x:=3x+z (d)w:=w+v (e)z:=y-z (f)v:=x+v A={a,b,c,d,e,f} w=acdcfbbe n=example3"
     * ```
     * where n is the name of the file where the graph should be saved to.
     *
     * One of the examples needs to be uncommented nevertheless.
     */

//    First example
    val data = if (args.isNotEmpty()) args else arrayOf("(a)x:=x+y", "(b)y:=y+2z", "(c)x:=3x+z", "(d)z:=y-z", "A={a,b,c,d}", "w=baadcb", "n=example1")

//   Second example
//    val data = if (args.isNotEmpty()) args else arrayOf("(a)x:=x+1", "(b)y:=y+2z", "(c)x:=3x+z", "(d)w:=w+v", "(e)z:=y-z", "(f)v:=x+v", "A={a,b,c,d,e,f}", "w=acdcfbbe", "n=example2")

    val input = InputParser.parseInput(data)

    val relations = RelationsParser.parseRelations(input)

    RelationsParser.printDependencyMatrices(relations)

    val graph = GraphParser.printGraphs(relations, input)

    GraphFactory.printGraph(graph, input.fileName)
}