package CopyGC;

import CollectedObject.CollectedObject;

import java.util.List;

public interface ICopyGC {


    void initializeGarbageCollector();

    List<CollectedObject> execute();

    int moveToNewHeap( int collectedObjectIdentifier );


}
