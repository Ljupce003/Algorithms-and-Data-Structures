package listi_testing;

import APS_Struct.one_Dimensional_Structures.*;
import APS_Struct.lists.DLL;
import APS_Struct.lists.DLLNode;
import APS_Struct.lists.SLL;
import APS_Struct.lists.SLLNode;

import java.io.IOException;
import java.util.*;

public class dvostrani_testing {


    private static void splitni_parnost(DLL<Integer> dll) {
        DLLNode<Integer> node=dll.getFirst();
        DLL<Integer> parni=new DLL<>();
        DLL<Integer> neparni=new DLL<>();
        while (node!=null){

            if(node.element%2==0){
                parni.insertLast(node.element);
            }
            else {
                neparni.insertLast(node.element);
            }

            node=node.succ;
        }


//        System.out.println("Parni");
//        System.out.println(parni);

        dll.deleteList();
        while (parni.length()!=0){
            dll.insertLast(parni.getFirst().element);
            parni.deleteFirst();
        }
        dll.insertLast(0);

        while (neparni.length()!=0){
            dll.insertLast(neparni.getFirst().element);
            neparni.deleteFirst();
        }

//        System.out.println("NeParni");
//        System.out.println(neparni);

    }

    private static void vmetni_prosek(DLL<Integer> dll) {
        DLLNode<Integer> node=dll.getFirst();
        boolean firstgone=false;
        while (node!=null){
            if(firstgone){
                int prosek=((node.element+node.pred.element)/2);
                dll.insertAfter(prosek,node.pred);
            }

            node=node.succ;
            firstgone=true;


        }
    }


    private static void proizvod_listi(DLL<DLL<Integer>> lista_listi) {
        ArrayList<Integer> sumi=new ArrayList<>(lista_listi.length());
        DLLNode<DLL<Integer>> lista_node=lista_listi.getFirst();
        int proiz=1;

        while (lista_node!=null){

            DLL<Integer> lista_od_node=lista_node.element;
            int sum=0;

            DLLNode<Integer> node_od_minilist=lista_od_node.getFirst();
            while (node_od_minilist!=null){
                sum+=node_od_minilist.element;
                node_od_minilist=node_od_minilist.succ;
            }
            sumi.add(sum);

            lista_node=lista_node.succ;
        }

        for (Integer integer : sumi) {
            proiz *= integer;

        }

        System.out.println(sumi);

        System.out.println(proiz);
    }




    private static void prevrti(DLL<Integer> lista,DLL<Integer> pomosna) {
      DLLNode<Integer> nodelast=lista.getLast();
        DLLNode<Integer> posledenparen=null;

      while (nodelast!=null){

//          if(nodelast.element%2==0)
//          {
//
//              if(posledenparen==null) {
//                  pomosna.insertFirst(nodelast.element);
//                  posledenparen=pomosna.find(nodelast.element);
//              }
//              else pomosna.insertAfter(nodelast.element,posledenparen);
//          }
//          else pomosna.insertLast(nodelast.element);
//
//          nodelast=nodelast.pred;
          if(nodelast.element%2==0){
              pomosna.insertLast(nodelast.element);
          }
          nodelast=nodelast.pred;
      }
      nodelast=lista.getLast();
      while (nodelast!=null){
          if(nodelast.element%2!=0){
              pomosna.insertLast(nodelast.element);

          }
          nodelast=nodelast.pred;
      }
    }



    private static void splitni_prosekDLL(DLL<Integer> list) {
        int sum=0;
        DLLNode<Integer> node=list.getFirst();
        while (node!=null){
            sum+=node.element;
            node=node.succ;
        }
        int prosek=sum/list.length();

        DLL<Integer> pomali=new DLL<>();
        DLL<Integer> pogolemi=new DLL<>();
        node=list.getFirst();
        while (node!=null){

            if(node.element>prosek)pogolemi.insertFirst(node.element);
            else pomali.insertFirst(node.element);

            node=node.succ;
        }


        System.out.println(pomali);
        System.out.println();
        System.out.println(pogolemi);

    }

    private static void remove_sublits(DLL<Integer> list, DLL<Integer> podlista) {
        DLLNode<Integer> node=list.getFirst();

        while (node!=null){
            DLLNode<Integer> cycle=node;
            DLLNode<Integer> curr=node;
            DLLNode<Integer> sublist_node=podlista.getFirst();
            int count=0;
            boolean contains=false;
            while (sublist_node!=null && node!=null){
                if(Objects.equals(node.element, sublist_node.element))count++;

                sublist_node=sublist_node.succ;
                node=node.succ;
                if(count== podlista.length())contains=true;
                if(contains){
                    sublist_node=podlista.getFirst();
                    while (sublist_node!=null){
                        list.delete(curr);
                        curr=curr.succ;
                        sublist_node=sublist_node.succ;
                    }
                }
            }
            node=cycle;
            node=node.succ;
        }
        System.out.println(list);

    }

