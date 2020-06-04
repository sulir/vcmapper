package com.github.sulir.vcmapper.base;

import com.github.sulir.vcmapper.exceptions.AmbiguityException;
import com.github.sulir.vcmapper.exceptions.NoMatchException;

import static org.junit.Assert.fail;

public abstract class VoiceControlTest {
    protected CommandExecutor executor;

    protected void execute(String command) {
        try {
            executor.execute(command);
        } catch (NoMatchException | AmbiguityException e) {
            fail(e.getMessage());
        }
    }
}
