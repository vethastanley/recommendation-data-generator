package com.bits.dissertation.generators;

import com.bits.dissertation.entity.ProfileEvent;

import java.util.List;

public class LikeEventGenerator extends NonValueEventGenerator {
    public LikeEventGenerator(String user, List<String> apis) {
        super(ProfileEvent.LIKE, user, apis,5, 3, 0.30);
    }
}
