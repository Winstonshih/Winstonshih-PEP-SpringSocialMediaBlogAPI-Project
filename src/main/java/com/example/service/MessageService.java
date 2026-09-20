package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
  private MessageRepository messageRepository;
  private AccountRepository accountRepository;
  /**
   * Constructor for instantiating MessageRepository or AccountRepository objects
   * @param messageRepository MessageRepository object
   * @param accountRepository AccountRepository object
   */
  @Autowired
  public MessageService(MessageRepository messageRepository, AccountRepository accountRepository)
  {
    this.messageRepository=messageRepository;
    this.accountRepository=accountRepository;
  }
  /**
   * Method to create a new social media post.
   * @param newMessage message that will be added into message repository
   * @return new message if creation is successful or null if message creation fails.
   */
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
  /**
   * Method to retrieve all posts in database.
   * @return all posts in message repository
   */
  public List<Message> getAllPosts() {
    return messageRepository.findAll();
  }
  /**
   * Method to get all posts by a message id.
   * @param id message id
   * @return all posts associated with message id
   */
  public Message getAllPostsById(Integer id)
  {
    if(messageRepository.findById(id).isPresent())
    {
      return messageRepository.findById(id).get();
    }
    return null;
  }
  /**
   * Method to delete all posts based on message id.
   * @param id message id
   * @return 1 if row is deleted or null if not.
   */
  public Integer deleteAllPostsById(Integer id)
  {
    if(messageRepository.findById(id).isPresent())
    {
      messageRepository.deleteById(id);
      return 1;
    }
    return null;
  }
  /**
   * Method to update all posts based on message id.
   * @param id message id
   * @param updateText updated text for post
   * @return 1 if row is updated or null if no rows are updated.
   */
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
  /**
   * Method to retrieve all social media posts based on user id.
   * @param id user id
   * @return account tied to user id.
   */
  public List<Message> getAllPostsByUser(Integer id) {
    return messageRepository.findByPostedBy(id);
  }
}
