import java.io.FileWriter;
import java.util.List;

import org.javatuples.*;
import FileHandler.*;
import GCAlgorithms.MarkCompact;

public class Main {
    public static void main(String[] args) {
        String rootsFileName = "F:\\roots.txt";
        String heapFileName = "F:\\heap.csv";
        String pointersFileName = "F:\\pointers.csv";
        List<Unit<Integer>> rootsList = readRoots.read(rootsFileName); // null if there is an Error
        List<Pair<Integer, Integer>> pointersList = readPointers.read(pointersFileName); // null if there is an Error        
        List<Triplet<Integer, Integer, Integer>> heapList = readHeap.read(heapFileName); // null if there is an Error

        System.out.println(heapList);

        String outputFileName = "F:\\new-heap";

        boolean writeDone = fileWriter.write(outputFileName, heapList);
        if(!writeDone) System.out.println("Error! can't Writte File !\n");

        List<Triplet<Integer, Integer, Integer>> heapListOfMarkCompact = 
                            new MarkCompact().getNewHeapList(heapList, rootsList, pointersList);
        boolean markCompactFinished = fileWriter.write(outputFileName + "-MarkCompact", heapListOfMarkCompact);
        if(!markCompactFinished) System.out.println("Error! Mark Compact can't Writte File!\n");
        else System.out.println("Done\n");

    }
}
