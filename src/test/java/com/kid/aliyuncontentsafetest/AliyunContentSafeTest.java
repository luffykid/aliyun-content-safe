package com.kid.aliyuncontentsafetest;

import com.jayway.jsonpath.JsonPath;
import com.kid.aliyuncontentsafe.AliyunContentSafe;
import com.kid.aliyuncontentsafe.ScanMedia;
import com.sun.xml.internal.bind.v2.TODO;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class AliyunContentSafeTest {

    private final static String rfc1036Pattern = "EEE, dd MMM yyyy HH:mm:ss z";

    @Test
    public void testScanNormalImage() {
        AliyunContentSafe aliyunContentSafe = new AliyunContentSafe();
        String imageUrl = "http://139.219.13.249/vimg/icon/market/efe127fa-au=3798078917,1929861432&fm=15&gp=0.jpg";
        String result = aliyunContentSafe.scanPornImage(Arrays.asList(ScanMedia.newInstance(imageUrl)));
        Assert.assertEquals("200", JsonPath.read(result, "$.code").toString());
        Assert.assertEquals("normal", JsonPath.read(result,"$.data[0].results[0].label"));
        System.out.println(result);
    }

    @Test
    public void testScanNormalVideo() throws InterruptedException {
        AliyunContentSafe aliyunContentSafe = new AliyunContentSafe();
        String videoUri = "http://139.219.13.249/vimg/icon/market/efe127fa-au=3798078917,1929861432&fm=15&gp=0.jpg";
        String result = aliyunContentSafe.asynScanPornVideo(Arrays.asList(ScanMedia.newInstance(videoUri)));
        Assert.assertEquals("200", JsonPath.read(result, "$.code").toString());
        System.out.println(result);
        Assert.assertEquals("1", JsonPath.read(result, "$.data.length()").toString());
        Thread.sleep(30000);
        String taskIds = convertToTaskIdsJson(result);
        System.out.println(taskIds);
        String scanResults = aliyunContentSafe
                                .getAsynScanVideoResults(taskIds);
        System.out.println(scanResults);

    }

    @Test
    public void testScanTerrorismImage() {
        AliyunContentSafe aliyunContentSafe = new AliyunContentSafe();
        String imageUrl = "http://139.219.13.249/vimg/icon/market/efe127fa-au=3798078917,1929861432&fm=15&gp=0.jpg";
        String result = aliyunContentSafe.scanTerrorismImage(Arrays.asList(ScanMedia.newInstance(imageUrl)));
        Assert.assertEquals("200", JsonPath.read(result, "$.code").toString());
        Assert.assertEquals("normal", JsonPath.read(result,"$.data[0].results[0].label"));
        System.out.println(result);
    }


    @Test
    public void testScanTerrorismVideo() throws InterruptedException {
        AliyunContentSafe aliyunContentSafe = new AliyunContentSafe();
        String videoUri = "http://139.219.13.249/vimg/icon/market/efe127fa-au=3798078917,1929861432&fm=15&gp=0.jpg";
        String result = aliyunContentSafe.asynScanTerrorismVideo(Arrays.asList(ScanMedia.newInstance(videoUri)));
        Assert.assertEquals("200", JsonPath.read(result, "$.code").toString());
        System.out.println(result);
        Assert.assertEquals("1", JsonPath.read(result, "$.data.length()").toString());
        Thread.sleep(30000);
        String taskIds = convertToTaskIdsJson(result);
        System.out.println(taskIds);
        String scanResults = aliyunContentSafe
                .getAsynScanVideoResults(taskIds);
        System.out.println(scanResults);
    }

    @Test
    public void testScanSfaceImage() {
        AliyunContentSafe aliyunContentSafe = new AliyunContentSafe();
        String imageUrl = "http://139.219.13.249/vimg/icon/market/efe127fa-au=3798078917,1929861432&fm=15&gp=0.jpg";
        String result = aliyunContentSafe.scanSfaceImage(Arrays.asList(ScanMedia.newInstance(imageUrl)));
        Assert.assertEquals("200", JsonPath.read(result, "$.code").toString());
        Assert.assertEquals("normal", JsonPath.read(result,"$.data[0].results[0].label"));
        System.out.println(result);
    }

    @Test
    public void testScanSfaceVideo() throws InterruptedException {
        AliyunContentSafe aliyunContentSafe = new AliyunContentSafe();
        String videoUri = "http://139.219.13.249/vimg/icon/market/efe127fa-au=3798078917,1929861432&fm=15&gp=0.jpg";
        String result = aliyunContentSafe.asynScanSfaceVideo(Arrays.asList(ScanMedia.newInstance(videoUri)));
        Assert.assertEquals("200", JsonPath.read(result, "$.code").toString());
        System.out.println(result);
        Assert.assertEquals("1", JsonPath.read(result, "$.data.length()").toString());
        Thread.sleep(30000);
        String taskIds = convertToTaskIdsJson(result);
        System.out.println(taskIds);
        String scanResults = aliyunContentSafe
                .getAsynScanVideoResults(taskIds);
        System.out.println(scanResults);
    }

    @Test
    public void testScanAdImage() {
        AliyunContentSafe aliyunContentSafe = new AliyunContentSafe();
        String imageUrl = "http://139.219.13.249/vimg/icon/market/efe127fa-au=3798078917,1929861432&fm=15&gp=0.jpg";
        String result = aliyunContentSafe.scanAdImage(Arrays.asList(ScanMedia.newInstance(imageUrl)));
        Assert.assertEquals("200", JsonPath.read(result, "$.code").toString());
        Assert.assertEquals("normal", JsonPath.read(result,"$.data[0].results[0].label"));
        System.out.println(result);
    }

    @Test
    public void testScanAdVideo() throws InterruptedException {
        AliyunContentSafe aliyunContentSafe = new AliyunContentSafe();
        String videoUri = "http://139.219.13.249/vimg/icon/market/efe127fa-au=3798078917,1929861432&fm=15&gp=0.jpg";
        String result = aliyunContentSafe.asynScanAdVideo(Arrays.asList(ScanMedia.newInstance(videoUri)));
        Assert.assertEquals("200", JsonPath.read(result, "$.code").toString());
        System.out.println(result);
        Assert.assertEquals("1", JsonPath.read(result, "$.data.length()").toString());
        Thread.sleep(30000);
        String taskIds = convertToTaskIdsJson(result);
        System.out.println(taskIds);
        String scanResults = aliyunContentSafe
                .getAsynScanVideoResults(taskIds);
        System.out.println(scanResults);
    }

    @Test
    public void testScanLiveImage() {
        AliyunContentSafe aliyunContentSafe = new AliyunContentSafe();
        String imageUrl = "http://139.219.13.249/vimg/icon/market/efe127fa-au=3798078917,1929861432&fm=15&gp=0.jpg";
        String result = aliyunContentSafe.scanLiveImage(Arrays.asList(ScanMedia.newInstance(imageUrl)));
        Assert.assertEquals("200", JsonPath.read(result, "$.code").toString());
        Assert.assertEquals("normal", JsonPath.read(result,"$.data[0].results[0].label"));
        System.out.println(result);
    }

    @Test
    public void testScanLiveVideo() throws InterruptedException {
        AliyunContentSafe aliyunContentSafe = new AliyunContentSafe();
        String videoUri = "http://139.219.13.249/vimg/icon/market/efe127fa-au=3798078917,1929861432&fm=15&gp=0.jpg";
        String result = aliyunContentSafe.asynScanLiveVideo(Arrays.asList(ScanMedia.newInstance(videoUri)));
        Assert.assertEquals("200", JsonPath.read(result, "$.code").toString());
        System.out.println(result);
        Assert.assertEquals("1", JsonPath.read(result, "$.data.length()").toString());
        Thread.sleep(30000);
        String taskIds = convertToTaskIdsJson(result);
        System.out.println(taskIds);
        String scanResults = aliyunContentSafe
                .getAsynScanVideoResults(taskIds);
        System.out.println(scanResults);
    }

    @Test
    public void testScanLogoImage() {
        AliyunContentSafe aliyunContentSafe = new AliyunContentSafe();
        String imageUrl = "http://139.219.13.249/vimg/icon/market/efe127fa-au=3798078917,1929861432&fm=15&gp=0.jpg";
        String result = aliyunContentSafe.scanLogoImage(Arrays.asList(ScanMedia.newInstance(imageUrl)));
        Assert.assertEquals("200", JsonPath.read(result, "$.code").toString());
        Assert.assertEquals("normal", JsonPath.read(result,"$.data[0].results[0].label"));
        System.out.println(result);
    }

    private String convertToTaskIdsJson(String asynScanPornVideoResponse) {
        Integer taskIdsSize = JsonPath.read(asynScanPornVideoResponse, "$.data.length()");
        List<String> taskIds = new ArrayList<>();
        for (int i = 0; i < taskIdsSize; i++) {
            String taskId = JsonPath.read(asynScanPornVideoResponse, "$.data["+i+"].taskId");
            taskIds.add(taskId);
            System.out.println(taskId);
        }
        return taskIds.toString();
    }





}
