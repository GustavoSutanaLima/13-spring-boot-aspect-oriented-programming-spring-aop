package edu.gustadev.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyDemoAPIAnalyticsClass extends MyDemoGeneralPointCuts {
    
    @Before("dPEBNoGetOrSet()")
    public void aPIAnalytics(){
        System.out.println("=====:>>Performing API Analytics...");
    }

}
