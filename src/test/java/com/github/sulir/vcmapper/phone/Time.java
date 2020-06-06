package com.github.sulir.vcmapper.phone;

import com.github.sulir.vcmapper.dialog.VoiceResponse;

public class Time implements VoiceResponse {
    @Override
    public String toString() {
        return "It is 1:00.";
    }

    @Override
    public String toResponse() {
        return "It is 2:00.";
    }
}
