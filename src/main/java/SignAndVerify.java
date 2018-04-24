import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class SignAndVerify {
	public static void signFile(File fileToSign, PrivateKey key, File signValueFile,String algorithmName1,String algorithmName) throws Exception {
		// try-with-resource语句创建的流不需要手动关闭
		try (FileInputStream fis = new FileInputStream(fileToSign);
				FileOutputStream fos = new FileOutputStream(signValueFile)) {
			//创建数字签名对象
			Signature signature = Signature.getInstance(algorithmName1);
			//用私钥初始化数字签名对象，让它做签名生成工作
			signature.initSign(key);
			String runData=SignRunData.RunModeData1(algorithmName, algorithmName1);
			fos.write(runData.getBytes());
			// 将文件内容加载到数字签名对象上
			byte[] buffer = new byte[1024];
			byte[] signaturValue;
			int n = 0;
			while ((n = fis.read(buffer)) != -1) {
				signature.update(buffer, 0, n);
			}
			signaturValue = signature.sign();
			fos.write(signaturValue);
		}
	}

	
	public static boolean verifyFile(File fileToVerify, PublicKey key, File signValueFile) throws Exception {
		// try-with-resource语句创建的流不需要手动关闭
		try (FileInputStream fisFileToVerify = new FileInputStream(fileToVerify);
				FileInputStream fisSignValueFile = new FileInputStream(signValueFile)) 
		{
			// 创建数字签名对象
			byte []al=new byte[2];
			fisSignValueFile.read(al);
			
			SignRunData.RunModeData0(new String(al));
			Signature signature = Signature.getInstance(SignRunData.algorithmName);
			// 用公钥初始化数字+签名对象，让它做签名验证工作
			signature.initVerify(key);
			// 将文件内容加载到数字签名对象上
			byte[] buffer = new byte[1024];
			int n = 0;
			while ((n = fisFileToVerify.read(buffer)) != -1) {
				signature.update(buffer, 0, n);
			}
			// 读取数字签名值
			byte[] signatureValue = new byte[fisSignValueFile.available()];
			fisSignValueFile.read(signatureValue);
			// 验证数字签名并返回验证结果
			return signature.verify(signatureValue);
		}
	}


}
