package com.github.sulir.vcdemo.mapper;

import com.github.sulir.vcdemo.mapper.impl.CommandExecutor;
import com.github.sulir.vcdemo.sample.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommandsTest {
    private LightService lightService = mock(LightService.class);
    private MonitorService monitorService = mock(MonitorService.class);
    private SpeechService speechService = mock(SpeechService.class);
    private CommandExecutor executor = new CommandExecutor(new Object[] {lightService, monitorService, speechService});

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
    @Ignore
    public void permutations() {
        execute("turn light on");
        verify(lightService).turnOn();
    }

    @Test
    @Ignore
    public void omittedWord() {
        execute("light on");
        verify(lightService).turnOn();
    }

    @Test
    @Ignore
    public void namedNumericParameter() {
        execute("turn on light number 1");
        verify(lightService).turnOn(1);
    }

    @Test
    @Ignore
    public void unnamedNumericParameter() {
        execute("turn on light 1");
        verify(lightService).turnOn(1);
    }

    @Test
    @Ignore
    public void emumParameter() {
        execute("turn on left light");
        verify(lightService).turnOn(Position.LEFT);
    }

    @Test
    @Ignore
    public void twoParameters() {
        execute("set light 1 to red color");
        verify(lightService).setColor(1, ColorEnum.RED);
    }

    @Test
    @Ignore
    public void mappingClass() {
        execute("set light 2 to green color");
        verify(lightService).setColor(2, new Color(0, 255, 0));
    }

    @Test
    @Ignore
    public void methodLocalSynonym() {
        execute("switch off light");
        verify(lightService).turnOff();
    }

    private void execute(String command) {
        executor.execute(command);
    }
}