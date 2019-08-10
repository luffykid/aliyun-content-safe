package com.kid.aliyuncontentsafetest;

import com.kid.aliyuncontentsafe.AliyunContentSafe;
import com.kid.aliyuncontentsafe.ScanMedia;
import org.junit.Test;

import java.util.*;

public class ImageScanTest {

    private final static String rfc1036Pattern = "EEE, dd MMM yyyy HH:mm:ss z";

    @Test
    public void test() {
        AliyunContentSafe aliyunContentSafe = new AliyunContentSafe();
        String imageUrl = "http://139.219.13.249/vimg/icon/market/efe127fa-au=3798078917,1929861432&fm=15&gp=0.jpg";
        String result = aliyunContentSafe.scanPornImage(Arrays.asList(ScanMedia.newInstance(imageUrl)));
        System.out.println(result);
    }






}
