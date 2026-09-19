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
  public Message getAllPostsById(Integer id)
  {
    if(messageRepository.findById(id).isPresent())
    {
      return messageRepository.findById(id).get();
    }
    return null;
  }
  public Integer deleteAllPostsById(Integer id)
  {
    if(messageRepository.findById(id).isPresent())
    {
      messageRepository.deleteById(id);
      return 1;
    }
    return null;
  }
  public Integer updateAllPostsById(Integer id)
  {
    if(messageRepository.findById(id).isPresent())
    {
      messageRepository.updateAllPostsById(id);
      return 1;
    }
    return null;
  }
}
