package com.recordit.play;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * http://omtlab.com/java-control-the-mouse-pointer-and-click/
 * https://blog.softhints.com/java-robot-key-press-mouse-move-and-click/
 *
 */
public class PlayMouse {

    private static final File file = new File("D:/mouse.txt");

    public static void play() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(file));
        Robot robot = new Robot();

        String input = null;
        while((input = in.readLine()) != null) {
            String[] splitKey = input.split("=");

            if (StringUtils.equals("MOUSE_MOVE", splitKey[0])) {
                mouseMove(robot, splitKey);
            }

            if (StringUtils.equals("MOUSE_PRESSED", splitKey[0])) {
                if (StringUtils.equals("1", splitKey[1])) {
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                }

                if (StringUtils.equals("2", splitKey[1])) {
                    robot.mousePress(InputEvent.BUTTON3_MASK);
                }
            }

            if (StringUtils.equals("MOUSE_DRAGGED", splitKey[0])) {
                mouseMove(robot, splitKey);
            }

            if (StringUtils.equals("MOUSE_RELEASED", splitKey[0])) {
                if (StringUtils.equals("1", splitKey[1])) {
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                }

                if (StringUtils.equals("2", splitKey[1])) {
                    robot.mouseRelease(InputEvent.BUTTON3_MASK);
                }
            }

            if (StringUtils.equals("MOUSE_WHEEL", splitKey[0])) {
                robot.mouseWheel(NumberUtils.toInt(splitKey[1]));
            }

            Thread.sleep(20);
            System.out.println(input);
        }

        System.exit(1);
    }

    private static void mouseMove(Robot robot, String[] splitKey) {
        String[] split = StringUtils.split(splitKey[1], ",");

        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        robot.mouseMove(x, y);
    }

    public static void main(String[] args) throws Exception {
        PlayMouse.play();
    }
}
