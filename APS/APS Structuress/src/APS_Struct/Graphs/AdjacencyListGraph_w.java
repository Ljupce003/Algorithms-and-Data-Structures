package APS_Struct.Graphs;

import java.util.*;

public class AdjacencyListGraph_w<T> {
    private Map<T, Map<T,Integer>> adjacency_list;

    public AdjacencyListGraph_w() {
        adjacency_list =new HashMap<>();
    }

    public void addVertex(T vertex){
        if(!adjacency_list.containsKey(vertex)){
            adjacency_list.put(vertex,new HashMap<>());
        }
    }

    public void removeVertex(T vertex){
        // Remove the vertex from all adjacency lists
        for (Map<T, Integer> value : adjacency_list.values()) {
            value.remove(vertex);
        }

        // Remove the vertex's own entry in the adjacency list
        adjacency_list.remove(vertex);
    }

    public void addEdge(T source,T destination,int weight){
        addVertex(source);
        addVertex(destination);
        adjacency_list.get(source).put(destination,weight);
        //adjacency_list.get(destination).put(source,weight);                //uncomment this for undirected graph
    }

    public void removeEdge(T source,T destination){
        if(adjacency_list.containsKey(source)){
            adjacency_list.get(source).remove(destination);
        }

        if(adjacency_list.containsKey(destination)){
            //adjacency_list.get(destination).remove(source);               //uncomment this for undirected graph
        }
    }

    public Map<T,Integer> getNeighbours(T vertex){
        return adjacency_list.getOrDefault(vertex,new HashMap<>());
    }


    //Dijkstra Algorithm
    public Map<T,Integer> shortestPath(T startVertex){
        Map<T,Integer> distances=new HashMap<>();
        PriorityQueue<T>queue=new PriorityQueue<>(Comparator.comparingInt(distances::get));
        Set<T> explored=new HashSet<>();

        for (T vertex : adjacency_list.keySet()) {
            distances.put(vertex,Integer.MAX_VALUE);
        }
        distances.put(startVertex,0);
        queue.add(startVertex);
        while (!queue.isEmpty()){
            T currentVertex=queue.poll();
            explored.add(currentVertex);

            for (Map.Entry<T, Integer> neighbourEntry : adjacency_list.get(currentVertex).entrySet()) {
                T neighbour=neighbourEntry.getKey();
                int newDist=distances.get(currentVertex)+neighbourEntry.getValue();

                if(newDist<distances.get(neighbour)){
                    distances.put(neighbour,newDist);
                }

                //Update the priority queue
                if(!explored.contains(neighbour)){
                    queue.add(neighbour);
                }
            }
        }







        return distances;
    }

}