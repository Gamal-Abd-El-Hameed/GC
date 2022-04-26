import java.util.List;

import GCAlgorithms.CopyGC.CopyGC;
import org.javatuples.*;
import FileHandler.*;
import GCAlgorithms.MarkCompact;

public class Main {
    public static void main(String[] args) {
        String rootsFileName = "D:\\roots.txt";
        String heapFileName = "D:\\heap.csv";
        String pointersFileName = "D:\\pointers.csv";
        List<Unit<Integer>> rootsList = readRoots.read(rootsFileName); // null if there is an Error
        List<Pair<Integer, Integer>> pointersList = readPointers.read(pointersFileName); // null if there is an Error        
        List<Triplet<Integer, Integer, Integer>> heapList = readHeap.read(heapFileName); // null if there is an Error

        System.out.println(heapList);

        String outputFileName = "D:\\new-heap.csv";

        boolean writeDone = fileWriter.write(outputFileName, heapList);
        if(!writeDone) System.out.println("Error! can't Write File !\n");

        CopyGC CGC = new CopyGC(heapList, rootsList, pointersList);

        List<Triplet<Integer, Integer, Integer>> heapListOfMarkCompact = CGC.execute();
        boolean markCompactFinished = fileWriter.write(outputFileName, heapListOfMarkCompact);
        if(!markCompactFinished) System.out.println("Error! Mark Compact can't Write File!\n");
        else System.out.println("Done\n");
    }
}
