package utils;

public enum SchedulePeriod {
	WEEKLY(1),
	BIWEEKLY(2),
	MONTHLY(3);
	
	private Integer value;
	
	SchedulePeriod(Integer value){
		this.value = value;
	}
	
	public Integer getValue(){
		return this.value;
	}

}
