package edu.gustadev.aopdemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.gustadev.aopdemo.dao.AccountDAO;
import edu.gustadev.aopdemo.dao.MembershipDAO;
import edu.gustadev.aopdemo.entity.Account;
import edu.gustadev.aopdemo.service.TrafficFortuneService;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	//Retorno do método do tipo CommandLineRunner sobrecarregando o método run():
	@Bean
	public CommandLineRunner commandLineRunnerMetodoRun(AccountDAO accountDAO, MembershipDAO membershipDAO, TrafficFortuneService trafficFortuneService) {

		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				//demoTheBeforeAdvice(accountDAO, membershipDAO);
				//demoTheAfterReturningAdivce(accountDAO);
				//demoTheAfterThrowningAdvice(accountDAO);
				//demoTheAroundAdvice(trafficFortuneService);
				demotheAroundAdviceHandleTheException(trafficFortuneService);
				}
			};
		}
			
	private void demotheAroundAdviceHandleTheException(TrafficFortuneService trafficFortuneService) {
		//Simulating an exception witha boolean value:

		boolean tripWire = true;
		System.out.println(trafficFortuneService.getFortune(tripWire));
	}
		
	private void demoTheAroundAdvice(TrafficFortuneService trafficFortuneService) {
		System.out.println(trafficFortuneService.getFortune());
	}
	
	
	private void demoTheAfterThrowningAdvice(AccountDAO accountDAO) {
		List<Account> accounts = null;

		try {
			// add a bloolean flag to simulate  exceptions
			boolean tripWire = true;
			
			accounts  = accountDAO.findAccounts(true);
		} catch (Exception e) {
			System.out.println("Main program  ... caught exception: " + e);
		}

	}

	private void demoTheAfterReturningAdivce(AccountDAO accountDAO) {
	
		System.out.println("Accounts found: " + accountDAO.findAccounts());
		System.out.println("");
	}
			
					//Método adicional:
	private void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		//this método will call the business method
		System.out.println("Calling " + AccountDAO.class.getName() + "'s method addAccount():");
		accountDAO.addAccount(new Account("Gustavo", "Entry-Level"), false);
		System.out.println(" ");

		System.out.println("Calling " + AccountDAO.class.getName() + "'s method addUser():");
		accountDAO.addUser();
		System.out.println(" ");

		//this método will call the business method:
		System.out.println("Calling " + MembershipDAO.class.getName() + "'s method addAccount():");
		membershipDAO.addAccount();
		System.out.println(" ");

		System.out.println("Setting name on Account DAO...");
		accountDAO.setName("foobar");
		System.out.println(" ");

		System.out.println("Setting serviceCode on Account DAO...");
		accountDAO.setServiceCode("ADW159753");
		System.out.println(" ");
		
		System.out.println("Getting name on Account DAO... " + accountDAO.getName());
		System.out.println(" ");
		
		System.out.println("Getting serviceCode on Account DAO... " + accountDAO.getServiceCode());
		System.out.println(" ");

		//this método will call the business method:
		System.out.println("Calling " + AccountDAO.class.getName() + "'s method addUserFlag()\n");
		boolean result = accountDAO.addUserFlag();
		System.out.println("After returning should be above and the result should be true: "+ result);
	}
	
	/*
	//Retorno do método do tipo CommandLineRunner usando expressão lambda:
	@Bean
	public CommandLineRunner commandLineRunnerLambda() {
		
		//As linahs dentro do corpo da expressão lambda abaixo serão executadas quando a aplicação Spring rodar:
		// Relembrando... expressão lambda (x, y) -> (x+y)
		// antes da seta tem-se os parâmetros, depois da seta tem-se o corpo;
		// Abaixo, temos o runnerLines como parâmtro (pode ser chamado de como desejar), e como corpo, temos a linha 
		// System.out.println("Spring Boot CommandLineRunner Studies - Runner Aplicação Expressão Lambda");

		return runnerLines -> {
			System.out.println("Spring Boot CommandLineRunner Studies - Runner Aplicação Expressão Lambda");
		};
	}
	*/
}
