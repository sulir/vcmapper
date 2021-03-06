package com.github.sulir.vcmapper.hardware;

import com.github.sulir.vcmapper.dialog.OnException;
import com.github.sulir.vcmapper.base.StringEnumeration;
import com.github.sulir.vcmapper.base.VoiceControllable;

@VoiceControllable
public class MonitorService {
    // 14. string parameter with all possible values enumerated
    public void turnOn(@StringEnumeration(PositionValues.class) String position) {

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

    // 18. exception message (voice output)
    @OnException(of = IndexOutOfBoundsException.class, say = "Monitor {number} does not exist.")
    public void turnOff(int number) {

    }
}
