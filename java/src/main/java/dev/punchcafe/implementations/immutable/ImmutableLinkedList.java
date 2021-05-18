package dev.punchcafe.implementations.immutable;

import java.util.Optional;

public final class ImmutableLinkedList<T> {
    public static <T> ImmutableLinkedList<T> of(){
        return new ImmutableLinkedList<>(null, null);
    }

    public static <T> ImmutableLinkedList<T> of(T... elements){
        if(elements.length == 0){
            return ImmutableLinkedList.of();
        }
        ImmutableLinkedList<T> tail = null;
        for(int i = elements.length - 1; i > 0; i--){
            tail = new ImmutableLinkedList<>(elements[i], tail);
        }
        return new ImmutableLinkedList<>(elements[0], tail);
    }

    private T head;
    private ImmutableLinkedList<T> tail;

    private ImmutableLinkedList(final T head, final ImmutableLinkedList<T> tail){
        this.head = head;
        this.tail = tail;
    }

    public T head(){
        return this.head;
    }

    public Optional<ImmutableLinkedList<T>> tail(){
        return Optional.ofNullable(this.tail);
    }

    public boolean isEmpty(){
        return this.head == null;
    }

    public ImmutableLinkedList<T> reverse(){
        ImmutableLinkedList<T> end = new ImmutableLinkedList<>(this.head, null);
        var nextTail = this.tail;
        while (nextTail != null){
            end = new ImmutableLinkedList<>(nextTail.head, end);
            nextTail = nextTail.tail;
        }
        return end;
    }
}
