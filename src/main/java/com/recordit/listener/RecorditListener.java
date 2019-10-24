package com.recordit.listener;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;
import org.slf4j.LoggerFactory;

import com.recordit.enums.RecorditEnum;

public class RecorditListener implements NativeKeyListener, NativeMouseInputListener, NativeMouseWheelListener {
    private static final File file = new File("data/recordit.txt");
    private static boolean WAITING = true;
    private int timeWaiting = 50;

    /** Logging */
    private static final Logger loggerGlobal = Logger.getLogger(GlobalScreen.class.getPackage().getName());

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RecorditListener.class);

    public void nativeMouseClicked(NativeMouseEvent e) {
        logger.info(RecorditEnum.MOUSE_CLICKED.toString() + "=" + e.getClickCount());
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

    public void nativeKeyPressed(NativeKeyEvent e) {
        writeStringToFile(RecorditEnum.KEY_PRESSED.toString() + "=" + NativeKeyEvent.getKeyText(e.getKeyCode()));

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
                System.exit(-1);
            } catch (NativeHookException ex) {
                logger.error("NativeHookException Key Pressed", ex);
                System.exit(-1);
            }
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        writeStringToFile(RecorditEnum.KEY_RELEASED.toString() + "=" + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        writeStringToFile(RecorditEnum.KEY_TYPED.toString() + "=" + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    private void writeStringToFile(String data) {
        try {
            FileUtils.writeStringToFile(file, data + "\n", "UTF-8", true);
            logger.info(data);
            WAITING = false;
        } catch (IOException ex) {
            logger.error("Write string to file ", ex);
            System.exit(-1);
        }
    }

    public RecorditListener withWaiting(int timeWaiting) {
        this.timeWaiting = timeWaiting;
        return this;
    }

    public void  execute() {
        try {
            FileUtils.deleteQuietly(file);
            loggerGlobal.setLevel(Level.OFF);

            GlobalScreen.registerNativeHook();

            RecorditListener recorditListener = new RecorditListener();

            // Add the appropriate listeners.
            GlobalScreen.addNativeMouseListener(recorditListener);
            GlobalScreen.addNativeMouseMotionListener(recorditListener);
            GlobalScreen.addNativeMouseWheelListener(recorditListener);
            GlobalScreen.addNativeKeyListener(recorditListener);

            new Thread(() -> {
                while(true) {
                    if (WAITING) {
                        writeStringToFile(RecorditEnum.WAITING.toString()+ "=" + timeWaiting);
                    }

                    try {
                        Thread.sleep(timeWaiting);
                    } catch (InterruptedException e) {

                    }

                    if (!WAITING) {
                        WAITING = true;
                    }
                }
            }).start();
        }
        catch (NativeHookException ex) {
            logger.error("There was a problem registering the native hook. ", ex);
            System.exit(-1);
        }
    }
    public static void main(String[] args) {
        new RecorditListener().execute();
    }

}
