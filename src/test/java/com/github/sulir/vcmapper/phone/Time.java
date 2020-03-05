package com.github.sulir.vcmapper.phone;

import com.github.sulir.vcmapper.api.VoiceRepresentation;

public class Time implements VoiceRepresentation {
    @Override
    public String toString() {
        return "it is 1:00";
    }

    @Override
    public String getVoiceRepresentation() {
        return "it is 1:00";
    }
}
