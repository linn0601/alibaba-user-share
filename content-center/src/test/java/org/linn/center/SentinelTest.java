package org.linn.center;

import org.springframework.web.client.RestTemplate;

/**
 * created by linn  20/3/29 9:37
 **/
public class SentinelTest {

    public static void main(String[] args) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();

        for (int i = 0; i < 100; i++) {
            String template = restTemplate.getForObject("http://localhost:8088/actuator", String.class);
            Thread.sleep(500);
        }
    }
}
