package com.bits.dissertation.generators;

import com.bits.dissertation.data.DataSource;
import com.bits.dissertation.es.ESManager;
import com.bits.dissertation.entity.ProfileEvent;
import com.bits.dissertation.entity.UserProfileEvent;
import com.bits.dissertation.es.ESUrlBuilder;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class NonValueEventGenerator implements Runnable {

    private ProfileEvent type;

    private String user;

    private List<String> apis;

    private double sample;

    private int mean;

    private int sd;

    private ESManager<UserProfileEvent> esManager;

    public NonValueEventGenerator(ProfileEvent type, String user, List<String> apis, int mean, int sd, double sample) {
        this.type = type;
        this.user = user;
        this.apis = apis;
        this.mean = mean;
        this.sd = sd;
        this.sample = sample;
        this.esManager = new ESManager<>(ESUrlBuilder.USER_PROFILE_EVENT_URL);
    }

    @Override
    public void run() {
        System.out.println("Started to generate event for the type "+ type + " for the user " + user);
        Set<Integer> indexes = new HashSet<>();
        Random random = new Random();
        int limit = Math.round(Math.round(apis.size() * sample));
        IntStream.range(0, limit).forEach(i -> {
            int index = -1;
            do {
                index = random.nextInt(apis.size());
            } while (indexes.contains(index));

            indexes.add(index);
            final int apiIndex = index;

            int gaussian = -1;

            do {
                gaussian = Math.round(Math.round((random.nextGaussian() * sd) + mean));
            } while (gaussian <= 0);

            IntStream.range(0, gaussian).forEach(j -> {
                UserProfileEvent event = new UserProfileEvent();
                event.setAction(type);
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
                        } catch (InterruptedException e1) {
                            System.err.println("Unable to make the thread sleep");
                        }
                    }
                }
            });
        });
        System.out.println("Completed to generate event for the type "+ type + " for the user " + user);
    }
}
