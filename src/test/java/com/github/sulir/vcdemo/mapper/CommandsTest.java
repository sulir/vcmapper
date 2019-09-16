package com.github.sulir.vcdemo.mapper;

import com.github.sulir.vcdemo.mapper.api.CommandExecutor;
import com.github.sulir.vcdemo.mapper.exceptions.AmbiguityException;
import com.github.sulir.vcdemo.mapper.exceptions.NoMatchException;
import com.github.sulir.vcdemo.sample.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommandsTest {
    private LightService lightService = mock(LightService.class);
    private MonitorService monitorService = mock(MonitorService.class);
    private SpeechService speechService = mock(SpeechService.class);
    private CommandExecutor executor = new CommandExecutor(lightService, monitorService, speechService);

    @Test
    public void simpleMethodCall() {
        execute("turn on light");
        verify(lightService).turnOn();
    }

    @Test
    public void stopWords() {
        execute("turn on the light");
        verify(lightService).turnOn();
    }

    @Test
    public void stemming() {
        execute("turn on lights");
        verify(lightService).turnOn();
    }

    @Test
    public void permutation() {
        execute("turn light on");
        verify(lightService).turnOn();
    }

    @Test
    public void omittedWord() {
        execute("light on");
        verify(lightService).turnOn();
    }

    @Test
    public void extraWord() {
        execute("could you please turn the light on");
        verify(lightService).turnOn();
    }

    @Test
    public void namedNumericParameter() {
        execute("turn on light number 1");
        verify(lightService).turnOn(1);
    }

    @Test
    public void unnamedNumericParameter() {
        execute("turn on light 1");
        verify(lightService).turnOn(1);
    }

    @Test
    public void emumParameter() {
        execute("turn on left light");
        verify(lightService).turnOn(Position.LEFT);
    }

    @Test
    public void twoParameters() {
        execute("set light 1 to red color");
        verify(lightService).setColor(1, ColorEnum.RED);
    }

    @Test
    public void mappingClass() {
        execute("set light 2 to yellow color");
        verify(lightService).setColor(2, new Color(255, 255, 0));
    }

    @Test
    public void methodLocalSynonym() {
        execute("switch off light");
        verify(lightService).turnOff();
    }

    private void execute(String command) {
        try {
            executor.execute(command);
        } catch (NoMatchException | AmbiguityException e) {
            fail(e.getMessage());
        }
    }
}