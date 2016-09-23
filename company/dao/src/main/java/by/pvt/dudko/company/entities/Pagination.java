package by.pvt.dudko.company.entities;

public class Pagination {
	
	private int amount;
	private int start;
	private int page;
	private int allPage;
	private int allAmount;
	
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getAllPage() {
		return allPage;
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

	public int getAllAmount() {
		return allAmount;
	}

	public void setAllAmount(int allAmount) {
		this.allAmount = allAmount;
	}
}
