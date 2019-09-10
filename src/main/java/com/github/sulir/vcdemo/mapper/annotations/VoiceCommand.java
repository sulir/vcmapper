package com.github.sulir.vcdemo.mapper.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface VoiceCommand {
    String value();
}
