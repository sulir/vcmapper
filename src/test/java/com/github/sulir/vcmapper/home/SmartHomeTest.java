package com.github.sulir.vcmapper.home;

import com.github.sulir.vcmapper.api.CommandExecutor;
import com.github.sulir.vcmapper.base.VoiceControlTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests using sample commands controlling a smart home.
 *
 * The sentences represent a small sample from the Fluent Speech Commands
 * dataset (https://www.fluent.ai/research/fluent-speech-commands/).
 */
@RunWith(MockitoJUnitRunner.class)
public class SmartHomeTest extends VoiceControlTest {
    private LightsService lightsService = mock(LightsService.class);
    private MusicService musicService = mock(MusicService.class);

    @Before
    public void setUp() {
        executor = new CommandExecutor(lightsService, musicService);
    }

    @Test
    public void activateLightsBedroom1() {
        execute("Lights on in the bedroom");
        verify(lightsService).turnLightsOn(Room.BEDROOM);
    }
}
