package com.github.sulir.vcmapper.home;

import com.github.sulir.vcmapper.api.Synonym;
import com.github.sulir.vcmapper.api.VoiceControllable;

@VoiceControllable
public class LightsService {
    @Synonym(of="turn", is="switch")
    public void turnLightsOn(Room room) {

    }
}
