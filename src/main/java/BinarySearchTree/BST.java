package BinarySearchTree;

import BinarySearchTree.TreePrinter.PrintableNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BST {

  private class Node implements Comparable<Node>, PrintableNode {
    public Node left;
    public Node right;
    private Integer val;

    public Node(Integer val) {
      this.val = val;
      this.left = null;
      this.right = null;
    }

    @Override
    public int compareTo(Node o) {
      return this.val.compareTo(o.val);
    }

    @Override
    public String toString() {
      return "Node{" +
          "val=" + val +
          '}';
    }

    /**
     * Get left child
     */
    @Override
    public PrintableNode getLeft() {
      return this.left;
    }

    /**
     * Get right child
     */
    @Override
    public PrintableNode getRight() {
      return this.right;
    }

    /**
     * Get text to be printed
     */
    @Override
    public String getText() {
      return this.val.toString();
    }
  }

  private Node root;
  private int count;

  public BST() {
    this.root = null;
    this.count = 0;
  }

  public void insert(Node node) {
    if (node == null) {
      return;
    }
    this.root = insert(node,this.root);

  }

  private Node insert(Node node, Node curr) {
    if (curr == null ) {
      count ++;
      return node;
    }
    else if (node.compareTo(curr) > 0) {
      curr.right = insert(node,curr.right);
    }
    else if (node.compareTo(curr) < 0)  {
      curr.left= insert(node, curr.left);
    }
    return curr;
  }

  public boolean search(Integer val) {
    return search(val,this.root);
  }

  private boolean search(Integer val, Node root) {
    if (root == null) {
      return false;
    }
    if (root.val == val) {
      return true;
    }
    else if (val.compareTo(root.val) > 0) {
      return search(val,root.right);
    }
    else if (val.compareTo(root.val) < 0) {
      return search(val, root.left);
    }
    return true;
  }

  public void printMinToMax() {
    Stack<Node> nodes = new Stack<>();
    Stack<Node> minNodes = new Stack<>();
    nodes.add(this.root);

    while (!nodes.isEmpty()) {
      Node curr = nodes.pop();
      minNodes.add(curr);
      if (curr.left != null) {
        nodes.add(curr.left);
      }
      if (curr.right != null) {
        nodes.add(curr.right);
      }

    }

    while(!minNodes.isEmpty()) {
      Node curr = minNodes.pop();
      System.out.println(curr);
    }
  }

  public boolean isValidBST() {
    return isValidBST(this.root);
  }

  private boolean isValidBST(Node root){
    if (root.left.val < root.val && root.right.val > root.val) {
      return true;
    }
    return isValidBST(root.left) && isValidBST(root.right);
  }

  public Node getMin(Node root) {
    Node curr = root;
    while (curr.left != null) {
      curr = curr.left;
    }
    return curr;
  }

  public Integer getMax() {
    Node curr = this.root;
    while (curr.right != null) {
      curr = curr.right;
    }
    return curr.val;
  }

  public Node deleteMin(Node root) {
    if (root.left == null) return root.right;
    root.left = deleteMin(root.left);
    return root;
  }
  // hibard deletion, 3 cases
  public Node delete(Integer val, Node node) {

    if (node == null) {
      return null;
    }

    if (val.compareTo(node.val) < 0) {
      node.left = delete(val, node.left);
    }
    else if (val.compareTo(node.val) >0) {
      node.right = delete(val, node.right);
    }
    else {
      if (node.right == null) return node.left;
      if (node.left == null) return node.right;

      Node curr = node.left;
      node = getMin(node.right);
      node.right = deleteMin(node);
      node.left = curr;
      return node;

    }
    count --;
    return node;

  }

  private int numChildren(Node node) {
    int count = 0;
    if (node.left!= null) count ++;
    if (node.right!=null) count ++;
    return count;
  }

  public int findHeight() {
    if (this.root == null) {
      return 0;
    }
    int leftHeight = 1 + findHeight(this.root.left);
    int rightHeight = 1 + findHeight(this.root.right);

    return Math.max(leftHeight, rightHeight);
  }

  private int findHeight(Node node) {
    if (node == null) {
      return 0;
    }
    return 1 + findHeight(node.left) + findHeight(node.right);
  }

//TODO implement delete

  public int count() {
    return this.count;
  }
  public static void main(String[] args) {
    BST bst = new BST();
    Node node1 = bst.new Node(5);
    Node node2 = bst.new Node(4);
    System.out.println("add 5");
    System.out.println("Add 4");
    bst.insert(node1);
    bst.insert(node2);

    System.out.println("Search 4 "+bst.search(4));
    System.out.println("Search 8 " + bst.search(8));

    System.out.println("Count should be 2 --- " + bst.count());
    bst.printMinToMax();
    bst.insert(bst.new Node(8));
    System.out.println("Add 8");
    System.out.println(bst.count);

    System.out.println("Is a valid BST " + bst.isValidBST());
    System.out.println("add 1");
    bst.insert(bst.new Node(1));
    System.out.println("Is a valid BST " + bst.isValidBST());

    System.out.println("min is "+bst.getMin(bst.root));
    System.out.println("max is "+bst.getMax());

    System.out.println("root num children is "+bst.numChildren(bst.root));

    System.out.println("Height is " + bst.findHeight());
    bst.insert(bst.new Node(7));
    System.out.println("Height doesnt change " + bst.findHeight());
    bst.insert(bst.new Node(0));
    System.out.println("Height changes " + bst.findHeight());
    TreePrinter.print(bst.root);
    System.out.println("Delete min 0 " + bst.deleteMin(bst.root));
    TreePrinter.print(bst.root);
    bst.insert(bst.new Node(9));
    TreePrinter.print(bst.root);
    bst.delete(8,bst.root);
    TreePrinter.print(bst.root);
    bst.insert(bst.new Node(10));
    bst.insert(bst.new Node(11));
    TreePrinter.print(bst.root);
    bst.delete(9,bst.root);
    TreePrinter.print(bst.root);
  }


}

