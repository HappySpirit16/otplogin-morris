package co.approbe.powwi.transaction.dao;

public class FinancialInformation {
	private int mainIncome;
	private int otherIncome;
	private int totalIncome;
	private int expense;
	private int assets;
	private int liability;
	private boolean declare;
	private boolean agent;
	private int totalNetworth;
	
	public int getMainIncome() {
		return mainIncome;
	}
	public void setMainIncome(int mainIncome) {
		this.mainIncome = mainIncome;
	}
	public int getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(int otherIncome) {
		this.otherIncome = otherIncome;
	}
	public int getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(int totalIncome) {
		this.totalIncome = totalIncome;
	}
	public int getExpense() {
		return expense;
	}
	public void setExpense(int expense) {
		this.expense = expense;
	}
	public int getAssets() {
		return assets;
	}
	public void setAssets(int assets) {
		this.assets = assets;
	}
	public int getLiability() {
		return liability;
	}
	public void setLiability(int liability) {
		this.liability = liability;
	}
	public boolean isDeclare() {
		return declare;
	}
	public void setDeclare(boolean declare) {
		this.declare = declare;
	}
	public boolean isAgent() {
		return agent;
	}
	public void setAgent(boolean agent) {
		this.agent = agent;
	}
	public int getTotalNetworth() {
		return totalNetworth;
	}
	public void setTotalNetworth(int totalNetworth) {
		this.totalNetworth = totalNetworth;
	}
	@Override
	public String toString() {
		return "FinancialInformation [mainIncome=" + mainIncome + ", otherIncome=" + otherIncome + ", totalIncome="
				+ totalIncome + ", expense=" + expense + ", assets=" + assets + ", liability=" + liability
				+ ", declare=" + declare + ", agent=" + agent + ", totalNetworth=" + totalNetworth + "]";
	}
	
	
	
}
