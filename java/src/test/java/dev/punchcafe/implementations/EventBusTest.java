/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package dev.punchcafe.implementations;

import dev.punchcafe.implementations.event.Birthday;
import dev.punchcafe.implementations.event.BirthdaySubscriber;
import dev.punchcafe.implementations.event.Event;
import dev.punchcafe.implementations.event.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventBusTest {

    private EventBus bus;

    @BeforeEach
    void beforeEach() {
        bus = new EventBus();
    }

    @Test
    void birthdayListenerRecordsMessages(){
        final BirthdaySubscriber birthdaySubscriber = new BirthdaySubscriber();
        bus.registerSubscriber(birthdaySubscriber);
        final Event birthdayEvent = new Birthday();
        bus.publishEvent(birthdayEvent);
        assertEquals(birthdaySubscriber.getBirthdayMessages(), List.of("It's your birthday!"));
    }

}
