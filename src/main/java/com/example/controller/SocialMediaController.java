package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
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
@RequestMapping("/api/users")
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
  
  @RequestMapping(value="/register", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Account> register(@RequestBody Account newUser) {
      if(newUser.getPassword().length()<4||newUser.getUsername().length()==0||newUser.getUsername()==null||newUser.getPassword()==null)
      {
        return ResponseEntity.status(400).body(newUser);
      }
      else if(accountRepository.findAccountByUserName(newUser.getUsername())!=null)
      {
        return ResponseEntity.status(409).body(newUser);
      }
      Account newAccount=accountService.register(newUser);
      return ResponseEntity.status(200).body(newAccount);
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public @ResponseBody Account login(@RequestBody Account existingUser) {
        // Logic to authenticate user login
        return null;
    }

}
