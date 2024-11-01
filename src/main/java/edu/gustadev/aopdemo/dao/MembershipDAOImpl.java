package edu.gustadev.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO{

    @Override
    public void addAccount() {
        System.out.println(getClass() + ": DOING DATA BASE WORK... FOR MembershipDAO.AddAccount()");
    }

}
