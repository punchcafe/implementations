package dev.punchcafe.implementations.abstrakt.factory;

import dev.punchcafe.implementations.abstrakt.factory.key.Key;

public class Employee {
    boolean isRemoteEmployee;
    Key key;

    public Employee(boolean isRemoteEmployee){
        this.isRemoteEmployee = isRemoteEmployee;
    }

    public void setKey(Key key){
        this.key = key;
    }

    public Key getKey(){
        return this.key;
    }

    public boolean isRemoteEmployee(){
        return isRemoteEmployee;
    }
}