import java.util.StringTokenizer;

public class RunModeData {
	public static String bitLength;
	public static String algorithmName;
	public static String runMode;
	public static String fillPlan;
	public static String numberString="";
	
	public  static void RunModeData0(String numberData) 
	{
		
		char []b=new char[numberData.length()];
		for (int i = 0; i < b.length; i++) {
			b[i]=numberData.charAt(i);
		}
	     setBitLength(b[0]);
	     setAlgorithmName(b[1]);
	     setRunMode(b[2]);
	     setFillPlan(b[3]);
	     
	}
	 public static String RunModeData1(String bit,String algor,String runMo,String fillPl) {
		 numberString="";
		 numberString=setBitLengthNumber(bit);
		 numberString=setAlgorithmNameNumber(algor);
		 numberString=setRunModeNumber(runMo);
		 numberString=setFillPlanNumber(fillPl);
		return numberString;
	}
	 public static String setBitLengthNumber(String bit) {
		 if (bit.equals("128")) {
			numberString=numberString+"A";
		}
		 else if (bit.equals("192")) {
			 numberString=numberString+"B";
		}
		 else if (bit.equals("256")) {
			 numberString=numberString+"C";
		}
		 else numberString=numberString+"D";
		 return numberString;
	}
	 public static String setAlgorithmNameNumber(String algor) {
		 if (algor.equals("AES")) {
			 numberString=numberString+"A";
		}
		 else {
			 numberString=numberString+"B";
		}
		 return numberString;
	}
	 public static String setRunModeNumber(String runMo) {
		 if (runMo.equals("ECB")) {
			 numberString=numberString+"A";
		}
		 else if (runMo.equals("CBC")) {
			 numberString=numberString+"B";
		}
		 else if (runMo.equals("CTR")) {
			 numberString=numberString+"C";
		}
		 else if (runMo.equals("OFB")) {
			 numberString=numberString+"D";
		}
		 else  {
			 numberString=numberString+"E";
		}
		 return numberString;
	}
	 public static String setFillPlanNumber(String fillPl) {
		 if (fillPl.equals("NoPadding")) {
			 numberString=numberString+"A";
		}
		 else if (fillPl.equals("PKCS5Padding")) {
			 numberString=numberString+"B";
		}
		 else {
			 numberString=numberString+"C";
		}
		 return numberString;
	 }
	public static void setBitLength(char b) {
		if (b=='A') 
		{
			bitLength="128";
		}
		else if (b=='B') {
			bitLength="192";
		}
		else if (b=='C') {
			bitLength="256";
		}else bitLength="64";
	}
	public static void setAlgorithmName(char b) {
		if (b=='A') 
		{
			algorithmName="AES";
		}
		else
			algorithmName="DES";
	}
	public static void setRunMode(char b) {
		if (b=='A') {
			runMode="ECB";	
			//new String[] {"ECB", "CBC", "CTR", "OFB", "CFB"
		}
		else if (b=='B') {
			runMode="CBC";
		}
		else if (b=='C') {
			runMode="CTR";
		}
		else if (b=='D') {
			runMode="OFB";
		}
		else {
			runMode="CFB";
		}
	}
	public static void setFillPlan(char b) {
		if (b=='A') {
			fillPlan="NoPadding";
		}
		else if (b=='B') {
			fillPlan="PKCS5Padding";		//PKCS5Padding", "ISO10126Padding", "NoPadding"		
		}
		else {
			fillPlan="ISO10126Padding";	
		}
	}
	

}
