package com.recordit.compare;

import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ImageComparison {
    private static final Logger logger = LoggerFactory.getLogger(ImageComparison.class);

    public static boolean findImage(String inputFile) {
        try {
            Screen screen = new Screen();

            Pattern pattern = new Pattern(new File(inputFile).getAbsolutePath());

            screen.find(pattern);

            return true;
        } catch (Exception ex) {
            logger.info("Not found image to screen");
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(findImage("data\\check.png"));
    }
}
