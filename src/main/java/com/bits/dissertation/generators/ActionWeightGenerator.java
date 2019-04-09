package com.bits.dissertation.generators;

import com.bits.dissertation.entity.ActionWeightage;
import com.bits.dissertation.entity.ProfileEvent;
import com.bits.dissertation.es.ESManager;
import com.bits.dissertation.es.ESUrlBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActionWeightGenerator {

    public static ActionWeightGenerator INSTANCE = new ActionWeightGenerator();

    private ActionWeightGenerator() {}

    public void generate() {
        List<ActionWeightage> actionWeightages = new ArrayList<>();
        actionWeightages.add(new ActionWeightage(ProfileEvent.RATE, 10, true));
        actionWeightages.add(new ActionWeightage(ProfileEvent.ACCESS_TOKEN, 25, true));
        actionWeightages.add(new ActionWeightage(ProfileEvent.TRYOUT, 3, true));
        actionWeightages.add(new ActionWeightage(ProfileEvent.SUBSCRIBE, 5, true));
        actionWeightages.add(new ActionWeightage(ProfileEvent.POST, 2, true));
        actionWeightages.add(new ActionWeightage(ProfileEvent.COMMENT, 2, true));
        actionWeightages.add(new ActionWeightage(ProfileEvent.LIKE, 1, true));
        actionWeightages.add(new ActionWeightage(ProfileEvent.VISIT, 1, true));
        actionWeightages.add(new ActionWeightage(ProfileEvent.EXPORT, 10, true));
        actionWeightages.add(new ActionWeightage(ProfileEvent.DOWNLOAD_SDK, 20, true));
        try {
            ESManager<ActionWeightage> esManager = new ESManager<>(ESUrlBuilder.ACTION_WEIGHTAGE_URL);
            esManager.deleteAll();
            actionWeightages.forEach(actionWeightage -> {
                try {
                    esManager.persist(actionWeightage);
                } catch (IOException e) {
                    System.err.println("Unable to perist weightage " + actionWeightage.toString());
                }
            });
        } catch (IOException e) {
            System.err.println("Unable to delete all weightages");
        }
    }
}
