package GCAlgorithms.MarkCompact;

import java.util.List;
import org.javatuples.*;

public interface IMarkCompactGC {
    List<Triplet<Integer, Integer, Integer>> execute();
    void markPhase();
    void compactPhase();

    // boolean writeResultsToFile(List<Triplet<Integer, Integer, Integer>> heapList,
    //                             List<Unit<Integer>> rootsList,
    //                             List<Pair<Integer, Integer>> pointersList);
}
