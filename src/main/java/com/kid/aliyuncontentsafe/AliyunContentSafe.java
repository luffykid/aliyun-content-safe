package com.kid.aliyuncontentsafe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AliyunContentSafe {
    private String baseUri = "https://green.cn-shanghai.aliyuncs.com";
    private String function = "/green/image/scan";
    private String accessKeySecret = "PsKmp1FlpgVpIlMomt2vE1Bi7aSBsG";
    private String accessKeyId = "LTAIGhzoQKSH1gGv";
    private RestTemplate client = new RestTemplate();
    private SimpleDateFormat rfc1036Format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);


    private HttpEntity<String> buildHttpEntity(Map<String, Object> body, String function) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(body);
        return buildHttpEntity(jsonBody, function);
    }

    private HttpEntity<String> buildHttpEntity(String jsonBody, String function) {
        HttpHeaders headers = buildHttpHeaders(jsonBody);
        String signature = generateSignature(headers, function);
        System.out.println(signature);
        headers.set("Authorization", "acs " + this.accessKeyId + ":" + signature);
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonBody, headers);
        return httpEntity;
    }

    private HttpHeaders buildHttpHeaders(String jsonBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-acs-version", "2018-05-09");
        headers.set("x-acs-signature-version", "1.0");
        headers.set("x-acs-signature-method", "HMAC-SHA1");
        headers.set("x-acs-signature-nonce", UUID.randomUUID().toString());
        headers.set("Date", rfc1036Format.format(new Date()));
        String contentMd5 = generateContentMd5(jsonBody);
        headers.set("Content-MD5", contentMd5);
        return headers;
    }


    private String generateContentMd5(String jsonBody) {
        String jsonBodyMd5DigestedAndBase64Encoded = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytesDigested = md.digest(jsonBody.getBytes());
            Base64.Encoder base64Encoder = Base64.getEncoder();
            jsonBodyMd5DigestedAndBase64Encoded =  new String(base64Encoder.encode(bytesDigested));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return jsonBodyMd5DigestedAndBase64Encoded;
    }

    private String generateSignature(HttpHeaders headers, String function) {
        String strToBeSigned = convertToStringToBeSigned(headers, function);
        SecretKeySpec keySpec = new SecretKeySpec(this.accessKeySecret.getBytes(), "HmacSHA1");
        try {
            Mac hMacSha1 = Mac.getInstance("HmacSHA1");
            hMacSha1.init(keySpec);
            Base64.Encoder base64Encoder = Base64.getEncoder();
            return new String(base64Encoder.encode(hMacSha1.doFinal(strToBeSigned.getBytes())));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String convertToStringToBeSigned(HttpHeaders headers, String function) {
        StringBuilder sb = new StringBuilder();
        sb.append("POST\n");
        sb.append(headers.get("Accept").get(0) + "\n");
        sb.append(headers.get("Content-MD5").get(0) + "\n");
        sb.append(headers.get("Content-Type").get(0) + "\n");
        sb.append(headers.get("Date").get(0) + "\n");
        sb.append("x-acs-signature-method:" + headers.get("x-acs-signature-method").get(0) + "\n");
        sb.append("x-acs-signature-nonce:" + headers.get("x-acs-signature-nonce").get(0) + "\n");
        sb.append("x-acs-signature-version:" + headers.get("x-acs-signature-version").get(0) + "\n");
        sb.append("x-acs-version:" + headers.get("x-acs-version").get(0)+"\n");
        sb.append(function);
        System.out.println(sb.toString());
        return sb.toString();
    }

    public String scanPornImage(List<ScanMedia> imagesTobeScanned) {
        try {
            String scanPornImageFunction = "/green/image/scan";
            Map<String, Object> body = new HashMap<>();
            String[] scenes = new String[]{"porn"};
            body.put("scenes", scenes);
            body.put("tasks", imagesTobeScanned);
            HttpEntity<String> httpEntity = buildHttpEntity(body, scanPornImageFunction);
            String requestUri = baseUri + scanPornImageFunction;
            HttpEntity<String> response = client.postForEntity(requestUri,
                    httpEntity,
                    String.class);
            return response.getBody();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String asynScanPornVideo(List<ScanMedia> videosTobeScanned) {
        try {
            String asynScanPornVideoFunction = "/green/video/asyncscan";
            Map<String, Object> body = new HashMap<>();
            String[] scenes = new String[]{"porn"};
            body.put("scenes", scenes);
            body.put("tasks", videosTobeScanned);
            HttpEntity<String> httpEntity = buildHttpEntity(body, asynScanPornVideoFunction);
            String requestUri = baseUri + asynScanPornVideoFunction;
            HttpEntity<String> response = client.postForEntity(requestUri,
                    httpEntity,
                    String.class);
            return response.getBody();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }


    public String getAsynScanVideoResults(String taskIdsJson) {
        String getAsynScanPornVideoResultsFunction = "/green/video/results";
        HttpEntity<String> httpEntity = buildHttpEntity(taskIdsJson, getAsynScanPornVideoResultsFunction);
        String requestUri = baseUri + getAsynScanPornVideoResultsFunction;
        HttpEntity<String> response = client.postForEntity(requestUri,
                httpEntity,
                String.class);
        return response.getBody();
    }

    public String scanTerrorismImage(List<ScanMedia> imagesTobeScanned) {
        try {
            String scanPornImageFunction = "/green/image/scan";
            Map<String, Object> body = new HashMap<>();
            String[] scenes = new String[]{"terrorism"};
            body.put("scenes", scenes);
            body.put("tasks", imagesTobeScanned);
            HttpEntity<String> httpEntity = buildHttpEntity(body, scanPornImageFunction);
            String requestUri = baseUri + scanPornImageFunction;
            HttpEntity<String> response = client.postForEntity(requestUri,
                    httpEntity,
                    String.class);
            return response.getBody();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String asynScanTerrorismVideo(List<ScanMedia> videosTobeScanned) {
        try {
            String asynScanPornVideoFunction = "/green/video/asyncscan";
            Map<String, Object> body = new HashMap<>();
            String[] scenes = new String[]{"terrorism"};
            body.put("scenes", scenes);
            body.put("tasks", videosTobeScanned);
            HttpEntity<String> httpEntity = buildHttpEntity(body, asynScanPornVideoFunction);
            String requestUri = baseUri + asynScanPornVideoFunction;
            HttpEntity<String> response = client.postForEntity(requestUri,
                    httpEntity,
                    String.class);
            return response.getBody();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String scanSfaceImage(List<ScanMedia> imagesTobeScanned) {
        try {
            String scanPornImageFunction = "/green/image/scan";
            Map<String, Object> body = new HashMap<>();
            String[] scenes = new String[]{"sface"};
            body.put("scenes", scenes);
            body.put("tasks", imagesTobeScanned);
            HttpEntity<String> httpEntity = buildHttpEntity(body, scanPornImageFunction);
            String requestUri = baseUri + scanPornImageFunction;
            HttpEntity<String> response = client.postForEntity(requestUri,
                    httpEntity,
                    String.class);
            return response.getBody();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String asynScanSfaceVideo(List<ScanMedia> videosTobeScanned) {
        try {
            String asynScanPornVideoFunction = "/green/video/asyncscan";
            Map<String, Object> body = new HashMap<>();
            String[] scenes = new String[]{"sface"};
            body.put("scenes", scenes);
            body.put("tasks", videosTobeScanned);
            HttpEntity<String> httpEntity = buildHttpEntity(body, asynScanPornVideoFunction);
            String requestUri = baseUri + asynScanPornVideoFunction;
            HttpEntity<String> response = client.postForEntity(requestUri,
                    httpEntity,
                    String.class);
            return response.getBody();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String scanAdImage(List<ScanMedia> imagesTobeScanned) {
        try {
            String scanPornImageFunction = "/green/image/scan";
            Map<String, Object> body = new HashMap<>();
            String[] scenes = new String[]{"ad"};
            body.put("scenes", scenes);
            body.put("tasks", imagesTobeScanned);
            HttpEntity<String> httpEntity = buildHttpEntity(body, scanPornImageFunction);
            String requestUri = baseUri + scanPornImageFunction;
            HttpEntity<String> response = client.postForEntity(requestUri,
                    httpEntity,
                    String.class);
            return response.getBody();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String asynScanAdVideo(List<ScanMedia> videosTobeScanned) {
        try {
            String asynScanPornVideoFunction = "/green/video/asyncscan";
            Map<String, Object> body = new HashMap<>();
            String[] scenes = new String[]{"ad"};
            body.put("scenes", scenes);
            body.put("tasks", videosTobeScanned);
            HttpEntity<String> httpEntity = buildHttpEntity(body, asynScanPornVideoFunction);
            String requestUri = baseUri + asynScanPornVideoFunction;
            HttpEntity<String> response = client.postForEntity(requestUri,
                    httpEntity,
                    String.class);
            return response.getBody();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String scanLiveImage(List<ScanMedia> imagesTobeScanned) {
        try {
            String scanPornImageFunction = "/green/image/scan";
            Map<String, Object> body = new HashMap<>();
            String[] scenes = new String[]{"live"};
            body.put("scenes", scenes);
            body.put("tasks", imagesTobeScanned);
            HttpEntity<String> httpEntity = buildHttpEntity(body, scanPornImageFunction);
            String requestUri = baseUri + scanPornImageFunction;
            HttpEntity<String> response = client.postForEntity(requestUri,
                    httpEntity,
                    String.class);
            return response.getBody();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String asynScanLiveVideo(List<ScanMedia> videosTobeScanned) {
        try {
            String asynScanPornVideoFunction = "/green/video/asyncscan";
            Map<String, Object> body = new HashMap<>();
            String[] scenes = new String[]{"live"};
            body.put("scenes", scenes);
            body.put("tasks", videosTobeScanned);
            HttpEntity<String> httpEntity = buildHttpEntity(body, asynScanPornVideoFunction);
            String requestUri = baseUri + asynScanPornVideoFunction;
            HttpEntity<String> response = client.postForEntity(requestUri,
                    httpEntity,
                    String.class);
            return response.getBody();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String scanLogoImage(List<ScanMedia> imagesTobeScanned) {
        try {
            String scanPornImageFunction = "/green/image/scan";
            Map<String, Object> body = new HashMap<>();
            String[] scenes = new String[]{"logo"};
            body.put("scenes", scenes);
            body.put("tasks", imagesTobeScanned);
            HttpEntity<String> httpEntity = buildHttpEntity(body, scanPornImageFunction);
            String requestUri = baseUri + scanPornImageFunction;
            HttpEntity<String> response = client.postForEntity(requestUri,
                    httpEntity,
                    String.class);
            return response.getBody();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
