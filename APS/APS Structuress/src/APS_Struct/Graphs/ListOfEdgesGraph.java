package APS_Struct.Graphs;

import java.util.ArrayList;
import java.util.List;


public class ListOfEdgesGraph<T> {

    private static class Edge<E>{
        E source;
        E destination;

        public Edge(E source, E destination) {
            this.source = source;
            this.destination = destination;
        }

        public boolean involves(E vertex) {
             return source.equals(vertex) || destination.equals(vertex);
        }
    }

    List<Edge<T>> edges;

    public ListOfEdgesGraph() {
        this.edges = new ArrayList<>();
    }

    public void addEdge(T source,T destination){
        edges.add(new Edge<>(source,destination));
    }


    public List<T> getNeighbours(T vertex){
        List<T> neighbours=new ArrayList<>();

        //Put all vertices connected to this vertex in the neighbours list

        for (Edge<T> edge : edges) {
            if(edge.source.equals(vertex))neighbours.add(edge.destination);
            else if(edge.destination.equals(vertex))neighbours.add(edge.source);
        }
        return neighbours;
    }

    public List<Edge<T>> getEdges(){
        return edges;
    }

    public void removeEdge(T source,T destination){
        for (Edge<T> edge : edges) {
            if(edge.source.equals(source) &&
                    edge.destination.equals(destination)  ||
                    edge.source.equals(destination) &&
                            edge.destination.equals(source))edges.remove(edge);
        }
    }

    public void removeVertex(T vertex){
        for (Edge<T> edge : edges) {
            if(edge.involves(vertex))edges.remove(edge);
        }
    }
}
