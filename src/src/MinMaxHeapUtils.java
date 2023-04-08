package src;

/**
 * Class MinMaxHeapUtils
 * Contains all MinMaxHeap functions
 * Each function is static, and accepts a Heap as a parameter
 * */
public class MinMaxHeapUtils {
    /**
     * Sort an array represented by a Heap object
     * Elements beyond heap-size are included in the sort (can happen after extract\delete)
     * After sorting heap-size=a.length()
     * @param a The heap
     * */
    public static void heapSort(Heap a) {
        //make a MinMaxHeap
        buildHeap(a);

        //extract all maxes, one by one
        for(int i = a.getLength() - 1; i > 0; --i) {
            a.exchange(0, i);
            a.setSize(a.getSize() - 1);
            heapify(a, 0);
        }

        a.setSize(a.getLength());
    }

    /**
     * Delete an Element of the heap
     * Assumes the heap is MinMaxHeap
     * @param a The heap
     * @param i Element's index, ranging from 0 to (heap-size - 1)
     * */
    public static void heapDelete(Heap a, int i) {
        if (a.isOutOfHeap(i)) {
            System.out.println("Index out of range");
            return;
        }

        //set the element to max, push to the top, extract it
        a.set(i, Integer.MAX_VALUE);
        heapPushUp(a, i);
        heapExtractMax(a);
    }

    /**
     * Insert a key to the heap
     * Assumes the heap is MinMaxHeap
     * @param a The heap
     * @param key Key to insert
     * */
    public static void heapInsert(Heap a, int key) {
        a.insert(key);

        //place the new key so that a remains MinMaxHeap
        heapPushUp(a, a.getSize() - 1);
    }


    /**
     * Push-up an element to it's correct location in the heap
     * Assumes the heap is MinMaxHeap
     * @param a The heap
     * @param i Index of the element, ranging from 0 to (heap-size - 1)
     * */
    private static void heapPushUp(Heap a, int i) {
        boolean isOdd = a.depth(i) % 2 != 0;
        int parent = a.parent(i);
        if(parent == i) return;
        int best = findBest(a, i, parent, isOdd);
        if(i != best) {
            a.exchange(i, best);
            heapPushUp(a, parent);
        } else {
            int grandparent = a.parent(parent);
            if (grandparent == parent) return;
            best = findBest(a, i, grandparent, !isOdd);
            if (i != best) {
                a.exchange(i, best);
                heapPushUp(a, grandparent);
            }
        }
    }

    /**
     * Extracts the min element of the heap, assuming it is a MinMaxHeap and prints it
     * Assumes the heap is MinMaxHeap
     * @param a The heap
     * @return The min element
     * */
    public static int heapExtractMin(Heap a) {
        if (a.isEmpty()) {
            System.out.println("Heap is empty");
            return -1;
        }

        //the min element is the first three elements
        int min = findBest(a, 0, 1, true);
        min = findBest(a, min, 2, true);

        //remove min from the heap
        a.exchange(min, a.getSize() - 1);
        a.setSize(a.getSize() - 1);

        //make the new heap MinMaxHeap
        heapify(a, min);
        return a.get(a.getSize());
    }

    /**
     * Extracts the max element of the heap, assuming it is a MinMaxHeap and prints it
     * Assumes the heap is MinMaxHeap
     * @param a The heap
     * @return The max element
     * */
    public static int heapExtractMax(Heap a) {
        if (a.isEmpty()) {
            System.out.println("Heap is empty");
            return -1;
        }

        //the max element is the first one
        int max = a.get(0);

        //remove max from heap
        a.exchange(0, a.getSize() - 1);
        a.setSize(a.getSize() - 1);

        //make the new heap MinMaxHeap
        heapify(a, 0);
        return max;
    }

    /**
     * Build a MinMaxHeap given an array represented by a Heap object
     * Elements that are bigger than heap's size (can happen after deletion) will be included
     * @param a The Heap
     * */
    public static void buildHeap(Heap a) {
        a.setSize(a.getLength());

        //iterate throw all non leaf elements, from end to start, and make their subtree MinMaxHeap
        for (int node = a.getSize() / 2 - 1; node >= 0; --node) {
            heapify(a, node);
        }
    }

    /**
     * Makes an Index's subtree MinMaxHeap
     * Assumes both subtrees of the Index's children are MinMaxHeap
     * @param a The heap
     * @param i The index, ranging from 0 to (heap-size - 1)
     * */
    public static void heapify(Heap a, int i) {
        if (a.isOutOfHeap(i)) {
            System.out.println("Index out of range");
            return;
        }
        boolean isOdd = a.depth(i) % 2 != 0;

        //find if the value of i is bigger/smaller(depth dependent) than its kids
        int best_with_child = findBest(a, i, a.right(i), isOdd);
        best_with_child = findBest(a, best_with_child, a.left(i), isOdd);

        //exchange i's value with the smaller/bigger grandchild
        if (best_with_child != i) a.exchange(best_with_child, i);

        //find if the value of i is bigger/smaller(depth dependent) than its grandkids
        int best_with_grand = findBest(a, i, a.right(a.right(i)), isOdd);
        best_with_grand = findBest(a, best_with_grand, a.right(a.left(i)), isOdd);
        best_with_grand = findBest(a, best_with_grand, a.left(a.right(i)), isOdd);
        best_with_grand = findBest(a, best_with_grand, a.left(a.left(i)), isOdd);

        //make sure that after the exchange the subtree of the descendant is MinMaxHeap
        if (best_with_grand != i) {
            //exchange i's value with the smaller/bigger grandchild
            a.exchange(best_with_grand, i);
            heapify(a, best_with_grand);
        } else if (best_with_child != i) heapify(a, best_with_child);
    }

    /**
     * Find the Min/Max node between a given node and a candidate node in a given heap
     * @param a The heap
     * @param node Index of the Current node
     * @param candidate Index of the Candidate node
     * @param small_compare True if compare by Min, False if by Max
     * @return Index of the Min/Max node if candidate is in heap,
     *          and the index of the given node otherwise
     * */
    public static int findBest(Heap a, int node, int candidate, boolean small_compare) {
        if (a.isOutOfHeap(candidate)) return node;
        if ((small_compare && a.get(candidate) < a.get(node)) || (!small_compare && a.get(candidate) > a.get(node))) {
            return candidate;
        }
        return node;
    }
}
