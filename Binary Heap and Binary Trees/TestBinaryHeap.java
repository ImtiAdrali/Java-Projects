package Assignment5;

public class TestBinaryHeap {
    public static void main(String args[]) {       
        ///Test 1
        BinaryHeap heap1 = new BinaryHeap();
        try{
            heap1.insert(4);
            heap1.insert(7);
            heap1.insert(8);
            heap1.insert(18);
            heap1.insert(3);
            heap1.insert(6);
            heap1.insert(9);
            heap1.insert(2);
            heap1.insert(5);
            heap1.insert(12);
            heap1.insert(10);
            heap1.insert(1);
            heap1.insert(15);
            heap1.insert(16);
            heap1.insert(20);
        } catch (IllegalStateException e) {
            System.out.println(e);
        }
        System.out.println("Size of tree: " + heap1.size());
        System.out.print("Inorder Traversal: ");
        heap1.displayInOrder(heap1.root);
        System.out.println();
        System.out.print("Minimum of the tree: " + heap1.findMin(heap1.root) + "\n");
        System.out.print("Maximum of the tree: " + heap1.findMax(heap1.root) + "\n");
        assertTrue("Test1: remove minimum: ", heap1.removeMin()==1);
        System.out.print("Inorder Traversal: ");
        heap1.displayInOrder(heap1.root);
        System.out.println();
        System.out.print("Heap Sort: ");
        heap1.heapSort();
      }  
        
    private static void assertTrue(String testName, boolean passed) {
        if (passed) System.out.println(testName + ": Passed!");
        else System.out.println(testName + "FAILED"); 
    }
}
