package com.recordit.compare;

import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageComparison {
    private static final Logger logger = LoggerFactory.getLogger(ImageComparison.class);

    public static boolean findImage(String inputFile) {
        try {
            Screen screen = new Screen();

            Pattern pattern = new Pattern(inputFile);

            screen.find(pattern);

            return true;
        } catch (Exception ex) {
            logger.error("ImageComparison", ex);
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        Screen screen = new Screen();

        Pattern openButton = new Pattern( "D:\\Recordit\\data\\check.png");

        screen.find(openButton);

    }
}
