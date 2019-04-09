package com.bits.dissertation.generators;

import com.bits.dissertation.entity.ProfileEvent;

import java.util.List;

public class VisitEventGenerator extends NonValueEventGenerator {
    public VisitEventGenerator(String user, List<String> apis) {
        super(ProfileEvent.VISIT, user, apis,20, 18, 1.0);
    }
}
