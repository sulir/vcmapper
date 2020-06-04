package com.github.sulir.vcmapper.home;

import com.github.sulir.vcmapper.base.StringEnumeration;
import com.github.sulir.vcmapper.base.Synonym;
import com.github.sulir.vcmapper.base.VoiceControllable;

@VoiceControllable
public class ObjectBringerService {
    @Synonym(of="bring", is="get")
    public void bring(@StringEnumeration(PossibleObjects.class) String object) {

    }
}
