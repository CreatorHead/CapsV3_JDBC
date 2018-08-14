package com.caps.jsp;

public class PasswordNotMatch extends RuntimeException {
	 @Override
	public String getMessage() {
		return "New Passwords are not Matching";
	}
}
