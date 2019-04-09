package com.bits.dissertation.generators;

import com.bits.dissertation.entity.ProfileEvent;

import java.util.List;

public class PostEventGenerator extends NonValueEventGenerator {
    public PostEventGenerator(String user, List<String> apis) {
        super(ProfileEvent.POST, user, apis, 3, 2, 0.25);
    }
}
