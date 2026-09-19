package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;
import com.example.service.MessageService;
/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
  private AccountService accountService;
  private MessageService messageService;
  private AccountRepository accountRepository;
  /**
   * No params constructor that instantiates AccountService and MessageService objects that will be used to construct 
   * eendpoints for REST API.
   */
  @Autowired
  public SocialMediaController(AccountService accountService, MessageService messageService, AccountRepository accountRepository)
  {
      this.accountService= accountService;
      this.messageService=messageService;
      this.accountRepository=accountRepository;
  }
  /**
   * 
   * @param newUser
   * @return
   */
  @RequestMapping(value="/register", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Account> register(@RequestBody Account newUser) {
      if(newUser.getPassword().length()<4||newUser.getUsername().length()==0||newUser.getUsername()==null||newUser.getPassword()==null)
      {
        return ResponseEntity.status(400).body(newUser);
      }
      else if(accountRepository.findAccountByUsername(newUser.getUsername())!=null)
      {
        return ResponseEntity.status(409).body(newUser);
      }
      return ResponseEntity.status(200).body(accountService.register(newUser));
    }
    /**
     * 
     * @param existingUser
     * @return
     */
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Account> login(@RequestBody Account existingUser) {
        if(accountService.login(existingUser)!=null)
        {
          return ResponseEntity.status(200).body(accountService.login(existingUser));
        }
        else
        {
          return ResponseEntity.status(401).body(existingUser);
        }
    }
    @RequestMapping(value="/messages", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Message> createPost(@RequestBody Message newMessage) {
      if(messageService.createMessage(newMessage)!=null)
      {
        return ResponseEntity.status(200).body(messageService.createMessage(newMessage));
      }
      //if(accountRepository.findById(newMessage.getPostedBy()).isPresent())
      //{
      //  return ResponseEntity.status(409).build();
      //}
      return ResponseEntity.status(400).build();
    }
    @RequestMapping(value="/messages", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Message>> getAllPosts() {
        return ResponseEntity.status(200).body(messageService.getAllPosts());
    }
    @RequestMapping(value="/messages/{messageId}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Message> getAllPostsById(@PathVariable("messageId") Integer id) {
        return ResponseEntity.status(200).body(messageService.getAllPostsById(id));
    }
    @RequestMapping(value="/messages/{messageId}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<Integer> deleteAllPostsById(@PathVariable("messageId") Integer id) {
        return ResponseEntity.status(200).body(messageService.deleteAllPostsById(id));
    }
    @RequestMapping(value="/messages/{messageId}", method = RequestMethod.PATCH)
    public @ResponseBody ResponseEntity<Integer> updateAllPostsById(@PathVariable("messageId") Integer id, @RequestBody Message message) {
        if(messageService.updateAllPostsById(id, message.getMessageText())!=null)
        {
          return ResponseEntity.status(200).body(messageService.updateAllPostsById(id, message.getMessageText()));
        }
        return ResponseEntity.status(400).build();
    }
    @RequestMapping(value="/accounts/{accountId}/messages", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Message>> retrieveAllPostsByUser(@PathVariable("accountId") Integer id) {
        return ResponseEntity.status(200).body(messageService.getAllPostsByUser(id));
    }
}