    private static void swapel(DLL<Integer> listIL, int beginswapIdx, int endswapIdx) {
        int i = 1;
        int j;
        DLLNode<Integer> nodebegin = listIL.getFirst();
        DLLNode<Integer> node = listIL.getFirst();
        DLLNode<Integer> pred = listIL.getFirst();
        DLL<Integer> newlist = new DLL<>();
        while (node != null) {
            if (i == beginswapIdx) {
                nodebegin = pred;
                j = i;
                while (node != null) {
                    if (j > endswapIdx) break;
                    newlist.insertLast(node.element);
                    listIL.delete(node);
                    j++;
                    node = node.succ;
                }
                break;
            }
            i++;
            pred=node;
            node = node.succ;
        }
        DLLNode<Integer> nodeb = listIL.find(nodebegin.element);
        newlist.mirror();
        while (newlist.length() != 0) {
            listIL.insertAfter(newlist.getFirst().element, nodeb);
            nodeb = nodeb.succ;
            newlist.deleteFirst();
        }
    }

    private static void vojska(DLL<Integer> list, int begin1, int end1, int begin2, int end2) {
        DLLNode<Integer> node=list.getFirst();
        DLL<Integer> list1=new DLL<>();
        DLL<Integer> list2=new DLL<>();

        DLLNode<Integer> i1pred=new DLLNode<>(0,null,null);
        DLLNode<Integer> i2pred=new DLLNode<>(0,null,null);
        int i=1;
        boolean enable=false;
        boolean enable1=false;
        while (node!=null){
            if(i==begin1){enable=true;i1pred=node.pred;}
            if(i>end1)enable=false;
            if(enable){list1.insertLast(node.element);list.delete(node);}

            if(i==begin2){enable1=true;i2pred=node.pred;}
            if(i>end2)enable1=false;
            if(enable1){list2.insertLast(node.element);list.delete(node);}

            node=node.succ;
            i++;
        }
        while (list2.length()!=0){
            if(i1pred!=null){
                list.insertAfter(list2.getFirst().element,i1pred);
                i1pred=list.find(i1pred.element).succ;
                list2.deleteFirst();
            }
            else {
                list.insertFirst(list2.getFirst().element);
                i1pred=list.getFirst();
                list2.deleteFirst();

            }


        }

        while (list1.length()!=0){
            list.insertAfter(list1.getFirst().element,i2pred);
            list1.deleteFirst();
            i2pred=list.find(i2pred.element).succ;
        }
    }


    public static void change(SLL<Integer> list, int br) {
        SLLNode<Integer> node=list.getFirst();
        int count=0;
        while (node!=null){
            if(node.element==br)count++;

            node=node.succ;
        }
        if(count%2!=0){
            node=list.getFirst();
            while (node!=null){

                if(node.element==br){list.insertBefore(br,node);break;}

                node=node.succ;

            }
        }
    }   //vezbanje KOL1 z.1

    public static long findMagicNumber(DLL<DLL<Integer>> list) {
        //Vashiot kod tuka...
        long prod=1;
        DLLNode<DLL<Integer>> node_so_lista=list.getFirst();

        while (node_so_lista!=null){
            int sum=0;
            DLL<Integer> minilista_od_node=node_so_lista.element;
            DLLNode<Integer> node_od_minilista=minilista_od_node.getFirst();
            while (node_od_minilista!=null){
                sum+=node_od_minilista.element;
                node_od_minilista=node_od_minilista.succ;
            }
            prod=prod*sum;
            node_so_lista=node_so_lista.succ;
        }
        return prod;
    }  //vezbanje KOL1 z.2


