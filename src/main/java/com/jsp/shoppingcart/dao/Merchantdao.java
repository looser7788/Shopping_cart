package com.jsp.shoppingcart.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shoppingcart.dto.Merchent;
import com.jsp.shoppingcart.dto.Product;

@Repository
public class Merchantdao {
	@Autowired
	EntityManagerFactory emf;
	@Autowired
	Productdao pdao;

	public void savemarchant(Merchent merchant) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.persist(merchant);
		et.commit();
	}

	public Merchent login(String email, String password) {
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select m from Merchent m where m.email=?1 and m.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			Merchent merchent = (Merchent) q.getSingleResult();
			return merchent;
		} catch (NoResultException e) {
			return null;
		}

	}

	public void deleteMerchantById(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Merchent m = em.find(Merchent.class, id);
		et.begin();
		em.remove(m);
		et.commit();
	}

	public Merchent findMarchantById(int id) {

		EntityManager em = emf.createEntityManager();
		Merchent m = em.find(Merchent.class, id);
		if (m != null)
			return m;
		else
			return null;
	}

	public void updatedateMerchant(Merchent m) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.merge(m);
		et.commit();

	}

	public Merchent deleteProductFromMerchant(int merchant_id, int product_id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		Merchent m = em.find(Merchent.class, merchant_id);
		List<Product> products = m.getProducts();
		List<Product> productlist = new ArrayList<Product>();
		for (Product p : products) {
			if (p.getId() != product_id) {
				productlist.add(p);
			}

		}
		m.setProducts(productlist);
		return m;

	}
}
