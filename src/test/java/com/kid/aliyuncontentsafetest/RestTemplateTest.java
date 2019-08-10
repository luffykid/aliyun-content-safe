package com.kid.aliyuncontentsafetest;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplateTest {
    @Test
    public void test() {
        RestTemplate client = new RestTemplate();
        ResponseEntity<String> response = client.getForEntity("http://www.baidu.com", String.class);
        System.out.println(response);
    }
}
