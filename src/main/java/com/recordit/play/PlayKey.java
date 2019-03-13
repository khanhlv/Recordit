package com.recordit.play;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.jnativehook.keyboard.NativeKeyEvent;

/**
 * http://omtlab.com/java-control-the-mouse-pointer-and-click/
 * https://blog.softhints.com/java-robot-key-press-mouse-move-and-click/
 *
 */
public class PlayKey {

    private static final File file = new File("D:/key.txt");
    private static final HashMap<String, Integer> hasKey = new HashMap<>();
    static {
        for (int i = 0 ; i < 16*16*16*16; i++) {
            hasKey.put(KeyEvent.getKeyText(i), i);
        }
    }

    public static void play() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(file));
        Robot robot = new Robot();
        Thread.sleep(4000);
        String input = null;
        while((input = in.readLine()) != null) {
            String[] splitKey = input.split("=");

            if (StringUtils.equals("KEY_PRESSED", splitKey[0])) {
                robot.keyPress(hasKey.get(splitKey[1]));
            }

            if (StringUtils.equals("KEY_RELEASED", splitKey[0])) {
                robot.keyRelease(hasKey.get(splitKey[1]));
            }

            Thread.sleep(20);
            System.out.println(input);
        }

        System.exit(1);
    }

    public static void main(String[] args) throws Exception {
        PlayKey.play();
    }
}
