package com.kid.aliyuncontentsafetest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class ImageScanTest {
    private String baseUri = "https://green.cn-shanghai.aliyuncs.com";
    private String function = "/green/image/scan";
    private String accessKeySecret = "PsKmp1FlpgVpIlMomt2vE1Bi7aSBsG";
    private String accessKeyId = "LTAIGhzoQKSH1gGv";
    private final static String rfc1036Pattern = "EEE, dd MMM yyyy HH:mm:ss z";

    @Test
    public void test() throws JsonProcessingException {
        RestTemplate client = new RestTemplate();
        Map<String, Object> body = new HashMap<>();
        String[] scenes = new String[] {"porn"};
        String imageUrl = "http://139.219.13.249/vimg/icon/market/efe127fa-au=3798078917,1929861432&fm=15&gp=0.jpg";
        ScanMedia[] tasks = new ScanMedia[]{ScanMedia.newInstance(imageUrl)};
        body.put("scenes", scenes);
        body.put("tasks", tasks);
        HttpEntity<String> httpEntity = buildHttpEntity(body);
        System.out.println(httpEntity);
        System.out.println(httpEntity.getBody());
        System.out.println(httpEntity.getHeaders());
        HttpEntity<String> response = client.postForEntity(this.baseUri+this.function, httpEntity, String.class);
        System.out.println(response.getBody());
    }

    private HttpEntity<String> buildHttpEntity(Map<String, Object> body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(body);
        HttpHeaders headers = buildHttpHeaders(jsonBody);
        String signature = generateSignature(headers);
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
        headers.set("Date", DateTool.rfc1036Format.format(new Date()));
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

    private String generateSignature(HttpHeaders headers) {
        String strToBeSigned = convertToStringToBeSigned(headers);
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

    private String convertToStringToBeSigned(HttpHeaders headers) {
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
        sb.append(this.function);
        System.out.println(sb.toString());
        return sb.toString();
    }




}
