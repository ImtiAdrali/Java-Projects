package Assignment3;
public class TestStack {
     public static void main (String[] args) {
         // Test1
        CharStack l1 = new CharStack();
        assertTrue("Test1.1", l1.toString().equals("{}") );
        assertTrue("Test1.2", l1.isEmpty()==true);
        assertTrue("Test1.3", l1.size()==0);

        // Test2
        l1.push('i');
        assertTrue("Test2.1", l1.toString().equals("{i}") );
        assertTrue("Test2.2", l1.isEmpty()==false);
        assertTrue("Test2.3", l1.size()==1);

        // Test3
        l1.push('t');
        l1.push('2');
        l1.push('0');
        l1.push('6');
        assertTrue("Test3.0", l1.toString().equals("{6,0,2,t,i}") );

        
        // Test4
        assertTrue("Test4.0", l1.top()=='6');
        assertTrue("Test4.1", l1.pop()=='6');
        assertTrue("Test4.2", l1.pop()=='0');
        assertTrue("Test4.3", l1.pop()=='2');
        assertTrue("Test4.4", l1.toString().equals("{t,i}") );
        assertTrue("Test4.5", l1.isEmpty()==false);
        assertTrue("Test4.6", l1.size()==2);
        assertTrue("Test4.7", l1.pop()=='t');
        assertTrue("Test4.8", l1.pop()=='i');
        assertTrue("Test4.9", l1.isEmpty()==true);
        assertTrue("Test4.10", l1.size()==0);

        //Test5
        boolean passed = false;
        try {
            l1.pop();
        } catch (IllegalStateException ise) {
            passed = true;
        }
        assertTrue("Test5.1", passed);

        passed = false;
        try {
            l1.top();
        } catch (IllegalStateException ise) {
            passed = true;
        }
        assertTrue("Test5.2", passed);

    }

    private static void assertTrue (String testName, boolean passed) {
        if (passed) System.out.println(testName + ":Passed");
        else System.out.println(testName + ":FAILED!"); 
    }

}
