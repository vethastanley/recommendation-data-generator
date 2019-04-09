package com.bits.dissertation.entity;

public class ActionWeightage {

    private ProfileEvent action;

    private int weight;

    private boolean enabled;

    public ActionWeightage(ProfileEvent action, int weight, boolean enabled) {
        this.action = action;
        this.weight = weight;
        this.enabled = enabled;
    }

    public ProfileEvent getAction() {
        return action;
    }

    public void setAction(ProfileEvent action) {
        this.action = action;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "ActionWeightage{" +
                "action=" + action +
                ", weight=" + weight +
                ", enabled=" + enabled +
                '}';
    }
}
