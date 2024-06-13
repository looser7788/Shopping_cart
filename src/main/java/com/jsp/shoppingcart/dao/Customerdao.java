package com.jsp.shoppingcart.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shoppingcart.dto.Customer;

@Repository
public class Customerdao {
	@Autowired
EntityManagerFactory emf;
public void saveCustomer(Customer c) {
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	et.begin();
	em.persist(c);
	et.commit();
}
public Customer login(String email,String password) {
	EntityManager em=emf.createEntityManager();
Query q=em.createQuery("select m from Customer m where m.email=?1 and m.password=?2");
q.setParameter(1,email);
q.setParameter(2,password);
try {
 Customer customer=(Customer)q.getSingleResult();
return customer;

}
catch(NoResultException e) {
	return null;
}}

public void deleteCustomerById(int id) {
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	Customer c=em.find(Customer.class, id);
	et.begin();
	em.remove(c);
	et.commit();
}
public Customer findCustomerById(int id) {
	
	EntityManager em=emf.createEntityManager();
	Customer c=em.find(Customer.class, id);
	if(c !=null)
		return c;
	else
		return null;
}
public void updatecustomer(Customer c) {
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	et.begin();
	em.merge(c);
	et.commit();
}
}
