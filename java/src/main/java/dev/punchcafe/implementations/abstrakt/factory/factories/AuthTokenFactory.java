package dev.punchcafe.implementations.abstrakt.factory.factories;

import dev.punchcafe.implementations.abstrakt.factory.Employee;
import dev.punchcafe.implementations.abstrakt.factory.key.AuthToken;

public class AuthTokenFactory implements KeyFactory {
    @Override
    public void generateKey(Employee employee) {
        employee.setKey(new AuthToken());
    }
}
