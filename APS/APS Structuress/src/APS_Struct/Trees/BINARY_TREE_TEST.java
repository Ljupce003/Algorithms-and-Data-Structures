package APS_Struct.Trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class BINARY_TREE_TEST {

    public static void create_intTree_example(BinaryTree<Integer> tree){
        tree.makeRoot(1);
        BNode<Integer> t1,t2;
        t1=tree.addChild(tree.root,1,7);
        tree.addChild(t1,1,2);

        t1=tree.addChild(t1,2,6);
        tree.addChild(t1,1,5);
        tree.addChild(t1,2,11);

        t2=tree.addChild(tree.root,2,9);
        t2=tree.addChild(t2,2,19);
        tree.addChild(t2,1,15);
    }
    public static void create_intTree_example1(BinaryTree<Integer> tree){
        tree.makeRoot(1);
        BNode<Integer> t1,t2;
        t1=tree.addChild(tree.root,1,7);
        tree.addChild(t1,1,2);

//        t1=tree.addChild(t1,2,6);
//        tree.addChild(t1,1,5);
//        tree.addChild(t1,2,11);

        t2=tree.addChild(tree.root,2,9);
        t2=tree.addChild(t2,2,19);
        tree.addChild(t2,1,15);
    }
    public static void create_intTree_example3(BinaryTree<Integer> tree){
        tree.makeRoot(26);
        BNode<Integer> t1,t2;
        t1=tree.addChild(tree.root,1,10);
        tree.addChild(t1,1,4);

        tree.addChild(t1,2,6);
//        tree.addChild(t1,1,5);
        //tree.addChild(t1,2,11);

        t2=tree.addChild(tree.root,2,3);
        tree.addChild(t2,2,3);
        //tree.addChild(t2,1,15);
    }
    public static void create_intTree_example4(BinaryTree<Integer> tree){
        tree.makeRoot(3);
        BNode<Integer> t1,t2;
        t1=tree.addChild(tree.root,1,5);
        tree.addChild(t1,1,1);

        t1=tree.addChild(t1,2,3);
        tree.addChild(t1,1,2);
        tree.addChild(t1,2,9);

        t2=tree.addChild(tree.root,2,1);
        t2=tree.addChild(t2,2,1);
        tree.addChild(t2,1,6);
    }
    public static void create_intTree_example5(BinaryTree<Integer> tree){
        tree.makeRoot(8);
        BNode<Integer> t1,t2;
        t1=tree.addChild(tree.root,1,2);
        tree.addChild(t1,1,1);

        t1=tree.addChild(t1,2,5);
        tree.addChild(t1,1,3);
        tree.addChild(t1,2,6);

        t2=tree.addChild(tree.root,2,11);
        t2=tree.addChild(t2,2,19);
        tree.addChild(t2,1,13);
    }
    public static void create_stringTree_example(BinaryTree<String> tree){
        BNode<String> t2;

        tree.addChild(tree.root,1,"pear");
        t2=tree.addChild(tree.root,2,"banana");

        tree.addChild(t2,1,"lemon");
        tree.addChild(t2,2,"orange");
    }
    private static void create_int_BTree(BinarySearchTree<Integer> btree) {
        btree.insert(8);
        btree.insert(2);
        btree.insert(1);
        btree.insert(5);
        btree.insert(3);
        btree.insert(6);
        btree.insert(11);
        btree.insert(19);
        btree.insert(13);
    }





    private static void Create_BTree_with_strings() {
        BinaryTree<String> tree=new BinaryTree<>("apple");
        create_stringTree_example(tree);

        tree.inorder();

        System.out.println(tree);


    }


    private static void Create_BTree_with_ints(Scanner sc) {
        BinaryTree<Integer> tree=new BinaryTree<>();
        create_intTree_example(tree);

        int number=sc.nextInt();

        //int found=find_Node(tree.root,number);

        BNode<Integer> node=tree.findNode(number);
        if(node!=null) System.out.println("There is a node with number "+node.info+" in the tree YES");
        else System.out.println("Node with number "+number+" doesn't exist in the tree NO");

        //if(found==0) System.out.println("Node with number "+number+" doesn't exist in the tree NO");
        //else System.out.println("There is a node with number "+number+" in the tree YES");

    }
    private static int find_Node(BNode<Integer> node, int i) {
        if(node==null)return 0;
        if(node.info==i)return 1;

        if(find_Node(node.left,i)==1)return 1;
        if(find_Node(node.right,i)==1)return 1;


        return 0;
    }


    private static void Find_depth_of_node(Scanner sc) {
        int number=sc.nextInt();

        BinaryTree<Integer> tree=new BinaryTree<>();

        create_intTree_example(tree);


        System.out.println(tree.find_level(number));


    }


    private static void Find_sum_of_tree() {
        BinaryTree<Integer> tree=new BinaryTree<>();
        create_intTree_example(tree);
        float sum=sum_of_tree(tree.root);

        System.out.println(sum);
    }
    private static int sum_of_tree(BNode<Integer> node) {

        if(node==null)return 0;
        int sum = node.info;

        sum +=sum_of_tree(node.left);
        sum +=sum_of_tree(node.right);

        return sum;
    }


    private static void Find_sum_of_subtree(Scanner sc) {
        int number=sc.nextInt();

        BinaryTree<Integer> tree=new BinaryTree<>();
        create_intTree_example(tree);

        BNode<Integer> node=tree.findNode(number);

        if(node!=null){
            float left_sub_sum=0;
            if(node.left!=null)left_sub_sum=sum_of_tree(node.left);

            float right_sub_sum=0;
            if(node.right!=null)right_sub_sum=sum_of_tree(node.right);

            System.out.println("the sum of left subtree of node "+number+" is :"+left_sub_sum);
            System.out.println("the sum of right subtree of node "+number+" is :"+right_sub_sum);

        }
    }


    private static void Find_distance_between_nodes(Scanner sc) {
        int number1=sc.nextInt();
        int number2=sc.nextInt();

        BinaryTree<Integer> tree=new BinaryTree<>();
        create_intTree_example(tree);

        BNode<Integer> ancestor=tree.find_LCA(number1,number2);

        if(ancestor!=null){

            int dist_l=tree.find_levelR(ancestor,number1,0);
            int dist_r=tree.find_levelR(ancestor,number2,0);

            System.out.println(dist_l+dist_r);

        }
    }


    private static void Sum_of_onlyLeft_nodes() {
        BinaryTree<Integer> tree=new BinaryTree<>();
        create_intTree_example1(tree);

        //System.out.println(tree);

        int sum=sum_only_left(tree.root);

        System.out.println(sum);
    }
    private static int sum_only_left(BNode<Integer> node) {
        if(node==null)return 0;
        int sum=0;
        if(node.right==null && node.left!=null)sum=node.info;

        sum+=sum_only_left(node.left);
        sum+=sum_only_left(node.right);
        return sum;

    }


    private static void Sum_tree_Check() {
        BinaryTree<Integer> tree=new BinaryTree<>();
        create_intTree_example3(tree);

        int c=sum_check(tree.root)/2;

        if(c!=0) System.out.println("YES this is a Sum Tree");
        else System.out.println("NO this is NOT a Sum Tree");
    }
    private static int sum_check(BNode<Integer> node) {
        if(node==null)return 0;
        if(node.left==null && node.right==null)return node.info;
        int left=sum_check(node.left);
        int right=sum_check(node.right);

        if(node.info==(left+right))return (node.info+left+right);
        else return 0;


    }


    private static void Path_sum_to_List() {
        BinaryTree<Integer> tree=new BinaryTree<>();
        create_intTree_example4(tree);

        int sum=0;
        ArrayDeque<Integer> que=new ArrayDeque<>();
        sum=path_sum_leafs(tree.root,que,sum);
        System.out.println(sum);
    }
    private static int path_sum_leafs(BNode<Integer> node, ArrayDeque<Integer> que, int sum) {

        if(node==null)return 0;
        que.add(node.info);
        if(node.left==null && node.right==null){
            int i=0;
            while (!que.isEmpty()){
                int pr=(int)Math.pow(10,i);
                int br=que.getLast();
                que.removeLast();
                sum+=pr*br;
                i++;
            }
            return sum;
        }
        else {
            ArrayDeque<Integer> left=new ArrayDeque<>(que);
            ArrayDeque<Integer> right=new ArrayDeque<>(que);
            return path_sum_leafs(node.left,left,sum)+path_sum_leafs(node.right,right,sum)+sum;
        }




    }


    private static void Check_if_tree_balanced() {
        BinaryTree<Integer> tree=new BinaryTree<>();
        create_intTree_example5(tree);


        if(tree.isBalanced()){
            System.out.println("Tree is balanced");
        }
        else System.out.println("Tree is not balanced");


    }


    private static void Find_inorder_succ_pred(Scanner sc) {
        int k_node=sc.nextInt();
        BinaryTree<Integer> tree=new BinaryTree<>();
        create_intTree_example5(tree);


        ArrayList<BNode<Integer>> array=new ArrayList<>(tree.getNodesCount());
        tree.inorder_fill_arr(array);
        BNode<Integer> node=tree.findNode(k_node);
        if(array.contains(node)){
            int i=array.indexOf(node);
            if(i>0 && i<array.size()-1) System.out.println("Predecessor is : "+array.get(i-1).info+" and Successor is : "+array.get(i+1).info);
            else if(i==0) System.out.println("There is on Predecessor but the Successor is : "+array.get(i+1));
            else System.out.println("Predecessor is : "+array.get(i-1).info+" but there is no Successor");
        }
        //System.out.println(array);
    }
    private static void Get_k_element_tree(Scanner sc) {
        int k=sc.nextInt();
        BinaryTree<Integer> tree=new BinaryTree<>();
        create_intTree_example5(tree);


        ArrayList<BNode<Integer>> array=new ArrayList<>(tree.getNodesCount());
        tree.inorder_fill_arr(array);
        if(k>=0 && k<array.size()){
            System.out.println("k-th Smallest element in the array is :"+array.get(k-1).info);
            System.out.println("k-th Largest element in the array is :"+array.get(array.size()-k).info);
        }
        System.out.println("\n"+array);
    }


    private static void Find_sum_of_k_nodes(Scanner sc) {
        int k=sc.nextInt();
        BinaryTree<Integer> tree=new BinaryTree<>();
        create_intTree_example5(tree);

        ArrayList<BNode<Integer>> arr=new ArrayList<>();
        tree.inorder_fill_arr(arr);

        BNode<Integer> node=arr.get(k-1);
        System.out.println(node);

        int sum=0;
        sum=sum_K_r(tree.root,sum,node.info);

        System.out.println(sum);

        System.out.println(arr);



    }
    private static int sum_K_r(BNode<Integer> node, int sum,int k_sum) {
        if(node!=null ){

            int l=sum_K_r(node.left,sum,k_sum);
            if(node.info<=k_sum)sum=node.info;
            else sum=0;
            int r=sum_K_r(node.right,sum,k_sum);
            return l+sum+r;
        }

        return 0;

    }


    private static void Remove_leafs_in_Bin_Search_T() {
        BinarySearchTree<Integer> btree=new BinarySearchTree<>();
        create_int_BTree(btree);
        System.out.println(btree);
        System.out.println("////////////////////////////////////////////////////////////////////////");
        btree.remove_leafs();

        System.out.println(btree);

    }


    private static void Find_LCA_Binary_Search_T(Scanner sc) {
        BinarySearchTree<Integer> btree=new BinarySearchTree<>();
        create_int_BTree(btree);
        int n1=sc.nextInt();
        int n2=sc.nextInt();
//        BNode<Integer> node1=btree.find(n1);
//        BNode<Integer> node2=btree.find(n2);
        BNode<Integer> LCA=btree.find_LCA(n1,n2);
    }


    private static void Simple_tree_test_print() {


        SLLTree<String> t=new SLLTree<>();

        Tree.Node<String> a,b,c;

        t.makeRoot("C:");

        a = t.addChild(t.root, "Program files");
        b = t.addChild(a, "CodeBlocks");
        c = t.addChild(b, "codeblocks.dll");
        c = t.addChild(b, "codeblocks.exe");
        b = t.addChild(a, "Notepad++");
        c = t.addChild(b, "languages.xml");

        c = t.addChild(b, "readme.txt");
        c = t.addChild(b, "notepad++.exe");
        a = t.addChild(t.root, "Users");
        b = t.addChild(a, "Darko");
        c = t.addChild(b, "Desktop");
        c = t.addChild(b, "Downloads");
        c = t.addChild(b, "My Documents");
        c = t.addChild(b, "My Pictures");
        b = t.addChild(a, "Public");
        a = t.addChild(t.root, "Windows");
        b = t.addChild(a, "Media");


        t.printTree();

    }


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        //Create_BTree_with_strings();

        //Create_BTree_with_ints(sc);

        //Find_depth_of_node(sc);

        //Find_sum_of_tree();

        //Find_sum_of_subtree(sc);

        //Find_distance_between_nodes(sc);
        
        //Sum_of_onlyLeft_nodes();

        //Sum_tree_Check();

        //Path_sum_to_List();

        //Check_if_tree_balanced();

        //Get_k_element_tree(sc);

        //Find_inorder_succ_pred(sc);

        //Find_sum_of_k_nodes(sc);

        //Remove_leafs_in_Bin_Search_T();

        //Find_LCA_Binary_Search_T(sc);

        //Simple_tree_test_print();



        BinaryTree<Integer> tree=new BinaryTree<>();
        create_intTree_example1(tree);

        //tree.inorder(); //INORDER: 2 7 1 9 15 19
        //tree.preorder();

        inorderNrecurs(tree);


        sc.close();
    }

    private static void inorderNrecurs(BinaryTree<Integer> tree){
        BNode<Integer> node=tree.root;
        Stack<BNode<Integer>> stack=new ArrayStack<>(tree.nodecount);

//        System.out.print("Preorder: ");
//        while (true){
//
//            while (node!=null){
//                System.out.print(node.info+" ");
//                stack.push(node);
//                node=node.left;
//            }
//
//
//            if(stack.isEmpty())break;
//            node=stack.pop().right;
//
//
//
//
////            node=node.right;
//
//        }


//        System.out.print("Inorder: ");
//        while (true){
//
//            while (node!=null){
//                stack.push(node);
//                node=node.left;
//            }
//
//            if(stack.isEmpty())break;
//            node=stack.pop();
//
//            System.out.print(node.info+" ");
//
//
//
//            node=node.right;
//
//        }


        while (true){

            while (node!=null){
                if(node.right!=null)stack.push(node.right);
                stack.push(node);
                node=node.left;
            }

            if(stack.isEmpty())break;


            node=stack.pop();
            if(node.right!=null && !stack.isEmpty() && node.right==stack.peek()){
                stack.pop();
                stack.push(node);
                node=node.right;
            }
            else {
                System.out.print(node.info+" ");
                node=null;
            }



        }

    }



}
