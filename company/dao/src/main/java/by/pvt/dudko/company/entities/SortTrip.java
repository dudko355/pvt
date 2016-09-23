package by.pvt.dudko.company.entities;

public class SortTrip {
	private String orderColumn;
	private String column;
    private final int start=1;
    
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
