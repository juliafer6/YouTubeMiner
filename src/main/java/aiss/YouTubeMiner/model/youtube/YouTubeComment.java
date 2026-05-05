package aiss.YouTubeMiner.model.youtube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeComment {

    @JsonProperty("id")
    private String id;

    @JsonProperty("snippet")
    private YouTubeCommentSnippet snippet;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public YouTubeCommentSnippet getSnippet() { return snippet; }
    public void setSnippet(YouTubeCommentSnippet snippet) { this.snippet = snippet; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class YouTubeCommentSnippet {
        @JsonProperty("topLevelComment")
        private TopLevelComment topLevelComment;

        public TopLevelComment getTopLevelComment() { return topLevelComment; }
        public void setTopLevelComment(TopLevelComment topLevelComment) { this.topLevelComment = topLevelComment; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TopLevelComment {
        @JsonProperty("id")
        private String id;
        @JsonProperty("snippet")
        private TopLevelCommentSnippet snippet;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public TopLevelCommentSnippet getSnippet() { return snippet; }
        public void setSnippet(TopLevelCommentSnippet snippet) { this.snippet = snippet; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TopLevelCommentSnippet {
        @JsonProperty("textDisplay")
        private String textDisplay;
        @JsonProperty("publishedAt")
        private String publishedAt;
        @JsonProperty("authorDisplayName")
        private String authorDisplayName;
        @JsonProperty("authorChannelUrl")
        private String authorChannelUrl;
        @JsonProperty("authorProfileImageUrl")
        private String authorProfileImageUrl;
        @JsonProperty("authorChannelId")
        private AuthorChannelId authorChannelId;

        public String getTextDisplay() { return textDisplay; }
        public void setTextDisplay(String textDisplay) { this.textDisplay = textDisplay; }
        public String getPublishedAt() { return publishedAt; }
        public void setPublishedAt(String publishedAt) { this.publishedAt = publishedAt; }
        public String getAuthorDisplayName() { return authorDisplayName; }
        public void setAuthorDisplayName(String authorDisplayName) { this.authorDisplayName = authorDisplayName; }
        public String getAuthorChannelUrl() { return authorChannelUrl; }
        public void setAuthorChannelUrl(String authorChannelUrl) { this.authorChannelUrl = authorChannelUrl; }
        public String getAuthorProfileImageUrl() { return authorProfileImageUrl; }
        public void setAuthorProfileImageUrl(String authorProfileImageUrl) { this.authorProfileImageUrl = authorProfileImageUrl; }
        public AuthorChannelId getAuthorChannelId() { return authorChannelId; }
        public void setAuthorChannelId(AuthorChannelId authorChannelId) { this.authorChannelId = authorChannelId; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AuthorChannelId {
        @JsonProperty("value")
        private String value;

        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }
    }
}