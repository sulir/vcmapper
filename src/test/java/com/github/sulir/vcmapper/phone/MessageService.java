package com.github.sulir.vcmapper.phone;

import com.github.sulir.vcmapper.api.IfAbsent;

public class MessageService {
    public void send(@IfAbsent("To whom?") String contact, String text) {

    }

    public void send(Message message) {

    }
}
