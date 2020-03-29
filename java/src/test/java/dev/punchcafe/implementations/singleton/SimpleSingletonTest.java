package dev.punchcafe.implementations.singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleSingletonTest {
    @Test
    void singletonReturnsOneInstance(){
        assertTrue(SimpleSingleton.getSingleton() == SimpleSingleton.getSingleton());
    }
}