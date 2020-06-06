package com.github.sulir.vcmapper.hardware;

import com.github.sulir.vcmapper.base.CommandExecutor;
import com.github.sulir.vcmapper.base.Operators;
import com.github.sulir.vcmapper.base.VoiceControlTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HardwareDevicesTest extends VoiceControlTest {
    private final LightService lightService = mock(LightService.class);
    private final MonitorService monitorService = mock(MonitorService.class);
    private final SpeechService speechService = mock(SpeechService.class);
    private final Operators operators = new Operators();

    @Before
    public void setUp() {
        executor = new CommandExecutor(lightService, monitorService, speechService, operators);
    }

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
    public void enumParameter() {
        execute("turn on left light");
        verify(lightService).turnOn(Position.LEFT);
    }

    @Test
    public void twoParameters() {
        execute("set light 1 to red color");
        verify(lightService).setColor(1, ColorEnum.RED);
    }

    @Test
    public void enumeratedStringValues() {
        execute("turn on the topmost monitor");
        verify(monitorService).turnOn("topmost");
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

    @Test
    public void classLocalSynonym() {
        execute("run light show");
        verify(lightService).startShow();
    }

    @Test
    public void fallbackRegex() {
        execute("say something nice");
        verify(speechService).speak("something nice");
    }

    @Test
    public void simpleComposition() {
        execute("turn on light and turn on the topmost monitor");
        verify(lightService).turnOn();
        verify(monitorService).turnOn("topmost");
    }
}