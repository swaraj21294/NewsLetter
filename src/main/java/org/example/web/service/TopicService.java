package org.example.web.service;

import org.example.dto.Content;
import org.example.dto.ContentResponseDTO;
import org.example.dto.Subscriber;
import org.example.dto.SubscriberResponseDTO;
import org.example.dto.Topic;
import org.example.dto.TopicContentResponseDTO;
import org.example.dto.TopicDTO;
import org.example.dto.TopicResponseDTO;
import org.example.dto.TopicSubscriberResponseDTO;
import org.example.web.controller.exception.TopicAlreadyExistsException;
import org.example.web.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<TopicResponseDTO> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();
        return topics.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<TopicResponseDTO> getTopicById(Long id) {
        return topicRepository.findById(id).map(this::convertToDTO);
    }

    @Transactional
    public Topic createTopic(TopicDTO topicDTO) {
        Topic topic = new Topic();
        topic.setName(topicDTO.getName());
        topic.setDescription(topicDTO.getDescription());
        try {
            return topicRepository.save(topic);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage() != null) {
                throw new TopicAlreadyExistsException("Topic with name '" + topicDTO.getName() + "' already exists.", e);
            }
            throw e;
        }
    }


    public Optional<TopicResponseDTO> updateTopic(Long id, TopicDTO topicDTO) {

        return topicRepository.findById(id).map(topic -> {
            // 1. Update the Topic Entity fields from the TopicDTO (input DTO)
            topic.setName(topicDTO.getName());
            topic.setDescription(topicDTO.getDescription());

            // 2. Save the updated entity
            Topic updatedTopic = topicRepository.save(topic);

            // 3. Map the saved entity to the response DTO
            return mapToTopicResponseDTO(updatedTopic);
        });
    }

    private TopicResponseDTO mapToTopicResponseDTO(Topic topic) {
        TopicResponseDTO dto = new TopicResponseDTO();

        // Map basic fields
        dto.setId(topic.getId());
        dto.setName(topic.getName());
        dto.setDescription(topic.getDescription());

        // Map related entities (Subscribers)
        if (topic.getSubscribers() != null) {
            dto.setSubscribers(topic.getSubscribers().stream()
                .map(this::mapToSubscriberResponseDTO) // Call mapping helper for Subscriber
                .collect(Collectors.toList()));
        }

        // Map related entities (Contents)
        if (topic.getContents() != null) {
            dto.setContents(topic.getContents().stream()
                .map(this::mapToContentResponseDTO) // Call mapping helper for Content
                .collect(Collectors.toList()));
        }

        return dto;
    }

    private TopicSubscriberResponseDTO mapToSubscriberResponseDTO(Subscriber subscriber) {
        TopicSubscriberResponseDTO dto = new TopicSubscriberResponseDTO();
        dto.setId(subscriber.getId());
        dto.setName(subscriber.getName());
        dto.setEmail(subscriber.getEmail());
        return dto;
    }

    public boolean deleteTopic(Long id) {
        if (topicRepository.existsById(id)) {
            topicRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private TopicContentResponseDTO mapToContentResponseDTO(Content content) {
        TopicContentResponseDTO dto = new TopicContentResponseDTO();
        dto.setId(content.getId());
        dto.setBody(content.getBody());
        dto.setTitle(content.getTitle());
        dto.setScheduledTime(content.getScheduledTime());
        dto.setSent(content.isSent());
        return dto;
    }

    private TopicResponseDTO convertToDTO(Topic topic) {
        TopicResponseDTO dto = new TopicResponseDTO();
        dto.setId(topic.getId());
        dto.setName(topic.getName());
        dto.setDescription(topic.getDescription());

        // Convert subscribers
        List<TopicSubscriberResponseDTO> subscriberDTOs = topic.getSubscribers().stream()
            .map(this::convertSubscriberToDTO)
            .collect(Collectors.toList());
        dto.setSubscribers(subscriberDTOs);

        // Convert contents
        List<TopicContentResponseDTO> contentDTOs = topic.getContents().stream()
            .map(this::convertContentToDTO)
            .collect(Collectors.toList());
        dto.setContents(contentDTOs);

        return dto;
    }

    private TopicSubscriberResponseDTO convertSubscriberToDTO(Subscriber subscriber) {
        TopicSubscriberResponseDTO dto = new TopicSubscriberResponseDTO();
        dto.setId(subscriber.getId());
        dto.setEmail(subscriber.getEmail());
        dto.setName(subscriber.getName());
        return dto;
    }

    private TopicContentResponseDTO convertContentToDTO(Content content) {
        TopicContentResponseDTO dto = new TopicContentResponseDTO();
        dto.setId(content.getId());
        dto.setTitle(content.getTitle());
        dto.setBody(content.getBody());
        dto.setScheduledTime(content.getScheduledTime());
        dto.setSent(content.isSent());
        return dto;
    }
}