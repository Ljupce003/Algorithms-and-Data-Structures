import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class AdjacencyMatrixGraph<T> {
    private int numVertices;
    private int[][] matrix;
    private T[] vertices;

    @SuppressWarnings("unchecked")
    public AdjacencyMatrixGraph(int numVertices) {
        this.numVertices = numVertices;
        matrix = new int[numVertices][numVertices];
        vertices = (T[]) new Object[numVertices];


        for(int i=0;i<numVertices;i++) {                  //
            for(int j=0;j<numVertices;j++) {              //
                matrix[i][j] = 0;                         //for weighted
            }                                             //
        }                                                 //
    }

    public void addVertex(int index, T data) {
        vertices[index] = data;
    }

    public T getVertex(int index) {
        return vertices[index];
    }

//    public void addEdge(int source, int destination) {                          //
//        matrix[source][destination] = 1;                                        //for unweighted
//        //matrix[destination][source] = 1; // For undirected graph                //
//    }                                                                           //

    public void addEdge(int source, int destination, int weight) {            //
    		matrix[source][destination] = weight;                               //for weighted
    		matrix[destination][source] = weight; // For undirected graph       //
    		}                                                                   //


    public boolean isEdge(int source, int destination) {
        return matrix[source][destination] != 0;
    }


    public void removeEdge(int source, int destination) {
        matrix[source][destination] = 0;
        matrix[destination][source] = 0; // For undirected graph
    }

    @SuppressWarnings("unchecked")
    public void removeVertex(int vertexIndex) {
        if (vertexIndex < 0 || vertexIndex >= numVertices) {
            throw new IndexOutOfBoundsException("Vertex index out of bounds!");
        }
        int[][] newMatrix = new int[numVertices-1][numVertices-1];
        T[] newVertices = (T[]) new Object[numVertices-1];
        // Copy the vertices and matrix excluding the given vertex
        int ni = 0;
        for (int i = 0; i < numVertices; i++) {
            if (i == vertexIndex) continue;
            int nj = 0;
            for (int j = 0; j < numVertices; j++) {
                if (j == vertexIndex) continue;
                newMatrix[ni][nj] = matrix[i][j];
                nj++;
            }
            newVertices[ni] = vertices[i];
            ni++;
        }
        // Replace the old matrix and vertices with the new ones
        matrix = newMatrix;
        vertices = newVertices;
        numVertices--;
    }

    public List<T> getNeighbors(int vertexIndex) {
        List<T> neighbors = new ArrayList<>();
        for (int i = 0; i < matrix[vertexIndex].length; i++) {
            if (matrix[vertexIndex][i] !=0 ) {
                neighbors.add(vertices[i]);
            }
        }
        return neighbors;
    }


    /**
     * This method should be used only in a weighted Graph.It returns all the
     * edges in the graph
     * @return List of Edges
     */
    private List<Edge> getAllEdges() {                               //
        List<Edge> edges = new ArrayList<>();                        //
                                                                     //
        for(int i=0;i<numVertices;i++) {                             //
            for(int j=0;j<numVertices;j++) {                         //
                if(isEdge(i,j)) {                                    //
                    edges.add(new Edge(i, j, matrix[i][j]));         //for wighted
                }                                                    //
            }                                                        //
        }                                                            //
                                                                     //
        return edges;                                                //
    }


    private void union(int u, int v, int[] trees) {                  //
        int findWhat, replaceWith;                                   //
        if(u<v) {                                                    //
            findWhat = trees[v];                                     //
            replaceWith = trees[u];                                  //
        } else {                                                     //
            findWhat = trees[u];                                     //
            replaceWith = trees[v];                                  //for weighted
        }                                                            //
                                                                     //
        for(int i=0;i<trees.length;i++) {                            //
            if(trees[i] == findWhat) {                               //
                trees[i] = replaceWith;                              //
            }                                                        //
        }                                                            //
    }                                                                //



    /**
     * This method should be used only in a weighted Graph.It creates a new graph with the
     * KRUSKAL algorithm
     * @return List of Edges
     */
    public List<Edge> kruskal() {
        List<Edge> mstEdges = new ArrayList<>();                                   //
        List<Edge> allEdges = getAllEdges();                                       //
                                                                                   //
        allEdges.sort(Comparator.comparingInt(Edge::getWeight));                   //
                                                                                   //
        int[] trees = new int[numVertices];                                        //
                                                                                   //
        for(int i=0;i<numVertices;i++)                                             //
            trees[i] = i;                                                          //for weighted
                                                                                   //
        for(Edge e: allEdges) {                                                    //
            if(trees[e.getFrom()] != trees[e.getTo()]) {                           //
                mstEdges.add(e);                                                   //
                                                                                   //
                union(e.getFrom(), e.getTo(), trees);                              //
            }                                                                      //
        }                                                                          //
                                                                                   //
        return mstEdges;                                                           //
    }                                                                              //



    /**
     * This method should be used only in a weighted Graph.It creates a new graph with the
     * PRIM algorithm
     * @param startVertexIndex The index of the starting Node
     * @return List of Edges
     */
    public List<Edge> prim(int startVertexIndex) {                                       //
        List<Edge> mstEdges = new ArrayList<>();                                         //
        Queue<Edge> q = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));   //
                                                                                         //
        boolean[] included = new boolean[numVertices];                                   //
                                                                                         //
        for(int i=0;i<numVertices;i++) {                                                 //
            included[i] = false;                                                         //
        }                                                                                //
                                                                                         //
        included[startVertexIndex] = true;                                               //
                                                                                         //
        for(int i=0;i<numVertices;i++) {                                                 //for Weighted
            if(isEdge(startVertexIndex,i)) {                                             //
                q.add(new Edge(startVertexIndex, i, matrix[startVertexIndex][i]));       //
            }                                                                            //
        }                                                                                //
                                                                                         //
        while(!q.isEmpty()) {                                                            //
            Edge e = q.poll();                                                           //
                                                                                         //
            if(!included[e.getTo()]) {                                                   //
                included[e.getTo()] = true;                                              //
                mstEdges.add(e);                                                         //
                for(int i=0;i<numVertices;i++) {                                         //
                    if(!included[i] && isEdge(e.getTo(),i)) {                            //
                        q.add(new Edge(e.getTo(), i, matrix[e.getTo()][i]));             //
                    }                                                                    //
                }                                                                        //
            }                                                                            //
        }                                                                                //
                                                                                         //
        return mstEdges;                                                                 //
    }

    private int getVertexIndex(T vertex){
        for (int i = 0; i < vertices.length; i++) {
            if(vertices[i].equals(vertex)){
                return i;
            }
        }
        return -1;
    }





    public List<Edge> adaptedKruskal(int[] trees) {                            //
    		List<Edge> mstEdges = new ArrayList<>();                           //
    		List<Edge> allEdges = getAllEdges();                               //
                                                                               //
    		allEdges.sort(Comparator.comparingInt(Edge::getWeight));           //
                                                                               //
    		for(Edge e: allEdges) {                                            //
    			if(trees[e.getFrom()] != trees[e.getTo()]) {                   //for weighted
    				mstEdges.add(e);                                           //
                                                                               //
    				union(e.getFrom(), e.getTo(), trees);                      //
    			}                                                              //
    		}                                                                  //
                                                                               //
    		return mstEdges;                                                   //
    	}                                                                      //


    /**
     * This method converts a matrix implementation of graph to a list implementation of the same graph
     * @return  a List implementation of the graph
     */
    public AdjacencyListGraph<T> toAdjacencyList() {
        AdjacencyListGraph<T> result = new AdjacencyListGraph<>();

        for(int i=0;i<numVertices;i++) {
            result.addVertex(vertices[i]);
        }

        for(int i=0;i<numVertices;i++) {
            for(int j=0;j<numVertices;j++) {
                if(matrix[i][j] > 0) {
                    result.addEdge(vertices[i], vertices[j],matrix[i][j]);
                    //result.addEdge(vertices[i], vertices[j]);
                }
            }
        }
        return result;
    }

    public List<T> topologicalSort(){
        Set<T> visited=new HashSet<>();
        Stack<T> stack=new Stack<>();

        for (T vertex : vertices) {
            topologicalSortR(vertex,visited,stack);
        }

        List<T> order = new ArrayList<>();
        while (!stack.isEmpty()) {
            order.add(stack.pop());
        }
        return order;


    }

    private void topologicalSortR(T vertex, Set<T> visited, Stack<T> stack) {
        if(!visited.contains(vertex)){
            visited.add(vertex);

            for (T neighbor : getNeighbors(getVertexIndex(vertex))) {
                topologicalSortR(neighbor,visited,stack);
            }
            stack.push(vertex);
        }
    }


    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("  ");
        for(int i = 0; i < numVertices; i++)
            ret.append(vertices[i]).append(" ");
        ret.append("\n");
        for(int i = 0; i < numVertices; i++){
            ret.append(vertices[i]).append(" ");
            for(int j = 0; j < numVertices; j++)
                ret.append(matrix[i][j]).append(" ");
            ret.append("\n");
        }
        return ret.toString();
    }

}


