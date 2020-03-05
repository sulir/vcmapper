package com.github.sulir.vcmapper.phone;

import com.github.sulir.vcmapper.api.ResponseMapper;

public class TimeResponse implements ResponseMapper<Time> {
    @Override
    public String getResponse(Time time) {
        return "it is" + time.getVoiceRepresentation();
    }
}
