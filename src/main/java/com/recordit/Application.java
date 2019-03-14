package com.recordit;

import org.apache.commons.lang3.StringUtils;

import com.recordit.listener.RecorditListener;
import com.recordit.play.RecorditPlay;

public class Application {
    public static void main(String[] args) {
        System.out.println("Thong tin tham so");
        System.out.println("1 - Ghi lai thao tac chuot va ban phim");
        System.out.println("2 - Phat lai thao tac chuot va ban phim");
        if (args != null) {
            if (StringUtils.equals("1", args[0])) {
                RecorditListener.execute();
            }
            if (StringUtils.equals("2", args[0])) {
                RecorditPlay.execute();
            }
        } else {
            System.out.println("Thieu tham so");
        }
    }
}
