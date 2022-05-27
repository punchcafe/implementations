package dev.punchcafe.implementations.index;

public class LeafNode<T extends Comparable<T>>{
    private LeafNode<T> next;
    private LeafNode<T> previous;
    private final EntryContainer<T> entryContainer;
}
