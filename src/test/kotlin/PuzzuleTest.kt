import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

val A = BigCave("A")
val b = SmallCave("b")
val c = SmallCave("c")
val d = SmallCave("d")


internal class PuzzuleTest {

    val links = listOf(
        Link(Start, A),
        Link(b, Start),
        Link(A, c),
        Link(A, b),
        Link(b, d),
        Link(A),
        Link(b),
    )

    val puzzuleOne = Puzzule(links);


    @Test
    fun `Finding next caves from Start`() {
        assertEquals(
            setOf(A, b), puzzuleOne.nextCaves(Start)
        )
    }

    @Test
    fun `Finding next caves from A`() {
        assertEquals(
            setOf(b, c, End), puzzuleOne.nextCaves(A)
        )
    }

    @Test
    fun `Finding next caves from c`() {
        assertEquals(
            setOf(A), puzzuleOne.nextCaves(c)
        )
    }

    @Test
    fun `Finding next caves from b`() {
        assertEquals(
            setOf(A, d, End), puzzuleOne.nextCaves(b)
        )
    }

    @Test
    fun `Finding next caves from End`() {
        assertEquals(
            emptySet<Cave>(), puzzuleOne.nextCaves(End)
        )
    }


    @Test
    fun `Finding paths from Start to End`() {
        val links = listOf(
            Link(Start, End)
        )


        assertEquals(
            listOf(listOf(Start, End)),
            Puzzule(links).findPaths()
        )

    }

    @Test
    fun `Finding paths from Start to End through A`() {
        val links = listOf(
            Link(Start, A),
            Link(A, End)
        )


        assertEquals(
            listOf(listOf(Start, A, End)),
            Puzzule(links).findPaths()
        )

    }

    @Test
    fun `Finding paths from Start, A, b, c, End`() {
        val links = listOf(
            Link(Start, A),
            Link(A, b),
            Link(b, c),
            Link(c, End)
        )

        assertEquals(
            listOf(listOf(Start, A, b, c, End)),
            Puzzule(links).findPaths()
        )

    }

    @Test
    fun `Finding paths from Start, A, End and c as dead end`() {
        val links = listOf(
            Link(Start, A),
            Link(A, c),
            Link(A, End)
        )

        assertEquals(
            listOf(
                listOf(Start, A, c, A, End),
                listOf(Start, A, End)
            ),
            Puzzule(links).findPaths()
        )

    }

    @Test
    fun `Finding paths through map one`() {
        val links = listOf(
            Link(A, Start),
            Link(b, Start),
            Link(b, d),
            Link(A, c),
            Link(A, b),
            Link(End, A),
            Link(End, b)
        )


        assertEquals(
            setOf(
                listOf(Start, A, b, A, c, A, End),
                listOf(Start, A, b, A, End),
                listOf(Start, A, b, End),
                listOf(Start, A, c, A, b, A, End),

                listOf(Start, A, c, A, b, End),
                listOf(Start, A, c, A, End),
                listOf(Start, A, End),
                listOf(Start, b, A, c, A, End),
                listOf(Start, b, A, End),
                listOf(Start, b, End)
            ), Puzzule(links).findPaths().toSet()
        )

    }

    @Test
    fun `Finding no paths from (Start, A)`() {
        val links = listOf(
            Link(Start, A)
        )

        assertEquals(
            emptyList<Cave>(),
            Puzzule(links).findPaths()
        )

    }

    @Test
    fun `Finding 2 straight paths`() {
        val links = listOf(
            Link(Start, A),
            Link(Start, b),
            Link(b, End),
            Link(A, End)
        )


        assertEquals(
            listOf(
                listOf(Start, A, End),
                listOf(Start, b, End)
            ),
            Puzzule(links).findPaths()
        )

    }


}