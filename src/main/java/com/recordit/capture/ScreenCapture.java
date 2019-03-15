package com.recordit.capture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ScreenCapture {
    public static void main(String[] args) throws Exception {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        Robot rt = new Robot();
        BufferedImage img = rt.createScreenCapture(new Rectangle((int) screen
                .getWidth(), (int) screen.getHeight()));
        ImageIO.write(img, "jpeg", new File("data/" + System.currentTimeMillis()
                + ".jpeg"));
    }
}
