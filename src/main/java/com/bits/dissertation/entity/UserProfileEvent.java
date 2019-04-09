package com.bits.dissertation.entity;

import java.util.Date;

public class UserProfileEvent {

    private String actor;

    private ProfileEvent action;

    private String api;

    private int actionValue;

    private Date time;

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public ProfileEvent getAction() {
        return action;
    }

    public void setAction(ProfileEvent action) {
        this.action = action;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public int getActionValue() {
        return actionValue;
    }

    public void setActionValue(int actionValue) {
        this.actionValue = actionValue;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "UserProfileEvent{" +
                "actor='" + actor + '\'' +
                ", action=" + action +
                ", api='" + api + '\'' +
                ", actionValue=" + actionValue +
                ", time=" + time +
                '}';
    }
}
