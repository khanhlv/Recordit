package com.recordit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.recordit.listener.RecorditListener;
import com.recordit.play.RecorditPlay;

public class Application {
    public static void main(String[] args) {
        System.out.println("Thong tin tham so");
        System.out.println("1 - Ghi lai thao tac chuot va ban phim");
        System.out.println("2 1 - Phat lai thao tac chuot va ban phim voi 1 lan");
        System.out.println("2 n - Phat lai thao tac chuot va ban phim voi n lan");
        if (args != null && args.length > 0) {
            if (StringUtils.equals("1", args[0])) {
                new RecorditListener().execute();
            }
            if (StringUtils.equals("2", args[0])) {
                if (StringUtils.isNotBlank(args[1])) {
                    for (int i = 0; i <= NumberUtils.toInt(args[1]); i++) {
                        new RecorditPlay().execute();
                    }
                } else {
                    new RecorditPlay().execute();
                }
            }
        } else {
            System.out.println("Thieu tham so");
        }
    }
}
