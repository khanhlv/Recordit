package com.recordit.scenario;

import com.recordit.play.RecorditPlay;
import com.recordit.utils.KeyUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ScenarioV2U {
    private static final Logger logger = LoggerFactory.getLogger(ScenarioV2U.class);
    private static final File file = new File("data/phone.txt");
    private static ConcurrentLinkedQueue<String> linkedQueue = new ConcurrentLinkedQueue<>();

    private void readPhone() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));

            String input = null;
            while ((input = in.readLine()) != null) {
                File isFile = new File("data/" + input + ".png");
                if (!isFile.exists()) {
                    System.out.println(input);
                    linkedQueue.add(input);
                }
            }
        } catch (Exception ex) {
            logger.error("ScenarioV2U", ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public void execute() {
        ScenarioV2U scenarioV2U = new ScenarioV2U();

        logger.info("#V2U_STARTING .......");

        // 1. Read Phone
        scenarioV2U.readPhone();

        logger.info("#V2U_QUEUE[" + linkedQueue.size() + "]");

        while(true) {
            String phone = linkedQueue.poll();
            logger.info("#V2U_PHONE[" + phone + "]");
            // 2. Copy phone
            KeyUtils.keyCopyValue(phone);

            // 3. Run play listener
            new RecorditPlay().withFileName(phone).execute();

            Scanner in = new Scanner(System.in);

            System.out.print("Enter continue PHONE_SIZE["+linkedQueue.size()+"]");
            String line = in.nextLine();
            logger.info("Waiting ......" + line);

//            try {
//                Thread.sleep(2000);
//                logger.info("Waiting ......");
//            } catch (InterruptedException e) {
//            }

            if (linkedQueue.size() == 0) {
                logger.info("#V2U_EXITING .....!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        ScenarioV2U scenarioV2U = new ScenarioV2U();

        scenarioV2U.execute();
    }
}
