package GCAlgorithms.CollectedObject;

import java.util.ArrayList;
import java.util.List;

public class CollectedObject {

    public int identifier;
    public int startIndex;
    public int endIndex;
    public List<Integer> pointers;

    public CollectedObject() {
        pointers = new ArrayList<Integer>();
    }

    public CollectedObject(int identifier, int startIndex, int endIndex) {
        this.identifier = identifier;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        pointers = new ArrayList<Integer>();

    }
}
