package com.github.sulir.vcmapper.phone;

import com.github.sulir.vcmapper.dialog.VoiceResponse;

public class Time implements VoiceResponse {
    @Override
    public String toString() {
        return "it is 1:00";
    }

    @Override
    public String toResponse() {
        return "it is 1:00";
    }
}
