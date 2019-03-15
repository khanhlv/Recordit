package com.recordit.utils;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public final class KeyUtils {
    public static HashMap<String, Integer> compareKey() {
        HashMap<String, Integer> hasKey = new HashMap<>();

        for (int i = 0 ; i < 16*16*16*16; i++) {
            if (KeyEvent.getKeyText(i).indexOf("Unknown keyCode") == -1) {
                if (!hasKey.containsKey(KeyEvent.getKeyText(i))) {
                    hasKey.put(KeyEvent.getKeyText(i), i);
                }

            }
        }
        return hasKey;
    }
}
