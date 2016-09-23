package by.pvt.dudko.company.dto;

public class PaginationDto {
	// count element on the page
	private int count;
	// the first
	private int start;
	// number of page
	private int page;
	// count page
	private int allPage;
	//count element
	private int allCount;

	public PaginationDto(int allAmount, int allPage, int amount, int page, int start) {
		this.allCount = allAmount;
		this.allPage = allPage;
		this.count = amount;
		this.page = page;
		this.start = start;
	}

	public PaginationDto() {

	}

	public int getCount() {
		return count;
	}

	public void setCount(int amount) {
		this.count = amount;
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

	public int getAllCount() {
		return allCount;
	}

	public void setAllCount(int allAmount) {
		this.allCount = allAmount;
	}

}
