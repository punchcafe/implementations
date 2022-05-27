package dev.punchcafe.implementations.index;

import java.util.ArrayList;

public class EntryContainer<T extends Comparable<T>> {
    private final static int LEAF_NODE_ENTRY_LIMIT = 100;
    private final ArrayList<Entry<T>> internalArray = new ArrayList<>();


    public boolean isFull() {
        return internalArray.size() == LEAF_NODE_ENTRY_LIMIT;
    }
}
