import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class LinkTest {

    @Test
    fun `Link construction from string`() {
        val strLinks = listOf(
            "start-A",
            "start-b",
            "A-c",
            "A-b",
            "b-d",
            "A-end",
            "b-end"
        )

        val expectedLinks = listOf(
            Link(Start, BigCave("A")),
            Link(Start, SmallCave("b")),
            Link(BigCave("A"), SmallCave("c")),
            Link(BigCave("A"), SmallCave("b")),
            Link(SmallCave("b"), SmallCave("d")),
            Link(BigCave("A")),
            Link(SmallCave("b")),
        )

        strLinks.map { Link(it) }
            .forEachIndexed { index, actualPath ->
                assertEquals(expectedLinks[index], actualPath)
            }

    }

}