package org.example.web.service;

import java.util.stream.Collectors;
import org.example.dto.Subscriber;
import org.example.dto.SubscriberDTO;
import org.example.dto.SubscriberResponseDTO;
import org.example.dto.Topic;
import org.example.web.controller.exception.ResourceNotFoundException;
import org.example.web.repository.SubscriberRepository;
import org.example.web.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    @Autowired
    private TopicRepository topicRepository;

    public List<SubscriberResponseDTO> getAllSubscribers() {
        List<Subscriber> subscribers = subscriberRepository.findAll();
        return subscribers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<SubscriberResponseDTO> getSubscriberById(Long id) {
        return subscriberRepository.findById(id).map(this::convertToDTO);
    }

    public List<SubscriberResponseDTO> getSubscribersByTopic(Long topicId) {
        List<Subscriber> subscribers = subscriberRepository.findByTopicId(topicId);
        return subscribers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Subscriber createSubscriber(SubscriberDTO subscriberDTO) {
        Topic topic = topicRepository.findById(subscriberDTO.getTopicId())
            .orElseThrow(() -> new RuntimeException("Topic not found with id: " + subscriberDTO.getTopicId()));

        Subscriber subscriber = new Subscriber();
        subscriber.setEmail(subscriberDTO.getEmail());
        subscriber.setName(subscriberDTO.getName());
        subscriber.setTopic(topic);
        return subscriberRepository.save(subscriber);
    }

    public SubscriberResponseDTO updateSubscriber(Long id, SubscriberDTO subscriberDTO) {
        Subscriber updatedSubscriber = subscriberRepository.findById(id).map(subscriber -> {
            Topic topic = topicRepository.findById(subscriberDTO.getTopicId())
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found with id: " + subscriberDTO.getTopicId()));

            subscriber.setEmail(subscriberDTO.getEmail());
            subscriber.setName(subscriberDTO.getName());
            subscriber.setTopic(topic);
            return subscriberRepository.save(subscriber);
        }).orElseThrow(() -> new RuntimeException("Subscriber not found"));
        return convertToDTO(updatedSubscriber);
    }

    public boolean deleteSubscriber(Long id) {
        if (subscriberRepository.existsById(id)) {
            subscriberRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private SubscriberResponseDTO convertToDTO(Subscriber subscriber) {
        SubscriberResponseDTO dto = new SubscriberResponseDTO();
        dto.setId(subscriber.getId());
        dto.setEmail(subscriber.getEmail());
        dto.setName(subscriber.getName());
        if (subscriber.getTopic() != null) {
            dto.setTopicId(subscriber.getTopic().getId());
            dto.setTopicName(subscriber.getTopic().getName());
        }
        return dto;
    }
}
