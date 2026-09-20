package com.example.repository;
import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AccountRepository extends JpaRepository<Account, Integer>{
  /**
   * Finds an account by its id.
   * @param username account's username.
   * @return account if id is valid or null if not found.
   */
  Account findAccountByUsername(String username);
}
