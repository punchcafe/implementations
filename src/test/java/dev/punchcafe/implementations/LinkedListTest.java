/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package dev.punchcafe.implementations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    @Test void canAppendToWithList() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Hello");
        linkedList.add("world");
        linkedList.add("!");

        assertEquals( "Hello", linkedList.get(0));
        assertEquals( "world", linkedList.get(1));
        assertEquals( "!", linkedList.get(2));
    }

    @Test void canAppendToWithWhenInitialisedWithArgList() {
        LinkedList<String> linkedList = new LinkedList<>("Hello");
        linkedList.add("world");
        linkedList.add("!");

        assertEquals( "Hello", linkedList.get(0));
        assertEquals( "world", linkedList.get(1));
        assertEquals( "!", linkedList.get(2));
    }

    @Test void canRetrieveSize() {
        LinkedList<String> linkedList = new LinkedList<>("Hello");
        linkedList.add("world");
        linkedList.add("!");
        assertEquals( linkedList.size(), 3);
        LinkedList<Integer> zeroArgsInitList = new LinkedList<>();
        zeroArgsInitList.add(1);
        zeroArgsInitList.add(2);
        assertEquals( zeroArgsInitList.size(), 2);
    }

    @Test void canDeleteFromList() {
        LinkedList<String> linkedList = new LinkedList<>("Hello");
        linkedList.add("world");
        linkedList.add("!");
        assertEquals( "world", linkedList.get(1));
        linkedList.delete(1);
        assertEquals("1", linkedList.get(1));
    }

    @Test void isEmptyTest(){
        LinkedList<String> linkedList = new LinkedList<>();
        assertTrue(linkedList.isEmpty());
        linkedList.add("Hello there");
        assertFalse(linkedList.isEmpty());
    }
}
