package com.github.sulir.vcmapper.phone;

import com.github.sulir.vcmapper.dialog.ResponseConverter;

public class TimeResponse implements ResponseConverter<Time> {
    @Override
    public String toResponse(Time time) {
        return "it is" + time.toResponse();
    }
}
