package com.recordit.play;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.commons.lang3.StringUtils;
import org.jnativehook.keyboard.NativeKeyEvent;

/**
 * http://omtlab.com/java-control-the-mouse-pointer-and-click/
 * https://blog.softhints.com/java-robot-key-press-mouse-move-and-click/
 *
 */
public class PlayKey {

    private static final File file = new File("D:/key.txt");

    public static int typeKey(String character) {
        int key = -1;

        for (int i = 0 ; i < 16*16*16*16; i++) {
            if (StringUtils.equals(KeyEvent.getKeyText(i), character)) {
                key = i;
                break;
            }
        }

        return key;
    }

    public static void play() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(file));
        Robot robot = new Robot();

        String input = null;
        while((input = in.readLine()) != null) {
            String[] splitKey = input.split("=");

            if (StringUtils.equals("KEY_PRESSED", splitKey[0])) {
                robot.keyPress(typeKey(splitKey[1]));
            }

            if (StringUtils.equals("KEY_RELEASED", splitKey[0])) {
                robot.keyPress(typeKey(splitKey[1]));
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
