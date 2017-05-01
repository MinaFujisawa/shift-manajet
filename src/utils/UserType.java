package utils;

public enum UserType {
	
	EMPLOYEE("E"),
	MANAGER("M");
	
	private String value;
	
	UserType(String typeValue){
		this.value = typeValue;
	}
	
	public String getValue(){
		return this.value;
	}

}
