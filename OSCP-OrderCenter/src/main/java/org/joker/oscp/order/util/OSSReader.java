package org.joker.oscp.order.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * 读取MinIO-OSS工具类
 * @author JOKER
 */
@Configuration
@ConfigurationProperties(prefix = "minio")
public class OSSReader {

    @Value("${minio.endpoint}")
    private String ENDPOINT;

    /**
     * 获取OSS上的json文件-【装换位json对象】
     * @param jsonAddress 座位文件的地址
     * @return 解析后的json对象
     */
    public ResponseEntity getSeatJsonByOSS(String jsonAddress) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity entity = new HttpEntity<String>(null,headers);
        ResponseEntity<String> exchange =
                restTemplate.exchange(ENDPOINT+jsonAddress,
                            HttpMethod.GET, entity, String.class);
        return exchange;
    }

}
