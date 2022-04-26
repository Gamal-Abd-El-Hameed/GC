package DataLoader;

import CollectedObject.CollectedObject;

import java.io.IOException;
import java.util.List;

public interface IDataLoader {

    List<CollectedObject> extractObjectsData ();

    List<Integer> extractRoots ();

    List<List<Integer>> extractPointers ();

    void storeData(List<CollectedObject> newHeap);

}
