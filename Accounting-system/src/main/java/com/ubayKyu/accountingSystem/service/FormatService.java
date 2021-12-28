package com.ubayKyu.accountingSystem.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatService {

    public static String FormatDateTime (LocalDateTime dateTime) {
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String answer =  dateFormat.format(dateTime);
    return answer;
    }
    
    public static Integer parseIntOrNull(String value) {//檢查字串是否為數字並回傳，不是的話回傳NULL
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
