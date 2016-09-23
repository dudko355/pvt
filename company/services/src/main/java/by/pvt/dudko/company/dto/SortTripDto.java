package by.pvt.dudko.company.dto;

public class SortTripDto {
	private String orderColumn;
	private String column ;
	private final int start = 1;

	public SortTripDto() {
	}

	public SortTripDto(String orderColumn, String column) {
		this.orderColumn=orderColumn;
		this.column=column;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public int getStart() {
		return start;
	}
}
