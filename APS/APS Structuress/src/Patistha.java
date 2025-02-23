import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import APS_Struct.graph_tree.*;

public class Patistha{
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        int N=(int)Double.parseDouble(br.readLine());
        AdjacencyListGraphWEIGHTED<String> graph=new AdjacencyListGraphWEIGHTED<>();
        AdjacencyMatrixGraphWEIGHTED<String> mgraph=new AdjacencyMatrixGraphWEIGHTED<>(N);
        int num=(int)Double.parseDouble(br.readLine());
        for (int j = 0; j < num; j++) {
            String []s=br.readLine().split("\\s+");
            double dolzina=Double.parseDouble(s[4]);

            int idx1=Integer.parseInt(s[0]);
            int idx2=Integer.parseInt(s[2]);

            String e1=s[1];
            String e2=s[3];

            mgraph.addVertex(idx1,e1);
            mgraph.addVertex(idx2,e2);
            mgraph.addEdge(idx1,idx2,(int)dolzina);

            graph.addVertex(e1);
            graph.addVertex(e2);
            graph.addEdge(e1,e2,dolzina);
        }
        String src=br.readLine();
        String dest=br.readLine();


        System.out.println(graph.shortestPath(src));


        for (String s : graph.shortestPaths(src).get(dest).getPath()) {
            System.out.print(s+" ");
        }
        System.out.println();


        for (int i = graph.shortestPaths(src).get(dest).getPath().size()-1; i >= 0; i--) {
            System.out.print(graph.shortestPaths(src).get(dest).getPath().get(i)+" ");
        }
        System.out.println();

        Map<String,Double> najktrtokmapa=graph.shortestPath(src);
        Map<String,Double> najktrtokmapaBACK=graph.shortestPath(dest);





        double distanc=najktrtokmapa.get(dest)+najktrtokmapaBACK.get(src);

        System.out.println(distanc);


        br.close();
    }
}


