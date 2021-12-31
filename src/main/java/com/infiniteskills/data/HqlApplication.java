package com.infiniteskills.data;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infiniteskills.data.entities.Account;
import com.infiniteskills.data.entities.Transaction;

public class HqlApplication {

	public static void main(String[] args) {
		
		SessionFactory factory = null;
		Session session = null;
		org.hibernate.Transaction tx = null;
		
		try{
			factory = HibernateUtil.getSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			
//			Query query = session.createQuery("select distinct t.account from Transaction t"
//			+ " where t.amount > 500 and lower(t.transactionType) = 'deposit'");

		Query query = session.getNamedQuery("Account.largeDeposits");
		List<Account> accounts = query.list();
		
		for(Account a:accounts){
			System.out.println(a.getName());
		}
		
		tx.commit();
		}catch(Exception e){
			tx.rollback();
		}finally{
			session.close();
			factory.close();
		}
	}
}

