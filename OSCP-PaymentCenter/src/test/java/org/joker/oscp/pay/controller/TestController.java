package org.joker.oscp.pay.controller;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.inject.internal.asm.$ByteVector;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestController {

    @Test
    public void testMinIOOss() {
        final String Url_per = "http://39.106.197.145:9090";

        String jsonUrl = "/oscp/seat/cgs.json";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity entity = new HttpEntity<String>(null,headers);
        ResponseEntity<String> exchange = restTemplate.exchange(Url_per+jsonUrl, HttpMethod.GET, entity, String.class);
        log.info("{}",exchange);
        log.info("======================>");
        log.info("body: {}", exchange.getBody());
        JsonObject jsonObject = new JsonParser().parse(exchange.getBody()).getAsJsonObject();
        String ids = jsonObject.get("ids").toString();
        log.info("ids: {}", ids);

    }

}
