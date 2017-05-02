package utils;

public enum UserType {
	
//	E("Employee"),
//	M("Manager");
	EMPLOYEE("E"),
	MANAGER("M");
	
	private String value;
	
	UserType(String typeValue){
		this.value = typeValue;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public static UserType getEnum(String value){
		for(UserType e : UserType.values()){
			if(e.value.equals(value)){
				return e;
			}
		}
		return null;
	}
	
}
