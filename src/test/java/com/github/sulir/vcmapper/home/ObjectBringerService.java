package com.github.sulir.vcmapper.home;

import com.github.sulir.vcmapper.api.Synonym;
import com.github.sulir.vcmapper.api.ValidValues;
import com.github.sulir.vcmapper.api.VoiceControllable;

@VoiceControllable
public class ObjectBringerService {
    @Synonym(of="bring", is="get")
    public void bring(@ValidValues(PossibleObjects.class) String object) {

    }
}
