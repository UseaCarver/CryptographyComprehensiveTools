
import java.awt.BasicStroke;
import java.io.File;


import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class AESChart {
  public static XYSeriesCollection GetCollection1(long [][]time,File [] files)
  {
	  
	
	long []FileSize=new long[4];
	for (int i = 0; i < FileSize.length; i++) {
		FileSize[i]=files[i].length()/1048576;
	}
    XYSeriesCollection mCollection = new XYSeriesCollection();
    XYSeries bitLength128 = new XYSeries("128");
    for (int i = 0; i < FileSize.length; i++) {
		bitLength128.add(FileSize[i],time[0][i]);
	}
    XYSeries bitLength192=new XYSeries("192");
    for (int i = 0; i < FileSize.length; i++) {
		bitLength192.add(FileSize[i],time[1][i]);
	}
    XYSeries bitLength256=new XYSeries("256");
    for (int i = 0; i < FileSize.length; i++) {
		bitLength256.add(FileSize[i],time[2][i]);
	}
   
     mCollection.addSeries(bitLength128);
     mCollection.addSeries(bitLength192);
     mCollection.addSeries(bitLength256);
     
    return mCollection;
  }

}