class Edge {
    private final int fromVertex;
    private final int toVertex;
    private final int weight;
    public Edge(int from, int to, int weight) {
        this.fromVertex = from;
        this.toVertex = to;
        this.weight = weight;
    }

    public int getFrom() {
        return this.fromVertex;
    }
    public int getTo() {
        return this.toVertex;
    }
    public int getWeight() {
        return this.weight;
    }
}


//////////////////////////////////////////////////////////////////////////////////////////////
class AdjacencyListGraph<T> {
    //private final Map<T, Set<T>> adjacencyList;
    private Map<T, Map<T, Integer>> adjacencyList;   //for weighted

    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            //adjacencyList.put(vertex, new HashSet<>());
            adjacencyList.put(vertex, new HashMap<>());   //for wighted
        }
    }

    // Remove a vertex from the graph
    public void removeVertex(T vertex) {
        // Remove the vertex from all adjacency lists
//        for (Set<T> neighbors : adjacencyList.values()) {
//            neighbors.remove(vertex);
//        }

        for (Map<T, Integer> neighbors : adjacencyList.values()) {      //
            neighbors.remove(vertex);                                   //for weighted
        }                                                               //


        // Remove the vertex's own entry in the adjacency list
        adjacencyList.remove(vertex);
    }

    // Add an edge to the graph
