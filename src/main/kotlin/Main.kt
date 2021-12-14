/***
 * launch main the arguments below:
 *
 *  start,HN,dc,HN,end
 *  start,HN,dc,HN,kj,HN,end
 *  start,HN,dc,end
 *  start,HN,dc,kj,HN,end
 *  start,HN,end
 *  start,HN,kj,HN,dc,HN,end
 *  start,HN,kj,HN,dc,end
 *  start,HN,kj,HN,end
 *  start,HN,kj,dc,HN,end
 *  start,HN,kj,dc,end
 *  start,dc,HN,end
 *  start,dc,HN,kj,HN,end
 *  start,dc,end
 *  start,dc,kj,HN,end
 *  start,kj,HN,dc,HN,end
 *  start,kj,HN,dc,end
 *  start,kj,HN,end
 *  start,kj,dc,HN,end
 *  start,kj,dc,end
 *
 *
 */

fun main(args: Array<String>) {
    val s = Puzzule(args.map { Link(it) })
        .findPaths()
    println("${s.size} Paths:")

    s.forEach { println(it) }

}
