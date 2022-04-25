
// Below is a Java program to create
// a Unit tuple from Constructor

import org.javatuples.Pair;
import org.javatuples.Unit;
import org.javatuples.Decade;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
//    public static void main(String[] args)
//    {
//        Unit<String> unit = new Unit<String>("GeeksforGeeks");
//
//        System.out.println(unit);
//    }
//    public static void main(String[] args) throws Exception {
//
//        Scanner sc = new Scanner(new File(fileName));
//        sc.useDelimiter(",");
//        while (sc.hasNext())  //returns a boolean value
//        {
//            System.out.print(sc.next());  //find and returns the next complete token from this scanner
//        }
//        sc.close();  //closes the scanner
//    }
public static void main(String[] args)
{
    String line = "";
    String fileName = "F:\\heap.csv";
    List<Pair<Integer, Integer>> x_values = new ArrayList<>();
    try
    {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        while ((line = br.readLine()) != null)   //returns a Boolean value
        {
            String[] values = line.split(",");
            List<Integer> intValues = Arrays.asList(values).stream()
                    .map(Double::parseDouble) // parse x.xxE+02 to xxx.0
                    .map(Double::intValue) // xxx.0 to integer xxx
                    .collect(Collectors.toList()); // back to List
            Pair<Integer, Integer> tmp
                    = Pair.with(intValues.get(0) + 3, intValues.get(1) + 3);
            x_values.add(tmp);
//            System.out.println("Employee [First Name=" + employee[0] + ", Last Name=" + employee[1] + ", Designation=" + employee[2] + "]");
        }
        System.out.println(x_values);
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    } catch (IOException e)
    {
        e.printStackTrace();
    }
}
}
