package com.jsp.shoppingcart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart.dao.Cartdao;
import com.jsp.shoppingcart.dao.Customerdao;
import com.jsp.shoppingcart.dao.ItemDao;
import com.jsp.shoppingcart.dao.Productdao;
import com.jsp.shoppingcart.dto.Cart;
import com.jsp.shoppingcart.dto.Customer;
import com.jsp.shoppingcart.dto.Iteam;
import com.jsp.shoppingcart.dto.Product;

@Controller
public class ItemController {
@Autowired
ItemDao idao;
@Autowired
Cartdao cdao;
@Autowired
Customerdao cusdao;
@Autowired
Productdao pdao;

@RequestMapping("/additem")
public ModelAndView additem(@RequestParam("id") int id) {
	Product p=pdao.findproductById(id);
	ModelAndView m=new  ModelAndView();
	m.addObject("prodobj",p);
	m.setViewName("itemform");
	return m;
}
@RequestMapping("/additemtocart")
public ModelAndView additemintocart(ServletRequest req,HttpSession session) {
	int product_id=Integer.parseInt(req.getParameter("id"));
	String brand=req.getParameter("brand");
	double price=Double.parseDouble(req.getParameter("price"));
	String modle=req.getParameter("model");
	String category=req.getParameter("category");

		int Quantity=Integer.parseInt(req.getParameter("quantity"));
	
	Iteam item=new Iteam();
	item.setBrand(brand);
	item.setModel(modle);
	item.setCatagory(category);
	item.setQuantity(Quantity);
	item.setP_id(product_id);
	item.setPrice(price*Quantity);
	Customer customer=(Customer)session.getAttribute("customerinfo");
Cart c=customer.getCart();
	if(c==null)
	{
		Cart cart=new Cart();
		double totalprice=0;
		List<Iteam> items=new ArrayList<Iteam>();
		items.add(item);
		cart.setIteams(items);
		cart.setName(customer.getName());
		for(Iteam i:items) {
			totalprice=totalprice+i.getPrice();}
			cart.setTotalprice(totalprice);
			customer.setCart(cart);
			idao.saveItem(item);
			cdao.saveCart(cart);
			cusdao.updatecustomer(customer);
			
		}
	else {
		List<Iteam> items=c.getIteams();
		if(items.size()>0) {
			items.add(item);
			c.setIteams(items);
			double totalprice=0;
			for(Iteam i:items) {
				totalprice=totalprice+i.getPrice();
			}
			c.setTotalprice(totalprice);
			customer.setCart(c);
			idao.saveItem(item);
			cdao.updatedCartBYid(c);
			cusdao.updatecustomer(customer);
			
		}
		else {
			List<Iteam> itemlist=new ArrayList<Iteam>();
			itemlist.add(item);
			c.setIteams(itemlist);
			c.setTotalprice(item.getPrice());
			customer.setCart(c);
			idao.saveItem(item);
			cdao.updatedCartBYid(c);
			cusdao.updatecustomer(customer);
			
		}
		
	}
	ModelAndView m=new ModelAndView();
	m.setViewName("redirect://displayproducts");
	return m;
}


}
