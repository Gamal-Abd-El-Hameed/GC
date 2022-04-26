package GCAlgorithms.MarkSweep;

import org.javatuples.Triplet;

import java.util.List;

public interface IMarkSweep {
    List<Triplet<Integer, Integer, Integer>> execute();
    void markPhase();
    void sweepPhase();

    // boolean writeResultsToFile(List<Triplet<Integer, Integer, Integer>> heapList,
    //                             List<Unit<Integer>> rootsList,
    //                             List<Pair<Integer, Integer>> pointersList);
}