package com.example.repository;
import com.example.entity.Message;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
public interface MessageRepository extends JpaRepository<Message, Integer>{
  /**
   * Returns a message based on message id.
   * @param id id of message
   * @return message if found or null if not found.
   */
  Optional<Message> findById(Integer id);
  /**
   * Method to retrieve a list of messages a user posted.
   * @param postedBy name of user who posted post
   * @return List of all messages posted by user if user is found or null if not found or not existing.
   */
  List<Message> findByPostedBy(Integer postedBy);
}
