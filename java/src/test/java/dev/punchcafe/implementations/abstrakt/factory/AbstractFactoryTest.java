package dev.punchcafe.implementations.abstrakt.factory;

import dev.punchcafe.implementations.abstrakt.factory.factories.FactoryProvider;
import dev.punchcafe.implementations.abstrakt.factory.key.AuthToken;
import dev.punchcafe.implementations.abstrakt.factory.key.KeyCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbstractFactoryTest {

    @Test
    void successfullyAssignsKeys(){
        FactoryProvider factoryProvider = new FactoryProvider();
        Employee remoteEmployee = new Employee(true);
        Employee onSiteEmployee = new Employee(false);

        factoryProvider.getKeyIssuer(remoteEmployee).generateKey(remoteEmployee);
        factoryProvider.getKeyIssuer(onSiteEmployee).generateKey(onSiteEmployee);

        assertTrue(remoteEmployee.getKey() instanceof AuthToken);
        assertTrue(onSiteEmployee.getKey() instanceof KeyCard);
    }
}
