package utils;

public enum SchedulePeriod {
	WEEKLY(1),
	BIWEEKLY(2),
	MONTHLY(3);
	
	private Integer value;
	
	SchedulePeriod(Integer value){
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static SchedulePeriod getEnum(Integer value){
		for(SchedulePeriod e : SchedulePeriod.values()){
			if(e.value == value){
				return e;
			}
		}
		return null;
	}
}
