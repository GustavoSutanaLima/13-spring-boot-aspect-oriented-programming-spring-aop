package edu.gustadev.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Repository;

import edu.gustadev.aopdemo.entity.Account;

@Repository
public class AccountDAOImpl implements AccountDAO {

    private String name;
    private String serviceCode;
    private List<Account> accounts =  new ArrayList<Account>();
    
    @Override
    public String getName() {
        System.out.println("Running AccountDAO.getName()");
        return name;
    }

    @Override
    public void setName(String name) {
        System.out.println("Running AccountDAO.setName()");
        this.name = name;
    }

    @Override
    public String getServiceCode() {
        System.out.println("Running AccountDAO.getServiceCode()");
        return serviceCode;
    }
    
    @Override
    public void setServiceCode(String serviceCode) {
        System.out.println("Running AccountDAO.setServiceCode()");
        this.serviceCode = serviceCode;
    }

    @Override
    public void addAccount(Account account, boolean vipPass) {
        System.out.print(getClass() + ": DOING DATA BASE WORK... FOR AccountDAO's account: " + account);
        if(vipPass){
            System.out.print(" (Platinum Client!)\n");        
        }
        else {
            System.out.print(" (Gold Client!)\n");
        }
    }

    @Override
    public void addUser() {
        System.out.println(getClass() + ": DOING DATA BASE WORK... FOR AccountDAO.addUser()");
    }

    @Override
    public boolean addUserFlag() {
        return true;
    }

    @Override
    public List<Account> findAccounts() {
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean flag) {
        
        //simulating an exception:
        if(flag) {
            throw new RuntimeException("No soup for you my friend!");
        }
        
        accounts.add(new Account("Jonn", "High-Level"));
        accounts.add(new Account("Leo", "Medium-level"));
        accounts.add(new Account("Gustavo", "Entry-Level"));
        
       return accounts;
    }

}