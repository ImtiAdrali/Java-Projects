package Assignment5;

public class BinaryHeap extends BinaryTree {
   public void insert(int k){
      insertHeap(k);
   };
   public int removeMin(){
      int min = removeMin1();
      return min;
   }
   public void upheap(){
      upheap(root);
   };
   public void downheap(){
      downheap(root);
   };
   public void heapSort(){
      heapSort1();
      System.out.println(sorted);
   };
}