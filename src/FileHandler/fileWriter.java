package FileHandler;

import org.javatuples.Triplet;
import java.io.*;
import java.util.List;

public class fileWriter {
    public static boolean write(String fileName, List<Triplet<Integer, Integer, Integer>> newHeapList) {
        try  {
            BufferedWriter newExcelFileWriter = new BufferedWriter(new FileWriter(fileName));
            int n = newHeapList.size();
            for (Triplet<Integer, Integer, Integer> heapElement : newHeapList) {
                StringBuilder sb = new StringBuilder();
                System.out.println(heapElement.getValue(0));
                sb.append(heapElement.getValue(0) + ",");
                sb.append(heapElement.getValue(1) + ",");
                sb.append(heapElement.getValue(2) + "\n");
                newExcelFileWriter.write(sb.toString());
            }
            newExcelFileWriter.close();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
