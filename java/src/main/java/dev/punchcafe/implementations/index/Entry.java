package dev.punchcafe.implementations.index;

public class Entry<T extends Comparable<T>> {
    private final T value;
    private final T id;

    public Entry(T value, T id){
        this.value = value;
        this.id = id;
    }
    public T getValue(){
        return this.value;
    }

    public T getId(){
        return this.id;
    }
}
