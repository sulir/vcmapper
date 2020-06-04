package com.github.sulir.vcmapper.base;

import java.util.function.BooleanSupplier;

public class Operators {
    @VoiceCommand("(.*) and (.*)")
    public void and(Runnable action1, Runnable action2) {
        action1.run();
        action2.run();
    }

    @VoiceCommand("if (.*) then (.*)")
    public void ifThen(BooleanSupplier condition, Runnable action) {
        if (condition.getAsBoolean())
            action.run();
    }
}
