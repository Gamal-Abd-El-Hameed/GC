package FileHandler;

import java.io.FileReader;
import org.javatuples.Unit;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class readRoots {
    public static List<Unit<Integer>> read(String fileName) {
        String line = "";    
        List<Unit<Integer>> rootsList = new ArrayList<>();
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
                Unit<Integer> tmp
                        = Unit.with(intValues.get(0));
                rootsList.add(tmp);
            }
            return rootsList;
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