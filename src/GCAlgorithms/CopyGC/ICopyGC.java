package CopyGC;

import CollectedObject.CollectedObject;

import java.io.IOException;
import java.util.List;

public interface ICopyGC {


    void initializeGarbageCollector() throws IOException;

    void execute() throws IOException;

    int moveToNewHeap( int collectedObjectIdentifier );


}
