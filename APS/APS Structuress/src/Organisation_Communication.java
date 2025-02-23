import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import APS_Struct.graph_tree.*;

public class Organisation_Communication {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        int N=(int)Double.parseDouble(br.readLine());
        AdjacencyListGraphWEIGHTED<String> graph=new AdjacencyListGraphWEIGHTED<>();
        AdjacencyMatrixGraphWEIGHTED<String> mgraph=new AdjacencyMatrixGraphWEIGHTED<>(N);
        int num=Integer.parseInt(br.readLine());
        for (int j = 0; j < num; j++) {
            String []s=br.readLine().split("\\s+");
            double Comm_degree=Double.parseDouble(s[4]);

            int idx1=Integer.parseInt(s[0]);
            int idx2=Integer.parseInt(s[2]);

            String e1=s[1];
            String e2=s[3];

            mgraph.addVertex(idx1,e1);
            mgraph.addVertex(idx2,e2);
            mgraph.addEdge(idx1,idx2,(int)Comm_degree);

            graph.addVertex(e1);
            graph.addVertex(e2);
            graph.addEdge(e1,e2,Comm_degree);
        }
        String Predsedatel=br.readLine();

        Object[] objects= mgraph.getVertices();
        int i;
        for (i = 0; i < objects.length; i++) {
            if(objects[i].toString().equals(Predsedatel))break;
        }
        int min=0;

        for (Edge edge : mgraph.prim(i)) {
            min+=edge.getWeight();
        }

        Object [] s=mgraph.getVertices();
        for (Edge edge : mgraph.prim(i)) {
            System.out.println(s[edge.getFrom()].toString()+" "+s[edge.getTo()].toString()+" "+edge.getWeight());
        }

        System.out.println(min);



        br.close();
    }
}
