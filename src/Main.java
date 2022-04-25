import java.util.List;

import org.javatuples.*;
import FileHandler.*;

public class Main {
    public static void main(String[] args) {
        String fileName = "F:\\heap.csv";
        List<Unit<Integer>> rootsList = readRoots.read(fileName); // null if there is an Error
        List<Pair<Integer, Integer>> pointersList = readPointers.read(fileName); // null if there is an Error        
        List<Triplet<Integer, Integer, Integer>> heapList = readHeap.read(fileName); // null if there is an Error
        System.out.println(rootsList);
        String outputFileName = "F:\\new-heap";
        boolean writeDone = fileWriter.write(outputFileName, heapList);
        if(!writeDone) System.out.println("Error! can't Writte File !\n");
    }
}
