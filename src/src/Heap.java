package src;

import java.util.List;


/**
 * Class Heap
 * Basic heap implementation
 * Includes basic heap functions, and some heap utilitarian functions
 * */
public class Heap {
    //Array to store the heap's values
    private final List<Integer> m_heap;
    //heap size
    private int m_size;

    public Heap(List<Integer> heap, int size) {
        m_heap = heap;
        m_size = size;
    }

    public int get(int node) {
        if (node >= 0 && node < m_heap.size()) return m_heap.get(node);
        return -1;
    }

    public void set(int node, int value) {
        if (node >= 0 && node < m_heap.size()) m_heap.set(node, value);
    }

    public void insert(int value) {
        if (isFull()) m_heap.add(value);
        else m_heap.set(m_size, value);
        ++m_size;
    }

    public int getSize() { return m_size; }

    public void setSize(int size) {
        if (size >= 0 && size <= m_heap.size()) {
            m_size = size;
        }
    }

    public void exchange(int i, int j) {
        if (inBoundaries(i) && inBoundaries(j)) {
            int tmp = m_heap.get(i);
            m_heap.set(i, m_heap.get(j));
            m_heap.set(j, tmp);
        }
    }

    private boolean inBoundaries(int node) { return node >= 0 && node < m_heap.size(); }

    public boolean isOutOfHeap(int node) { return node < 0 || node >= m_size; }

    private boolean isFull() { return m_size == m_heap.size(); }

    public int left(int node) { return 2 * node + 1; }

    public int right(int node) { return 2 * node + 2; }

    public int parent(int node) { return (node - 1) / 2; }

    public int depth(int node) { return (int)Math.floor(Utils.log(node + 1)); }

    public int getLength() { return m_heap.size(); }

    public boolean isEmpty() { return m_size == 0; }

    public void printArray() {
        for (int node : m_heap) System.out.print(node + " ");
    }

    public void printHeap() {
        for (int node = 0; node < m_size; ++node) System.out.print(m_heap.get(node) + " ");
    }
}
