package com.infiniteskills.data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infiniteskills.data.entities.Account;
import com.infiniteskills.data.entities.AccountType;
import com.infiniteskills.data.entities.Address;
import com.infiniteskills.data.entities.Bank;
import com.infiniteskills.data.entities.Bond;
import com.infiniteskills.data.entities.Credential;
import com.infiniteskills.data.entities.Currency;
import com.infiniteskills.data.entities.Investment;
import com.infiniteskills.data.entities.Market;
import com.infiniteskills.data.entities.Portfolio;
import com.infiniteskills.data.entities.Stock;
import com.infiniteskills.data.entities.Transaction;
import com.infiniteskills.data.entities.User;
import com.infiniteskills.data.entities.UserCredentialView;
import com.infiniteskills.data.entities.ids.CurrencyId;

/*
  JPA single table strategy � a estrategia mais usada para mapear heran�a, al�m de ser
  uma das estrat�gias mais perform�ticas que podemos usar.
   
  
  Essa estr�tegia vai juntar os campos das tabelas Stock, Bond, e coloc�los na tabela Investment.
 */

public class Application {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = null;
		Session session = null;
		org.hibernate.Transaction tx = null;
		
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			UserCredentialView view = (UserCredentialView) session.get(UserCredentialView.class, 1L);
			System.out.println(view.getFirstName());
			System.out.println(view.getLastName());
			
			tx.commit();


		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	private static Bond createBond() {
		Bond bond = new Bond();
		bond.setInterestRate(new BigDecimal("123.22"));
		bond.setIssuer("JP Morgan Chase");
		bond.setMaturityDate(new Date());
		bond.setPurchaseDate(new Date());
		bond.setName("Long Term Bond Purchases");
		bond.setValue(new BigDecimal("10.22"));
		return bond;
	}

	private static Stock createStock(){
		Stock stock = new Stock();
		stock.setIssuer("Allen Edmonds");
		stock.setName("Private American Stock Purchases");
		stock.setPurchaseDate(new Date());
		stock.setQuantity(new BigDecimal("1922"));
		stock.setSharePrice(new BigDecimal("100.00"));
		return stock;
	}
	
	private static User createUser() {
		User user = new User();
		Address address = createAddress();
		user.setAddresses(Arrays.asList(new Address[]{createAddress()}));
		user.setBirthDate(new Date());
		user.setCreatedBy("Kevin Bowersox 666");
		user.setCreatedDate(new Date());
		user.setCredential(createCredential(user));
		user.setEmailAddress("test@test.com");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setLastUpdatedBy("system");
		user.setLastUpdatedDate(new Date());
		return user;
	}
	
	private static Credential createCredential(User user) {
		Credential credential = new Credential();
		credential.setUser(user);
		credential.setUsername("test_username");
		credential.setPassword("test_password");
		return credential;
	}
	
	private static Address createAddress() {
		Address address = new Address();
		address.setAddressLine1("101 Address Line");
		address.setAddressLine2("102 Address Line");
		address.setCity("New York");
		address.setState("PA");
		address.setZipCode("10000");
		address.setAddressType("PRIMARY");
		return address;
	}
	
	private static Transaction createNewBeltPurchase(Account account) {
		Transaction beltPurchase = new Transaction();
		beltPurchase.setAccount(account);
		beltPurchase.setTitle("Dress Belt");
		beltPurchase.setAmount(new BigDecimal("50.00"));
		beltPurchase.setClosingBalance(new BigDecimal("0.00"));
		beltPurchase.setCreatedBy("Kevin Bowersox");
		beltPurchase.setCreatedDate(new Date());
		beltPurchase.setInitialBalance(new BigDecimal("0.00"));
		beltPurchase.setLastUpdatedBy("Kevin Bowersox");
		beltPurchase.setLastUpdatedDate(new Date());
		beltPurchase.setNotes("New Dress Belt");
		beltPurchase.setTransactionType("Debit");
		return beltPurchase;
	}

	private static Transaction createShoePurchase(Account account) {
		Transaction shoePurchase = new Transaction();
		shoePurchase.setAccount(account);
		shoePurchase.setTitle("Work Shoes");
		shoePurchase.setAmount(new BigDecimal("100.00"));
		shoePurchase.setClosingBalance(new BigDecimal("0.00"));
		shoePurchase.setCreatedBy("Kevin Bowersox");
		shoePurchase.setCreatedDate(new Date());
		shoePurchase.setInitialBalance(new BigDecimal("0.00"));
		shoePurchase.setLastUpdatedBy("Kevin Bowersox");
		shoePurchase.setLastUpdatedDate(new Date());
		shoePurchase.setNotes("Nice Pair of Shoes");
		shoePurchase.setTransactionType("Debit");
		return shoePurchase;
	}

	private static Account createNewAccount() {
		Account account = new Account();
		account.setCloseDate(new Date());
		account.setOpenDate(new Date());
		account.setCreatedBy("Kevin Bowersox 777");
		account.setInitialBalance(new BigDecimal("50.00"));
		account.setName("Savings Account");
		account.setCurrentBalance(new BigDecimal("100.00"));
		account.setLastUpdatedBy("Kevin Bowersox");
		account.setLastUpdatedDate(new Date());
		account.setCreatedDate(new Date());
		return account;
	}
	
	private static Bank createBank() {
		Bank bank = new Bank();
		bank.setName("First United Federal");
		bank.setAddressLine1("103 Washington Plaza");
		bank.setAddressLine2("Suite 332");
		bank.setAddressType("PRIMARY");
		bank.setCity("New York");
		bank.setCreatedBy("Kevin Bowersox");
		bank.setCreatedDate(new Date());
		bank.setInternational(false);
		bank.setLastUpdatedBy("Kevin Bowersox");
		bank.setLastUpdatedDate(new Date());
		bank.setState("NY");
		bank.setZipCode("10000");
		return bank;
	}
}
