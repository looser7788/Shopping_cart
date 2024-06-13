package com.jsp.shoppingcart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shoppingcart.dto.Product;

@Repository
public class Productdao {
	@Autowired
	EntityManagerFactory emf;
	public void saveproduct(Product p) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		et.begin();
		em.persist(p);
		et.commit();
	}
	public void updatedataproduct(Product p) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		et.begin();
		em.merge(p);
		et.commit();
	}
	public void deleteproductById(int id) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		
		Product p=em.find(Product.class, id);
		et.begin();
		em.remove(p);
		et.commit();
	}
	public Product findproductById(int id) {
		EntityManager em=emf.createEntityManager();
		Product p=em.find(Product.class, id);
		if(p !=null)
			return p;
		else
			return null;
	}
	public List<Product> fetchAllProduct(){
		EntityManager et=emf.createEntityManager();
		Query q=et.createQuery("select s from Product s");
		return q.getResultList();
	}
	public List<Product> findProductByBrand(String brand){
		EntityManager em= emf.createEntityManager();
		Query query= em.createQuery("select p from Product p where p.brand=?1");
		query.setParameter(1, brand);
		return query.getResultList();
		}
		public List<Product> findProductByCategory(String category){
			EntityManager em= emf.createEntityManager();
			Query query= em.createQuery("select p from Product p where p.category=?1");
			query.setParameter(1, category);
			return query.getResultList();
		}
		public Product login(String email,String password) {
			EntityManager em=emf.createEntityManager();
			Query query =em.createQuery("select n from Product n where n.email=?1,n.password=?2");
			query.setParameter(1, email);
			query.setParameter(2, password);
			Product merchant =(Product)query.getSingleResult();
			if(merchant!=null) {
				return merchant;
			}else {return null;}

	}
}
