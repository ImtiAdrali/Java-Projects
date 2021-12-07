package Assignment4;

import java.util.Comparator;

public class TestArrayList {

    public static void main(String[] args) {
    
        try {
            ArrayList myList;
            myList = new ArrayList();
            myList.add(0,3); 
            myList.add(0,1); 
            myList.add(1,2);


            try { 
                myList.add(-1,10);
                assertTrue("Test1.1",false);
            } catch (IndexOutOfBoundsException e) {
                assertTrue("Test1.1",true);
            }
            try {
                myList.add(10,10);
                assertTrue("Test1.2",false);
            } catch (IndexOutOfBoundsException e) {
                assertTrue("Test1.2",true);
            }
            try {
                myList.add(4,10);
                assertTrue("Test1.3",false);
            } catch (IndexOutOfBoundsException e) {
                assertTrue("Test1.3",true);
            }
            try {
                myList.set(3,10);
                assertTrue("Test1.4",false);
            } catch (IndexOutOfBoundsException e) {
                assertTrue("Test1.4",true);
            }
            try {
                myList.set(-1,10);
                assertTrue("Test1.5",false);
            } catch (IndexOutOfBoundsException e) {
                assertTrue("Test1.5",true);
            }
            try {
                myList.get(-1);
                assertTrue("Test1.6",false);
            } catch (IndexOutOfBoundsException e) {
                assertTrue("Test1.6",true);
            }
            try {
                myList.get(3);
                assertTrue("Test1.7",false);
            } catch (IndexOutOfBoundsException e) {
                assertTrue("Test1.7",true);
            }

           for (int idx = 3; idx < 16; idx++) {
               myList.add(0,idx);
            }
            myList.add(0,16);
            assertTrue("Test1.8a", myList.size() == 17);
            assertTrue("Test1.8b", myList.remove(0) == 16);
            assertTrue("Test1.8c", myList.size() == 16);
            myList.add(0,17);
            assertTrue("Test1.8c", myList.size() == 17);


            String s = "Before Sortng: ";
            for(int i=0; i< myList.size();i++ ){
               s += myList.get(i) + " ";
            }
            System.out.println(s);
            myList.quickSort(myList.getFirstIndex(), myList.getLastindex());
            
            s = "After Sorting: ";
            for(int i=0; i< myList.size();i++ ){
               s += myList.get(i) + " ";
            }
            System.out.println(s);



        } catch (Exception e) {
            assertTrue("Test1 -- Unexpected Exception: " + e.getMessage(),false); 
        }


        try {
            ArrayList myList2;
            myList2 = new ArrayList();

            try {
                myList2.add(0,0);
                myList2.minimize();
                assertTrue("Test2.1", myList2.size() == myList2.capacity());
            } catch (Exception e) {
                assertTrue("Test2.1 -- Unexpected Exception: " + e.getMessage(),false); 
            }
            try {
                for (int idx = 1; idx < 1024; idx++) {
                    myList2.add(0,idx);
                }
                myList2.minimize();
                assertTrue("Test2.2", myList2.size() == myList2.capacity());
            } catch (Exception e) {
                assertTrue("Test2.2 -- Unexpected Exception: " + e.getMessage(),false); 
            }
            
        } catch (Exception e) {
            assertTrue("Test4 -- Unexpected Exception: " + e.getMessage(),false); 
        }

     }   
    private static void assertTrue(String testName, boolean passed) {
        if (passed) System.out.println(testName + ": Passed!");
        else System.out.println(testName + ": FAILED FAILED FAILED"); 
    } 

}