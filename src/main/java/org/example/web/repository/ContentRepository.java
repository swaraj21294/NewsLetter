// ContentRepository.java
package org.example.web.repository;

import org.example.dto.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByTopicId(Long topicId);
    
    @Query("SELECT c FROM Content c WHERE c.scheduledTime = :now AND c.sent = false")
    List<Content> findDueContents(@Param("now") LocalDateTime now);
    
    List<Content> findBySentFalseAndScheduledTimeBefore(LocalDateTime time);
}