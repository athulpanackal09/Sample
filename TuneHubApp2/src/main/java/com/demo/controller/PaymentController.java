package com.demo.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Controller
public class PaymentController {

	@PostMapping("/createOrder")
	public String createOrder() {
		
		try
		{
			RazorpayClient razorpay = new RazorpayClient("rzp_test_dOfBa6zHV5jg31", "96y0BxwDs0Uw5EO2UsVSvAOU");

			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount",50000);
			orderRequest.put("currency","INR");
			orderRequest.put("receipt", "receipt#1");
			JSONObject notes = new JSONObject();
			notes.put("notes_key_1","Tea, Earl Grey, Hot");
			orderRequest.put("notes",notes);

			Order order = razorpay.orders.create(orderRequest);
		}
		catch(Exception e)
		{
			System.out.println("Exception while creating an order");
		}
		return "order";
		
	}
}
