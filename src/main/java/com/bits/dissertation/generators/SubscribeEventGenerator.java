package com.bits.dissertation.generators;


import com.bits.dissertation.data.DataSource;
import com.bits.dissertation.es.ESManager;
import com.bits.dissertation.entity.ProfileEvent;
import com.bits.dissertation.entity.UserProfileEvent;
import com.bits.dissertation.es.ESUrlBuilder;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class SubscribeEventGenerator implements Runnable {

    private String user;

    private ESManager<UserProfileEvent> esManager;

    private List<String> apis;

    public SubscribeEventGenerator(String user, List<String> apis) {
        this.user = user;
        this.esManager = new ESManager<>(ESUrlBuilder.USER_PROFILE_EVENT_URL);
        this.apis = apis;
    }

    @Override
    public void run() {
        System.out.println("Started to generate event for the type "+ ProfileEvent.SUBSCRIBE + " for the user " + user);
        Set<Integer> indexes = new HashSet<>();
        Random random = new Random();
        int limit = Math.round(Math.round(apis.size() * 0.4));
        IntStream.range(0, limit).forEach(i -> {
            int index = -1;
            do {
                index = random.nextInt(apis.size());
            } while (indexes.contains(index));

            indexes.add(index);
            int apiIndex = index;

            UserProfileEvent event = new UserProfileEvent();
            event.setAction(ProfileEvent.SUBSCRIBE);
            event.setActionValue(1);
            event.setActor(user);
            event.setApi(apis.get(apiIndex));
            event.setTime(new Date());
            boolean persisted = false;
            while (!persisted) {
                try {
                    esManager.persist(event);
                    persisted = true;
                } catch (IOException e) {
                    System.err.println("Error publishing event " + event.toString());
                    try {
                        Thread.sleep(3000);
                        System.out.println("Retrying to publish the event " + event.toString());
                    } catch (InterruptedException e1) {
                        System.err.println("Unable to make the thread sleep");
                    }
                }
            }
        });
        System.out.println("Started to generate event for the type "+ ProfileEvent.SUBSCRIBE + " for the user " + user);
    }
}
