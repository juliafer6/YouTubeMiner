package aiss.YouTubeMiner.controller;

import aiss.YouTubeMiner.exception.ChannelNotFoundException;
import aiss.YouTubeMiner.model.videominer.Channel;
import aiss.YouTubeMiner.service.YouTubeMinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/youtubemine")
public class YouTubeMinerController {

    @Autowired
    private YouTubeMinerService service;

    @Value("${videominer.base.url}")
    private String videoMinerUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    // GET: solo lectura para pruebas, no envía a VideoMiner
    @GetMapping("/{channelId}")
    public ResponseEntity<Channel> previewChannel(
            @PathVariable String channelId,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxComments) throws ChannelNotFoundException {

        Channel channel = service.getChannel(channelId, maxVideos, maxComments);

        if (channel == null) {
            throw new ChannelNotFoundException(channelId);
        }

        return ResponseEntity.ok(channel);
    }

    // POST: extrae datos y los envía a VideoMiner
    @PostMapping("/{channelId}")
    public ResponseEntity<Channel> mineAndSend(
            @PathVariable String channelId,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxComments) throws ChannelNotFoundException {

        Channel channel = service.getChannel(channelId, maxVideos, maxComments);

        if (channel == null) {
            throw new ChannelNotFoundException(channelId);
        }

        restTemplate.postForObject(videoMinerUrl + "/videominer/channels", channel, Channel.class);

        return ResponseEntity.ok(channel);
    }
}