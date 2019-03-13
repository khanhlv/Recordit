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

//        String input = null;
//        while((input = in.readLine()) != null) {
//            String[] splitKey = input.split("=");
//
//            if (StringUtils.equals("MOVE", splitKey[0])) {
//                String[] split = StringUtils.split(splitKey[1], ",");
//
//                int x = Integer.parseInt(split[0]);
//                int y = Integer.parseInt(split[1]);
//                robot.mouseMove(x, y);
//
//            }
//
//            if (StringUtils.equals("PRESSED", splitKey[0])) {
//                if (StringUtils.equals("1", splitKey[1])) {
//                    robot.mousePress(InputEvent.BUTTON1_MASK);
//                }
//
//                if (StringUtils.equals("2", splitKey[1])) {
//                    robot.mousePress(InputEvent.BUTTON3_MASK);
//                }
//            }
//
//            if (StringUtils.equals("DRAGGED", splitKey[0])) {
//                String[] split = StringUtils.split(splitKey[1], ",");
//
//                int x = Integer.parseInt(split[0]);
//                int y = Integer.parseInt(split[1]);
//                robot.mouseMove(x, y);
//
//            }
//
//            if (StringUtils.equals("RELEASED", splitKey[0])) {
//                if (StringUtils.equals("1", splitKey[1])) {
//                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
//                }
//
//                if (StringUtils.equals("2", splitKey[1])) {
//                    robot.mouseRelease(InputEvent.BUTTON3_MASK);
//                }
//            }
//
//            Thread.sleep(50);
//            System.out.println(input);
//        }
//        robot.keyPress(29);
//        robot.keyRelease(47);

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
