package chat.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class GeradorSerial {

	public static String Criar() {
		UUID uuid = UUID.randomUUID();  
		String myRandom = uuid.toString();  
		return myRandom.substring(0,20); 
	}
	
	private static Integer incremental = 0;
	
	public static Integer AutoIncremento(){
		return ++incremental;
	}
}
