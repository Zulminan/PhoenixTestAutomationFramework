package com.api.constants;

public enum ServiceLocation {
	
	SERVCE_LOCATION_A(1),
	SERVCE_LOCATION_B(2),
	SERVCE_LOCATION_C(3);
	
	int code;
	
	private ServiceLocation(int code)
	{
		this.code = code;
	}

	public int getCode()
	{
		return code;
	}
}
