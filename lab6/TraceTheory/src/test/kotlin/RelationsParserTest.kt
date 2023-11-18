import main.kotlin.parsers.InputParser
import models.Relations
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import parsers.RelationsParser

class RelationsParserTest {

    @Test
    fun testParseRelations() {

        // Given
        val input = InputParser.parseInput(arrayOf("(a)x:=x+y", "(b)y:=y+2z", "(c)x:=3x+z", "(d)z:=y-z", "A={a,b,c,d}", "w=baadcb"))

        // When
        val result = RelationsParser.parseRelations(input)

        // Then
        val expected = Relations(mutableMapOf(
            'a' to mutableSetOf('a', 'b', 'c'),
            'b' to mutableSetOf('a', 'b', 'd'),
            'c' to mutableSetOf('a', 'c', 'd'),
            'd' to mutableSetOf('b', 'c', 'd')
        ))

        assertEquals(expected, result)
    }

    @Test
    fun testParseRelationsToMatrix() {

        // Given
        val relations = Relations(mutableMapOf(
            'a' to mutableSetOf('a', 'b', 'c'),
            'b' to mutableSetOf('a', 'b', 'd'),
            'c' to mutableSetOf('a', 'c', 'd'),
            'd' to mutableSetOf('b', 'c', 'd')
        ))

        // When
        val result = RelationsParser.parseRelationsToMatrix(relations)

        // Then
        val expected = arrayListOf(
            arrayListOf(true, true, true, false),
            arrayListOf(true, true, false, true),
            arrayListOf(true, false, true, true),
            arrayListOf(false, true, true, true)
        )

        assertEquals(expected, result)

    }

    @Test
    fun testGetRelationsNames() {

        // Given
        val relations = Relations(mutableMapOf(
            'a' to mutableSetOf('a', 'b', 'c'),
            'b' to mutableSetOf('a', 'b', 'd'),
            'c' to mutableSetOf('a', 'c', 'd'),
            'd' to mutableSetOf('b', 'c', 'd')
        ))

        // When
        val result = RelationsParser.getRelationsNames(relations)

        // Then
        val expected = listOf('a', 'b', 'c', 'd')

        assertEquals(expected, result)

    }
}