package com.recordit.listener;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyListener implements NativeKeyListener {
    private static final Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());

    private static final File file = new File("D:/key.txt");

    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()) + "-" + e.getKeyCode());

//        try {
//            FileUtils.writeStringToFile(file, "KEY_PRESSED=" + e.getKeyCode() + "\n", "UTF-8", true);
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
//        try {
//            FileUtils.writeStringToFile(file, "KEY_RELEASED=" + e.getKeyCode() + "\n", "UTF-8", true);
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()) + "-" + e.getKeyCode());
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
//        try {
//            FileUtils.writeStringToFile(file, "KEY_TYPED=" + e.getKeyCode() + "\n", "UTF-8", true);
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
        System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()) + "-" + e.getKeyCode());
    }

    public static void main(String[] args) {
        try {
            logger.setUseParentHandlers(false);
            logger.setLevel(Level.ALL);

            GlobalScreen.registerNativeHook();

            GlobalScreen.addNativeKeyListener(new KeyListener());
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }
    }
}