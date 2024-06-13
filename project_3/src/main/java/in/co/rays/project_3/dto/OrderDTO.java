package in.co.rays.project_3.dto;

import java.util.Date;

import in.co.rays.project_3.controller.BaseCtl;

public class OrderDTO extends BaseDTO {

	private String customerName;
	private String product;
	private String amount;
	private Date date;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return product;
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return product;
	}
	

	}
