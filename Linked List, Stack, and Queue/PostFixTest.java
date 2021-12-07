package Assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class PostFixTest {

    private static final String INPUT_FILE = "src\\Assignment3\\postfix.txt";
    private static String[] fix;
    private static String[] newFix;
    private static int numLines = 0;

    public static void main(String[] args) throws IOException {
        StackUsingDLL post = new StackUsingDLL();
        retrieveData();
        for (int i=0; i<newFix.length; i++) {
            System.out.println((i + 1) + ": " +post.postFix(newFix[i]));
       }

    }
    private static void retrieveData() throws IOException {
        int incrementSize = 5;
        fix = new String[incrementSize];

        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                    line.trim();
                    fix[numLines] = line;
                    numLines++;
                    if (numLines == fix.length) {
                        String[] tmpData = new String[fix.length + incrementSize];
                        for (int i = 0; i < fix.length; i++) {
                            tmpData[i] = fix[i];
                        }
                        fix = tmpData;
                    }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + "");
            System.out.println(e.getMessage());
        }
        fixNullRemove(fix);
    }

    private static void fixNullRemove(String[] fix) {
        newFix = new String[fix.length - 3];
        for (int i=0; i<newFix.length; i++) {
            if (fix[i] == null) {
                continue;
            }else {
                newFix[i] = fix[i];
            }
        }
    }


}