//    public void addEdge(T source, T destination) {
//        addVertex(source);
//        addVertex(destination);
//
//        adjacencyList.get(source).add(destination);
//    }

    public void addEdge(T source, T destination, int weight) {                           //
    		addVertex(source);                                                             //
    		addVertex(destination);                                                        //for weighted
    		adjacencyList.get(source).put(destination, weight);                            //
    		adjacencyList.get(destination).put(source, weight); // for undirected graph    //
    	}                                                                                  //


    // Remove an edge from the graph
    public void removeEdge(T source, T destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }

        if (adjacencyList.containsKey(destination)) {
        			adjacencyList.get(destination).remove(source);          // for undirected graph
        		}


    }

    // Get all neighbors of a vertex
//    public Set<T> getNeighbors(T vertex) {
//        return adjacencyList.getOrDefault(vertex, new HashSet<>());
//    }

    public Map<T, Integer> getNeighbors(T vertex) {                             //
    		return adjacencyList.getOrDefault(vertex, new HashMap<>());           //for weighted
    	}                                                                         //
                                                                                  //
    public void DFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        DFSUtil(startVertex, visited);
    }

    private void DFSUtil(T vertex, Set<T> visited) {
        // Mark the current node as visited and print it
        visited.add(vertex);
        System.out.print(vertex + " ");

        // Recur for all the vertices adjacent to this vertex
//        for (T neighbor : getNeighbors(vertex)) {
//            if (!visited.contains(neighbor)) {
//                DFSUtil(neighbor, visited);
//            }
//        }

        for (T neighbor : getNeighbors(vertex).keySet()) {              //
        			if (!visited.contains(neighbor)) {                    //
        				DFSUtil(neighbor, visited);                       //for weighted
        			}                                                     //
        		}                                                         //


    }


    public void DFSNonRecursive(T startVertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        stack.push(startVertex);
        while (!stack.isEmpty()) {
            T vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                System.out.print(vertex + " ");

//                for (T neighbor : getNeighbors(vertex)) {
//                    if (!visited.contains(neighbor)) {
//                        stack.push(neighbor);
//                    }
//                }

                for (T neighbor : getNeighbors(vertex).keySet()) {             //
                    if (!visited.contains(neighbor)) {           //
                        stack.push(neighbor);                    //for weighted
                    }                                            //
                }                                                //


            }
        }
    }

    public int getA_index(T vertex){
        int i=0;
        for (T t : adjacencyList.keySet()) {
            if(vertex.equals(t))return i;
            i++;
        }

        return -1;
    }

    public T getVertex_Findex(int vertexIDX){
        int i=0;

        for (T t : adjacencyList.keySet()) {
            if(i==vertexIDX)return t;
            i++;
        }

        return null;
    }



    private void union(int u, int v, int[] trees) {                  //
        int findWhat, replaceWith;                                   //
        if(u<v) {                                                    //
            findWhat = trees[v];                                     //
            replaceWith = trees[u];                                  //
        } else {                                                     //
            findWhat = trees[u];                                     //
            replaceWith = trees[v];                                  //for weighted
        }                                                            //
        //
        for(int i=0;i<trees.length;i++) {                            //
            if(trees[i] == findWhat) {                               //
                trees[i] = replaceWith;                              //
            }                                                        //
        }                                                            //
    }



    public List<Edge> kruskal() {
        List<Edge> mstEdges = new ArrayList<>();                                   //
        List<Edge> allEdges = getAllEdges();                                       //
        //
        allEdges.sort(Comparator.comparingInt(Edge::getWeight));                   //
        //
        int[] trees = new int[adjacencyList.keySet().size()];                                        //
        //
        for(int i=0;i<adjacencyList.keySet().size();i++)                                             //
            trees[i] = i;                                                          //for weighted
        //
        for(Edge e: allEdges) {                                                    //
            if(trees[e.getFrom()] != trees[e.getTo()]) {                           //
                mstEdges.add(e);                                                   //
                //
                union(e.getFrom(), e.getTo(), trees);                              //
            }                                                                      //
        }                                                                          //
        //
        return mstEdges;                                                           //
    }

    public Map<T,Integer> getMapping(){
        Map<T,Integer> map=new HashMap<>();
        int idx=0;
        for (Map.Entry<T, Map<T, Integer>> entry : adjacencyList.entrySet()) {
            map.put(entry.getKey(),idx);
            idx++;
        }

        return map;
    }


    private List<Edge> getAllEdges() {                               //
        List<Edge> edges = new ArrayList<>();                        //
        //

        Map<T,Integer> map=new HashMap<>();
        int idx=0;
        for (Map.Entry<T, Map<T, Integer>> entry : adjacencyList.entrySet()) {
            map.put(entry.getKey(),idx);
            idx++;
        }

        for (Map.Entry<T, Map<T, Integer>> entrySRC : adjacencyList.entrySet()) {
            for (Map.Entry<T, Integer> entryDEST : entrySRC.getValue().entrySet()) {
                edges.add(new Edge(map.get(entrySRC.getKey()),map.get(entryDEST.getKey()),entryDEST.getValue()));
            }
        }
//        for(int i=0;i<numVertices;i++) {                             //
//            for(int j=0;j<numVertices;j++) {                         //
//                if(isEdge(i,j)) {                                    //
//                    edges.add(new Edge(i, j, matrix[i][j]));         //for wighted
//                }                                                    //
//            }                                                        //
//        }                                                            //
        //
        return edges;                                                //
    }



    public List<Edge> adaptedKruskal(int[] trees) {                            //
        List<Edge> mstEdges = new ArrayList<>();                           //
        List<Edge> allEdges = getAllEdges();                               //
        //
        allEdges.sort(Comparator.comparingInt(Edge::getWeight));           //
        //
        for(Edge e: allEdges) {                                            //
            if(trees[e.getFrom()] != trees[e.getTo()]) {                   //for weighted
                mstEdges.add(e);                                           //
                //
                union(e.getFrom(), e.getTo(), trees);                      //
            }                                                              //
        }                                                                  //
        //
        return mstEdges;                                                   //
    }



    public List<Edge> prim(T startVertex) {                                       //
        List<Edge> mstEdges = new ArrayList<>();                                         //
        Queue<Edge> q = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));   //

        int numVertices=adjacencyList.keySet().size();
        boolean[] included = new boolean[numVertices];                                   //

        int startVertexIndex=getA_index(startVertex);


        for (boolean b : included) {
            b=false;
        }                                                                              //
        //
        included[startVertexIndex] = true;                                               //
        //

        for(int i=0;i<numVertices;i++) {                                                 //for Weighted
            if(isEdge(startVertexIndex,i)) {                                             //
                q.add(new Edge(startVertexIndex, i, adjacencyList.get(getVertex_Findex(startVertexIndex)).get(getVertex_Findex(i))));       //
            }                                                                            //
        }                                                                                //
        //
        while(!q.isEmpty()) {                                                            //
            Edge e = q.poll();                                                           //
            //
            if(!included[e.getTo()]) {                                                   //
                included[e.getTo()] = true;                                              //
                mstEdges.add(e);                                                         //
                for(int i=0;i<numVertices;i++) {                                         //
                    if(!included[i] && isEdge(e.getTo(),i)) {                            //
                        q.add(new Edge(e.getTo(), i, adjacencyList.get(getVertex_Findex(e.getTo())).get(getVertex_Findex(i))));             //
                    }                                                                    //
                }                                                                        //
            }                                                                            //
        }                                                                                //
        //
        return mstEdges;                                                                 //
    }

    private boolean isEdge(int startVertexIndex, int neighbourIDX) {
        int i=0;
        T startVertex=null;
        T neighbour=null;
        for (T t : adjacencyList.keySet()) {
            if(i==startVertexIndex)startVertex=t;
            if(i==neighbourIDX)neighbour=t;
            i++;
        }


        return adjacencyList.get(startVertex).containsKey(neighbour);
    }

    public void printPath(T source, T destination) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        stack.push(source);
        visited.add(source);
        while (!stack.isEmpty() && !stack.peek().equals(destination)) {
            T vertex = stack.peek();

            boolean f = true;

//            for (T neighbor : getNeighbors(vertex)) {
//                if (!visited.contains(neighbor)) {
//                    stack.push(neighbor);
//                    visited.add(neighbor);
//                    f = false;
//                    break;
//                }
//            }

            for(T neighbor: getNeighbors(vertex).keySet()) {              //
            				if(!visited.contains(neighbor)) {               //
            					stack.push(neighbor);                       //
            					visited.add(neighbor);                      //for weighted
            					f = false;                                  //
            					break;                                      //
            				}                                               //
            			}                                                   //


            if (f) {
                stack.pop();
            }
        }

        Stack<T> path = new Stack<>();

        while (!stack.isEmpty()) {
            path.push(stack.pop());
        }

        while (!path.isEmpty()) {
            System.out.print(path.pop() + " ");
        }
    }


    public Set<String> paths_w_size(T startVertex,int maxsize){
        Set<String> paths=new HashSet<>();
        for (T t : getNeighbors(startVertex).keySet()) {
            paths.addAll(paths_w_sizeR(t,maxsize,paths,startVertex.toString()));

        }
        return paths;
    }

    private Set<String> paths_w_sizeR(T t, int maxsize, Set<String> paths,String p) {
        if(maxsize==0){
            paths.add(p);}

        else {
            p+=t.toString();
            for (T neighbour : getNeighbors(t).keySet()) {
                paths.addAll(paths_w_sizeR(neighbour,maxsize-1,paths,p));
            }
        }


        return paths;
    }


    public void BFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            System.out.print(vertex + " ");

