package aiss.YouTubeMiner.service;

import aiss.YouTubeMiner.model.youtube.*;
import aiss.YouTubeMiner.model.videominer.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class YouTubeMinerService {

    @Value("${youtube.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String YT_BASE = "https://www.googleapis.com/youtube/v3";

    public Channel getChannel(String channelId, int maxVideos, int maxComments) {

        // 1. Obtener datos del canal
        String channelUrl = YT_BASE + "/channels?part=snippet&id=" + channelId + "&key=" + apiKey;
        Map response = restTemplate.getForObject(channelUrl, Map.class);

        List<Map> items = (List<Map>) response.get("items");
        if (items == null || items.isEmpty()) {
            return null;
        }

        Map channelItem = items.get(0);
        Map snippet = (Map) channelItem.get("snippet");

        // Mapear Channel
        Channel channel = new Channel();
        channel.setId(channelId);
        channel.setName((String) snippet.get("title"));
        channel.setDescription((String) snippet.get("description"));
        channel.setCreatedTime((String) snippet.get("publishedAt"));

        // 2. Obtener videos del canal
        String videosUrl = YT_BASE + "/search?part=snippet&channelId=" + channelId
                + "&type=video&maxResults=" + maxVideos + "&key=" + apiKey;
        Map videosResponse = restTemplate.getForObject(videosUrl, Map.class);
        List<Map> videoItems = (List<Map>) videosResponse.get("items");

        List<Video> videos = new ArrayList<>();

        if (videoItems != null) {
            for (Map videoItem : videoItems) {
                Map idMap = (Map) videoItem.get("id");
                String videoId = (String) idMap.get("videoId");
                Map videoSnippet = (Map) videoItem.get("snippet");

                // Mapear Video
                Video video = new Video();
                video.setId(videoId);
                video.setName((String) videoSnippet.get("title"));
                video.setDescription((String) videoSnippet.get("description"));
                video.setReleaseTime((String) videoSnippet.get("publishedAt"));

                // Mapear User
                User user = new User();
                user.setId((String) videoSnippet.get("channelId"));
                user.setName((String) videoSnippet.get("channelTitle"));
                user.setUserLink("https://www.youtube.com/channel/" + channelId);
                Map thumbnails = (Map) videoSnippet.get("thumbnails");
                if (thumbnails != null) {
                    Map defaultThumb = (Map) thumbnails.get("default");
                    if (defaultThumb != null) {
                        user.setPictureLink((String) defaultThumb.get("url"));
                    }
                }
                video.setUser(user);

                // 3. Obtener comentarios del video
                List<Comment> comments = new ArrayList<>();
                try {
                    String commentsUrl = YT_BASE + "/commentThreads?part=snippet&videoId=" + videoId
                            + "&maxResults=" + maxComments + "&key=" + apiKey;
                    Map commentsResponse = restTemplate.getForObject(commentsUrl, Map.class);
                    List<Map> commentItems = (List<Map>) commentsResponse.get("items");

                    if (commentItems != null) {
                        for (Map commentItem : commentItems) {
                            Map commentSnippet = (Map) commentItem.get("snippet");
                            Map topLevel = (Map) commentSnippet.get("topLevelComment");
                            Map topSnippet = (Map) topLevel.get("snippet");

                            Comment comment = new Comment();
                            comment.setId((String) topLevel.get("id"));
                            comment.setText((String) topSnippet.get("textDisplay"));
                            comment.setCreatedOn((String) topSnippet.get("publishedAt"));
                            comments.add(comment);
                        }
                    }
                } catch (Exception e) {
                    // Algunos videos tienen comentarios desactivados
                    System.out.println("Comments disabled for video: " + videoId);
                }
                video.setComments(comments);

                // 4. Obtener captions del video
                List<Caption> captions = new ArrayList<>();
                try {
                    String captionsUrl = YT_BASE + "/captions?part=snippet&videoId=" + videoId + "&key=" + apiKey;
                    Map captionsResponse = restTemplate.getForObject(captionsUrl, Map.class);
                    List<Map> captionItems = (List<Map>) captionsResponse.get("items");

                    if (captionItems != null) {
                        for (Map captionItem : captionItems) {
                            Map captionSnippet = (Map) captionItem.get("snippet");

                            Caption caption = new Caption();
                            caption.setId((String) captionItem.get("id"));
                            caption.setLanguage((String) captionSnippet.get("language"));
                            caption.setLink("https://www.youtube.com/watch?v=" + videoId);
                            captions.add(caption);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Captions not available for video: " + videoId);
                }
                video.setCaptions(captions);

                videos.add(video);
            }
        }

        channel.setVideos(videos);
        return channel;
    }
}