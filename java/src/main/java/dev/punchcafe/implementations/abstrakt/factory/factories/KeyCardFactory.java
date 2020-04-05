package dev.punchcafe.implementations.abstrakt.factory.factories;

import dev.punchcafe.implementations.abstrakt.factory.Employee;
import dev.punchcafe.implementations.abstrakt.factory.key.KeyCard;

public class KeyCardFactory implements KeyFactory {
    @Override
    public void generateKey(Employee employee) {
        employee.setKey(new KeyCard());
    }
}
