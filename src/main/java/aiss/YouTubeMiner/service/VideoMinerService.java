package aiss.YouTubeMiner.service;

import aiss.YouTubeMiner.model.videominer.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Service
public class VideoMinerService {

    @Autowired
    private RestTemplate restTemplate;

    // Si lo abrimos desde Railway usará VIDEOMINER_URL, si no, localhost por defecto
    @Value("${videominer.base.url:http://localhost:8080}")
    private String videoMinerUrl;

    public Channel postChannel(Channel channel) {
        String url = videoMinerUrl + "/videominer/channels";

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "trabajoAiss123");

        HttpEntity<Channel> request = new HttpEntity<>(channel, headers);

        return restTemplate.postForObject(url, request, Channel.class);
    }
}