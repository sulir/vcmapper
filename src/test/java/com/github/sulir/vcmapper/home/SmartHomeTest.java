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
 * The natural language sentences and method names represent a sample from
 * the Fluent Speech Commands dataset by Fluent.ai (used with permission).
 * Dataset: https://www.fluent.ai/research/fluent-speech-commands/.
 * License: http://fluent.ai:2052/Fluent_Speech_Commands_Public_License.pdf.
 */
@RunWith(MockitoJUnitRunner.class)
public class SmartHomeTest extends VoiceControlTest {
    private LightsService lightsService = mock(LightsService.class);
    private MusicService musicService = mock(MusicService.class);
    private ObjectBringerService objectBringerService = mock(ObjectBringerService.class);
    private HeatingService heatingService = mock(HeatingService.class);
    private VolumeService volumeService = mock(VolumeService.class);
    private LanguageService languageService = mock(LanguageService.class);

    @Before
    public void setUp() {
        executor = new CommandExecutor(lightsService, musicService, objectBringerService, heatingService,
                volumeService, languageService);
    }

    @Test
    public void activateLightsBedroom1() {
        execute("Lights on in the bedroom");
        verify(lightsService).turnLightsOn(Room.BEDROOM);
    }

    @Test
    public void activateLightsBedroom2() {
        execute("Switch on the lights in the bedroom");
        verify(lightsService).turnLightsOn(Room.BEDROOM);
    }

    @Test
    public void activateLightsBedroom3() {
        execute("Turn on the lights in the bedroom");
        verify(lightsService).turnLightsOn(Room.BEDROOM);
    }

    @Test
    public void activateLightsKitchen() {
        execute("Turn the lights on in the kitchen");
        verify(lightsService).turnLightsOn(Room.KITCHEN);
    }

    @Test
    public void activateLightsWashroom1() {
        execute("Washroom lights on");
        verify(lightsService).turnLightsOn(Room.WASHROOM);
    }

    // enum-local synonym needed
    @Test
    public void activateLightsWashroom2() {
        execute("Switch on the bathroom lights");
        verify(lightsService).turnLightsOn(Room.WASHROOM);
    }

    @Test
    public void activateLightsWashroom3() {
        execute("Switch on the washroom lights");
        verify(lightsService).turnLightsOn(Room.WASHROOM);
    }

    @Test
    public void activateMusicNone() {
        execute("Start the music");
        verify(musicService).start();
    }

    // synonyms should be definable in any direction
    // stemming of sentence vs. parameter words
    @Test
    public void bringNewspaperNone() {
        execute("Get me the newspaper");
        verify(objectBringerService).bring("newspaper");
    }

    @Test
    public void deactivateLampNone() {
        execute("Lamp off");
        verify(lightsService).turnLampOff();
    }

    @Test
    public void deactivateLightsKitchen() {
        execute("Turn the lights off in the kitchen");
        verify(lightsService).turnLightsOff(Room.KITCHEN);
    }

    @Test
    public void deactivateMusicNone() {
        execute("Stop music");
        verify(musicService).stop();
    }

    // multi-word synonyms required
    @Test
    public void decreaseHeatBedroom() {
        execute("Turn the temperature in the bedroom down");
        verify(heatingService).decreaseTemperature(Room.BEDROOM);
    }

    @Test
    public void decreaseHeatKitchen1() {
        execute("Turn down the temperature in the kitchen");
        verify(heatingService).decreaseTemperature(Room.KITCHEN);
    }

    @Test
    public void decreaseHeatKitchen2() {
        execute("Decrease the temperature in the kitchen");
        verify(heatingService).decreaseTemperature(Room.KITCHEN);
    }

    @Test
    public void decreaseHeatKitchen3() {
        execute("Turn the heat down in the kitchen");
        verify(heatingService).decreaseTemperature(Room.KITCHEN);
    }

    @Test
    public void decreaseHeatNone1() {
        execute("Heat down");
        verify(heatingService).decreaseTemperature();
    }

    // the sentence is completely different than the other ones - fallback could be a better fit than synonyms
    // "decrease" and "cooler" are not synonyms in the traditional sense
    @Test
    public void decreaseHeatNone2() {
        execute("Make it cooler");
        verify(heatingService).decreaseTemperature();
    }

    @Test
    public void decreaseHeatNone3() {
        execute("Decrease the heating");
        verify(heatingService).decreaseTemperature();
    }

    @Test
    public void decreaseHeatNone4() {
        execute("Decrease the temperature");
        verify(heatingService).decreaseTemperature();
    }

    @Test
    public void decreaseHeatNone5() {
        execute("Turn the heat down");
        verify(heatingService).decreaseTemperature();
    }

