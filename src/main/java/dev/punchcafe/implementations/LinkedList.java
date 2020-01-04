package dev.punchcafe.implementations;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

class LinkedList<T> implements List<T> {
    private int length;
    private ListElement<T> head;
    //recursion would be prettier but list length would be restricted to recursion depth.

    LinkedList(T headElement) {
        this.head = new ListElement<>(headElement);
        this.length = 1;
    }

    LinkedList() {
        this.length = 0;
    }

    public boolean add(T element) {
        if (length == 0) {
            this.head = new ListElement<>(element);
        } else {
            ListElement<T> target = this.head;
            for (int i = 0; i < length-1; i++) {
                target = target.next();
            }
            target.setNext(new ListElement<>(element));
        }
        this.length = this.length + 1;
        return true;

    }

    public void delete(int index){
        //need to check this is garbage collected
        if(this.length <= 2){

        }
        ListElement<T> iteratedElement = this.head;
        ListElement<T> before = null;
        ListElement<T> after = null;
        for (int i = 0; i <= index; i++) {
            if(i == index-1){
                before = iteratedElement;
            } else if (i == index){
                after = iteratedElement.next();
                iteratedElement = null;
                break;
            }
            iteratedElement = iteratedElement.next();
        }
        before.setNext(after);
        this.length = this.length - 1;
    }

    public T get(int index) {
        ListElement<T> target = this.head;
        try {
            for (int i = 0; i < index; i++) {
                target = target.next();
            }
        } catch (NullPointerException ex) {
            System.out.println("Index out of list range");
        }
        return target.getElement();
    }



    public int size() {
        return length;
    }

    public boolean isEmpty(){
        return !(length > 0);
    }

    public boolean contains(Object object){
        ListElement<T> target = this.head;
        for (int i = 0; i < this.length ; i++){
            if(object.equals(target.getElement())) return true;
            target = target.next();
        }
        return false;
    }

    //TODO

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }
}

class ListElement<T> {
    private T element;
    private ListElement<T> next;

    ListElement(T element) {
        this.element = element;
    }

    ListElement() {
    }

    ListElement<T> next() {
        return next;
    }

    T getElement() {
        return element;
    }

    void setNext(ListElement<T> listElement) {
        this.next = listElement;
    }
}
