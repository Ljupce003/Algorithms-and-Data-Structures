package APS_Struct.Graphs;

import java.util.*;

public class AdjacencyListGraph<T> {
    private Map<T, Set<T>> adjacency_list;

    public AdjacencyListGraph() {
        adjacency_list =new HashMap<>();
    }

    public void addVertex(T vertex){
        if(!adjacency_list.containsKey(vertex)){
            adjacency_list.put(vertex,new HashSet<>());
        }
    }
    
    public void removeVertex(T vertex){
        for (Set<T> value : adjacency_list.values()) {
            value.remove(vertex);
        }

        adjacency_list.remove(vertex);
    }

    public void addEdge(T source,T destination){
       addVertex(source);
       addVertex(destination);
       adjacency_list.get(source).add(destination);
       adjacency_list.get(destination).add(source);                   //uncomment this for undirected graph
    }

    public void removeEdge(T source,T destination){
        if(adjacency_list.containsKey(source)){
            adjacency_list.get(source).remove(destination);
        }

        if(adjacency_list.containsKey(destination)){
            adjacency_list.get(destination).remove(source);               //uncomment this for undirected graph
        }
    }

    public Set<T> getNeighbours(T vertex){
        return adjacency_list.getOrDefault(vertex,new HashSet<>());
    }

    public void DFS(T startVertex){
        Set<T> visited=new HashSet<>();
        DFSr(startVertex,visited);
    }
    private void DFSr(T startVertex, Set<T> visited) {
        visited.add(startVertex);
        System.out.print(startVertex.toString()+" ");
        for (T neighbour : getNeighbours(startVertex)) {
            if(!visited.contains(neighbour))DFSr(neighbour,visited);
        }
    }



    public void BFS(T startVertex){
        Set<T> visited=new HashSet<>();
        Deque<T> deque=new LinkedList<>();

        //Firstly the beginning vertex is added
        visited.add(startVertex);
        deque.add(startVertex);


        while (!deque.isEmpty()){
            //The vertex on the front of the queue is removed
            // and printed,and then we look for his neighbours
            T vertex= deque.poll();
            System.out.print(vertex.toString()+" ");
            for (T neighbour : getNeighbours(vertex)) {
                if(!visited.contains(neighbour)){
                    //this neighbour hasn't been found before,
                    // so we add it to the visited ones
                    // and put it on the tail of the queue
                    deque.add(neighbour);
                    visited.add(neighbour);
                }
            }
        }
    }

    public AdjacencyMatrixGraph<T> toAdjacencyMatrix(){
        AdjacencyMatrixGraph<T> graph=new AdjacencyMatrixGraph<>(adjacency_list.keySet().size());

        List<T> vertices = new ArrayList<>(adjacency_list.keySet());


        for (int i = 0; i < vertices.size(); i++) {
            graph.addVertex(vertices.get(i),i);
        }

        for (T vertex : adjacency_list.keySet()) {
            int v1=graph.getVertexIndex(vertex);
            for (T vertexNeighbour : adjacency_list.get(vertex)) {
                int v2=graph.getVertexIndex(vertexNeighbour);
                graph.addEdge(v1,v2);
            }
        }



        return graph;
    }


    public boolean BidirectionalSearch(T startVertex,T endVertex){
        if(startVertex.equals(endVertex))return true;

        Set<T> visitedFromStart=new HashSet<>();
        Set<T> visitedFromEnd=new HashSet<>();

        Deque<T> dequeStart=new LinkedList<>();
        Deque<T> dequeEnd=new LinkedList<>();

        visitedFromStart.add(startVertex);
        dequeStart.add(startVertex);

        visitedFromEnd.add(endVertex);
        dequeEnd.add(endVertex);

        while (!dequeStart.isEmpty() && !dequeEnd.isEmpty()){
            if(pathExists(dequeStart,visitedFromStart,visitedFromEnd))return true;

            if(pathExists(dequeEnd,visitedFromEnd,visitedFromStart))return true;
        }
        return false;
    }
    private boolean pathExists(Deque<T>deque,Set<T> visitedOneEnd,Set<T> visitedOtherEnd){
        T currVertex=deque.poll();
        for (T neighbour : getNeighbours(currVertex)) {
            if(visitedOtherEnd.contains(neighbour))return true;
            if(!visitedOneEnd.contains(neighbour)){
                visitedOneEnd.add(neighbour);
                deque.add(neighbour);
            }
        }
        return false;
    }



    //topologicalSort is only used in directed graphs NASOCENI
    public List<T> topologicalSort(){
        Stack<T>stack=new Stack<>();
        Set<T> visited=new HashSet<>();

        for (T vertex : adjacency_list.keySet()) {
            if(!visited.contains(vertex))topologicalSortR(vertex,visited,stack);
        }
        List<T> order=new ArrayList<>();
        while (!stack.isEmpty()){
            order.add(stack.pop());
        }

        return order;
    }
    private void topologicalSortR(T vertex,Set<T> visited,Stack<T> stack){
        visited.add(vertex);
        for (T neighbour : getNeighbours(vertex)) {
            if(!visited.contains(neighbour))topologicalSortR(neighbour,visited,stack);
        }

        stack.push(vertex);
    }




}
