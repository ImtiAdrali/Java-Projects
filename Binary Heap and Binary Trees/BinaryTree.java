package Assignment5;




import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class BinaryTree {
    // Root of BST
    Node root;
    private int size = 0;
    int max = Integer.MAX_VALUE;
    int min = Integer.MAX_VALUE;
    String sorted = "";


    // Constructor
    BinaryTree(){
         root = null;
    }


    /* A recursive function to insert a new node in BST */
    public void addNode(int key) {
        Node newNode = new Node(key);
        if (isEmpty()) {
            root = newNode;
            size++;
        }else {
            Node focuseNode = root;
            Node parent;
            while (true) {
                parent = focuseNode;
                if (key < focuseNode.key) {
                    focuseNode = focuseNode.left;
                    if (focuseNode == null) {
                        parent.left = newNode;
                        size++;
                        return;
                    }
                }else {
                    focuseNode = focuseNode.right;
                    if (focuseNode == null) {
                        parent.right = newNode;
                        size++;
                        return;
                    }
                }
            }
        }

    }

    // find and return the minimum key of the tree
    public int findMin(Node node){

        if (isEmpty()) {
            throw new IllegalStateException("The tree is empty");
        }
        if (node.key < min) {
            min = node.key;
        }

        if (node.left != null && node.right != null) {
            findMin(node.left);
            findMin(node.right);
        }
        return min;
    }
    
    public int leftChild(Node p){
      return p.left.key;
    }
    
    public int rightChild(Node p){
      return p.right.key;
    }
    
    
    // find and return the maximum key of the tree
    public int findMax (Node node) {
        if (isEmpty()) {
            throw new IllegalStateException("The tree is empty");
        }
        if (node.key > max) {
            max = node.key;
        } else {
            max = root.key;
        }
        if (node.left != null && node.right != null) {
            findMax(node.left);
            findMax(node.right);
        }
        return max;
    }
    // Tests if the list is empty. return true if Tree is empty; else false 
    public boolean isEmpty(){
        return size == 0;
    }
    //Returns the number of elements in the tree
    public int size(){
        return size;
    } 
    
    // print tree nodes in an inorder traversal 
    public void displayInOrder(Node currentNode){
       if (isEmpty()) {
           throw new IllegalStateException("The tree is Empty");
       }

       if (currentNode != null) {
           displayInOrder(currentNode.left);
           System.out.print(currentNode.key + ",");
           displayInOrder(currentNode.right);
       }
    }
    
    public void displayPreOrder(Node currentNode){
        if (isEmpty()) {
            throw new IllegalStateException("The tree is Empty");
        }

        if (currentNode != null) {
            System.out.print(currentNode + ",");
            displayPreOrder(currentNode.left);
            displayPreOrder(currentNode.right);
        }
    }
    
    public void displayPostOrder(Node currentNode){
        if (root == null) {
            throw new IllegalStateException("The tree is Empty");
        }

        if (currentNode != null) {
            displayPostOrder(currentNode.left);
            displayPostOrder(currentNode.right);
            System.out.print(currentNode + ",");
        }
    }

    public int search1(Node node, int key) {
        boolean found = true;
        if (isEmpty()) {
            throw new IllegalStateException("The tree is Empty");
        }

        if (key == node.key) {
            found = true;
            return node.key;

        }else if (key < node.key && node.left != null) {
            found = true;
            return search1(node.left, key);
        }else if (key > node.key && node.right != null){
            found = true;
            return search1(node.right, key);
        }else {
            found = false;
            System.out.println();
            System.out.println("Could not find the value " + key);
            return -1;
        }

    }





    public int deletBST(int data) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = false;

        if (current == null) {
            return - 1;
        }

        while (current != null && current.key != data) {
            parent = current;
            if (data < current.key) {
                current = current.left;
                isLeftChild = true;
            }else {
                current = current.right;
                isLeftChild = false;
            }
        }
        if (current == null) {
            return - 1;
        }
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }else {
                if(isLeftChild) {
                    parent.left = null;
                    size--;
                }else{
                    parent.right = null;
                    size--;
                }
            }
        }else if (current.right == null) {
            if (current == root) {
                root = current.left;
            }else if (isLeftChild) {
                parent.left = current.left;
            }else {
                parent.right = current.left;
            }
        }else if (current.left == null) {
            if (current == root) {
                root = current.right;
            }else if (isLeftChild) {
                parent.left = current.right;
            }else {
                parent.right = current.right;
            }
        }else {
            Node successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            }else if (isLeftChild) {
                parent.left = successor;
            }else parent.right = successor;
            successor.left = current.left;
        }


        return current.key;

    }

    public Node getSuccessor(Node node) {
        Node parentOfSuccessor = node;
        Node sussessor = node;
        Node current  = node.right;
        while (current != null) {
            parentOfSuccessor = sussessor;
            sussessor = current;
            current = current.left;
        }
        if (sussessor != node.right) {
            parentOfSuccessor.left = sussessor.right;
            sussessor.right = node.right;
        }
        return sussessor;
    }








