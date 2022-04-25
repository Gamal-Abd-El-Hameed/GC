package FileReader;

import java.io.FileReader;
import org.javatuples.Pair;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class readHeap {
    public static List<Pair<Integer, Integer>> read(String fileName) {
    String line = "";    
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
        return x_values;
    } catch (FileNotFoundException e) {
        return null;
        // throw new RuntimeException(e);
    } catch (IOException e)
    {
        // e.printStackTrace();
        return null;
    }
}
}