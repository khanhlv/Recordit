package com.recordit.listener;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

public class MouseWheelListener implements NativeMouseWheelListener {
    public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
        System.out.println("Mouse Wheel Moved: " + e.getWheelRotation());
    }

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            ex.printStackTrace();

            System.exit(1);
        }

        GlobalScreen.addNativeMouseWheelListener(new MouseWheelListener());

    }
}