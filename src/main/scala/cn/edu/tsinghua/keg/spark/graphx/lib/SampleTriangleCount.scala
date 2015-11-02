package cn.edu.tsinghua.keg.spark.graphx.lib

import org.apache.spark.graphx.Graph

import scala.reflect.ClassTag

/**
 * Sampling to count triangles
 *
 * Use sample & hold method for sampling
 *
 * Note that the input graph must have been partitioned using
 * [[org.apache.spark.graphx.Graph#partitionBy]].
 */
object SampleTriangleCount {
  /**
   * Compute the number of triangles
   * return a graph with vertex value representing the local clustering coefficient of that vertex
   *
   * @param p probability for sampling
   * @param graph the graph for which to count triangles
   *
   * @return approximated number of triangles
   *
   */
  def run[VD: ClassTag, ED: ClassTag](graph: Graph[VD,ED], p: Double): Double = {
    // Remove redundant edges
    val g = graph.groupEdges((a, b) => a).cache()

    // sample graph
    scala.util.Random.setSeed(745623)
    val subgraph = g.subgraph(epred = (edge) => scala.util.Random.nextFloat() <= p)

    // count triangles in subgraph
    val triangleCount = subgraph.triangleCount()
    val verts = triangleCount.vertices

    // approximation
    var res: Double = 0
    verts.collect().foreach { case (vid, count) =>
      res += count / (p * p * p)
    }
    res / 3
  }
}

