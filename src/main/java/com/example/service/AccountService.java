package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
@Service
public class AccountService {
  private AccountRepository accountRepository;
  /**
   * Constructor for accountRepository objects.
   * @param accountRepository accountRepository object
   */
  @Autowired
  public AccountService(AccountRepository accountRepository)
  {
    this.accountRepository=accountRepository;
  }
  /**
   * Method to register accounts by checking if username is already used.
   * @param account account that will bee created.
   * @return registered account if username is null or null if username is not null.
   */
  public Account register(Account account)
  {
    Account newUser=accountRepository.findAccountByUsername(account.getUsername());
    if(newUser==null)
    {
      return accountRepository.save(account);
    }
    return null;
  }
  /**
   * Login method that checks if username is in database.
   * @param account existing user account
   * @return existing user account if it exists or null if not.
   */
  public Account login(Account account)
  {
    Account existingUser=accountRepository.findAccountByUsername(account.getUsername());
    if(existingUser!=null && existingUser.getPassword().equals(account.getPassword()))
    {
      return existingUser;
    }
    return null;
  }
}
