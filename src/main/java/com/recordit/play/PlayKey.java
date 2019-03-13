package com.recordit.play;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.jnativehook.keyboard.NativeKeyEvent;

/**
 * http://omtlab.com/java-control-the-mouse-pointer-and-click/
 *
 */
public class PlayKey {

    private static final File file = new File("D:/key.txt");

    public static void play() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(file));
        Robot robot = new Robot();


        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(29));
        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(47));

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        System.exit(-1);
    }

    public static void main(String[] args) throws Exception {
        PlayKey.play();
    }
}
