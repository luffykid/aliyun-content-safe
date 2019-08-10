package com.kid.aliyuncontentsafe;

import java.util.UUID;


public class ScanMedia {
    private String dataId;
    private String url;

    private ScanMedia(){};

    public static ScanMedia newInstance(String url) {
        if (url == null && "".equals(url))
            throw new IllegalArgumentException("url must not be null or empty string!");
        ScanMedia scanMedia = new ScanMedia();
        scanMedia.dataId = UUID.randomUUID().toString();
        scanMedia.url = url;
        return scanMedia;
    }

    public String getDataId() {
        return dataId;
    }

    public String getUrl() {
        return url;
    }
}
