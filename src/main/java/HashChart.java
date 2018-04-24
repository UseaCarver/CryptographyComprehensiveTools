
import java.awt.BasicStroke;
import java.io.File;

import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class HashChart {
  public static XYSeriesCollection GetCollection1(long [][]time,File [] files)
  {
	  
	
	long []FileSize=new long[4];
	for (int i = 0; i < FileSize.length; i++) {
		FileSize[i]=files[i].length()/1048576;
	}
    XYSeriesCollection mCollection = new XYSeriesCollection();
    XYSeries SHA1 = new XYSeries("SHA1");
    for (int i = 0; i < FileSize.length; i++) {
		SHA1.add(FileSize[i],time[0][i]);
	}
    XYSeries SHA1_256=new XYSeries("SHA-256");
    for (int i = 0; i < FileSize.length; i++) {
		SHA1_256.add(FileSize[i],time[1][i]);
	}
    XYSeries SHA1_384=new XYSeries("SHA-384");
    for (int i = 0; i < FileSize.length; i++) {
		SHA1_384.add(FileSize[i],time[2][i]);
	}
    XYSeries SHA1_512=new XYSeries("SHA-512");
    for (int i = 0; i < FileSize.length; i++) {
		SHA1_512.add(FileSize[i],time[3][i]);
	}
     mCollection.addSeries(SHA1);
     mCollection.addSeries(SHA1_256);
     mCollection.addSeries(SHA1_384);
     mCollection.addSeries(SHA1_512);
    return mCollection;
  }

}
