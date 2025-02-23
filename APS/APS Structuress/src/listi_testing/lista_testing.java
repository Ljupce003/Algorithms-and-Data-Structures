package listi_testing;


import java.util.Scanner;

import APS_Struct.lists.*;

public class lista_testing {

    private static void Battalion(SLL<Integer> listIL, int beginswapIdx, int endswapIdx) {
        int i = 1;
        int j;
        SLLNode<Integer> nodebegin = listIL.getFirst();
        SLLNode<Integer> node = listIL.getFirst();
        SLLNode<Integer> pred = listIL.getFirst();
        SLL<Integer> newlist = new SLL<>();
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
        SLLNode<Integer> nodeb = listIL.find(nodebegin.element);
        newlist.mirror();
        while (newlist.size() != 0) {
            listIL.insertAfter(newlist.getFirst().element, nodeb);
            nodeb = nodeb.succ;
            newlist.deleteFirst();
        }
    }


    private static void Filter(SLL<Integer> list_filtered) {
        SLLNode<Integer> f = list_filtered.getFirst();
        SLLNode<Integer> prev = null;

        while (f != null) {
            if (prev != null) {
                if (f.element % 2 != 0 && prev.element % 2 != 0 || f.element % 2 == 0 && prev.element % 2 == 0) {
                    //list_filtered.insertAfter(0,prev);
                    list_filtered.delete(f);
                    //f=f.succ;
                }
            }
            prev = f;
            f = f.succ;
        }
    }


    private static void filterKOL(DLL<Integer> listFiltered) {
        DLLNode<Integer> node = listFiltered.getFirst();
        DLLNode<Integer> prev = null;
        while (node != null) {
            if (node.element == 0) {
                listFiltered.delete(node);
            }
            if (prev != null && prev.element > 0 && node.element > 0) {
                listFiltered.delete(node);
            }
            if (prev != null && prev.element < 0 && node.element < 0) {
                listFiltered.insertAfter(Math.abs(prev.element), prev);
            }
            prev = node;
            node = node.succ;

        }
    }


    private static void brisiICR(SLL<Integer> listFiltered) {
        int a=1;
        int counter=0;
        SLLNode<Integer> node=listFiltered.getFirst();
        while (node!=null){
            counter++;
            if(counter==a){
                listFiltered.delete(node);
                a++;
                counter=0;
            }
            node=node.succ;

        }

    }


    private static void filterKOLS(SLL<Integer> listFiltered) {
        SLLNode<Integer> node = listFiltered.getFirst();
        SLLNode<Integer> prev = null;
        while (node != null) {
            if (node.element == 0) {
                listFiltered.delete(node);
            }
            if (prev != null && prev.element > 0 && node.element > 0) {
                listFiltered.delete(node);
            }
            if (prev != null && prev.element < 0 && node.element < 0) {
                listFiltered.insertAfter(Math.abs(prev.element), prev);
            }
            prev = node;
            node = node.succ;

        }
    }


    private static void rearange(SLL<String> list){
        int size_list= list.size();

        SLL<String> list1=new SLL<>();
        SLL<String> list2=new SLL<>();
        SLLNode<String> node=list.getFirst();
        for (int i = 0; i < size_list/2; i++) {
            list1.insertLast(node.element);
            node=node.succ;
        }
        for (int i = size_list/2; i < size_list; i++) {
            list2.insertLast(node.element);
            node=node.succ;
        }

        list.deleteList();
        for (int i = 0; i <= size_list/2; i++) {
            if(list1.size()!=0){
                list.insertLast(list1.getFirst().element);
                list1.deleteFirst();
            }
            if(list2.size()!=0){
                SLLNode<String> temp=list2.getFirst();
                while (temp.succ!=null){
                    temp=temp.succ;
                }
                list.insertLast(temp.element);
                list2.delete(temp);
            }
        }

    }



    private static void Cocatenate(DLL<String> list) {
        DLLNode<String> node = list.getFirst();
        StringBuilder str = new StringBuilder();

        while (node != null) {
            str.append(node.element);
            node = node.succ;
        }

        String[] splits = str.toString().split(",");


        list.deleteList();
        for (String split : splits) {
            list.insertLast(split);
        }
    }




    private static SLL<Integer> merged_kol(SLL<Integer> list1, SLL<Integer> list2) {
        SLL<Integer> rlist=new SLL<>();

        int maxsize=Math.max(list1.size(),list2.size());
        for (int i = 0; i < maxsize; i++) {
            if(list1.size()!=0){
                rlist.insertLast(list1.getFirst().element);
                list1.deleteFirst();
                if(list1.size()!=0){
                    rlist.insertLast(list1.getFirst().element);
                    list1.deleteFirst();
                }
            }
            if(list2.size()!=0){
                rlist.insertLast(list2.getFirst().element);
                list2.deleteFirst();
                if(list2.size()!=0){
                    rlist.insertLast(list2.getFirst().element);
                    list2.deleteFirst();
                }
            }

            if(list1.size()==0 && list2.size()==0)break;
        }



        return rlist;
    }

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        DLL<Integer> dll=new DLL<>();
        for (int i = 0; i < 10; i++) {
            dll.insertLast(i);
        }

