package com.github.sulir.vcmapper.home;

import com.github.sulir.vcmapper.api.Synonym;
import com.github.sulir.vcmapper.api.VoiceControllable;

@VoiceControllable
@Synonym(of="decrease", is="turn down")
@Synonym(of="decrease", is="cooler")
@Synonym(of="increase", is="turn up")
public class HeatingService {
    public void decreaseTemperature(Room room) {

    }

    public void decreaseTemperature() {

    }

    public void increaseTemperature(Room room) {

    }

    public void increaseTemperature() {

    }
}
