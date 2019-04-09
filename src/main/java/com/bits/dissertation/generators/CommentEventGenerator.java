package com.bits.dissertation.generators;

import com.bits.dissertation.entity.ProfileEvent;

import java.util.List;

public class CommentEventGenerator extends NonValueEventGenerator {
    public CommentEventGenerator(String user, List<String> apis) {
        super(ProfileEvent.COMMENT, user, apis,4, 3, 0.25);
    }
}
