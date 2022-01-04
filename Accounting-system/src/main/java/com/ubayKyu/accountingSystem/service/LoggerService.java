package com.ubayKyu.accountingSystem.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class LoggerService {


    public static void WriteLoggerFile(String message) throws IOException {
     //   System.out.println(ClassLoader.getSystemResource(""));
     //   System.out.println(System.getProperty("user.dir"));

        String path = System.getProperty("user.dir");//當前專案路徑
        path += "\\log.txt";
        //System.out.println(path);
        String path2 = "D:\\log.txt";
        File file = new File(path);
            if(!file.exists()){
            file.getParentFile().mkdirs();
        }
            file.createNewFile();

        // write
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
            bw.write(message+"\r\n");
            bw.flush();
            bw.close();
            fw.close();

        }
}
