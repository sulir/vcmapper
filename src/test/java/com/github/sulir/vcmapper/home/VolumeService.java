package com.github.sulir.vcmapper.home;

import com.github.sulir.vcmapper.api.Synonym;
import com.github.sulir.vcmapper.api.VoiceCommand;
import com.github.sulir.vcmapper.api.VoiceControllable;

@VoiceControllable
@Synonym(of="volume", is="music")
public class VolumeService {
    @Synonym(of="decrease", is="quieter")
    @Synonym(of="decrease", is="turn down")
    @Synonym(of="decrease", is="lower")
    @VoiceCommand(".*too loud.*")
    public void decrease() {

    }

    @Synonym(of="increase", is="max")
    @Synonym(of="increase", is="louder")
    @Synonym(of="increase", is="turn up")
    @VoiceCommand(".*(too quiet|can't hear).*")
    public void increase() {

    }
}
