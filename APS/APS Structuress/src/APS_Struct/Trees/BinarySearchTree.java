package APS_Struct.Trees;

// BinarySearchTree class

//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// Comparable find( x )   --> Return item that matches x
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<E extends Comparable<E>> {

    /** The tree root. */
    private BNode<E> root;

    /**
     * Construct the tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert(E x) {
        root = insert(x, root);
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove(E x) {
        root = remove(x, root);
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public E findMin() {
        return elementAt(findMin(root));
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public E findMax() {
        return elementAt(findMax(root));
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return the matching item or null if not found.
     */
    public BNode<E> find(E x) {
        return find(x, root);
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty tree");
        } else {
            printTree(root);
        }
    }

    /**
     * Internal method to get element field.
     * @param t the node.
     * @return the element field or null if t is null.
     */
    private E elementAt(BNode<E> t) {
        if (t == null)
            return null;
        return t.info;
    }

    /**
     * Internal method to insert into a subtree.
     * @param data the item to insert.
     * @param node the node that roots the tree.
     * @return the new root.
     */
    private BNode<E> insert(E data, BNode<E> node) {
        if (node == null) {
            node = new BNode<E>(data, null, null);
        } else if (data.compareTo(node.info) < 0) {
            node.left = insert(data, node.left);
        } else if (data.compareTo(node.info) > 0) {
            node.right = insert(data, node.right);
        }   // Duplicate; do nothing
        return node;
    }

    /**
     * Internal method to remove from a subtree.
     * @param data the item to remove.
     * @param node the node that roots the tree.
     * @return the new root.
     */
    @SuppressWarnings({"raw", "unchecked"})
    private BNode<E> remove(Comparable data, BNode<E> node) {
        if (node == null)
            return null;   // Item not found; do nothing

        if (data.compareTo(node.info) < 0) {
            node.left = remove(data, node.left);
        } else if (data.compareTo(node.info) > 0) {
            node.right = remove(data, node.right);
        } else if (node.left != null && node.right != null) { // Two children
            node.info = findMin(node.right).info;
            node.right = remove(node.info, node.right);
        } else {
            if (node.left != null)
                return node.left;
            else
                return node.right;
        }
        return node;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param node the node that roots the tree.
     * @return node containing the smallest item.
     */
    private BNode<E> findMin(BNode<E> node) {
        if (node == null) {
            return null;
        } else if (node.left == null) {
            return node;
        }
        return findMin(node.left);
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param node the node that roots the tree.
     * @return node containing the largest item.
     */
    private BNode<E> findMax(BNode<E> node) {
        if (node == null) {
            return null;
        } else if (node.right == null) {
            return node;
        }
        return findMax(node.right);
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param node the node that roots the tree.
     * @return node containing the matched item.
     */
    private BNode<E> find(E x, BNode<E> node) {
        if (node == null)
            return null;

        if (x.compareTo(node.info) < 0) {
            return find(x, node.left);
        } else if (x.compareTo(node.info) > 0) {
            return find(x, node.right);
        } else {
            return node;    // Match
        }
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the tree.
     */
    private void printTree(BNode<E> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.info);
            printTree(t.right);
        }
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

    public void remove_leafs() {
        remove_leafsR(root);
    }

    private void remove_leafsR(BNode<E> node) {
        if(node==null)return;
        if(node.left!=null || node.right!=null){
            remove_leafsR(node.left);
            remove_leafsR(node.right);
        }
        else remove(node.info);
    }

    public BNode<E> find_LCA(E a,E b) {


        return find_LCAr(root,a,b);


    }

    private BNode<E> find_LCAr(BNode<E> node, E a, E b) {
        if(node==null)return null;

        if(node.info.equals(a) || node.info.equals(b)) return node;

        BNode<E> leftLCA=find_LCAr(node.left,a,b);
        BNode<E> rightLCA=find_LCAr(node.right,a,b);

        if(leftLCA!=null && rightLCA!=null) return node;
        if(leftLCA==null && rightLCA==null)return null;

        if(leftLCA!=null)return leftLCA;
        else return rightLCA;

//        else {
//            if(node.info.compareTo(a)>0 && node.info.compareTo(b)>0){
//                return find_LCAr(node.left,a,b);
//            }
//            else if(node.info.compareTo(a)>0 && node.info.compareTo(b)<0 ){
//                return node;
//            }
//            else find_LCAr(node.right,a,b);
//        }

        
    }

    // Test program
    /*
    public static void main(String[] args) {
        int i,j,k;

        Random r = new Random(System.currentTimeMillis());
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();

        for (i=0;i<1000;i++)
            bst.insert(r.nextInt(1000000));

        bst.printTree();

    }
    */
}
