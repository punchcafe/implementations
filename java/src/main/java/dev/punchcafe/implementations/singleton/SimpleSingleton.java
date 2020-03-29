package dev.punchcafe.implementations.singleton;

public class SimpleSingleton {

    static SimpleSingleton singleton = new SimpleSingleton();

    private SimpleSingleton(){}

    static public SimpleSingleton getSingleton(){
        return singleton;
    }
}