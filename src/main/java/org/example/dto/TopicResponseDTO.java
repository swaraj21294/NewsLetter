package org.example.dto;

import java.util.ArrayList;
import java.util.List;

public class TopicResponseDTO {
    private Long id;
    private String name;
    private String description;
    private List<TopicSubscriberResponseDTO> subscribers = new ArrayList<>();
    private List<TopicContentResponseDTO> contents = new ArrayList<>();
    
    // Constructors
    public TopicResponseDTO() {}
    
    public TopicResponseDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public List<TopicSubscriberResponseDTO> getSubscribers() { return subscribers; }
    public void setSubscribers(List<TopicSubscriberResponseDTO> subscribers) { this.subscribers = subscribers; }
    
    public List<TopicContentResponseDTO> getContents() { return contents; }
    public void setContents(List<TopicContentResponseDTO> contents) { this.contents = contents; }
}