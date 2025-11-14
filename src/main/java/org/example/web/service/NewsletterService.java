package org.example.web.service;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.Content;
import org.example.dto.Subscriber;
import org.example.web.repository.ContentRepository;
import org.example.web.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class NewsletterService {
    
    @Autowired
    private ContentRepository contentRepository;
    
    @Autowired
    private SubscriberRepository subscriberRepository;
    
    @Autowired
    private EmailService emailService;
    
//    @Scheduled(fixedRate = 60000) // Run every minute
    public void sendScheduledNewsletters() {
        log.info("Running Schedule");
        LocalDateTime now = LocalDateTime.now();
        List<Content> dueContents = contentRepository.findDueContents(now);
        
        for (Content content : dueContents) {
            log.info("Found content :: {} for topic {}",content.getTitle(),content.getTopic());
            sendNewsletterForContent(content);
            content.setSent(true);
            contentRepository.save(content);
        }
    }
    
    private void sendNewsletterForContent(Content content) {
        List<Subscriber> subscribers = subscriberRepository.findByTopic(content.getTopic());
        for (Subscriber subscriber : subscribers) {
            log.info("Found subscriber :: {}",subscriber.getEmail());
            String subject = content.getTitle();
            String body = "Hello " + subscriber.getName() + ",\n\n" +
                         content.getBody() + "\n\n" +
                         "Best regards,\nNewsletter Team";
            log.info("Sending email");
//            emailService.sendNewsletter(subscriber.getEmail(), subject, body);
        }
    }
}