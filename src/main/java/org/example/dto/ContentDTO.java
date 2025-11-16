package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ContentDTO {
    private Long id;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Body is required")
    private String body;
    
    @NotNull(message = "Scheduled time is required")
    private LocalDateTime scheduledTime;
    
    @NotNull(message = "Topic ID is required")
    private Long topicId;
    
    public ContentDTO() {}
    
    public ContentDTO(String title, String body, LocalDateTime scheduledTime, Long topicId) {
        this.title = title;
        this.body = body;
        this.scheduledTime = scheduledTime;
        this.topicId = topicId;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
    
    public LocalDateTime getScheduledTime() { return scheduledTime; }
    public void setScheduledTime(LocalDateTime scheduledTime) { this.scheduledTime = scheduledTime; }
    
    public Long getTopicId() { return topicId; }
    public void setTopicId(Long topicId) { this.topicId = topicId; }
}