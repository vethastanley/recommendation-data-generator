package com.bits.dissertation.generators;

import com.bits.dissertation.data.DataSource;
import com.bits.dissertation.es.ESManager;
import com.bits.dissertation.entity.ProfileEvent;
import com.bits.dissertation.entity.UserProfileEvent;
import com.bits.dissertation.es.ESUrlBuilder;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class RatingEventGenerator implements Runnable {

    private String user;

    private ESManager<UserProfileEvent> esManager;

    private List<String> apis;

    public RatingEventGenerator(String user, List<String> apis) {
        this.user = user;
        this.esManager = new ESManager<>(ESUrlBuilder.USER_PROFILE_EVENT_URL);
        this.apis = apis;
    }

    @Override
    public void run() {
        System.out.println("Started to generate event for the type "+ ProfileEvent.RATE + " for the user " + user);
        Set<Integer> indexes = new HashSet<>();
        Random random = new Random();
        int limit = Math.round(Math.round(apis.size() * 0.3));
        IntStream.range(0, limit).forEach(i -> {
            int index = -1;
            do {
                index = random.nextInt(apis.size());
            } while (indexes.contains(index));

            indexes.add(index);
            final int apiIndex = index;

            int rating = -1;
            do {
                rating = random.nextInt(5);
            } while (rating == 0);

            UserProfileEvent event = new UserProfileEvent();
            event.setAction(ProfileEvent.RATE);
            event.setActionValue(rating);
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
                    } catch (InterruptedException e1) {
                        System.err.println("Unable to make the thread sleep");
                    }
                }
            }
        });
        System.out.println("Completed to generate event for the type "+ ProfileEvent.RATE + " for the user " + user);
    }
}
