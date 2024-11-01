package edu.gustadev.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import edu.gustadev.aopdemo.entity.Account;

@Aspect
@Component
@Order(1) //@Order Annotation, as the name says defines the order of execution of the aspects, the lower number
          //inside parentheres will be executed first and so on, negative numbers are allowed;


//This is an Aspect (a class) which is basically a collection of advices (methods), these methods
//contain the logic to be execeuted before, during or after the target method.

public class MyDemoLoggingClass extends MyDemoGeneralPointCuts{
    //this is an aspect to be using for Logging therefore it'll hold the related logging adivces

    //this is an @Before advice 

    //The attribute inside this annotation (which is called "pointcut expressions") references the 
    //target method: addAccount() which of the void type.
    //Since we're using an @Before the advice (method) below will be executed before the addAccount() method.
    /* 
        @Before("execution(public void edu.gustadev.aopdemo.DAO.AccountDAO.addAccount(edu.gustadev.aopdemo.Account, boolean))")
        public void beforeAddAccountAdviceAccountDAO() {
            System.out.println("=======>> Executing the BEFORE Advice on AccountDAO's method called: addAccount(Account accoutToBeAdded, boolean vipPass)");
        }
    */
    //this annotation is basically the same as the above, except this is executed after the any target method 
    //is executed: the method should be public, of any return tipe (*), it has
    //to be in the package: "edu.gustadev.aopdemo.DAO.AccountDAO" must start with "add" and the it can't have any
    //parameters:
   
    /*SINTAXE BÁSICO DO POINTCUT EXPRESSION: execution:
     * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?)
     * if the pattern has an "?" and its end, it's optional;
     * 
     * No caso de: @Before("execution(public void addAccount())"), como não foi especifica uma classe, qualquer
     * classe que tiver o método addAccount(), este advice será executado.
     * 
     * Também é possível usar wildcards, como "*":
     * @Before("execution(public void add*())")
     * Agora, qualquer método que iniciar com add, esse advice será executado.
     */


    /* É possível definir uma pointcut expression padrão para ser usada em diferentes advices
     * para isso, basta usar a anotação @Point([pointcut_expression here]) em um método de nome qualquer
     * e depois, passar o nome deste método no pointcut expression de um @Before advice, por exemplo:
     */
     
    @Before("dPEBNoGetOrSet()")
    public void loginSecurity(JoinPoint joinPoint){
        /*
        var objects = joinPoint.getArgs();
        if (objects != null) {
            System.out.println("Getting argument objects...");
            for (Object object : objects) {
                System.out.println(object);
            }
        }
        */
        System.out.println("=====:>>Performing Login Security Check...");
    }


    @AfterReturning(
        pointcut = "dPEBNoGetOrSet()", 
        returning = "result") 
    public void loginSecurityForAfterReturning(JoinPoint joinPoint, List<Account> result) {
        System.out.println("=====:>>Performing Logout Security Check... for the returned objects below:");
        convertiningAccountNamesToUpperCase(result);
                
            }
        
        
    private void convertiningAccountNamesToUpperCase(List<Account> result) {
        for (Account account : result) {
            account.setName(account.getName().toUpperCase());
        }
        System.out.println(result);
    }
    

    @AfterThrowing(pointcut = "dPEBNoGetOrSet()",
                   throwing = "exceptionThrown")
    public void afterThrowingNoSoupException(JoinPoint joinPoint, Throwable exceptionThrown) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("=====:>>Executing @AfterThrowning...");
        System.out.println("=====:>>Logging the exception " + method);
    }

    @After("dPEBNoGetOrSet()")
    public void afterAddAccountAdvice() {
        System.out.println("=====:>>Executing @After Advice...");
    }

    //@Around Advice executes Before and After the method;
    /*
     * @Around: É um tipo de advice que permite executar lógica antes e depois da execução do método alvo. 
     * É o advice mais poderoso, pois pode até impedir a execução do método alvo.
     * ProceedingJoinPoint: É um tipo especial de JoinPoint usado dentro de @Around advices. 
     * Ele permite que você controle a execução do método alvo, através do método proceed(): que executa o 
     * método alvo. Quando você chama proceed(), ele invoca o método que está sendo interceptado.
     * Sem chamar proceed(), o método alvo não será executado.
     */

    @Around("dPEAround()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        
        //priting out the method we're adivising on:
        System.out.println("=====:>>Executing @Around Advice on..." + proceedingJoinPoint.getSignature().toShortString());
        
        //getting begin timestamp:
        long timeThisAdviceStarted = System.currentTimeMillis();

        //executing the method (this object is the actual target method)
        //O objeto result contém o valor retornado pelo método alvo. 
        //Você pode modificar esse valor antes de retorná-lo, se necessário.
        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();

            //getting end timestamp:
            long timeThisAdviceFinished = System.currentTimeMillis();
            //calculating duration:
            long duration = timeThisAdviceFinished - timeThisAdviceStarted;
            //Logging...
            System.out.println("Total duration (in seconds): " + duration/1000);

            return result;
        } catch (Exception e) {
            System.out.println("@Around Adivce: We had a problem here: " +  e);
            throw e;
        }

    }
}
