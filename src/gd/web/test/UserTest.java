package gd.web.test;

import org.junit.*;
import gd.web.entity.*;

public class UserTest {
	//private User u1 = new User();
	private UserSub us1 = new UserSub();
	private Sub2 us2 = new Sub2();
	@Test
	public void testUser(){
		//test if the sub class have the private property from super class
		//u1.setUserName("username1");
		//System.out.println(u1.getUserName());
		System.out.println(us1.getName());
		System.out.println(us2.getName());
		us1.setName();
		us2.setName();
		//System.out.println(u1.getUserName());
		System.out.println(us1.getName());
		System.out.println(us2.getName());
		System.out.println();
		System.out.println();
		System.out.println();
		
		// as the result say, the private property can be inherited by sub class
		// but the private property could not be accessed directly in sub class
		// but through the public or protected method.like getter and setter
	}
	@Test
	public void tStringToFloat(){
		String si = "5344.22276567";
		double fa = Double.parseDouble(si);
		System.out.println(fa);
		fa = Double.valueOf(si);
		System.out.println(fa);
		fa = new Double(si);

		System.out.println(fa);
	}
}
