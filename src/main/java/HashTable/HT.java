package HashTable;

public class HT<K, V> {

  private class Node<K, V> {

    private K key;
    private V value;

    public Node(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }

    @Override
    public int hashCode() {
      return this.key.hashCode();
    }

    @Override
    public String toString() {
      return key.toString();
    }


    public boolean equals(Node node) {
      return value.equals(node.value) && key.equals(node.key);
    }
  }

  private Node[] htTable;
  private static final Integer INITIALSIZE = 5;
  private int count;

  public HT() {
    htTable = new Node[INITIALSIZE];
    count = 0;
  }

  public HT(int cap) {
    count = 0;
    htTable = new Node[cap];
  }

  private Integer hash(Node o) {
    return o.hashCode() % this.count;
  }

  public void add(K key, V value) {

    Node<K, V> toAdd = new Node<K, V>(key, value);
    if (htTable.length == 0) {
      htTable = new Node[INITIALSIZE];
    }
    Integer hash = getPos(toAdd);
    if (htTable[hash] == null) {
      htTable[hash] = toAdd;

    } else if (htTable[hash].equals(toAdd)) {
      htTable[hash].value = value;
    } else {
      int curr = hash + 1;
      if (curr >= htTable.length) {
        grow();
      }
      while (htTable[curr] != null) {
        curr++;
        if (curr >= htTable.length) {
          grow();
        }
      }
      htTable[curr] = toAdd;
      count++;
    }

  }

  private void resize(int cap) {
    HT t = new HT(cap);
    for (int i = 0; i < htTable.length; i++) {
      if (htTable[i] != null) {
        t.add(htTable[i].key, htTable[i].value);
      }
    }
    htTable = t.htTable;
  }

  public void shrink() {
    int cap = htTable.length / 2;
    resize(cap);
  }

  public void grow() {
    int cap = htTable.length * 2;
    resize(cap);
  }

  private int getPos(Node node) {
    if (htTable.length <= 0) {
      return 0;
    }
    return node.hashCode() % htTable.length;
  }

  public boolean exists(K key) {
    Node tmp = new Node(key, 5);
    int pos = getPos(tmp);
    if (htTable[pos] == null) {
      return false;
    } else if (htTable[pos].key.equals(key)) {
      return true;
    } else {
      int curr = pos;
      // position is filled so linear probe to find if it exists.
      while (curr < htTable.length && htTable[curr] != null) {
        if (htTable[curr].key.equals(key)) {
          return true;
        }
        curr++;
      }
      return false;
    }

  }

  public boolean delete(K key) {
    if (htTable == null || htTable.length == 0) {
      return false;
    }
    int pos = getPos(new Node(key, 5));

    if (htTable[pos] == null) {
      return false;
    }
    int curr = pos;
    while (curr < htTable.length && htTable[curr] != null) {
      if (htTable[curr].key.equals(key)) {
        htTable[curr] = null;
        count--;
        curr++;
        // have to reput the cluster
        while (curr < htTable.length && htTable[curr] != null) {
          Node<K, V> tmp = htTable[curr];
          htTable[curr] = null;
          add(tmp.key, tmp.value);
          curr++;
        }
        if (count < htTable.length / 2) {
          shrink();
        }
        return true;
      }

      curr++;
    }
    return false;
  }

  public void printArr() {
    for (int i = 0; i < htTable.length; i++) {
      System.out.print(htTable[i] + " ");
    }
    System.out.println("");
  }

  public static void main(String[] args) {
    HT ht = new HT();
    ht.add(5, 5);
    ht.add(5, 5);
    ht.add(6, 3);

    ht.add(2, 5);
    ht.add(3, 8);

    ht.add(18, 4);
    ht.printArr();
    System.out.println("Does 5 exist? true=" + ht.exists(5));
    System.out.println("Does 800 exist? false=" + ht.exists(800));
    System.out.println("Delete 18 " + ht.delete(18));
    ht.printArr();
    ht.delete(2);
    ht.delete(3);
    ht.printArr();
    ht.delete(5);
    ht.delete(6);
    ht.printArr();
    System.out.println("Can we delete nothing false=" + ht.delete(5));
    ht.add(4,3);
    ht.add(5,8);
    ht.printArr();
  }

  /*
   * Mistakes made
   * Not resizing correctly - must rehash every item
   * Do out of bounds checks FIRST
   *
   * */


}
