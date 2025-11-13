package org.example.dto;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "topics")
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String name;
    
    private String description;
    
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Subscriber> subscribers = new ArrayList<>();
    
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Content> contents = new ArrayList<>();
    
    public Topic() {}
    
    public Topic(String name, String description) {
        this.name = name;
        this.description = description;
    }
}