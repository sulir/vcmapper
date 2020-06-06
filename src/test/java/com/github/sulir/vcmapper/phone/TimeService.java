package com.github.sulir.vcmapper.phone;

import com.github.sulir.vcmapper.base.VoiceControllable;
import com.github.sulir.vcmapper.dialog.OnException;
import com.github.sulir.vcmapper.dialog.ResponseConversion;

import java.net.SocketException;

@SuppressWarnings("UnusedReturnValue")
@VoiceControllable
public class TimeService {
    public String whatTimeIsIt() {
        return "It is 12:34.";
    }

    public Time tellMeCurrentTime() {
        return new Time();
    }

    @ResponseConversion(TimeResponse.class)
    public Time iWouldLikeToKnowTime() {
        return new Time();
    }

    @OnException(of = SocketException.class, say = "I don't know, please try later.")
    public boolean isTimePrecise() throws SocketException {
        throw new SocketException();
    }
}
