package gd.web.test;

import org.junit.Test;
import gd.web.util.Enum;

public class UtilTest {

	@Test
	public void testEnum(){
		String admin = Enum.ADMINISTRATOR.toString();
		System.out.println(admin);
	}
}
