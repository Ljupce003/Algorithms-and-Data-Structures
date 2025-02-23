package APS_Struct.Graphs;


import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class GRAPH_TESTING {

    private static void CreateMatrixGraph(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        String Createline=sc.nextLine();
        String []c=Createline.split("\\s+");
        String []letters={"A","B","C","D","E","F","G","H"};
        if(!c[0].equals("CREATE")) System.out.println("First create the graph maan");
        else {

            int numV=Integer.parseInt(c[1]);

            AdjacencyMatrixGraph<String> graph=new AdjacencyMatrixGraph<>(numV);
            for (int i = 0; i < numV; i++) {
                graph.addVertex(letters[i],i);
            }
            n--;
            for (int i = 0; i < n; i++) {
                String command=sc.nextLine();
                String []parts=command.split("\\s++");
                if(parts[0].equals("ADDEDGE")){
                    int v1=Integer.parseInt(parts[1]);
                    int v2=Integer.parseInt(parts[2]);
                    graph.addEdge(v1,v2);
                }
                if(parts[0].equals("PRINTMATRIX")){
                    graph.printMatrix();
                }
                if(parts[0].equals("PRINTNODE")){
                    int v=Integer.parseInt(parts[1]);
                    System.out.println(graph.getVertex(v));
                }
                if(parts[0].equals("ADJACENT")){
                    int v1=Integer.parseInt(parts[1]);
                    int v2=Integer.parseInt(parts[2]);
                    System.out.println(graph.isEdge(v1,v2));
                }
                if(parts[0].equals("DELETEEDGE")){
                    int v1=Integer.parseInt(parts[1]);
                    int v2=Integer.parseInt(parts[2]);
                    graph.removeEdge(v1,v2);
                }
            }
        }
    }


    private static void Test_convert_ListToMatrix() {
        AdjacencyListGraph<Integer> listGraph = new AdjacencyListGraph<>();
        for (int i = 0; i < 10; i++) {
            listGraph.addVertex(i*2);
        }

        listGraph.addEdge(0,2);
        listGraph.addEdge(0,6);
        listGraph.addEdge(2,10);
        listGraph.addEdge(2,4);
        listGraph.addEdge(4,8);
        listGraph.addEdge(8,12);
        listGraph.addEdge(12,18);
        listGraph.addEdge(18,14);
        listGraph.addEdge(14,16);
        listGraph.addEdge(14,6);

        AdjacencyMatrixGraph<Integer> m_graph=listGraph.toAdjacencyMatrix();

        m_graph.shortestPathsFrom(5);
    }

    private static void Santa_Kolokvium(Scanner sc){
        int nodeNum= sc.nextInt();

        AdjacencyMatrixGraph_w<Integer> graph=new AdjacencyMatrixGraph_w<>(nodeNum);

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


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        //CreateMatrixGraph(sc);

        //Print_ShortestPath_from_Node();

        //Test_convert_ListToMatrix();

        //Santa_Kolokvium(sc);




        sc.close();
    }



    private static void Print_ShortestPath_from_Node() {
        AdjacencyMatrixGraph<Integer> graph=new AdjacencyMatrixGraph<>(10);
        for (int i = 0; i < 10; i++) {
            graph.addVertex(i,i);
        }

        graph.addEdge(0,1);
        graph.addEdge(0,3);
        graph.addEdge(1,2);
        graph.addEdge(1,5);
        graph.addEdge(2,5);
        graph.addEdge(2,4);
        graph.addEdge(4,6);
        graph.addEdge(6,9);
        graph.addEdge(9,7);
        graph.addEdge(3,7);
        graph.addEdge(8,7);








    }


}
