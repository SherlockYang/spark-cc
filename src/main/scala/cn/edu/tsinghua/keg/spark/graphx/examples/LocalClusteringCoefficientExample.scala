package cn.edu.tsinghua.keg.spark.graphx.examples

import org.apache.spark.graphx.Graph
import org.apache.spark.graphx.PartitionStrategy.RandomVertexCut
import org.apache.spark.{SparkContext, SparkConf}
import cn.edu.tsinghua.keg.spark.graphx.lib.LocalClusteringCoefficient

/**
 * Created by Sherlock on 10/20/15.
 */
object LocalClusteringCoefficientExample {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("test app").setMaster("local")
    val sc = new SparkContext(conf)

    // build a triangle graph with duplicated edges
    val edges = Array( 0L->1L, 1L->2L, 2L->0L )
    val rawEdges = sc.parallelize(edges ++ edges, 2)
    val graph = Graph.fromEdgeTuples(rawEdges, true, uniqueEdges = Some(RandomVertexCut)).cache()

    // output results
    val lcc = LocalClusteringCoefficient.run(graph)
    val verts = lcc.vertices
    verts.collect.foreach { case (vid, count) =>
      println(vid, count)
    }
  }
}
