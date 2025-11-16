package org.example.dto;

import jakarta.validation.constraints.NotBlank;

public class TopicDTO {
    private Long id;
    
    @NotBlank(message = "Topic name is required")
    private String name;
    
    private String description;
    
    public TopicDTO() {}
    
    public TopicDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}