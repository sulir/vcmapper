package com.github.sulir.vcmapper.hardware;

import com.github.sulir.vcmapper.base.VoiceCommand;

public class SpeechService {
    // 19. completely other command than class/method names, hard-coded form (high priority)
    @VoiceCommand("say (.*)")
    public void speak(String sentence) {

    }
}
