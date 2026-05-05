package aiss.YouTubeMiner.model.youtube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeCaption {

    @JsonProperty("id")
    private String id;

    @JsonProperty("snippet")
    private YouTubeCaptionSnippet snippet;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public YouTubeCaptionSnippet getSnippet() { return snippet; }
    public void setSnippet(YouTubeCaptionSnippet snippet) { this.snippet = snippet; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class YouTubeCaptionSnippet {
        @JsonProperty("language")
        private String language;
        @JsonProperty("trackKind")
        private String trackKind;

        public String getLanguage() { return language; }
        public void setLanguage(String language) { this.language = language; }
        public String getTrackKind() { return trackKind; }
        public void setTrackKind(String trackKind) { this.trackKind = trackKind; }
    }
}