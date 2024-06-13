package com.jsp.shoppingcart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart.dao.Merchantdao;
import com.jsp.shoppingcart.dao.Productdao;
import com.jsp.shoppingcart.dto.Merchent;
import com.jsp.shoppingcart.dto.Product;

@Controller
public class ProductController {
	@Autowired
	Productdao dao;
	@Autowired
	Merchantdao mdao;
@RequestMapping("/addproduct")
public ModelAndView addproduct() {
	Product p=new Product();
	ModelAndView m=new ModelAndView();
	m.addObject("productobj",p);
	m.setViewName("productform");
	return m;
	
}
@RequestMapping("/saveproduct")
public ModelAndView saveproduct(@ModelAttribute("productobj") Product p,HttpSession session) {
	Merchent m1=(Merchent)session.getAttribute("merchantinfo");
	List<Product> products=m1.getProducts();
	if(products.size()>0) {
		products.add(p);
	}else {
		List<Product> productlist=new ArrayList<Product>();
		productlist.add(p);
		m1.setProducts(productlist);
	}
	dao.saveproduct(p);
	mdao.updatedateMerchant(m1);
	ModelAndView m=new ModelAndView();
	m.addObject("msg","saved successfully");
	m.setViewName("merchantoption");
	return m;
	
}
@RequestMapping("/displayproducts")
public ModelAndView displayProducts() {
	List<Product> products= dao.fetchAllProduct();
	ModelAndView mav= new ModelAndView();
	mav.addObject("productlist", products);
	mav.setViewName("viewallproductscustomer");
	return mav;}
@RequestMapping("/deleteproduct")
public ModelAndView deleteproduct(@RequestParam("id") int id,HttpSession session) {
	Merchent merchant=(Merchent) session.getAttribute("merchantinfo");
	Merchent me=mdao.deleteProductFromMerchant(merchant.getId(), id);
	mdao.updatedateMerchant(me);
	dao.deleteproductById(id);
	session.setAttribute("merchantinfo", me);
	ModelAndView m=new ModelAndView();
	m.setViewName("viewAllproducts");
	
	return m;
	
}
@RequestMapping("/displayproductbybrand")
public ModelAndView displayProductbybrand() {
	List<Product> products= dao.fetchAllProduct();
	ModelAndView mav= new ModelAndView();
	mav.addObject("productlist", products);
	mav.setViewName("viewallproductscustomer");
	return mav;
}
@RequestMapping("/categorybyproduct")
public ModelAndView displayproductByCategory(ServletRequest req) {
	String category=req.getParameter("category");
	List<Product> products=dao.findProductByCategory(category);
	ModelAndView mav = new ModelAndView();
	mav.addObject("productlist", products);
	mav.setViewName("viewallproductscustomer");
	return mav;
}

}
