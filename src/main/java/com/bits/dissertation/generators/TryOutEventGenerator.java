package com.bits.dissertation.generators;

import com.bits.dissertation.entity.ProfileEvent;

import java.util.List;

public class TryOutEventGenerator extends NonValueEventGenerator {
    public TryOutEventGenerator(String user, List<String> apis) {
        super(ProfileEvent.TRYOUT, user, apis,15, 10, 0.6);
    }
}
