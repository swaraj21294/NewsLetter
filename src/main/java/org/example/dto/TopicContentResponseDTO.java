package org.example.dto;

import java.time.LocalDateTime;

public class TopicContentResponseDTO {
    private Long id;
    private String title;
    private String body;
    private LocalDateTime scheduledTime;
    private boolean sent;
    
    public TopicContentResponseDTO() {}
    
    public TopicContentResponseDTO(Long id, String title, String body, LocalDateTime scheduledTime, 
                             boolean sent) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.scheduledTime = scheduledTime;
        this.sent = sent;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
    
    public LocalDateTime getScheduledTime() { return scheduledTime; }
    public void setScheduledTime(LocalDateTime scheduledTime) { this.scheduledTime = scheduledTime; }
    
    public boolean isSent() { return sent; }
    public void setSent(boolean sent) { this.sent = sent; }
    
}