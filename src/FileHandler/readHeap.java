package FileHandler;

import java.io.FileReader;
import org.javatuples.Triplet;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class readHeap {
    public static List<Triplet<Integer, Integer, Integer>> read(String fileName) {
        String line = "";    
        List<Triplet<Integer, Integer, Integer>> heapList = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] values = line.split(",");
                List<Integer> intValues = Arrays.asList(values).stream()
                        .map(Double::parseDouble)
                        .map(Double::intValue)
                        .collect(Collectors.toList());
                Triplet<Integer, Integer, Integer> tmp
                        = Triplet.with(intValues.get(0), intValues.get(1), intValues.get(2));
                heapList.add(tmp);
            }
            return heapList;
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!!\n");
            return null;
            // throw new RuntimeException(e);
        } catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}