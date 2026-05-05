package aiss.YouTubeMiner.model.youtube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeChannel {

    @JsonProperty("id")
    private String id;

    @JsonProperty("snippet")
    private YouTubeChannelSnippet snippet;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public YouTubeChannelSnippet getSnippet() { return snippet; }
    public void setSnippet(YouTubeChannelSnippet snippet) { this.snippet = snippet; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class YouTubeChannelSnippet {
        @JsonProperty("title")
        private String title;
        @JsonProperty("description")
        private String description;
        @JsonProperty("publishedAt")
        private String publishedAt;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getPublishedAt() { return publishedAt; }
        public void setPublishedAt(String publishedAt) { this.publishedAt = publishedAt; }
    }
}