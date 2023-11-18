import main.kotlin.models.Input
import main.kotlin.models.Transaction
import main.kotlin.parsers.InputParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InputParserTest {

    @Test
    fun testInput1() {

        // Given
        val input = arrayOf("(a)x:=x+y", "(b)y:=y+2z", "(c)x:=3x+z", "(d)z:=y-z", "A={a,b,c,d}", "w=baadcb")

        // When
        val expected = Input(
            transactions = listOf(
                Transaction('a', "x:=x+y"),
                Transaction('b', "y:=y+2z"),
                Transaction('c', "x:=3x+z"),
                Transaction('d', "z:=y-z")
            ),
            word = "baadcb",
            vars = mutableSetOf('x', 'y', 'z'), ""
        )

        // Then
        val result = InputParser.parseInput(input)

        assertEquals(expected, result)
    }

    @Test
    fun testParseInput() {

        // Given
        val input = arrayOf(
            "(a)x:=x+1", "(b)y:=y+2z", "(c)x:=3x+z",
            "(d)w:=w+v", "(e)z:=y−z", "(f)v:=x+v",
            "A={a,b,c,d,e,f}", "w=acdcfbbe"
        )

        // When
        val expected = Input(
            transactions = listOf(
                Transaction('a', "x:=x+1"),
                Transaction('b', "y:=y+2z"),
                Transaction('c', "x:=3x+z"),
                Transaction('d', "w:=w+v"),
                Transaction('e', "z:=y−z"),
                Transaction('f', "v:=x+v")
            ),
            word = "acdcfbbe",
            vars = mutableSetOf('x', 'y', 'z', 'w', 'v'), ""
        )

        // Then
        val result = InputParser.parseInput(input)

        assertEquals(expected, result)
    }
}