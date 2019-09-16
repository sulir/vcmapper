package com.github.sulir.vcmapper.sample;

import com.github.sulir.vcmapper.api.VoiceCommand;
import com.github.sulir.vcmapper.api.VoiceControllable;

public class SpeechService {
    // 19. completely other command than class/method names, hard-coded form (high priority)
    // (and @VoiceControllable can be also at the method level)
    @VoiceControllable
    @VoiceCommand("say (.*)")
    public void speak(String sentence) {

    }
}
