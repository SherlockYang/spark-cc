## Spark-CC: Computing Clustering Coefficient for a Given Graph

The Clustering Coefficient (CC) is a fundamental measure in social (or other type of) network analysis assessing the degree to which nodes tend to cluster together [1][2]. Clustering coefficient, along with density, node degree, path length, diameter, connectedness, and node centrality are seven most important properties to characterise a network [3].

We found that GraphX has already implemented connectedness, node centrality, path length, but does not have a componenet for computing clustering coefficient. This actually was the first intention for us to implement an algorithm to compute clustering coefficient for each vertex of a given graph.

Clustering coefficient is very helpful to many real applications, such as user behaviour prediction and structure prediction (like link prediction). We did that before in a bunch of papers (e.g., [4-5]), and also found many other publication papers using this metric in their work [6-8]. We are very confident that this feature will benefit GraphX and attract a large number of users.


#### References

[1] https://en.wikipedia.org/wiki/Clustering_coefficient

[2] Watts, Duncan J., and Steven H. Strogatz. "Collective dynamics of ‘small-world’ networks." nature 393.6684 (1998): 440-442. (with 27266 citations).

[3] https://en.wikipedia.org/wiki/Network_science

[4] Jing Zhang, Zhanpeng Fang, Wei Chen, and Jie Tang. Diffusion of "Following" Links in Microblogging Networks. IEEE Transaction on Knowledge and Data Engineering (TKDE), Volume 27, Issue 8, 2015, Pages 2093-2106.

[5] Yang Yang, Jie Tang, Jacklyne Keomany, Yanting Zhao, Ying Ding, Juanzi Li, and Liangwei Wang. Mining Competitive Relationships by Learning across Heterogeneous Networks. In Proceedings of the Twenty-First Conference on Information and Knowledge Management (CIKM'12). pp. 1432-1441.

[6] Clauset, Aaron, Cristopher Moore, and Mark EJ Newman. Hierarchical structure and the prediction of missing links in networks. Nature 453.7191 (2008): 98-101. (with 973 citations)

[7] Adamic, Lada A., and Eytan Adar. Friends and neighbors on the web. Social networks 25.3 (2003): 211-230. (1238 citations)

[8] Lichtenwalter, Ryan N., Jake T. Lussier, and Nitesh V. Chawla. New perspectives and methods in link prediction. In KDD'10.

#### Usage
Here is a usage example for LocalClusteringCoefficient:

```Scala
import org.apache.spark.graphx._
import org.apache.spark._

val conf = new SparkConf().setAppName("testApp")
val sc = new SparkContext(conf)
// load a graph
val graph = GraphLoader.edgeListFile(sc, "graph.txt").partitionBy(PartitionStrategy.RandomVertexCut)

// perform the local clustering coefficient computation 
val lcc = LocalClusteringCoefficient.run(graph)

// output results for each vertex
val verts = lcc.vertices
verts.collect().foreach { case (vid, count) =>
    println(vid + ": " + count)
}
```
