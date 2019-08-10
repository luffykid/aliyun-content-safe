package com.kid.aliyuncontentsafetest;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClutterTest {
    @Ignore
    @Test
    public void testMd5() throws NoSuchAlgorithmException {
        String body = "{\"scenes\": " +
                "[\"porn\"], " +
                "\"tasks\": [" + "{\"dataId\": " +
                "\"test2NInmO$tAON6qYUrtCRgLo-1mwxdi\"," +
                "\"url\": \"https://img.alicdn.com/tfs/TB1urBOQFXXXXbMXFXXXXXXXXXX-1442-257.png\" " +
                "}]}";
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] base64 = Base64.getEncoder().encode(md.digest(body.getBytes()));
        System.out.println(bytesToHex(md.digest(body.getBytes())));
        System.out.println("length: " + base64.length);
        String str = new String(base64, StandardCharsets.UTF_8);
        String str1 = new String(base64, StandardCharsets.US_ASCII);
        String str2 = new String(base64, StandardCharsets.ISO_8859_1);
        String str3 = new String(base64, StandardCharsets.UTF_16);
        System.out.println(str);
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
    }

    @Ignore
    @Test
    public void testNewGMTDate() {
        Calendar calendar = Calendar.getInstance();
        TimeZone gmtTz = TimeZone.getTimeZone("GMT");
        GregorianCalendar rightNow = new GregorianCalendar(gmtTz);
        Date mydate=rightNow.getTime();
        System.out.println(new Date());
        System.out.println(calendar.toString());
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(mydate.toGMTString());
    }

    @Test
    public void testGMT() {
        HttpHeaders headers = new HttpHeaders();

        System.out.println(headers.getDate());
    }


    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if(hex.length() < 2){
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
