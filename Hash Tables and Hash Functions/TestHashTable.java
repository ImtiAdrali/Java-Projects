package Assignment6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestHashTable {
    private static final String INPUT_FILE = "src\\Assignment6\\Data.csv";
    private static int numLines = 0;
    private static int nullCount = 0;
    private static String[] fix;
    private static String[] newFix;
    private static String[] search;

    public static void main(String[] args) throws Exception {

         final int CAPACITY = 37;
         HashTable HTS = new HashTable(CAPACITY);





         retrieveData();

         for (int i=0; i<fix.length; i++) {

            if (fix[i] == null) {
                continue;
            }
             String[] arr = fix[i].split(",");
            if (arr[0].equals("I")) {
                String key = arr[1];
                String value = arr[2] + "," + arr[3] + "," + arr[4];
                 HTS.tableInsert(key, value);


            }
            else if (arr[0].equals("S")) {
                String key = arr[1];
                System.out.println("Searched Entries: " + HTS.tableSearch(key));
            }
            else if (arr[0].equals("R")) {
                String key = arr[1];
                System.out.printf("Removed Entries: " + HTS.tableRemove(key));
            }
         }
        HTS.tablePrint();


    }

    private static void retrieveData() throws IOException {
        int incrementSize = 5;
        fix = new String[incrementSize];

        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
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
    }
}
