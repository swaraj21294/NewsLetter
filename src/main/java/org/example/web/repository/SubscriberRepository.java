package org.example.web.repository;

import org.example.dto.Subscriber;
import org.example.dto.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    Optional<Subscriber> findByEmail(String email);
    List<Subscriber> findByTopic(Topic topic);
    boolean existsByEmail(String email);
    List<Subscriber> findByTopicId(Long topicId);
}