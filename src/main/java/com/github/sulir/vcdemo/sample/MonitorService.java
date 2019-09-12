package com.github.sulir.vcdemo.sample;

import com.github.sulir.vcdemo.mapper.annotations.OptionalWord;
import com.github.sulir.vcdemo.mapper.annotations.VoiceControllable;

@VoiceControllable
public class MonitorService {
    // 14. add optional word - "turn on" means "turn on all"
    // (not sure if it is applicable for the current algorithm)
    @OptionalWord("all")
    public void turnOn() {

    }

    // 15. array: "turn on monitors 1, 7 and 9", "turn on monitors 10 to 13"
    public void turnOn(int[] numbers) {

    }

    // 16. instead of an array, we can use a (possibly lazy) collection
    // laziness can be useful in cases such as "1 to 10,000"
    public void turnOn(Iterable<Integer> identifiers) {

    }

    // 17. instead of integers, we can use enums: "turn on the left and right monitor"
    public void turnOn(Position[] positions) {

    }
}
