package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
@Service
public class AccountService {
  private AccountRepository accountRepository;
  @Autowired
  public AccountService()
  {
    
  }
  public AccountService(AccountRepository accountRepository)
  {
    this.accountRepository=accountRepository;
  }
  public Account register(Account account)
  {
    Account newUser=accountRepository.findAccountByUserName(account.getUsername());
    if(newUser==null)
    {
      return accountRepository.save(account);
    }
    return null;
  }
  public Account login(Account account)
  {
    Account existingUser=accountRepository.findAccountByUserName(account.getUsername());
    if(existingUser!=null && existingUser.getPassword().equals(account.getPassword()))
    {
      return existingUser;
    }
    return null;
  }
}