//            for (T neighbor : getNeighbors(vertex)) {
//                if (!visited.contains(neighbor)) {
//                    visited.add(neighbor);
//                    queue.add(neighbor);
//                }
//            }

            for (T neighbor : getNeighbors(vertex).keySet()) {          //
            				if (!visited.contains(neighbor)) {            //
            					visited.add(neighbor);                    //for weighted
            					queue.add(neighbor);                      //
            				}                                             //
            			}                                                 //

        }
    }

    // DFS utility function used for topological sorting
    private void topologicalSortUtil(T vertex, Set<T> visited, Stack<T> stack) {
        visited.add(vertex);

//        for (T neighbor : getNeighbors(vertex)) {
//            if (!visited.contains(neighbor)) {
//                topologicalSortUtil(neighbor, visited, stack);
//            }
//        }

        for (T neighbour : getNeighbors(vertex).keySet()) {
            if (!visited.contains(neighbour)) {
                topologicalSortUtil(neighbour, visited, stack);
            }
        }
        stack.push(vertex);
    }

    public List<T> topologicalSort() {
        Stack<T> stack = new Stack<>();
        Set<T> visited = new HashSet<>();

        for (T vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                topologicalSortUtil(vertex, visited, stack);
            }
        }

        List<T> order = new ArrayList<>();
        while (!stack.isEmpty()) {
            order.add(stack.pop());
        }
        return order;
    }

    AdjacencyMatrixGraph<T> toAdjacencyMatrixGraph(){
        AdjacencyMatrixGraph<T> result=new AdjacencyMatrixGraph<>(adjacencyList.keySet().size());

        int i=0;
        Map<T,Integer> indexmap=new HashMap<>();
        for (T t : adjacencyList.keySet()) {
            result.addVertex(i,t);
            indexmap.put(t,i);
            i++;
        }

        for (Map.Entry<T, Map<T, Integer>> entry : adjacencyList.entrySet()) {
            T vertex=entry.getKey();
            for (T neighbour : entry.getValue().keySet()) {
            int i1=indexmap.get(vertex);
            int i2=indexmap.get(neighbour);
            int weight=entry.getValue().get(neighbour);
            result.addEdge(i1,i2,weight);
            }
    }
//        for (Map.Entry<T, Set<T>> entry : adjacencyList.entrySet()) {
//            T vertex = entry.getKey();
//            for (T neighbour : entry.getValue()) {
//                int i1 = indexmap.get(vertex);
//                int i2 = indexmap.get(neighbour);
//                result.addEdge(i1, i2);
//            }
//        }

        return result;


        //

    }

    public Map<T, Integer> shortestPath(T startVertex) {                                           //
   		Map<T, Integer> distances = new HashMap<>();                                               //
   		PriorityQueue<T> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));     //
   		Set<T> explored = new HashSet<>();                                                         //
                                                                                                   //
    		// Initialize distances                                                                //
        for (T vertex : adjacencyList.keySet()) {                                                  //
    		distances.put(vertex, Integer.MAX_VALUE);                                              //
   		}                                                                                          //
   		distances.put(startVertex, 0);                                                             //
                                                                                                   //
   		queue.add(startVertex);                                                                    //
                                                                                                   //for
    	while (!queue.isEmpty()) {                                                                 //weighted
    		T current = queue.poll();                                                              //Dijkstra
    		explored.add(current);                                                                 //
                                                                                                   //
    		for (Map.Entry<T, Integer> neighborEntry : adjacencyList.get(current).entrySet()) {    //
    			T neighbor = neighborEntry.getKey();                                               //
    			int newDist = distances.get(current) + neighborEntry.getValue();                   //
                                                                                                   //
    			if (newDist < distances.get(neighbor)) {                                           //
    				distances.put(neighbor, newDist);                                              //
                                                                                                   //
    				// Update priority queue                                                       //
    				if (!explored.contains(neighbor)) {                                            //
    					queue.add(neighbor);                                                       //
                    }                                                                              //
    			}                                                                                  //
            }                                                                                      //
        }                                                                                          //
                                                                                                   //
   		return distances;                                                                          //
   	}                                                                                              //


    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
