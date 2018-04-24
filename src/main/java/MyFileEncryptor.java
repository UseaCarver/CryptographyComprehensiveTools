

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.junit.validator.PublicClassValidator;
import org.omg.CORBA.PUBLIC_MEMBER;

public class MyFileEncryptor {
	public static void encryptFile(File sourceFile, 
			File encryptedFile, String password,String bitString,String algorithmName,String runMode,String fillPlan) {
		try {
			
			FileInputStream fis = new FileInputStream(sourceFile);
			FileOutputStream fos = new FileOutputStream(encryptedFile);
			int byteLength=Integer.parseInt(bitString)/8;
			//创建密钥
			MessageDigest md = MessageDigest.getInstance("SHA3-512");
			byte[] hashValue = md.digest(password.getBytes());
			
			String runModeString=algorithmName+"/"+runMode+"/"+fillPlan;
			
			if (algorithmName.equals("AES")) {
				SecretKeySpec key = new SecretKeySpec(hashValue, 0, byteLength, algorithmName);
				if (!runMode.equals("ECB")) {
					byte[] ivValue = new byte[16];
					Random random = new Random(System.currentTimeMillis());
					random.nextBytes(ivValue);
					IvParameterSpec iv = new IvParameterSpec(ivValue);
					fos.write("MyFileEncryptor1".getBytes());//16个字节加密校验
					fos.write(RunModeData.RunModeData1(bitString, algorithmName, runMode, fillPlan).getBytes());
					fos.write(hashValue); //64个字节的密码哈希值
					
					fos.write(ivValue);  //16个字节的随机变量 作为盐。AES 16个字节 DES 8个字节
					//创建并配置Cipher对象用于加密
					Cipher cipher = Cipher.getInstance(runModeString);
					cipher.init(Cipher.ENCRYPT_MODE, key, iv);
					//创建加密输入流
					CipherInputStream cis = new CipherInputStream(fis, cipher);
					byte[] buffer = new byte[64];
					int n = 0;
					while((n = cis.read(buffer)) != -1) {
						fos.write(buffer, 0, n);
					}
					fos.close();
					cis.close();
				}
				else
				{
					fos.write("MyFileEncryptor1".getBytes());//16个字节加密校验
					fos.write(RunModeData.RunModeData1(bitString, algorithmName, runMode, fillPlan).getBytes());
					fos.write(hashValue); //64个字节的密码哈希值
					Cipher cipher = Cipher.getInstance(runModeString);
					cipher.init(Cipher.ENCRYPT_MODE, key);
					//创建加密输入流
					CipherInputStream cis = new CipherInputStream(fis, cipher);
					byte[] buffer = new byte[64];
					int n = 0;
					while((n = cis.read(buffer)) != -1) {
						fos.write(buffer, 0, n);
					}
					fos.close();
					cis.close();
				}
				}
			else {               //DES加密从此开始
				SecretKeySpec key = new SecretKeySpec(hashValue, 0, byteLength, algorithmName);
				if (!runMode.equals("ECB")) {
					byte[] ivValue = new byte[8];
					Random random = new Random(System.currentTimeMillis());
					random.nextBytes(ivValue);
					IvParameterSpec iv = new IvParameterSpec(ivValue);
					fos.write("MyFileEncryptor1".getBytes());//16个字节加密校验
					fos.write(RunModeData.RunModeData1(bitString, algorithmName, runMode, fillPlan).getBytes());
					fos.write(hashValue); //64个字节的密码哈希值
					fos.write(ivValue);  //16个字节的随机变量 作为盐。AES 16个字节 DES 8个字节
					//创建并配置Cipher对象用于加密
					Cipher cipher = Cipher.getInstance(runModeString);
					cipher.init(Cipher.ENCRYPT_MODE, key, iv);
					//创建加密输入流
					CipherInputStream cis = new CipherInputStream(fis, cipher);
					byte[] buffer = new byte[64];
					int n = 0;
					while((n = cis.read(buffer)) != -1) {
						fos.write(buffer, 0, n);
					}
					fos.close();
					cis.close();
				}
				else
				{
					fos.write("MyFileEncryptor1".getBytes());//16个字节加密校验
					fos.write(RunModeData.RunModeData1(bitString, algorithmName, runMode, fillPlan).getBytes());
					fos.write(hashValue); //64个字节的密码哈希值
					Cipher cipher = Cipher.getInstance(runModeString);
					cipher.init(Cipher.ENCRYPT_MODE, key);
					//创建加密输入流
					CipherInputStream cis = new CipherInputStream(fis, cipher);
					byte[] buffer = new byte[64];
					int n = 0;
					while((n = cis.read(buffer)) != -1) {
						fos.write(buffer, 0, n);
					}
					fos.close();
					cis.close();
				}
				
			}
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void decryptFile(File encryptedFile,
			File decryptedFile, String password) {
		try {
			FileInputStream fis = new FileInputStream(encryptedFile);
			FileOutputStream fos = new FileOutputStream(decryptedFile);
			MessageDigest md = MessageDigest.getInstance("SHA3-512");
			byte[] hashValue = md.digest(password.getBytes());
			
			byte[] fileIdentifier = new byte[16];
			fis.read(fileIdentifier);   //首先读取文件识别码
			byte[] runModeData=new byte[4];    //第一位记录加密长度，第二位记录算法，第三位记录运行模式，第四位记录填充方案
			fis.read(runModeData);    //其次读取算法的运行数据
			
			RunModeData.RunModeData0(new String(runModeData));
			String runModeString=RunModeData.algorithmName+"/"+RunModeData.runMode+"/"+RunModeData.fillPlan;
			int decryptLength=Integer.parseInt(RunModeData.bitLength)/8;
			
			byte[] savedHashValue=new byte[64];
			fis.read(savedHashValue);    //读取哈希值
			

			if (fileIdentifier.toString().equals("MyFileEncryptor1")) {
				JOptionPane.showMessageDialog(null, "文件不是我加密的，爱找谁找谁去！");
			}
			else if (!Arrays.equals(hashValue, savedHashValue)) {
				JOptionPane.showMessageDialog(null, "口令输入错误！");
			}
			else {
			//	1.判断算法是AES,还是DES，以及是是否存在iv，iv的长度
				//2.构造密钥对象，执行解密
				SecretKeySpec key = new SecretKeySpec(hashValue, 0, decryptLength, RunModeData.algorithmName);
				Cipher cipher = Cipher.getInstance(runModeString);
				
				if (runModeData[1]=='A') {
					if (runModeData[2]=='A') {
						//AES下的ECB解密
						cipher.init(Cipher.DECRYPT_MODE, key);
						//将文件输入流fis封装为解密文件输入流
						CipherInputStream cis = new CipherInputStream(fis, cipher);
						byte[] buffer = new byte[64];
						int n = 0;
						while((n = cis.read(buffer)) != -1) {
							fos.write(buffer, 0, n);
						}
						fos.close();
						cis.close();
					} else {        //AES下的其他运行模式解密
						byte[] ivValue = new byte[16];
						fis.read(ivValue);
						IvParameterSpec iv = new IvParameterSpec(ivValue);
						
						//创建并配置Cipher对象用于解密
						cipher.init(Cipher.DECRYPT_MODE, key, iv);
						//将文件输入流fis封装为解密文件输入流
						CipherInputStream cis = new CipherInputStream(fis, cipher);
						byte[] buffer = new byte[64];
						int n = 0;
						while((n = cis.read(buffer)) != -1) {
							fos.write(buffer, 0, n);
						}
						
						fos.close();
						cis.close();

					}
					
				} else {                    //DES解密
					
					if (runModeData[2]=='A') {
						//DES下的ECB解密
						cipher.init(Cipher.DECRYPT_MODE, key);
						//将文件输入流fis封装为解密文件输入流
						CipherInputStream cis = new CipherInputStream(fis, cipher);
						byte[] buffer = new byte[64];
						int n = 0;
						while((n = cis.read(buffer)) != -1) {
							fos.write(buffer, 0, n);
						}
						fos.close();
						cis.close();
						
					} else {        //DES下的其他运行模式解密
						byte[] ivValue = new byte[8];
						fis.read(ivValue);
						IvParameterSpec iv = new IvParameterSpec(ivValue);
						
						//创建并配置Cipher对象用于解密
						cipher.init(Cipher.DECRYPT_MODE, key, iv);
						//将文件输入流fis封装为解密文件输入流
						CipherInputStream cis = new CipherInputStream(fis, cipher);
						byte[] buffer = new byte[64];
						int n = 0;
						while((n = cis.read(buffer)) != -1) {
							fos.write(buffer, 0, n);
						}
						
						fos.close();
						cis.close();

					}

				}
			}
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
}














