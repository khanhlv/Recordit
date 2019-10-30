package com.recordit.play;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.recordit.capture.ScreenCapture;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.LoggerFactory;

import com.recordit.enums.RecorditEnum;
import com.recordit.utils.KeyUtils;

public class RecorditPlay {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RecorditPlay.class);
    private static final HashMap<String, Integer> hasKey = KeyUtils.compareKey();

    private  File file = new File("data/recordit.txt");
    private String fileImage = String.valueOf(System.currentTimeMillis());

    public RecorditPlay withFileImage(String fileImage) {
        this.fileImage = fileImage;
        return this;
    }

    public RecorditPlay withFileRecodit(String fileRecodit) {
        this.file = new File(fileRecodit);
        return this;
    }

    public void execute() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
            Robot robot = new Robot();

            String input = null;
            while((input = in.readLine()) != null) {
                String[] splitKey = input.split("=");

                if (StringUtils.equals(RecorditEnum.MOUSE_MOVE.toString(), splitKey[0]) ||
                        StringUtils.equals(RecorditEnum.MOUSE_DRAGGED.toString(), splitKey[0])) {
                    mouseMove(robot, splitKey);
                }

                if (StringUtils.equals(RecorditEnum.MOUSE_PRESSED.toString(), splitKey[0])) {
                    if (StringUtils.equals("1", splitKey[1])) {
                        robot.mousePress(InputEvent.BUTTON1_MASK);
                    }

                    if (StringUtils.equals("2", splitKey[1])) {
                        robot.mousePress(InputEvent.BUTTON3_MASK);
                    }

                    if (StringUtils.equals("3", splitKey[1])) {
                        robot.mousePress(InputEvent.BUTTON2_MASK);
                    }
                }

                if (StringUtils.equals(RecorditEnum.MOUSE_RELEASED.toString(), splitKey[0])) {
                    if (StringUtils.equals("1", splitKey[1])) {
                        robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    }

                    if (StringUtils.equals("2", splitKey[1])) {
                        robot.mouseRelease(InputEvent.BUTTON3_MASK);
                    }

                    if (StringUtils.equals("3", splitKey[1])) {
                        robot.mouseRelease(InputEvent.BUTTON2_MASK);
                    }
                }

                if (StringUtils.equals(RecorditEnum.MOUSE_WHEEL.toString(), splitKey[0])) {
                    robot.mouseWheel(NumberUtils.toInt(splitKey[1]));
                }

                // KEYBOARD
                if (StringUtils.equals(RecorditEnum.KEY_PRESSED.toString(), splitKey[0])) {
                    robot.keyPress(hasKey.get(splitKey[1]));
                }

                if (StringUtils.equals(RecorditEnum.KEY_RELEASED.toString(), splitKey[0])) {
                    robot.keyRelease(hasKey.get(splitKey[1]));

                    // CAPTURE SCREEN
                    if (StringUtils.equals("Print Screen", splitKey[1])) {
                        ScreenCapture.execute(fileImage);
                    }
                }

                // WAITING
                if (StringUtils.equals(RecorditEnum.WAITING.toString(), splitKey[0])) {
                    robot.delay(NumberUtils.toInt(splitKey[1]));
                }

                logger.info(input);
            }
        } catch (Exception ex) {
            logger.error("RecorditPlay", ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private void mouseMove(Robot robot, String[] splitKey) {
        String[] split = StringUtils.split(splitKey[1], ",");

        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        robot.mouseMove(x, y);
    }

    public static void main(String[] args) throws Exception {
        new RecorditPlay().execute();
    }
}
