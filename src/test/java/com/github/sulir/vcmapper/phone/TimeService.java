package com.github.sulir.vcmapper.phone;

import com.github.sulir.vcmapper.dialog.ResponseConversion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeService {
    public String whatTimeIsIt() {
        return "it is " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public Time getCurrentTime() {
        return new Time();
    }

    @ResponseConversion(TimeResponse.class)
    public Time getTime() {
        return new Time();
    }
}
