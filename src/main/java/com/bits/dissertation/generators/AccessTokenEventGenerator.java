package com.bits.dissertation.generators;

import com.bits.dissertation.entity.ProfileEvent;

import java.util.List;

public class AccessTokenEventGenerator extends NonValueEventGenerator {

    public AccessTokenEventGenerator(String user, List<String> apis) {
        super(ProfileEvent.ACCESS_TOKEN, user, apis, 2, 1, 0.2);
    }
}
