package gd.web.test;

import org.junit.*;
import gd.web.entity.*;

public class UserTest {
	
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
