package FileHandler;

import java.io.FileReader;
import org.javatuples.Pair;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class readPointers {
    public static List<Pair<Integer, Integer>> read(String fileName) {
        String line = "";    
        List<Pair<Integer, Integer>> pointersList = new ArrayList<>();
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
                Pair<Integer, Integer> tmp
                        = Pair.with(intValues.get(0), intValues.get(1));
                pointersList.add(tmp);
            }
            return pointersList;
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
