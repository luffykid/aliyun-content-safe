package com.kid.aliyuncontentsafe;

import java.util.UUID;

public class MediaComparison {
    private String url;
    private String dataId;
    private Extras extras;
    public static MediaComparison newInstance(String url, String faceUrl) {
        checkValid(url, faceUrl);
        MediaComparison mediaComparison = new MediaComparison();
        mediaComparison.dataId = UUID.randomUUID().toString();
        mediaComparison.url = url;
        Extras extras = new Extras(faceUrl);
        mediaComparison.extras = extras;
        return mediaComparison;
    }

    private static void checkValid(String url, String faceUrl) {
        if (url == null)
            throw new NullPointerException("url must not be null!");
        if (faceUrl == null)
            throw new NullPointerException("faceUrl must not be null!");
        if("".equals(url))
            throw new IllegalArgumentException("url must not be empty string!");
        if("".equals(faceUrl))
            throw new IllegalArgumentException("faceUrl must not be empty string!");
    }

    static class Extras {
        private String faceUrl;

        public Extras(String faceUrl) {
            this.faceUrl = faceUrl;
        }

        public String getFaceUrl() {
            return faceUrl;
        }
    }

    public String getUrl() {
        return url;
    }

    public String getDataId() {
        return dataId;
    }

    public Extras getExtras() {
        return extras;
    }
}
