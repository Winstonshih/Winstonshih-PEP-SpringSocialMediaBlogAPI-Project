package com.example.repository;
import com.example.entity.Message;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
public interface MessageRepository extends JpaRepository<Message, Integer>{
  Optional<Message> findById(Integer id);
  Optional<Message> deleteAllPostsById(Integer id);
  Optional<Message> updateAllPostsById(Integer id);
}
