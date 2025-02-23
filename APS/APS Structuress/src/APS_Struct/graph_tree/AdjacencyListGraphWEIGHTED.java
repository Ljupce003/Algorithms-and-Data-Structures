package APS_Struct.graph_tree;

import java.util.*;


public class AdjacencyListGraphWEIGHTED<T> {


    private final Map<T, Map<T, Double>> adjacencyList;

    public AdjacencyListGraphWEIGHTED() {
        this.adjacencyList = new HashMap<>();

    }

    // Add a vertex to the graph
    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashMap<>());
        }
    }

    // Remove a vertex from the graph
    public void removeVertex(T vertex) {
        // Remove the vertex from all adjacency lists
        for (Map<T, Double> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        // Remove the vertex's own entry in the adjacency list
        adjacencyList.remove(vertex);
    }

    // Add an edge to the graph
    public void addEdge(T source, T destination, double weight) {
        addVertex(source);
        addVertex(destination);

        adjacencyList.get(source).put(destination, weight);
        adjacencyList.get(destination).put(source, weight); // for undirected graph
    }

    // Remove an edge from the graph
    public void removeEdge(T source, T destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }
        if (adjacencyList.containsKey(destination)) {
            adjacencyList.get(destination).remove(source); // for undirected graph
        }
    }

    // Get all neighbors of a vertex
    public Map<T, Double> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashMap<>());
    }

    public void DFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        DFSUtil(startVertex, visited);
    }

    private void DFSUtil(T vertex, Set<T> visited) {
        // Mark the current node as visited and print it
        visited.add(vertex);
        System.out.print(vertex + " ");

        // Recur for all the vertices adjacent to this vertex
        for (T neighbor : getNeighbors(vertex).keySet()) {
            if (!visited.contains(neighbor)) {
                DFSUtil(neighbor, visited);
            }
        }
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
                for (T neighbor : getNeighbors(vertex).keySet()) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    public void printPath(T source, T destination) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();
        stack.push(source);
        visited.add(source);


        while (!stack.isEmpty() && !stack.peek().equals(destination)) {
            T vertex = stack.peek();

            boolean f = true;
            for (T neighbor : getNeighbors(vertex).keySet()) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                    f = false;
                    break;
                }
            }

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

    public void BFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            System.out.print(vertex + " ");

            for (T neighbor : getNeighbors(vertex).keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public Map<T, Double> shortestPath(T startVertex) {
        Map<T, Double> distances = new HashMap<>();
        PriorityQueue<T> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        Set<T> explored = new HashSet<>();


        // Initialize distances
        for (T vertex : adjacencyList.keySet()) {
            distances.put(vertex, (double) Integer.MAX_VALUE);
        }
        distances.put(startVertex, (double) 0);

        queue.add(startVertex);


        while (!queue.isEmpty()) {
            T current = queue.poll();
            explored.add(current);

            for (Map.Entry<T, Double> neighborEntry : adjacencyList.get(current).entrySet()) {
                T neighbor = neighborEntry.getKey();
                double newDist = distances.get(current) + neighborEntry.getValue();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    // Update priority queue
                    if (!explored.contains(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }
        }


        return distances;
    }

    public Map<T, PathInfo<T>> shortestPaths(T startVertex) {
        Map<T, Double> distances = new HashMap<>();
        Map<T, List<T>> shortestPaths = new HashMap<>();
        PriorityQueue<T> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        Set<T> explored = new HashSet<>();

        // Initialize distances
        for (T vertex : adjacencyList.keySet()) {
            distances.put(vertex, Double.MAX_VALUE);
            shortestPaths.put(vertex, new ArrayList<>());
        }
        distances.put(startVertex, 0.0);
        shortestPaths.get(startVertex).add(startVertex);

        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            explored.add(current);

            for (Map.Entry<T, Double> neighborEntry : adjacencyList.get(current).entrySet()) {
                T neighbor = neighborEntry.getKey();
                double newDist = distances.get(current) + neighborEntry.getValue();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    List<T> newPath = new ArrayList<>(shortestPaths.get(current));
                    newPath.add(neighbor);
                    shortestPaths.put(neighbor, newPath);

                    // Update priority queue
                    if (!explored.contains(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        Map<T, PathInfo<T>> result = new HashMap<>();
        for (T vertex : distances.keySet()) {
            result.put(vertex, new PathInfo<>(distances.get(vertex), shortestPaths.get(vertex)));
        }

        return result;
    }

    @Override
    public String toString() {
        String ret = "";
        for (Map.Entry<T, Map<T, Double>> vertex : adjacencyList.entrySet())
            ret += vertex.getKey() + ": " + vertex.getValue() + "\n";
        return ret;
    }

    public static class PathInfo<T> {
        private final double distance;
        private final List<T> path;

        public PathInfo(double distance, List<T> path) {
            this.distance = distance;
            this.path = path;
        }

        public double getDistance() {
            return distance;
        }

        public List<T> getPath() {
            return path;
        }
    }


}
