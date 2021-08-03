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
  }

  private Node[] htTable;
  private static final Integer INITIALSIZE = 5;
  private int count;

  public HT() {
    htTable = new Node[INITIALSIZE];
    count = 0;
  }

  private Integer hash(Node o) {
    return o.hashCode() % this.count;
  }

  public void add(K key, V value) {

    Node<K, V> toAdd = new Node<K, V>(key, value);
    Integer hash = toAdd.hashCode() % htTable.length;

    if (htTable[hash] == null) {
      htTable[hash] = toAdd;

    } else {
      int curr = hash + 1;
      if (curr >= htTable.length) {
        resize(htTable);
      }
      while (htTable[curr] != null) {
        curr++;
        if (curr >= htTable.length) {
          resize(htTable);
        }
      }
      htTable[curr] = toAdd;
    }

  }

  private void resize(Node[] a) {
    Node[] tmp = new Node[htTable.length * 2];

    for (int i = 0; i < htTable.length; i++) {
      tmp[i] = htTable[i];
    }
    htTable = tmp;
  }

  public boolean exists(K key) {

  }

  public void printArr() {
    for (int i = 0; i < htTable.length; i++) {
      System.out.println(htTable[i]);
    }
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
  }


}
