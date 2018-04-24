import javax.swing.JOptionPane;

public class SignRunData {
	public static String algorithmName;   //eg SHAWITHRSA
	public static String algorithmName1;//eg RSA
	public static String numberString;
	public static String []algorithmNameCollect={"SHA224withRSA",
			"SHA256withRSA",
			"SHA384withRSA",
			"SHA512withRSA",
			"RIPEMD128withRSA",
			"RIPEMD160withRSA",
			"SHA224withDSA",
			"SHA256withDSA",
			"SHA384withDSA",
			"SHA512withDSA",
			"SHA1withECDSA",
			"SHA224withECDSA",
			"SHA256withECDSA",
			"SHA384withECDSA",
			"SHA512withECDSA"};
	public static String []algorithmNameCollect1={"RSA","DSA","ECDSA"};
	public  static void RunModeData0(String numberData) 
	{
		
		char []b=new char[numberData.length()];
		for (int i = 0; i < b.length; i++) {
			b[i]=numberData.charAt(i);
		}
		try {
			algorithmName=algorithmNameCollect[b[1]-65];
			algorithmName1=algorithmNameCollect1[b[0]-65];
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ç©ÃûÑéÖ¤Ê§°Ü");
		}
	}
	 public static String RunModeData1(String an1,String an) {
		 numberString="";
		 
		 numberString=setalgorithmName1(an1);
		 numberString=setalgorithmName(an);
		return numberString;
	}
	 public static void setName(String aString) {
		 char []b=new char[aString.length()];
			for (int i = 0; i < b.length; i++) {
				b[i]=aString.charAt(i);
			}
			
	}
	 public static String setalgorithmName1(String an1) {
		for (int i = 0; i < algorithmNameCollect1.length; i++) {
			if (an1.equals(algorithmNameCollect1[i])) {
				char []a=new char[1];
				a[0]=(char) (i+65);
				String s = new String(a);
				
				numberString=numberString+s;
				
			}
		}
		
		return numberString;
	 }
	 public static String setalgorithmName(String an) {
			for (int i = 0; i < algorithmNameCollect.length; i++) {
				if (an.equals(algorithmNameCollect[i])) {
					char []a=new char[1];
					a[0]=(char) (i+65);
					String s = new String(a);
					numberString=numberString+s;
				}
			}
			
			return numberString;
		 }
}
