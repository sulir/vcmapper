package com.github.sulir.vcdemo.sample;

import com.github.sulir.vcdemo.mapper.api.Synonym;
import com.github.sulir.vcdemo.mapper.api.VoiceControllable;
import com.github.sulir.vcdemo.mapper.api.StringMapping;

@VoiceControllable
@Synonym(of = "start", is = "run")
public class LightService {
    // 1. get a list of all @VoiceControllable annotated classes, remove implementation-dependent suffixes
    // "turn on light" -> LightService::turnOn
    public void turnOn() {

    }

    // 2. remove stop words (which?): "turn on the light"

    // 3. stem words - both input and classes/method names: "turn on lights"

    // 4. allow permutations, e.g.: "turn light on"
    // (should order of the method name's words stay the same?)

    // 5. allow omitting some words: "light on"

    // 6. numeric parameter
    // "turn on light number 1", "turn on light 1"
    public void turnOn(int number) {

    }

    // 7. enum parameter: "turn on left light"
    public void turnOn(Position position) {

    }

    // 8. two parameters: "set light 1 to red color"
    // (for successful matching, all parameters must be present in the sentence)
    public void setColor(int number, ColorEnum color) {

    }

    // 9. custom mapping class: "set light 2 to green color"
    public void setColor(
            int number,
            @StringMapping(ColorMapper.class)
            Color color) {
    }

    // 11. method-local synonym: "switch off lights"
    @Synonym(of = "turn", is = "switch")
    @Synonym(of = "turn off", is = "shut")
    public void turnOff() {

    }

    // 12. synonyms can be defined also at a class or package level
    public void startShow() {

    }

    // 13. parameters can have synonyms, too: "dim lamp 11"
    public void dim(@Synonym(of = "light", is = {"lamp", "number"}) int light) {

    }
}
