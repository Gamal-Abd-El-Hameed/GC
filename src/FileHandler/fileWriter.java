package FileHandler;

import org.javatuples.Triplet;
import java.io.*;
import java.util.List;

public class fileWriter {
    public static boolean write(String fileName, List<Triplet<Integer, Integer, Integer>> newHeapList) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            int n = newHeapList.size();
            for(int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                System.out.println(newHeapList.get(i).getValue(0));
                sb.append(newHeapList.get(i).getValue(0) + ",");
                sb.append(newHeapList.get(i).getValue(1) + ",");
                sb.append(newHeapList.get(i).getValue(2) + "\n");
                writer.write(sb.toString());
            }                
            return true;
      
          } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
          }
    }
}
