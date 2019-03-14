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
import org.slf4j.LoggerFactory;

import com.recordit.enums.RecorditEnum;
import com.recordit.play.PlayKey;

public class KeyListener implements NativeKeyListener {
    private static final Logger loggerGlobal = Logger.getLogger(GlobalScreen.class.getPackage().getName());
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PlayKey.class);

    private static final File file = new File("data/key.txt");

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

            GlobalScreen.addNativeKeyListener(new KeyListener());
        } catch (NativeHookException ex) {
            logger.error("There was a problem registering the native hook. ", ex);
            System.exit(-1);
        }
    }
}