package dev.punchcafe.implementations.event;

import java.util.ArrayList;
import java.util.List;

public class BirthdaySubscriber implements Subscriber {

    private List<String> birthdayMessages= new ArrayList<>();

    @Override
    public void publishEvent(final Event event) {
        birthdayMessages.add(event.message());
    }

    public List<String> getBirthdayMessages(){
        return this.birthdayMessages;
    }
}
