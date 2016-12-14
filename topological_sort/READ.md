# Topological Sort

For a given Graph (or matrix, array which can be treat as a vertex)
we traverse from largest / smallest vertex to entire graph, 
each level we calculate some result and mark the visited from traversal again.

For problem LongestIncreasingPathInMatrix, 
I think the core idea is treat the graph as topology sorted, and each time we delete from the end (which means we will delete all nodes within the same level), and increment count. The reason behind is: we can choose one (and only one) node from current level for next step.

So in this way we get the longest path from end to start.

