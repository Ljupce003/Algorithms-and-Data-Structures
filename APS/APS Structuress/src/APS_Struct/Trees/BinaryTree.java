package APS_Struct.Trees;

import java.util.ArrayList;

public class BinaryTree<E extends Comparable<E>> {

    public BNode<E> root;
    int nodecount;


    public BinaryTree() {
        root = null;
        nodecount=0;
    }

    public BinaryTree(E info) {
        root = new BNode<E>(info);
        nodecount=1;
    }

    public void makeRoot(E elem) {
        if(root==null)nodecount++;
        root = new BNode<E>(elem);

    }

    public BNode<E> addChild(BNode<E> node, int where, E elem) {

        BNode<E> tmp = new BNode<E>(elem);

        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }
        nodecount++;

        return tmp;
    }

    public int height() {
        return heightR(root);
    }
    public int getNodesCount(){
        return nodecount;
    }

    public int heightR(BNode<E> node) {
        if(node==null)return 0;

        int left=heightR(node.left);
        int right=heightR(node.right);

        return 1+Math.max(left,right);

    }

    boolean isBalanced(){
        return isBalancedR(root);
    }
    private boolean isBalancedR(BNode<E> node){

        if(node==null)return true;

        int left=heightR(node.left);
        int right=heightR(node.right);

        if(Math.abs(left-right)<=1 &&  isBalancedR(node.left) && isBalancedR(node.right)){
            return true;
        }
        return false;


    }


    //find_Lowest_Common_Ancestor
    public BNode<E> find_LCA(E data1, E data2){
        return find_LCA_r(this.root,data1,data2);
    }

    private BNode<E> find_LCA_r(BNode<E> node, E data1, E data2) {

        if(node==null)return null;

        if(node.info.equals(data1) || node.info.equals(data2)) return node;  //This node is the LCA

        BNode<E> left_LCA=find_LCA_r(node.left,data1,data2);        //Search for LCA in left subtree

        BNode<E> right_LCA=find_LCA_r(node.right,data1,data2);      //Search for LCA in right subtree


        // LCA is the root of the subtrees
        if(left_LCA!=null && right_LCA!=null)return node;

        // LCA is not found
        if(left_LCA==null && right_LCA==null)return null;

        //LCA found in left subtree
        if(left_LCA!=null)return left_LCA;

        else return right_LCA;
        //LCA found in right subtree




    }


    public BNode<E> findNode(E data){
        return findNodeR(data,root);
    }
    private BNode<E> findNodeR(E data, BNode<E> node){

        if(node==null)return null;

        if(data.compareTo(node.info)<0)return findNodeR(data,node.left);            // 'data' is smaller than the current node's data, so continue searching in the left subtree
        else if(data.compareTo(node.info)>0)return findNodeR(data,node.right);      // 'data' is larger than the current node's data, so continue searching in the right subtree.
        else return node;                                                           // 'data' matches the current node's data, so we've found a match.
    }

    public int find_level(E data){
        return find_levelR(root,data,0);
    }
    public int find_levelR(BNode<E> node, E data, int level) {
        if(node==null)return 0;

        if(node.info.equals(data))return level;

        int l_level=find_levelR(node.left,data,level+1);
        if(l_level!=0)return l_level;

        int r_level;
        r_level=find_levelR(node.right,data,level+1);
        return r_level;

    }






    public void inorder() {
        System.out.print("INORDER: ");
        inorderR(root);
        System.out.println();
    }

    public void inorderR(BNode<E> n) {
        if (n != null) {
            inorderR(n.left);
            System.out.print(n.info.toString()+" ");
            inorderR(n.right);
        }
    }

    public void preorder() {
        System.out.print("PREORDER: ");
        preorderR(root);
        System.out.println();
    }

    public void preorderR(BNode<E> n) {
        if (n != null) {
            System.out.print(n.info.toString()+" ");
            preorderR(n.left);
            preorderR(n.right);
        }
    }

    public void postorder() {
        System.out.print("POSTORDER: ");
        postorderR(root);
        System.out.println();
    }

    public void postorderR(BNode<E> n) {
        if (n != null) {
            postorderR(n.left);
            postorderR(n.right);
            System.out.print(n.info.toString()+" ");
        }
    }

    public void inorderNonRecursive() {
        ArrayStack<BNode<E>> s = new ArrayStack<BNode<E>>(100);
        BNode<E> p = root;
        System.out.print("INORDER (nonrecursive): ");

        while (true) {
            // pridvizuvanje do kraj vo leva nasoka pri sto site koreni
            // na potstebla se dodavaat vo magacin za podocnezna obrabotka
            while (p != null) {
                s.push(p);
                p = p.left;
            }

            // ako magacinot e prazen znaci deka stebloto e celosno izminato
            if (s.isEmpty())
                break;

            p = s.peek();
            // pecatenje (obrabotka) na jazelot na vrvot od magacinot
            System.out.print(p.info.toString()+" ");
            // brisenje na obraboteniot jazel od magacinot
            s.pop();
            // pridvizuvanje vo desno od obraboteniot jazel i povtoruvanje na
            // postapkata za desnoto potsteblo na jazelot
            p = p.right;

        }
        System.out.println();

    }

    int insideNodesR(BNode<E> node) {
        if (node == null)
            return 0;

        if ((node.left == null)&&(node.right == null))
            return 0;

        return insideNodesR(node.left) + insideNodesR(node.right) + 1;
    }

    public int insideNodes() {
        return insideNodesR(root);
    }

    int leavesR(BNode<E> node) {
        if (node != null) {
            if ((node.left == null)&&(node.right == null))
                return 1;
            else
                return (leavesR(node.left) + leavesR(node.right));
        } else {
            return 0;
        }
    }

    public int leaves() {
        return leavesR(root);
    }

    int depthR(BNode<E> node) {
        if (node == null)
            return 0;
        if ((node.left == null)&&(node.right == null))
            return 0;
        return (1 + Math.max(depthR(node.left), depthR(node.right)));
    }

    public int depth() {
        return depthR(root);
    }

    void mirrorR(BNode<E> node) {
        BNode<E> tmp;

        if (node == null)
            return;

        // simetricno preslikuvanje na levoto i desnoto potsteblo
        mirrorR(node.left);
        mirrorR(node.right);

        // smena na ulogite na pokazuvacite na momentalniot jazel
        tmp = node.left;
        node.left = node.right;
        node.right = tmp;

    }

    public void mirror() {
        mirrorR(root);
    }



    @Override
 public String toString() {
        // Use a StringBuilder to build the output string.
        StringBuilder sb = new StringBuilder();
        // Call the helper function to build the output string.
        toStringHelper(sb, root, 0, 9);
        // Convert the StringBuilder to String and return it.
        return sb.toString();
    }


    // Helper function that builds the output recursively
 private void toStringHelper(StringBuilder sb, BNode<E> node, int
            space, int count) {

         if (node == null)
             return;

         // to increase the distance between levels
         space += count;

         // print the right child first
         toStringHelper(sb, node.right, space, count);

         // print the current node after adding the spaces
         sb.append("\n");
         for (int i = count; i < space; i++) sb.append(" ");
         sb.append(node.info).append("\n");

         //print the left child
         toStringHelper(sb, node.left, space, count);
         }

    public void inorder_fill_arr(ArrayList<BNode<E>> array) {
        inorder_fill_arrR(root,array);
    }

    private void inorder_fill_arrR(BNode<E> node, ArrayList<BNode<E>> array) {
        if(node!=null){
            inorder_fill_arrR(node.left,array);
            array.add(node);
            inorder_fill_arrR(node.right,array);
        }
    }




    /*
    public static void main(String[] args) {
//        TreeNode<Character> a, b, c;
//        BinaryTree<Character> tree = new BinaryTree<Character>('F');
//        a = tree.addChild(tree.root, TreeNode.LEFT, 'D');
//        b = tree.addChild(a, TreeNode.LEFT, 'B');
//        c = tree.addChild(b, TreeNode.LEFT, 'A');
//        c = tree.addChild(b, TreeNode.RIGHT, 'C');
//        c = tree.addChild(a, TreeNode.RIGHT, 'E');
//        a = tree.addChild(tree.root, TreeNode.RIGHT, 'G');
//        b = tree.addChild(a, TreeNode.RIGHT, 'I');
//        c = tree.addChild(b, TreeNode.LEFT, 'H');
//        c = tree.addChild(b, TreeNode.RIGHT, 'J');
//
//        tree.inorder();
//        tree.preorder();
//        tree.postorder();
//        tree.inorderNonRecursive();
//
//        System.out.println("Brojot na vnatresni jazli e " + tree.insideNodes());
//        System.out.println("Brojot na listovi e " + tree.leaves());
//        System.out.println("Dlabocinata na drvoto e " + tree.depth());
//
//        tree.mirror();
//        tree.inorder();

//        BinaryTree<Integer> tree=new BinaryTree<>();
//
//        TreeNode<Integer> t11,t12,t121;
//
//        tree.makeRoot(1);
//        t11=tree.addChild(tree.root,1,2);
//        t12=tree.addChild(tree.root,2,5);
//
//        tree.addChild(t11,1,3);
//        tree.addChild(t11,2,4);
//
//        t121=tree.addChild(t12,1,6);
//        tree.addChild(t12,2,7);
//
//        //tree.addChild(t121,1,100);
//
//        System.out.println("Depth of my tree is: "+tree.depth());
//        System.out.println("Level count of my tree is:"+tree.insideNodes());
//        System.out.println("List count of my tree is:"+tree.leaves());
//
//        System.out.println();
//        tree.inorder();
//
//        System.out.println();
//        tree.preorder();
//
//        System.out.println();
//        tree.preorder();



    }
    */

}
