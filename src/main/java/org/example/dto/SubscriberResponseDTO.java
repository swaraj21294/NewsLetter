package org.example.dto;

public class SubscriberResponseDTO {
    private Long id;
    private String email;
    private String name;
    private Long topicId;
    private String topicName;
    
    public SubscriberResponseDTO() {}
    
    public SubscriberResponseDTO(Long id, String email, String name, Long topicId, String topicName) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.topicId = topicId;
        this.topicName = topicName;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Long getTopicId() { return topicId; }
    public void setTopicId(Long topicId) { this.topicId = topicId; }
    
    public String getTopicName() { return topicName; }
    public void setTopicName(String topicName) { this.topicName = topicName; }
}