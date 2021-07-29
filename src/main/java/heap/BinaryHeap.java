package heap;

public class BinaryHeap<Key extends Comparable<Key>> {


    private final Key[] pq;
    private Integer count;

    public BinaryHeap(Integer maxN) {
        pq = (Key[]) new Comparable[maxN+1];
    }

    public void insert(Key v) {

    }

    public Key max() {

    }

    public Key delMax() {

    }

    public boolean isEmpty() {

    }

    public int size() {
        return count;
    }

    private boolean  less(int i, int j) {  return pq[i].compareTo(pq[j]) < 0;  }

    private void  exch(int i, int j) {  Key t = pq[i]; pq[i] = pq[j]; pq[j] = t;  }

    private void sink(int k) {

    }

    private void swim(int k) {

    }
}
