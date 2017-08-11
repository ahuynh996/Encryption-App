import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class encodeDecode {

    public static void DESmain(String keyWord, String filename, String enOrDe) {

        File file = new File(filename);
        String absolutePath = file.getAbsolutePath();
        String filePath = absolutePath.substring(0,absolutePath.lastIndexOf(File.separator));
        if(file.isDirectory()) return;
        try{
            //System.out.println("working.... " + filename);
            FileInputStream fis = new FileInputStream(filename);
            String outputFilename = filePath+"/__" + file.getName();
            FileOutputStream fos = new FileOutputStream(outputFilename);
            
            String key = keyWord; // needs to be at least 8 characters for DES
	    
            if(enOrDe.compareToIgnoreCase("encode")==0){

                encrypt(key, fis, fos);
                
            }
            else{
                if(enOrDe.compareToIgnoreCase("decode")==0){

                    decrypt(key, fis, fos);
                }
            }
            File outFile = new File(outputFilename);
            
            boolean success = outFile.renameTo(file);
            if(!success)
                System.out.println("Could not rename the temp file!");
            
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void encrypt(String key, InputStream is, OutputStream os) throws Throwable {
	
        encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
    }

    public static void decrypt(String key, InputStream is, OutputStream os) throws Throwable {
	
        encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
    }

    public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {

	
        DESKeySpec dks = new DESKeySpec(key.getBytes());
	
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
	
        SecretKey desKey = skf.generateSecret(dks);
	
        Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

	
        if (mode == Cipher.ENCRYPT_MODE) {
	    
            cipher.init(Cipher.ENCRYPT_MODE, desKey);
	    
            CipherInputStream cis = new CipherInputStream(is, cipher);
	    
            doCopy(cis, os);
	
        } else if (mode == Cipher.DECRYPT_MODE) {
	    
            cipher.init(Cipher.DECRYPT_MODE, desKey);
	    
            CipherOutputStream cos = new CipherOutputStream(os, cipher);
	    
            doCopy(is, cos);
	
        }
    }

    public static void doCopy(InputStream is, OutputStream os) throws IOException {
	
        byte[] bytes = new byte[64];
	
        int numBytes;
	
        while ((numBytes = is.read(bytes)) != -1) {
	    
            os.write(bytes, 0, numBytes);
	
        }
	
        os.flush();
	
        os.close();
	
        is.close();
    }

}