//        for (Map.Entry<T, Set<T>> vertex : adjacencyList.entrySet()) {
//            ret.append(vertex.getKey()).append(": ").append(vertex.getValue()).append("\n");
//        }

        for (Map.Entry<T, Map<T, Integer>> vertex : adjacencyList.entrySet()){                               // for weighted
            ret.append(vertex.getKey()).append(": ").append(vertex.getValue()).append("\n");}                //

        return ret.toString();
    }


    final static int INF=Integer.MAX_VALUE/2;
    public Map<T,Path<T>> shortest(T startV) {

        Map<T,Path<T>> distances=new HashMap<>();
        for (T vertex : adjacencyList.keySet()) {
            distances.put(vertex,new Path<T>(INF,new LinkedHashSet<>()));
        }

        PriorityQueue<T> queue=new PriorityQueue<>(Comparator.comparing(distances::get));
        queue.add(startV);
        distances.get(startV).dist=0;
        //distances.get(startV).path.add(startV);
        Set<T> visited=new HashSet<>();

        while (!queue.isEmpty()){
            T temp=queue.poll();
            visited.add(temp);

            for (Map.Entry<T, Integer> neighbourentry : getNeighbors(temp).entrySet()) {
                T neighbour=neighbourentry.getKey();
                int distToNeighbour=neighbourentry.getValue();

                int newDist=distances.get(temp).dist+distToNeighbour;

                if(newDist<distances.get(neighbour).dist){

                    distances.get(neighbour).dist=newDist;
                    distances.get(neighbour).path.clear();
                    distances.get(neighbour).path.addAll(distances.get(temp).path);
                    distances.get(neighbour).path.add(temp);


                }

                if(!visited.contains(neighbour)){
                    queue.add(neighbour);
                }
            }


        }

        for (Map.Entry<T, Path<T>> pathEntry : distances.entrySet()) {
            pathEntry.getValue().path.add(pathEntry.getKey());
        }

        return distances;
  }

}

