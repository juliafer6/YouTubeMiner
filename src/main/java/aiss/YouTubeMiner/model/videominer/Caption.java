package aiss.YouTubeMiner.model.videominer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Caption {
    @JsonProperty("id")
    private String id;
    @JsonProperty("language")
    private String language;
    @JsonProperty("link")
    private String link;

    public Caption() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}