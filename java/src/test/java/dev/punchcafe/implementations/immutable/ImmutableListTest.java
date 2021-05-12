package dev.punchcafe.implementations.immutable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImmutableListTest {


    //TODO: consider different Immutable List implementations, and bench marking tests
    @Test
    void shouldCreateImmutableListAndRetrieveElements(){
        final var initalList = ImmutableList.of("Hello", "World");
        assertEquals(initalList.get(0), "Hello");
        assertEquals(initalList.get(1), "World");
    }

    @Test
    void shouldConcatenateImmutableListAndRetrieveElements(){
        final var initalList = ImmutableList.of("Hello", "World");
        final var concatenatedList = initalList.concatenate("I'm", "here!");
        assertEquals(concatenatedList.get(0), "Hello");
        assertEquals(concatenatedList.get(1), "World");
        assertEquals(concatenatedList.get(2), "I'm");
        assertEquals(concatenatedList.get(3), "here!");
    }

    @Test
    void shouldPrependImmutableListAndRetrieveElements(){
        final var initalList = ImmutableList.of("Hello", "World");
        final var prependedList = initalList.prepend("um...", "ahem!");
        assertEquals(prependedList.get(0), "um...");
        assertEquals(prependedList.get(1), "ahem!");
        assertEquals(prependedList.get(2), "Hello");
        assertEquals(prependedList.get(3), "World");
    }
}
