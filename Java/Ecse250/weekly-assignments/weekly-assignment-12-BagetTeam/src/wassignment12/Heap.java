package wassignment12;

import java.util.ArrayList;

public class Heap<T extends Comparable<T>>{
    public ArrayList<T> heap;

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }

    public Heap() {
        heap = new ArrayList<>();
    }

    public void add(T e) {
        // TODO: Add your code here
        heap.add(e);
        int current = heap.size() - 1;
        while(current > 0 && heap.get(parent(current)).compareTo(e) > 0) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public T removeMin() {
        // TODO: Add your code here
        T minHeap = heap.getFirst();
        int i = 0;
        int n = heap.size() - 1;
        heap.set(i, heap.get(n));
        heap.remove(n);

        while (leftChild(i) < n) {
            int child = leftChild(i);
            
            if (child < n-1 && heap.get(child+1).compareTo(heap.get(child)) < 0) { 
                child++;
            }
            System.out.println(child);
            if (heap.get(i).compareTo(heap.get(child)) > 0) {
                swap(i, child);
                i = child;
            }
            else {
                break;
            }
        }

        return minHeap;
    }

    public void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}