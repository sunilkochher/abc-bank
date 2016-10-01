package com.abc;

public class MainClass {

	public static void main(String[] args) {
		Bank bank=new Bank();
		
		Customer sunil=new Customer("Sunil");
		
		bank.addCustomer(sunil);
		
		Account sunilChecking=new Account(Account.CHECKING);
		Account sunilSaving=new Account(Account.SAVINGS);
		Account sunilMaxi=new Account(Account.MAXI_SAVINGS);
		
		sunil.openAccount(sunilChecking);
		sunil.openAccount(sunilSaving);
		sunil.openAccount(sunilMaxi);

		
		sunilChecking.deposit(100.0);
		sunilChecking.withdraw(50.0);
		
		sunilSaving.deposit(1000.0);
		sunilSaving.withdraw(500.0);
		
		sunilSaving.deposit(1000.0);
		sunilMaxi.withdraw(500.0);
		
		System.out.println(sunil.getStatement());
		
		sunil.transferBetweenAccounts(sunilSaving, sunilChecking, 100);
		
		System.out.println(sunil.getStatement());
		System.out.println("*******Total Interest  ="+ sunil.totalInterestEarned());


	}

}
