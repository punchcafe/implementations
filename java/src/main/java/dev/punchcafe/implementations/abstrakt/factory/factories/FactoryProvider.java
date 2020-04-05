package dev.punchcafe.implementations.abstrakt.factory.factories;

import dev.punchcafe.implementations.abstrakt.factory.Employee;

public class FactoryProvider {

    KeyCardFactory keyCardFactory = new KeyCardFactory();
    AuthTokenFactory authTokenFactory = new AuthTokenFactory();

    public KeyFactory getKeyIssuer(Employee employee){
        if(employee.isRemoteEmployee()){
            return authTokenFactory;
        }
        return keyCardFactory;
    }

}
