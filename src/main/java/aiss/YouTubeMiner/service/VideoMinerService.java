package aiss.YouTubeMiner.service;

import aiss.YouTubeMiner.model.videominer.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VideoMinerService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${videominer.base.url:http://localhost:8080}")
    private String videoMinerUrl;

    public Channel postChannel(Channel channel) {
        String url = videoMinerUrl + "/videominer/channels";
        // Enviamos el canal transformado a VideoMiner
        return restTemplate.postForObject(url, channel, Channel.class);
    }
}