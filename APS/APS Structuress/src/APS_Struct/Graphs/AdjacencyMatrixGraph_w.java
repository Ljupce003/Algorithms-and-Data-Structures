package APS_Struct.Graphs;

import java.util.*;

public class AdjacencyMatrixGraph_w<T> {
    int [][]matrix;
    T []vertices;
    int numVertices;

    public static final int INFINITY=Integer.MAX_VALUE/2;
    @SuppressWarnings("unchecked")
    public AdjacencyMatrixGraph_w(int num_Vertices) {
        numVertices = num_Vertices;
        vertices= (T[]) new Object[num_Vertices];
        matrix=new int[num_Vertices][num_Vertices];
        for (int i = 0; i < num_Vertices; i++) {
            for (int j = 0; j < num_Vertices; j++) {
                if(i==j)matrix[i][j]=0;
                else matrix[i][j]=INFINITY;
            }
        }
    }


    public T getVertex(int index) {
        return vertices[index];
    }

    public void addVertex(T vertex,int index) {
        this.vertices[index]=vertex;
    }

    public void addEdge(int source,int destination,int weight){
        matrix[source][destination]=weight;
        //matrix[destination][source]=weight;             //comment this line for DIRECTIONAL graph
    }

    public boolean isEdge(int source,int destination){
        return matrix[source][destination] != 0 && matrix[source][destination] != INFINITY;
    }

    public void removeEdge(int source,int destination){
        matrix[source][destination]=INFINITY;
        //matrix[destination][source]=0;             //comment this line for DIRECTIONAL graph

    }

    @SuppressWarnings("unchecked")
    public void removeVertex(int vertex_index){

        if(vertex_index<0 || vertex_index>numVertices)throw new IndexOutOfBoundsException("The index "+vertex_index+" is out of bounds for size "+numVertices);

        int [][]new_matrix=new int[numVertices-1][numVertices-1];
        T [] new_vertices=(T[])new Object[numVertices-1];
        int nn=0;
        for (int i = 0; i < numVertices; i++) {
            if(i==vertex_index)continue;
            int jj=0;
            for (int j = 0;  j< numVertices; j++) {
                if(j==vertex_index)continue;
                new_matrix[nn][jj]=matrix[i][j];
                jj++;
            }
            new_vertices[nn]=vertices[i];
            nn++;
        }

        matrix=new_matrix;
        vertices=new_vertices;
        numVertices--;
    }


    public List<T> getNeighbours(int vertex_index){
        List<T> neighbours=new ArrayList<>();
        for (int i = 0; i < matrix[vertex_index].length; i++) {
            if(matrix[vertex_index][i]!=0 || matrix[vertex_index][i]!=INFINITY){
                neighbours.add(vertices[i]);
            }
        }

        return neighbours;
    }

    //Floyd Warshall for finding the shortest path
    public int[][] floydWarshallPath(){
        int [][] dist=new int[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                dist[i][j]=matrix[i][j];
            }
        }

        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if(dist[i][k]+dist[k][j]<dist[i][j]){
                        dist[i][j]=dist[i][k]+dist[k][j];
                    }
                }
            }
        }

        return dist;
    }





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
    }                                                                                    //





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



}
