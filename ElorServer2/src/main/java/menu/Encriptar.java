package menu;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Encriptar {
	private static String algoritmo = "AES";


	 private static SecretKeySpec crearClave() throws NoSuchAlgorithmException, UnsupportedEncodingException {
	     
	     byte[] claveEncriptacion = algoritmo.getBytes("UTF-8");
	         
	        MessageDigest sha = MessageDigest.getInstance("SHA-1");
	         
	        claveEncriptacion = sha.digest(claveEncriptacion);
	        claveEncriptacion = Arrays.copyOf(claveEncriptacion, 16);
	         
	        SecretKeySpec secretKey = new SecretKeySpec(claveEncriptacion,algoritmo);
	 
	        return secretKey;
	    }
	 
	 public String encriptar(String datos) throws UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
	        SecretKeySpec secretKey = crearClave();
	         
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");        
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	 
	        byte[] datosEncriptar = datos.getBytes("UTF-8");
	        byte[] bytesEncriptados = cipher.doFinal(datosEncriptar);
	        String encriptado = Base64.getEncoder().encodeToString(bytesEncriptados);
	 
	        return encriptado;
	    }
	 
	 
	 public String desencriptar(String datosEncriptados) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {

	        SecretKeySpec secretKey = crearClave();
	 
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, secretKey);
	         
	        byte[] bytesEncriptados = Base64.getDecoder().decode(datosEncriptados);
	        byte[] datosDesencriptados = cipher.doFinal(bytesEncriptados);
	        String datos = new String(datosDesencriptados);
	         
	        return datos;
	    }
}
