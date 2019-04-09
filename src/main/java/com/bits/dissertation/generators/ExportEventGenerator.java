package com.bits.dissertation.generators;

import com.bits.dissertation.entity.ProfileEvent;

import java.util.List;

public class ExportEventGenerator extends NonValueEventGenerator {
    public ExportEventGenerator(String user, List<String> apis) {
        super(ProfileEvent.EXPORT, user, apis, 2, 1, 0.2);
    }
}
