package aiss.YouTubeMiner.model.youtube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeVideo {

    @JsonProperty("id")
    private YouTubeVideoId id;

    @JsonProperty("snippet")
    private YouTubeVideoSnippet snippet;

    public YouTubeVideoId getId() { return id; }
    public void setId(YouTubeVideoId id) { this.id = id; }
    public YouTubeVideoSnippet getSnippet() { return snippet; }
    public void setSnippet(YouTubeVideoSnippet snippet) { this.snippet = snippet; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class YouTubeVideoId {
        @JsonProperty("videoId")
        private String videoId;

        public String getVideoId() { return videoId; }
        public void setVideoId(String videoId) { this.videoId = videoId; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class YouTubeVideoSnippet {
        @JsonProperty("title")
        private String title;
        @JsonProperty("description")
        private String description;
        @JsonProperty("publishedAt")
        private String publishedAt;
        @JsonProperty("channelId")
        private String channelId;
        @JsonProperty("channelTitle")
        private String channelTitle;
        @JsonProperty("thumbnails")
        private Thumbnails thumbnails;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getPublishedAt() { return publishedAt; }
        public void setPublishedAt(String publishedAt) { this.publishedAt = publishedAt; }
        public String getChannelId() { return channelId; }
        public void setChannelId(String channelId) { this.channelId = channelId; }
        public String getChannelTitle() { return channelTitle; }
        public void setChannelTitle(String channelTitle) { this.channelTitle = channelTitle; }
        public Thumbnails getThumbnails() { return thumbnails; }
        public void setThumbnails(Thumbnails thumbnails) { this.thumbnails = thumbnails; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Thumbnails {
        @JsonProperty("default")
        private Thumbnail defaultThumbnail;

        public Thumbnail getDefaultThumbnail() { return defaultThumbnail; }
        public void setDefaultThumbnail(Thumbnail defaultThumbnail) { this.defaultThumbnail = defaultThumbnail; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Thumbnail {
        @JsonProperty("url")
        private String url;

        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
    }
}