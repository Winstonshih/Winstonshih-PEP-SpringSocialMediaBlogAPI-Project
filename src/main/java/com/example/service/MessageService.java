package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
  private MessageRepository messageRepository;
  private AccountRepository accountRepository;
  @Autowired
  public MessageService(MessageRepository messageRepository, AccountRepository accountRepository)
  {
    this.messageRepository=messageRepository;
    this.accountRepository=accountRepository;
  }
  public Message createMessage(Message newMessage)
  {
    if(newMessage.getMessageText()==null||newMessage.getMessageText().length()==0||newMessage.getMessageText().length()>255)
    {
      return null;
    }
    if(newMessage.getPostedBy()==null)
    {
      return null;
    }
    if(!accountRepository.existsById(newMessage.getPostedBy()))
    {
      return null;
    }
    return messageRepository.save(newMessage);
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
  public Integer updateAllPostsById(Integer id, String updateText)
  {
    if(updateText==null||updateText.length()==0||updateText.length()>255)
    {
      return null;
    }
    if(messageRepository.findById(id).isPresent())
    {
      messageRepository.findById(id).get().setMessageText(updateText);
      messageRepository.save(messageRepository.findById(id).get());
      return 1;
    }
    return null;
  }
  public List<Message> getAllPostsByUser(Integer id) {
    return messageRepository.findByPostedBy(id);
  }
}
