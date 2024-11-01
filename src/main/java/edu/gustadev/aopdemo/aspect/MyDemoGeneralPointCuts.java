package edu.gustadev.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MyDemoGeneralPointCuts {
    @Pointcut("execution(* edu.gustadev.aopdemo.dao.*.*(..))")
    protected void dPEBefore(){
    }

    @Pointcut("execution(* edu.gustadev.aopdemo.dao.*.get**(..))")
    protected void dPEBeforeGetters(){
    }

    @Pointcut("execution(* edu.gustadev.aopdemo.dao.*.set**(..))")
    protected void dPEBeforeSetters(){
    }

    @Pointcut("dPEBefore() && !(dPEBeforeGetters() || dPEBeforeSetters())")
    protected void dPEBNoGetOrSet() {

    }

    @Pointcut("execution(* edu.gustadev.aopdemo.service.*.getFortune(..))")
    protected void dPEAround() {

    }
}
