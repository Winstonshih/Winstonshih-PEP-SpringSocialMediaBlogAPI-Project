package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
  private MessageRepository messageRepository;
  @Autowired
  public MessageService(MessageRepository messageRepository)
  {
    this.messageRepository=messageRepository;
  }
  public Message createMessage(Message newMessage)
  {
    return null;
  }
  public List<Message> getAllPosts() {
    return messageRepository.findAll();
  }
  public Message getAllPostsById(long id)
  {
    Optional<Message> i=messageRepository.findById(id);
    if(i.isPresent())
    {
      return i.get();
    }
    return null;
  }
}
