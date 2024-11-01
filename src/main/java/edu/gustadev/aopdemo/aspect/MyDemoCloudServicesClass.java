package edu.gustadev.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyDemoCloudServicesClass extends MyDemoGeneralPointCuts{

    @Before("dPEBNoGetOrSet()")
    public void setCloudServ(){
        System.out.println("=====:>>Setting up Cloud Services...");
    }

}
