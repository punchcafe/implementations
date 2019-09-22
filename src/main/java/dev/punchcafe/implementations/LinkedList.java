package dev.punchcafe.implementations;

import java.util.List;

class LinkedList<T> /*implements List<T>*/ {
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

    public void add(T element) {
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

    }

    public void delete(int index){
        //need to check this is garbage collected
        ListElement<T> target = this.head;
        ListElement<T> before = null;
        ListElement<T> after = null;
        for (int i = 1; i <= index; i++) {
            if( i == index -1 ){
                //this will probably break because it refferences the refference?
                before = target;
            }else if(i == index){
                after = target.next();
            }
            target = target.next();
        }
        before.setNext(after);


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