        System.out.println(dll);

        DLLNode<Integer> node=dll.getFirst();

        node=node.succ.succ;


        node.pred.succ=node.succ;
        node.succ.pred=node.pred;
        dll.insertAfter(100,node.pred);
        System.out.println(dll);
        System.out.println(node);

//
//
//
//        System.out.println("How many test cases");
//        int TEST = sc.nextInt();
//
//        if (TEST == 0) {
//            System.out.println("Test case 0");
//            SLL<Integer> list_filtered = creationSLL(sc);
//            int begin_idx= sc.nextInt();
//            int end_idx= sc.nextInt();
//            Battalion(list_filtered,begin_idx,end_idx);
//            System.out.println("Filtered :" + list_filtered);
//        }
//
//
//        if (TEST == 1) {
//            System.out.println("Test case 1");
//            SLL<Integer> list_filtered = creationSLL(sc);
//            Filter(list_filtered);
//            System.out.println("Filtered :" + list_filtered);
//        }
//        if (TEST == 2) {
//            System.out.println("Test case 2");
//            DLL<String> listIL = new DLL<>();
//            DLL<String> list_filtered = new DLL<>();
//            int N = sc.nextInt();
//            for (int i = 0; i < N; i++) {
//                String bukva = sc.next();
//                listIL.insertLast(bukva);
//                list_filtered.insertLast(bukva);
//            }
//            System.out.println();
//            System.out.println("Original :" + listIL);
//
//            Cocatenate(list_filtered);
//            System.out.println("Cocatenated :" + list_filtered);
//        }
//
//        if (TEST == 3) {
//            System.out.println("Test case 3");
//            //DLL<Integer> filtration = creation(sc);
//            SLL<Integer> sll=creationSLL(sc);
//            filterKOLS(sll);
//            System.out.println("Filtered :" + sll);
//
//            //filterKOL(filtration);
//            //System.out.println("Cocatenated :" + filtration);
//        }
//
//        if(TEST==4){
//            System.out.println("Test case 4");
//            SLL<String> sll=creationSLL_S(sc);
//            rearange(sll);
//            System.out.println("Rearange :" + sll);
//        }
//
//
//        if (TEST == 5) {
//            System.out.println("Test case 5");
//            SLL<Integer> list_filtered = creationSLL(sc);
//            brisiICR(list_filtered);
//            System.out.println("Filtered :" + list_filtered);
//        }
//
//        if (TEST == 6) {
//            System.out.println("Test case 6");
//            SLL<Integer> list1 = creationSLL_INT(sc);
//            SLL<Integer> list2 = creationSLL_INT(sc);
//            SLL<Integer> list_filtered=merged_kol(list1,list2);
//            System.out.println("Merged :" + list_filtered);
//        }



        sc.close();
    }


//    private static DLL<Integer> creation(Scanner sc) {
//        DLL<Integer> original = new DLL<>();
//        DLL<Integer> filtration = new DLL<>();
//        int N = sc.nextInt();
//        for (int i = 0; i < N; i++) {
//            int broj = sc.nextInt();
//            original.insertLast(broj);
//            filtration.insertLast(broj);
//        }
//        System.out.println();
//        System.out.println("   Original :" + original);
//        return filtration;
//    }


    private static SLL<String> creationSLL_S(Scanner sc) {
        SLL<String> filtration = new SLL<>();
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {


            String number = sc.next();

            filtration.insertLast(number);
        }
        System.out.println();
        System.out.println("Original :" + filtration);
        return filtration;
    }

    private static SLL<Integer> creationSLL(Scanner sc) {
        SLL<Integer> filtration = new SLL<>();
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int number=sc.nextInt();
            filtration.insertLast(number);
        }
        System.out.println();
        System.out.println("Original :" + filtration);
        return filtration;
    }


    private static SLL<Integer> creationSLL_INT(Scanner sc) {
        SLL<Integer> filtration = new SLL<>();
        int N = sc.nextInt();
        String rt=sc.nextLine();
        String read=sc.nextLine();
        for (String s : read.split("\\s+")) {
            if(!s.isEmpty()){
                int num=Integer.parseInt(s);
                filtration.insertLast(num);
            }
        }
        System.out.println();
        System.out.println("Original :" + filtration);
        return filtration;
    }



}
