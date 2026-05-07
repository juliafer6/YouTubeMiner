package aiss.YouTubeMiner.controller;

import aiss.YouTubeMiner.exception.ChannelNotFoundException;
import aiss.YouTubeMiner.model.videominer.Channel;
import aiss.YouTubeMiner.etl.Transformer;
import aiss.YouTubeMiner.service.VideoMinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/youtubeminer/channels")
public class YouTubeMinerController {

    // ¡AQUÍ ESTÁ LA CLAVE! Usamos el Transformer que arreglamos antes
    @Autowired
    private Transformer transformer;

    // Usamos el servicio nuevo que acabamos de crear
    @Autowired
    private VideoMinerService videoMinerService;

    // GET: solo lectura para pruebas, no envía a VideoMiner
    @GetMapping("/{channelId}")
    public ResponseEntity<Channel> previewChannel(
            @PathVariable String channelId,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxComments) throws ChannelNotFoundException {

        // Llamamos al método buildChannel de TU Transformer
        Channel channel = transformer.buildChannel(channelId, maxVideos, maxComments);

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

        // 1. Transformamos los datos de YouTube a nuestro modelo
        Channel channel = transformer.buildChannel(channelId, maxVideos, maxComments);

        if (channel == null) {
            throw new ChannelNotFoundException(channelId);
        }

        // 2. Lo enviamos usando el servicio limpio
        Channel createdChannel = videoMinerService.postChannel(channel);

        return ResponseEntity.ok(createdChannel);
    }
}