package Assignment5;

public class BinarySearchTree extends BinaryTree {
   // add a node in the BST
   public int add(int k){
       addNode(k);
       return k;
    }
    
    // delete a node from the BST. Implement all the three cases for deletion. 
    public int remove(int k){
        int value = deletBST(k);
        return value;
    }
    
    // search for a node with a key k. return the key if found; else throw an exeption
    public int search(int k){
       int value = search1(root, k);
        return value;
    }
}
