package com.recordit.listener;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class MouseListener implements NativeMouseInputListener {

    private static final File file = new File("D:/mouse.txt");

    /** Logging */
    private static final Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());

    public void nativeMouseClicked(NativeMouseEvent e) {
        System.out.println("Mouse Clicked: " + e.getClickCount());
//        try {
//            FileUtils.writeStringToFile(file, "CLICK=" + e.getClickCount() + "\n", "UTF-8", true);
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
    }

    public void nativeMousePressed(NativeMouseEvent e) {
        System.out.println("Mouse Pressed: " + e.getButton());
        try {
            FileUtils.writeStringToFile(file, "MOUSE_PRESSED=" + e.getButton() + "\n", "UTF-8", true);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void nativeMouseReleased(NativeMouseEvent e) {
        try {
            FileUtils.writeStringToFile(file, "MOUSE_RELEASED=" + e.getButton() + "\n", "UTF-8", true);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println("Mouse Released: " + e.getButton());
    }

    public void nativeMouseMoved(NativeMouseEvent e) {
        try {
            FileUtils.writeStringToFile(file, "MOUSE_MOVE=" + e.getX() + "," + e.getY() + "\n", "UTF-8", true);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println("Mouse Moved: " + e.getX() + ", " + e.getY());
    }

    public void nativeMouseDragged(NativeMouseEvent e) {
        try {
            FileUtils.writeStringToFile(file, "MOUSE_DRAGGED=" + e.getX() + "," + e.getY() + "\n", "UTF-8", true);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println("Mouse Dragged: " + e.getX() + ", " + e.getY());
    }

    public static void main(String[] args) {
        try {
            FileUtils.deleteQuietly(file);
            logger.setUseParentHandlers(false);
            logger.setLevel(Level.ALL);

            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        // Construct the example object.
        MouseListener example = new MouseListener();

        // Add the appropriate listeners.
        GlobalScreen.addNativeMouseListener(example);
        GlobalScreen.addNativeMouseMotionListener(example);
    }
}