    @Test
    public void decreaseHeatWashroom1() {
        execute("Turn the heat down in the washroom");
        verify(heatingService).decreaseTemperature(Room.WASHROOM);
    }

    @Test
    public void decreaseHeatWashroom2() {
        execute("Decrease the heating in the bathroom");
        verify(heatingService).decreaseTemperature(Room.WASHROOM);
    }

    @Test
    public void decreaseHeatWashroom3() {
        execute("Turn the heat down in the bathroom");
        verify(heatingService).decreaseTemperature(Room.WASHROOM);
    }

    // again - a too different sentence, synonyms not ideal
    @Test
    public void decreaseVolumeNone1() {
        execute("Make it quieter");
        verify(volumeService).decrease();
    }

    // the first part of the sentence is redundant, could be removed
    @Test
    public void decreaseVolumeNone2() {
        execute("It's too loud, turn the volume down");
        verify(volumeService).decrease();
    }

    @Test
    public void decreaseVolumeNone3() {
        execute("Decrease volume");
        verify(volumeService).decrease();
    }

    @Test
    public void decreaseVolumeNone4() {
        execute("Turn down the volume");
        verify(volumeService).decrease();
    }

    @Test
    public void decreaseVolumeNone5() {
        execute("Lower the volume");
        verify(volumeService).decrease();
    }

    @Test
    public void decreaseVolumeNone6() {
        execute("Decrease audio volume");
        verify(volumeService).decrease();
    }

    // fallback + word analysis for the same method required
    @Test
    public void decreaseVolumeNone7() {
        execute("That's too loud");
        verify(volumeService).decrease();
    }

    @Test
    public void changeLanguageEnglishNone() {
        execute("Set my phone's language to English");
        verify(languageService).setLanguage(Language.ENGLISH);
    }

    // the argument ensures the correct result despite many redundant words
    @Test
    public void changeLanguageGermanNone() {
        execute("I need to practice my German. Switch the language");
        verify(languageService).setLanguage(Language.GERMAN);
    }

    @Test
    public void changeLanguageChineseNone() {
        execute("I need to practice my Chinese. Switch the language");
        verify(languageService).setLanguage(Language.CHINESE);
    }

    @Test
    public void changeLanguageKoreanNone() {
        execute("OK now switch the main language to Korean");
        verify(languageService).setLanguage(Language.KOREAN);
    }

    @Test
    public void changeLanguageNoneNone1() {
        execute("Switch the language");
        verify(languageService).switchLanguage();
    }

    @Test
    public void changeLanguageNoneNone2() {
        execute("Set the language");
        verify(languageService).switchLanguage();
    }

    // synonym "settings"-"switch" was not necessary (settings -> set), but it would be a strange synonym
    @Test
    public void changeLanguageNoneNone3() {
        execute("Language settings");
        verify(languageService).switchLanguage();
    }

    @Test
    public void increaseHeatKitchen() {
        execute("Kitchen heat up");
        verify(heatingService).increaseTemperature(Room.KITCHEN);
    }

    @Test
    public void increaseHeatNone1() {
        execute("Increase the temperature");
        verify(heatingService).increaseTemperature();
    }

    @Test
    public void increaseHeatNone2() {
        execute("Turn the temperature up");
        verify(heatingService).increaseTemperature();
    }

    @Test
    public void increaseHeatWashroom1() {
        execute("Turn up the heat in the washroom");
        verify(heatingService).increaseTemperature(Room.WASHROOM);
    }

    @Test
    public void increaseHeatWashroom2() {
        execute("Turn up the bathroom temperature");
        verify(heatingService).increaseTemperature(Room.WASHROOM);
    }

    @Test
    public void increaseVolumeNone1() {
        execute("Volume max");
        verify(volumeService).increase();
    }

    @Test
    public void increaseVolumeNone2() {
        execute("Louder please");
        verify(volumeService).increase();
    }

    @Test
    public void increaseVolumeNone3() {
        execute("Volume up");
        verify(volumeService).increase();
    }

    @Test
    public void increaseVolumeNone4() {
        execute("That's too quiet");
        verify(volumeService).increase();
    }

    // application of multiple synonyms per method is necessary
    @Test
    public void increaseVolumeNone5() {
        execute("Make the music louder");
        verify(volumeService).increase();
    }

    @Test
    public void increaseVolumeNone6() {
        execute("Turn it up");
        verify(volumeService).increase();
    }

    // groups in a regex do not have to be parameters of a method
    @Test
    public void increaseVolumeNone7() {
        execute("I can't hear that");
        verify(volumeService).increase();
    }
}
