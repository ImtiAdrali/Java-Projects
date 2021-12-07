package Assignment3;
public class SLCharQueueTest {
    public static void main (String[] args) {

        // Test1
        SLCharQueue l1 = new SLCharQueue();
        assertTrue("Test1.1", l1.toString().equals("{}") );
        assertTrue("Test1.2", l1.isEmpty()==true);
        assertTrue("Test1.3", l1.size()==0);

        // Test2
        l1.enqueue('a');
        assertTrue("Test2.1", l1.toString().equals("{a}") );
        assertTrue("Test2.2", l1.isEmpty()==false);
        assertTrue("Test2.3", l1.size()==1);

        // Test3
        l1.enqueue('l');
        l1.enqueue('g');
        l1.enqueue('o');
        l1.enqueue('r');
        l1.enqueue('i');
        l1.enqueue('t');
        l1.enqueue('h');
        l1.enqueue('m');

        assertTrue("Test3.0", l1.toString().equals("{a,l,g,o,r,i,t,h,m}") );


        // Test4
        assertTrue("Test4.0", l1.first()=='a');
        assertTrue("Test4.1", l1.dequeue()=='a');
        assertTrue("Test4.2", l1.dequeue()=='l');
        assertTrue("Test4.3", l1.dequeue()=='g');
        assertTrue("Test4.4", l1.toString().equals("{o,r,i,t,h,m}") );
        assertTrue("Test4.5", l1.isEmpty()==false);
        assertTrue("Test4.6", l1.size()==6);

        //Test5
        boolean passed = false;
        try {
            l1.dequeue();
            passed = true;
        } catch (IllegalStateException ise) {
            passed = false;
        }
        assertTrue("Test5.1", passed);

        passed = false;
        try {
            l1.first();
            passed = true;
        } catch (IllegalStateException ise) {
            passed = false;
        }
        assertTrue("Test5.2", passed);

    }

    private static void assertTrue (String testName, boolean passed) {
        if (passed) System.out.println(testName + ":Passed");
        else System.out.println(testName + ":FAILED!");
    }

}
