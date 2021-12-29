package com.infiniteskills.data;

import java.util.Calendar;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.infiniteskills.data.entities.Address;
import com.infiniteskills.data.entities.Credential;
import com.infiniteskills.data.entities.User;

public class Application {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Transaction transaction = session.beginTransaction();
		
		/*==> testando a conexao
		session.beginTransaction();
		session.close();
		*/
		
		/*
		session.getTransaction().begin();
		User user = new User();
		user.setBirthDate(getMyBirthDay());
		user.setCreatedBy("kevin");
		user.setCreatedDate(new Date());
		user.setEmailAddress("kevin@gmail.com");
		user.setFirstName("Kevin 123");
		user.setLastName("da silva 123");
		user.setLastUpdateBy("kevin arroh");
		user.setLastUpdateDate(new Date());
		
		session.save(user);
		session.getTransaction().commit();
		session.refresh(user);
		System.out.println(user.getAge());
		/*
		session.beginTransaction();
		User dbUser = (User) session.get(User.class, user.getUserId());
		dbUser.setFirstName("ARROOOOOOH");
		session.update(dbUser);
		session.getTransaction().commit();
		session.close();
		*/
		
			// @Embedded e @Embebedable
		/*
		Address address = new Address("New York city", "75134290", "NY");
		Bank bank = new Bank("Federal trust",address, false, "Fred ramos",new Date(),"Kevvin devito", new Date());
		*/
		
		/*
		// @ElementCollection e @CollectionTable
		Bank bank = new Bank();
		bank.setName("Federal trust");
		Address end = new Address();
		end.setCity("New york");
		end.setState("NY");
		end.setZipCode("74555687");
		bank.setAddress(end);
		bank.setCreatedBy("kevin");
		bank.setCreatedDate(new Date());
		bank.getContacts().put("Chefe", "Frederico");
		bank.getContacts().put("Secretaria", "Mary");
		*/
		/*
		User user = new User();
		Address end1 = new Address();
		Address end2 = new Address();
		setAddressFields(end1);
		setAddressFields2(end2);
		user.getAddress().add(end1);
		user.getAddress().add(end2);
		setUserFields(user);
		
		session.save(user);
		transaction.commit();
		*/
		User user  = new User();
		user.setFirstName("Kevin 33456");
		user.setLastName("Kevin last name 7789");
		user.setAge(20);
		user.setBirthDate(getMyBirthDay());
		user.setCreatedBy("Fred 556");
		user.setCreatedDate(new Date());
		user.setEmailAddress("fred@gmail.com");
		user.setLastUpdateDate(new Date());
		user.setLastUpdateBy("fred 556");
		
		Credential credential = new Credential();
		credential.setPassword("password 22334");
		credential.setUserName("arroh credential 777");
		credential.setUser(user);
		user.setCredential(credential);
		
		session.save(credential);
		transaction.commit();
		
		User dbUser = (User) session.get(User.class, credential.getUser().getUserId());
		System.out.println(dbUser.getFirstName());
		
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
	}
	
	private static Date getMyBirthDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 1984);
		calendar.set(Calendar.MONTH, 6);
		calendar.set(Calendar.DATE, 19);
		return calendar.getTime();
	}
	
	private static void setAddressFields(Address end) {
		end.setAddressLine1("Line 1");
		end.setAddressLine2("Line 2");
		end.setCity("New York");
		end.setState("NY");
		end.setZipCode("80000000");
	}
	
	private static void setAddressFields2(Address end) {
		end.setAddressLine1("Line 3");
		end.setAddressLine2("Line 4");
		end.setCity("New York 2");
		end.setState("NY 2");
		end.setZipCode("90000900");
	}
	
	private static void setUserFields(User user) {
		user.setAge(22);
		user.setBirthDate(new Date());
		user.setFirstName("Fred arroh");
	}
}
