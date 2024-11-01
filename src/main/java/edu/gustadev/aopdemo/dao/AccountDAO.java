package edu.gustadev.aopdemo.dao;

import java.util.List;

import edu.gustadev.aopdemo.entity.Account;

public interface AccountDAO {

    //Definindo assinatura do método para adicionar conta:
    void addAccount(Account account, boolean vipPass);

     //Definindo assinatura do método para adicionar usuário:
     void addUser();

     boolean addUserFlag();

     String getName();

     void setName(String name);

     String getServiceCode();

     void setServiceCode(String serviceCode);

     List<Account> findAccounts();

     List<Account> findAccounts(boolean flag);
     
}
