package com.abc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    public List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public  void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
        	synchronized(this){///to make transactions atomic
        	transactions.add(new Transaction(amount));
        	}
        }
    }

public void withdraw(double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
    } else {
    	synchronized(this){///to make transactions atomic
    	transactions.add(new Transaction(-amount));
    	}
    }
}

    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount-1000) * 0.002;

            case MAXI_SAVINGS:  
            	if(checkTxnsInLastTenDays())
            		return amount * 0.05;
                return amount * 0.001;
            default:
                return amount * 0.001;
        }
    }

    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }
    
    public boolean checkTxnsInLastTenDays(){
    	boolean flag=false;
    	
    	Collections.reverse(this.transactions);///revers to start woith latest txn
    	
    	for(Transaction t: transactions){
    		if(t.amount<0) {///check if it is withdrawls
    			flag=checkDate(t.getTransactionDate());	
    			break;
    		}	
    		
    	}
    	return flag;	
    }
    
    private boolean checkDate(Date txnDate){
    	boolean flag=false;
    	
    	Date today=new Date();
    	Calendar cal=new GregorianCalendar();
    	cal.setTime(today);
    	
    	for(int i=10;i>=1;i--){
    		cal.add(Calendar.DAY_OF_MONTH, i* -1);//check for last ten days
    		if(txnDate.getTime()==cal.getTime().getTime())flag= true;
    		
    	}
    	return flag;
    }
    
    
    

}
