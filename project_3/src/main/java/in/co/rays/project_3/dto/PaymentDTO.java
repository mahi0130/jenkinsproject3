package in.co.rays.project_3.dto;

public class PaymentDTO  extends BaseDTO{
	private String name;
	private String accountNumber;
	private int amount;
	private String bankName;
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return bankName;
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return bankName;
	}
	
	

}
