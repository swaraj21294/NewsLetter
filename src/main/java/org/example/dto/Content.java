package org.example.dto;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "contents")
@Data
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String body;
    
    @Column(name = "scheduled_time")
    private LocalDateTime scheduledTime;
    
    private boolean sent = false;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;
    
    public Content() {}
    
    public Content(String title, String body, LocalDateTime scheduledTime, Topic topic) {
        this.title = title;
        this.body = body;
        this.scheduledTime = scheduledTime;
        this.topic = topic;
    }
}