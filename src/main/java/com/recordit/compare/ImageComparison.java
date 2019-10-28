package com.recordit.compare;

import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class ImageComparison {

    public static void main(String[] args) throws Exception {
        Screen screen = new Screen();

        Pattern openButton = new Pattern( "D:\\Recordit\\data\\check.png");

        screen.find(openButton);


    }
}