    private static int najdolgaOpagackaSekvenca(int[] a) {
        int max=1;

        int n=a.length;

        int[] dp=new int[n];

        for (int i = 0; i < n; i++) {
            dp[i]=1;
        }


        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] < a[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    if(dp[i]>max)max=dp[i];
                }
            }
        }

        /*
        for (int i = 0; i < a.length; i++) {
            biggest=a[i];
            Set<Integer> visited=new LinkedHashSet<>();
            for (int j = i; j < a.length; j++) {
                visited.add(biggest);
                if(a[j]<biggest){
                    visited.add(a[j]);

                }
            }
            ArrayList<Integer> lista=new ArrayList<>(new ArrayList<>(visited));

            if(lista.size()>1){

                int pred=lista.get(0);
                for (int k = 1; k < lista.size(); k++) {
                    if(pred>lista.get(k)){
                        count++;
                        pred=lista.get(k);
                        if(max<count)max=count;

                    }
                    else {
                        pred=lista.get(k);
                        if(max<count)max=count;
                        count=1;
                    }
                }
            }


            count=1;
        }
        */

        return max;
    }   //vezbanje KOL1 z.4


    public static int evaluateExpression(String expression){
        // Vasiot kod tuka

        String []parts=expression.split("\\+");
        int sum=0;
        for (int i = 0; i < parts.length; i++) {
            sum+=calc(parts[i]);
        }

        return sum;
    }   //vezbanje KOL1 z.5
    private static int calc(String part) {
        String []parts=part.split("\\+");
        int sum=0;
        if(parts.length>1){
            for (int i = 0; i < parts.length; i++) {
                sum+=calc(parts[i]);
            }
        }
        else {
            if(isNumber(part))return Integer.parseInt(part);
            else {
                parts=part.split("\\*");
                if(parts.length>1){
                    int prod=1;
                    for (String s : parts) {
                        prod*=Integer.parseInt(s);
                    }
                    return prod;
                }
            }
        }

        return sum;

    }
    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int count(int N){
        int count=0;
        // Vasiot kod tuka

        ArrayDeque<Integer> deque=new ArrayDeque<>();
        ArrayStack<Integer> stack=new ArrayStack<>(7);

        for (int i = 1; i < 52; i++) {

            deque.add(i);
        }
        while (deque.peek()!=N){
            for (int i = 0; i < 7; i++) {
                int b=deque.pop();
                stack.push(b);
            }
            while (!stack.isEmpty()){
                deque.add(stack.pop());
                deque.add(deque.pop());
            }

            count++;
        }

        return count;
    }  //vezbanje KOL1 z.6



    public static void JunskaIspitna(Scanner sc){
        /*

Во ДЛЛ со едно изминување да се провере колку броја поголеми може да се најдат од големиот број
како збир од два соседни.Ако се спојат два соседни се брише вториот.

Sample input:
9 8 7 6 5 4 3 2 1 2 3 4 5 6 7 8 9
40

Sample output:
98<->76<->54<->3<->2<->1<->2<->3<->45<->67<->89<->

*/
        String []nums=sc.nextLine().split("\\s++");
        ArrayList<Integer> arr=new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int a=Integer.parseInt(nums[i]);
            arr.add(a);
        }
        int broj=Integer.parseInt(sc.nextLine());
        DLL<Integer> dll=new DLL<>();

        for (Integer i : arr) {
            dll.insertLast(i);
        }

        System.out.println(dll);
        JunskaIspitnamedot(dll,broj);
        System.out.println(dll);

    }
    public static void JunskaIspitnamedot(DLL<Integer> dll,int broj){

        DLLNode<Integer> node=dll.getFirst();
        DLLNode<Integer> pred=null;
        boolean firstcycle=false;

        while (node!=null){
            if(!firstcycle){
                pred=node;
                node=node.succ;
                firstcycle=true;
            }
            else {
                int test=10*pred.element+node.element;
                if(test>broj){
                    dll.insertAfter(test,pred);
                    dll.delete(pred);
                    dll.delete(node);

                    node=node.succ;
                }
                pred=node;
                if(node == null)break;
                node=node.succ;

            }


        }


//        for (int i = 1; i < arr.size(); i++) {
//            int a=arr.get(i-1);
//            int b=arr.get(i);
//            int test=a*10+b;
//            if(test>broj){
//                dll.insertLast(test);
//                i++;
//            }
//            else {
//                dll.insertLast(a);
//                //dll.insertLast(b);
//            }
//        }



    }




    private static void vojskaBEZPLUS(DLL<Integer> list, int begin1, int end1, int begin2, int end2){
        DLLNode<Integer> int1begin=list.find(begin1);
        DLLNode<Integer> int2begin=list.find(begin2);

        DLLNode<Integer> int1end=list.find(end1);
        DLLNode<Integer> int2end=list.find(end2);

        DLLNode<Integer>int1pred=int1begin.pred;
        DLLNode<Integer>int2pred=int2begin.pred;

        while (int1begin!=int1end.succ){
            int b=int1begin.element;
            list.insertAfter(b,int2pred);
            list.delete(int1begin);
            int1begin=int1begin.succ;
            int2pred=list.find(b);
        }

        while (int2begin!=int2end.succ){
            int b=int2begin.element;
            if(int1pred==null){
                list.insertFirst(b);
                int1pred=list.getFirst();
                list.delete(int2begin);
                int2begin=int2begin.succ;
            }
            else {
                list.insertAfter(b,int1pred);
                list.delete(int2begin);
                int2begin=int2begin.succ;
                int1pred=list.find(b);
            }

        }





    }

    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        DLL<Integer> dll=new DLL<>();
        for (int i = 1; i <=10; i++) {
            dll.insertLast(i);
        }
        int i1begin=sc.nextInt();
        int i1end=sc.nextInt();
        int i2begin=sc.nextInt();
        int i2end=sc.nextInt();

        System.out.println(dll);
        //vojska(dll,i1begin,i1end,i2begin,i2end);
        vojskaBEZPLUS(dll,i1begin,i1end,i2begin,i2end);
        System.out.println(dll);
    }

}