// Heap implementaiton for the Binary Heap

    public void insertHeap(int key) {

        Node newNode = new Node(key);
        if (root == null) {
            root = newNode;
            size++;
            return;
        }
        Node lastaddress = lastAddress();
        if (lastaddress.left == null) {
            lastaddress.left = newNode;
            newNode.parent  = lastaddress;
            size++;
        }else {
            lastaddress.right = newNode;
            newNode.parent = lastaddress;
            size++;
        }
        upheap(newNode);
    }

    public Node lastAddress() {
        Queue q = new LinkedList();
        q.add(root);
        Node temp = null;
        while (!q.isEmpty()) {
            temp = (Node) q.peek();
            q.poll();
            if (temp.left != null && temp.right != null) {
                q.add(temp.left);
                q.add(temp.right);
            }else  {
                break;
            }
        }
        return temp;
    }

    public void upheap(Node node) {
        int data;
        if (node.parent == null){
            return;
        }
        if (node.parent.key > node.key) {

            data = node.key;
            node.key = node.parent.key;
            node.parent.key = data;
            upheap(node.parent);
        }
    }




    public void downheap(Node node) {
        int temp;
        Node minNode = node;

        if (isEmpty()) {
            throw new IllegalStateException("The heap is empty");
        }

        if (node.left != null && node.left.key < minNode.key ) {
            minNode = node.left;
        }

        if (node.right != null && node.right.key < minNode.key) {
            minNode = node.right;
        }

        if (minNode != node) {
            temp = minNode.key;
            minNode.key = node.key;
            node.key = temp;
            downheap(minNode);
        }
    }

    public int removeMin1() {
        if (isEmpty()) {
            throw new IllegalStateException("The heap is empty");
        }
        Node lastNode = getLastNode();
        if (lastNode == root) {
            root = null;
        }
        int temp = lastNode.key;
        lastNode.key = root.key;
        root.key = temp;

        Node lastParent = lastNode.parent;
        if (lastParent.left == lastNode) {
            lastParent.left = null;
        }else {
            lastParent.right = null;
        }
        downheap(root);
        return lastNode.key;
    }

    public Node getLastNode() {
        Queue q = new LinkedList();
        q.add(root);
        Node last = null;
        while (!q.isEmpty()) {
            last = (Node) q.peek();
            q.remove();
            if (last.left != null) {
                q.add(last.left);
            }
            if (last.right != null) {
                q.add(last.right);
            }
        }
        return last;
    }

    void heapSort1() {

        int temp;

        if (isEmpty()) {
            throw new IllegalStateException("The tree is Empty");
        }
        Node rootNode = root;
        Node lastNode = getLastNode();

        temp = rootNode.key;
        rootNode.key = lastNode.key;
        lastNode.key = temp;



        Node lastParent = lastNode.parent;
        if (lastParent != null) {
            if (lastParent.left == lastNode) {
                lastParent.left = null;
            } else {
                lastParent.right = null;
            }
        }
        sorted += lastNode.key + ",";
        downheap(rootNode);


        if (lastNode.key != rootNode.key) {
            heapSort1();
        }


    }


    private class Node {
        private int key;
        private Node parent;
        private Node left;
        private Node right;


        Node(int data) {
            this.key = data;
            left = right = null;
        }

        public String toString() {
            return key + "";
        }
    }
}
