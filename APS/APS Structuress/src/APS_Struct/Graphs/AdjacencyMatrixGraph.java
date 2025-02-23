package APS_Struct.Graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class AdjacencyMatrixGraph<T> {
    private int [][]matrix;
    private T []vertices;
    private int numVertices;

    @SuppressWarnings("unchecked,raw")
    public AdjacencyMatrixGraph(int num_Vertices) {
        numVertices = num_Vertices;
       // vertices= (T[]) new Comparable[num_Vertices];
        vertices=(T[]) new Object[num_Vertices];
        matrix=new int[num_Vertices][num_Vertices];
    }


    public T getVertex(int index) {
        return vertices[index];
    }

    public int[][] getMatrix() {
        return matrix;
    }


    public int getVertexIndex(T vertex){
        for (int i = 0; i < vertices.length; i++) {
            if(vertices[i].equals(vertex))return i;
        }
        return -1;
    }

    public void addVertex(T vertex, int index) {
        this.vertices[index]=vertex;
    }

    public void addEdge(int source,int destination){
        matrix[source][destination]=1;
        matrix[destination][source]=1;             //comment this line for DIRECTIONAL graph
    }

    public boolean isEdge(int source,int destination){
        return matrix[source][destination]==1;
    }

    public void removeEdge(int source,int destination){
        matrix[source][destination]=0;
        matrix[destination][source]=0;             //comment this line for DIRECTIONAL graph

    }

    @SuppressWarnings("unchecked")
    public void removeVertex(int vertex_index){

        if(vertex_index<0 || vertex_index>numVertices)throw new IndexOutOfBoundsException("The index "+vertex_index+" is out of bounds for size "+numVertices);

        int [][]new_matrix=new int[numVertices-1][numVertices-1];
        //T [] new_vertices=(T[])new Object[numVertices-1];
        T[] new_vertices=(T[]) new Comparable[numVertices-1];
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
            if(matrix[vertex_index][i]==1){
                neighbours.add(vertices[i]);
            }
        }

        return neighbours;
    }

    public void printMatrix() {
//        for(int i=-1;i<numVertices;i++) {
//            for(int j=-1;j<numVertices;j++) {
//                if(i==-1 && j==-1) System.out.print("  ");
//                else if(i==-1) System.out.printf("%4s",vertices[j]);
//                else if(j==-1) System.out.printf("%4s",vertices[i]);
//                    else System.out.printf("%3d ",matrix[i][j]);
//
//            }
//            System.out.println();
//        }
//        System.out.println();

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(matrix[i][j]+" ");
                if(j==numVertices-1) System.out.print(" - "+vertices[i].toString());
            }
            System.out.println();
        }


    }

    public AdjacencyListGraph<T> toAdjacencyList(){
        AdjacencyListGraph<T> result=new AdjacencyListGraph<>();

        for (int i = 0; i < numVertices; i++) {
            result.addVertex(vertices[i]);
        }
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if(matrix[i][j]!=0 )result.addEdge(vertices[i],vertices[j]);
            }
        }

        return result;
    }


    public void shortestPathsFrom(int vertexIndex){
        boolean [] visited=new boolean[numVertices];
        int [] distances=new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i]=false;
            distances[i]=-1;
        }
        visited[vertexIndex]=true;
        distances[vertexIndex]=0;
        System.out.println(vertexIndex+": "+vertices[vertexIndex]);

        Deque<Integer> deque=new ArrayDeque<>();
        deque.add(vertexIndex);

        int tmp;

        while (!deque.isEmpty()){
            tmp=deque.poll();
            for (int i = 0; i < numVertices; i++) {
                if(isEdge(tmp,i)){
                    if(!visited[i]){
                        visited[i]=true;
                        distances[i]=distances[tmp]+1;
                        System.out.println(i+": "+vertices[i]);
                        deque.add(i);
                    }
                }
            }
        }






    }



}
