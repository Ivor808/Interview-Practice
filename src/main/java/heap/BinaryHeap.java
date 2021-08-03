package heap;

import java.util.Arrays;

public class BinaryHeap<Key extends Comparable<Key>> {


    private final Key[] pq;
    private Integer count;

    public BinaryHeap(Integer maxN) {
        pq = (Key[]) new Comparable[maxN+1];
        count = 0;
    }

    public void insert(Key v) {

        pq[++count] = v;
        swim(count);

    }

    public Key max() {
        return pq[1];
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, count--);
        sink(1);
        //forgot to include this to prevent loitering
        pq[count+1] = null;
        return max;

    }

    public boolean isEmpty() {
        return count ==0;

    }

    public int size() {
        return count;
    }

    private boolean  less(int i, int j) {  return pq[i].compareTo(pq[j]) < 0;  }

    private void  exch(int i, int j) {  Key t = pq[i]; pq[i] = pq[j]; pq[j] = t;  }

    private void sink(int k) {
        //while we arent at end of array
        while (2*k <= count) {
            // set j to a child of k
            int j = 2*k;
            // get the largest of the two children to maintain heap order
            if (j < count && less(j, j+1)) j ++;
            //if k is not less than the biggest of the two children, break
            if (!less(k, j)) break;
            exch(k, j);
            k = j;

        }


    }

    private void swim(int k) {
        // check were not at root and child is greater than parent
        while (k > 1 && less(k/2, k)) {
            // exchange child and parent
            exch(k/2, k);
            // set new key to look at to parent
            k = k/2;
        }

    }

    public void heapify(Key[] arr) {
        for (int i = count /2; i > 0; i--) {
            sink(i);
        }
    }

    public void delete(int k) {
        exch(k,count--);
        pq[count+1] = null;
        Key child = pq[2*k];
        if (pq[k].compareTo(pq[k/2]) > 0) swim(k);
        else {
            sink(k);
        }
    }

    public Key[] heapSort() {
        Key[] arr = (Key[]) new Comparable[count];
        int tmp = count;
        for (int i = 0; i < tmp; i ++) {
            arr[i] = delMax();

        }
        return arr;
    }

    public boolean validHeap() {
        for (int i = 1; i <= count/2 -1; i ++) {
            if (pq[i].compareTo(pq[i*2]) < 0 || pq[i].compareTo(pq[i*2 + 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BinaryHeap heap = new BinaryHeap(15);
        heap.insert(5);
        heap.insert(54);
        heap.insert(3);
        heap.insert(8);
        heap.insert(9);
        heap.insert(15);
        heap.insert(2);
        System.out.println("count is: " + heap.count);
        System.out.println(Arrays.toString(heap.pq));
        heap.delete(3);
        System.out.println(Arrays.toString(heap.pq));
        System.out.println("set last element to be large manually so not valid heap " + heap.validHeap());
        System.out.println(Arrays.toString(heap.pq));
        heap.delete(5);
        System.out.println(heap.validHeap());
        System.out.println(Arrays.toString(heap.pq));
        heap.pq[6]= 50;
        heap.count ++;
        System.out.println(Arrays.toString(heap.pq));
        System.out.println(heap.validHeap());


    }
}
