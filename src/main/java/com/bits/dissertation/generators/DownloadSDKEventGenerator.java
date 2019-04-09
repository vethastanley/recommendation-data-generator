package com.bits.dissertation.generators;

import com.bits.dissertation.entity.ProfileEvent;

import java.util.List;

public class DownloadSDKEventGenerator extends NonValueEventGenerator {
    public DownloadSDKEventGenerator(String user, List<String> apis) {
        super(ProfileEvent.DOWNLOAD_SDK, user, apis, 2, 1, 0.2);
    }
}
