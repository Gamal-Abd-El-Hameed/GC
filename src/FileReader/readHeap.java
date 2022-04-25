package FileReader;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class readHeap {
    public static void read(String fileName) throws Exception {
        Scanner sc = new Scanner(new File(fileName));
        sc.useDelimiter(",");
        while (sc.hasNext())  //returns a boolean value
        {
            System.out.print(sc.next());  //find and returns the next complete token from this scanner
        }
        sc.close();  //closes the scanner
    }
}