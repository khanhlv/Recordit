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
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;
import org.slf4j.LoggerFactory;

import com.recordit.enums.RecorditEnum;

public class MouseListener implements NativeMouseInputListener, NativeMouseWheelListener {

    private static final File file = new File("data/mouse.txt");

    /** Logging */
    private static final Logger loggerGlobal = Logger.getLogger(GlobalScreen.class.getPackage().getName());
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MouseListener.class);

    public void nativeMouseClicked(NativeMouseEvent e) {
        System.out.println(RecorditEnum.MOUSE_CLICKED.toString() + "=" + e.getClickCount());
    }

    public void nativeMousePressed(NativeMouseEvent e) {
        writeStringToFile(RecorditEnum.MOUSE_PRESSED.toString() + "=" + e.getButton());
    }

    public void nativeMouseReleased(NativeMouseEvent e) {
        writeStringToFile(RecorditEnum.MOUSE_RELEASED.toString() +  "=" + e.getButton());
    }

    public void nativeMouseMoved(NativeMouseEvent e) {
        writeStringToFile(RecorditEnum.MOUSE_MOVE.toString() + "=" + e.getX() + "," + e.getY());
    }

    public void nativeMouseDragged(NativeMouseEvent e) {
        writeStringToFile(RecorditEnum.MOUSE_DRAGGED.toString() + "=" + e.getX() + "," + e.getY());
    }

    public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
        writeStringToFile(RecorditEnum.MOUSE_WHEEL.toString() + "=" + e.getWheelRotation());
    }

    private void writeStringToFile(String data) {
        try {
            FileUtils.writeStringToFile(file, data + "\n", "UTF-8", true);
            logger.info(data);
        } catch (IOException ex) {
            logger.error("Write string to file ", ex);
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        try {
            FileUtils.deleteQuietly(file);
            loggerGlobal.setLevel(Level.OFF);

            GlobalScreen.registerNativeHook();

            // Construct the example object.
            MouseListener example = new MouseListener();

            // Add the appropriate listeners.
            GlobalScreen.addNativeMouseListener(example);
            GlobalScreen.addNativeMouseMotionListener(example);
            GlobalScreen.addNativeMouseWheelListener(example);

        }
        catch (NativeHookException ex) {
            logger.error("There was a problem registering the native hook. ", ex);
            System.exit(-1);
        }
    }
}