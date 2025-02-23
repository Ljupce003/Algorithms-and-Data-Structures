package APS_Struct.graph_tree;

import java.util.Comparator;

public class Heap<E extends Comparable<E>> {

    private final E[] elements;

    private Comparator<? super E> comparator;

    @SuppressWarnings("unchecked")
    public Heap(int size) {
        elements = (E[]) new Comparable[size];
    }

    private int compare(E k1, E k2) {
        return (comparator == null ? k1.compareTo(k2) : comparator.compare(k1, k2));
    }

    int getParent(int i) {
        return (i + 1) / 2 - 1;
    }

    public E getAt(int i) {
        return elements[i];
    }

    int getLeft(int i) {
        return (i + 1) * 2 - 1;
    }

    int getRight(int i) {
        return (i + 1) * 2;
    }

    void setElement(int index, E elem) {
        elements[index] = elem;
    }

    void swap(int i, int j) {
        E tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;
    }

    void adjust(int i, int n) {

        while (i < n) {

            int left = getLeft(i);
            int right = getRight(i);
            int largest = i;

            if ((left < n) && (elements[left].compareTo(elements[largest]) > 0))
                largest = left;
            if ((right < n) && (elements[right].compareTo(elements[largest]) > 0))
                largest = right;

            if (largest == i)
                break;

            swap(i, largest);
            i = largest;

        }

    }

    void buildHeap() {
        int i;
        for (i = elements.length / 2 - 1; i >= 0; i--)
            adjust(i, elements.length);
    }

    public void heapSort() {
        int i;
        buildHeap();
        for (i = elements.length; i > 1; i--) {
            swap(0, i - 1);
            adjust(0, i - 1);
        }
    }


    //TESTING


    /*
    public static void main(String[] args) {
        int i;
        Random r = new Random(System.currentTimeMillis());

        Heap<Integer> heap = new Heap<Integer>(150);

        for (i=0;i<150;i++)
            heap.setElement(i, r.nextInt(1000));

        System.out.println(Arrays.toString(heap.elements));
        heap.heapSort();

        for (i=1;i<150;i++) {
            if (heap.getAt(i-1).compareTo(heap.getAt(i)) > 0)
                System.out.println("ERROR");
        }

        System.out.println(Arrays.toString(heap.elements));

    }

    */
}
