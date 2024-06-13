package com.jsp.shoppingcart.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart.dao.Customerdao;
import com.jsp.shoppingcart.dao.Productdao;
import com.jsp.shoppingcart.dto.Customer;


@Controller
public class CustomerController {
	@Autowired
	Customerdao cdao;
	@Autowired
	Productdao pdao;
@RequestMapping("/addcustomer")
public ModelAndView addcustomer() {
	Customer c=new Customer();
	ModelAndView m=new ModelAndView();
	m.addObject("customerobj",c);
	m.setViewName("customerform");
	return m;
}@RequestMapping("/savecustomer")
public ModelAndView saveCustomer(@ModelAttribute("customerobj") Customer c) {
	cdao.saveCustomer(c);
	ModelAndView m=new ModelAndView();
	m.addObject("msgs","success");
	m.setViewName("customerAccount");
	return m;
}
@RequestMapping("/CLoginValidation")
public ModelAndView login(ServletRequest req,HttpSession session) {
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	Customer c=cdao.login(email, password);
	ModelAndView mav=new ModelAndView();
	if(c!=null) {
		mav.addObject("msg","sucessfully logedin");
		session.setAttribute("customerinfo", c);
		mav.setViewName("customeroption");
		
		return mav;
	}
	else {
		
		mav.addObject("msg"," invalid credintials");
		mav.setViewName("customerlogin");
		return mav;
	}
	
}
}
