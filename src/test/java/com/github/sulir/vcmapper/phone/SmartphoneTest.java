package com.github.sulir.vcmapper.phone;

import com.github.sulir.vcmapper.base.CommandExecutor;
import com.github.sulir.vcmapper.base.VoiceControlTest;
import com.github.sulir.vcmapper.hardware.SpeechService;
import org.junit.Before;
import org.junit.Test;

import java.net.SocketException;

import static org.mockito.Mockito.*;

public class SmartphoneTest extends VoiceControlTest {
    private final TimeService timeService = spy(TimeService.class);
    private final SpeechService speechService = mock(SpeechService.class);

    @Before
    public void setUp() {
        executor = new CommandExecutor(timeService);
        executor.setVoiceOutput(speechService::speak);
    }

    @Test
    public void stringResponse() {
        execute("What time is it?");
        verify(timeService).whatTimeIsIt();
        verify(speechService).speak("It is 12:34.");
    }

    @Test
    public void toResponseInClass() {
        execute("Tell me the current time.");
        verify(timeService).tellMeCurrentTime();
        verify(speechService).speak("It is 2:00.");
    }

    @Test
    public void toResponseInConverter() {
        execute("I would like to know time.");
        verify(timeService).iWouldLikeToKnowTime();
        verify(speechService).speak("It is 2:00.");
    }

    @Test
    public void exceptionResponse() {
        execute("Is time precise?");
        try {
            verify(timeService).isTimePrecise();
        } catch (SocketException e) { /* OK */ }
        verify(speechService).speak("I don't know, please try later.");
    }
}
