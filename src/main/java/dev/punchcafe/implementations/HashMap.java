package dev.punchcafe.implementations;

import java.util.*;

public class HashMap<K,V> implements Map<K,V>{

    private List<V> internalList;

    private int hashFunction(K object) {
        return object.hashCode();
    }

    private void expandListTo(int size, List<V> list){
        int requiredElements = size - list.size();
        for(int i = 0; i < requiredElements; i++){
            list.add(null);
        }
    }

    public HashMap(){
        this.internalList = new ArrayList<>();
    }

    public V get(Object key){
        return internalList.get(hashFunction((K) key));
    }

    public V put(K key, V value){
        int hashCode = hashFunction(key);
        if(internalList.size() < hashCode){
            expandListTo(hashCode, internalList);
        }
        internalList.add(hashCode, value);
        return value;
    }


    // TODO
    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
    public int size(){return 0;}
    public boolean isEmpty(){return false ;}
    public boolean containsKey(Object object){ return false ;}
    public boolean containsValue(Object object){return false ;}
}