class Path<T> implements Comparable<Path<T>>{
     int dist;
     LinkedHashSet<T> path;

    public Path(int dist, LinkedHashSet<T> path) {
        this.dist = dist;
        this.path = path;
    }

    @Override
    public String toString() {
        return "The Distance is "+dist+" The path is"+path;
//        return dist + ", path" + path;
    }

    @Override
    public int compareTo(Path o) {
        return Integer.compare(this.dist,o.dist);
    }
}




public class Graph_Test {
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);

//        AdjacencyMatrixGraph<Integer> graph=new AdjacencyMatrixGraph<>(10);
//
//        for (int i = 0; i < 10; i++) {
//            graph.addVertex(i,i);
//        }
//
//
//        graph.addEdge(0,1,1);
//        graph.addEdge(0,3,2);
//        graph.addEdge(1,2,6);
//        graph.addEdge(1,5,2);
//        graph.addEdge(2,5,2);
//        graph.addEdge(2,4,4);
//        graph.addEdge(4,6,2);
//        graph.addEdge(6,9,3);
//        graph.addEdge(9,7,7);
//        graph.addEdge(3,7,3);
//        graph.addEdge(8,7,1);
//        graph.addEdge(4,8,1);


        //Flood(sc);

        //Component_Connectivity(sc);

        //Count_paths_with_size(sc);

        //Patarina(sc);

        //Vodovod(sc);

        //Santa(sc);

        //Test_Big_Graph(sc);

        Count_users_putty(sc);


        sc.close();




    }

    private static void Count_users_putty(Scanner sc) {
        List<String> lista=new ArrayList<>();
        while (sc.hasNextLine()){
            String line=sc.nextLine();
            String[]lines=line.split("\\s++");
            for (String s : lines) {
                lista.add(s);
            }
        }

        System.out.println(lista.size());
        for (String covek : lista) {
            System.out.println(covek);
        }
    }

    private static void Test_Big_Graph(Scanner sc) {

        int n= sc.nextInt();
        AdjacencyMatrixGraph<Integer> graph=new AdjacencyMatrixGraph<>(n);
        int [] trees=new int[n];
        for (int i = 0; i < n; i++) {
            graph.addVertex(i,i);
            trees[i]=i+1;
        }


        int m= sc.nextInt();
        for (int i = 0; i < m; i++) {
            int a=sc.nextInt();
            int b= sc.nextInt();
            int w=sc.nextInt();
            graph.addEdge(a,b,w);
        }
        int p=sc.nextInt();
        for (int i = 0; i < p; i++) {
            int a=sc.nextInt();
            int b=sc.nextInt();
            trees[a]=0;
            trees[b]=0;
        }

        for (Edge edge : graph.adaptedKruskal(trees)) {
            System.out.println(edge.getFrom()+" : "+ edge.getTo()+" w= "+ edge.getWeight());
        }





    }

    private static void Santa(Scanner sc) {

        int nodeNum= sc.nextInt();

        AdjacencyMatrixGraph<Integer> graph=new AdjacencyMatrixGraph<>(nodeNum);

        for (int i = 0; i < nodeNum; i++) {
            graph.addVertex(i,i);
        }
        int edgeNum=Integer.parseInt(sc.nextLine());
        for (int i = 0; i < edgeNum; i++) {
            int a=sc.nextInt();
            int b=sc.nextInt();
            graph.addEdge(a,b,1);
        }


        int start=sc.nextInt();

        List<Edge> edges=graph.prim(start);
        Set<Integer> lakes=new HashSet<>();
        for (Edge edge : edges) {
            if(edge.getTo()!=start)lakes.add(edge.getTo());
            if(edge.getFrom()!=start)lakes.add(edge.getFrom());
        }

        System.out.println(lakes.size());
    }

    private static void Patarina(Scanner sc) {
        int n= sc.nextInt();

        int pointA= sc.nextInt();
        int pointB=sc.nextInt();
        int m= sc.nextInt();
        AdjacencyListGraph<Integer> graph=new AdjacencyListGraph<>();

        for (int i = 1; i <= n; i++) {
            graph.addVertex(i);
        }
        for (int i = 0; i < m; i++) {
            int a= sc.nextInt();
            int b=sc.nextInt();
            int w=sc.nextInt();
            //graph.addVertex(a);
            //graph.addVertex(b);
            graph.addEdge(a,b,w);
        }



        Map<Integer,Integer> shortest=graph.shortestPath(pointA);
        System.out.println(shortest.get(pointB));

    }

    private static void Vodovod(Scanner sc){
        int n=sc.nextInt();
        AdjacencyListGraph<Integer> graph=new AdjacencyListGraph<>();
        int [] nivoa=new int[n];
        for (int i = 0; i < n; i++) {
            int vertex=sc.nextInt();
            int nivo= sc.nextInt();
            graph.addVertex(vertex);
            nivoa[i]=nivo;
        }
        int m=sc.nextInt();
        for (int i = 0; i < m; i++) {
            int a= sc.nextInt();
            int b=sc.nextInt();
            int w= sc.nextInt();
            graph.addEdge(a,b,w);
        }
        int promena=sc.nextInt();
        for (int i = 0; i < promena; i++) {
            int a= sc.nextInt();
            int b=sc.nextInt();
            int w=sc.nextInt();
            int initialW=graph.getNeighbors(a).get(b);

            graph.addEdge(a,b,w*initialW);
        }

        Map<Integer,Integer> shortes=graph.shortestPath(0);

        for (Map.Entry<Integer, Integer> entry : shortes.entrySet()) {
            int v=entry.getKey();
            int dist=entry.getValue();

            if(nivoa[0]-(dist+nivoa[v])<=0  && v!=0){
                System.out.println(v);
            }
        }


    }

    private static void Count_paths_with_size(Scanner sc) {
        int n=sc.nextInt();
        int m=sc.nextInt();
        AdjacencyListGraph<Integer> graph=new AdjacencyListGraph<>();
        for (int i = 0; i < m; i++) {
            int n1= sc.nextInt();
            int n2=sc.nextInt();
            graph.addVertex(n1);
            graph.addVertex(n2);
            graph.addEdge(n1,n2,1);
        }
        int neededNode=sc.nextInt();
        int max_lenght= sc.nextInt();

        int counter=0;
        //TODO
        //Map<Integer,Integer> pathss=graph.shortestPath(neededNode);

//        for (Map.Entry<Integer, Integer> integerIntegerEntry : pathss.entrySet()) {
//            System.out.println(integerIntegerEntry);
//        }
        Set<String> p=graph.paths_w_size(neededNode,max_lenght);

        System.out.println(p.size());

        //System.out.println(counter);

    }

    private static void Component_Connectivity(Scanner sc) {
        int n=sc.nextInt();
        AdjacencyMatrixGraph<Integer> graph=new AdjacencyMatrixGraph<>(n);
        for (int i = 0; i < n; i++) {
            int m=sc.nextInt();
            graph.addVertex(m,m);
            for (int j = 0; j < m; j++) {
                int node=sc.nextInt();
                graph.addVertex(node,node);
                graph.addEdge(m,node,1);
            }
        }

        int nodesearch=sc.nextInt();
        for (Integer neighbor : graph.getNeighbors(nodesearch)) {
            System.out.print(neighbor+" ");
        }
    }

    private static void Flood(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine().trim());
        int m=Integer.parseInt(sc.nextLine().trim());
        int p=Integer.parseInt(sc.nextLine().trim());
        AdjacencyMatrixGraph<String> graph=new AdjacencyMatrixGraph<>(n);
        Map<String,Integer> map=new HashMap<>();
        int []trees=new int[m];
        for (int i = 0; i < n; i++) {
            String city=sc.nextLine().trim();
            graph.addVertex(i,city);
            map.put(city,i);
            trees[i]=i+1;
        }
        for (int i = 0; i < m; i++) {
            String line= sc.nextLine();
            String[] parts=line.split("\\s++");
            String city1=parts[0];
            String city2=parts[1];
            int dist=Integer.parseInt(parts[2]);
            graph.addEdge(map.get(city1),map.get(city2),dist);
        }
        for (int i = 0; i < p; i++) {
            String line= sc.nextLine();
            String[] parts=line.split("\\s++");
            String city1=parts[0];
            String city2=parts[1];
            trees[map.get(city1)]=0;
            trees[map.get(city2)]=0;
        }

        List<Edge> resultKruskal=graph.adaptedKruskal(trees);

        int sum=0;


        for (Edge edge : resultKruskal) {
            sum+=edge.getWeight();
        }
        System.out.println("Number roads left to fix is "+resultKruskal.size());
        System.out.println("Cost of the roads left to fix is "+sum);

        for (Edge edge : resultKruskal) {
            System.out.println(graph.getVertex(edge.getFrom())+" "+graph.getVertex(edge.getTo()));
        }
    }
}
