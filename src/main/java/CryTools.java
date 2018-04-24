import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.bouncycastle.crypto.io.MacInputStream;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStore.SecretKeyEntry;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.RenderingHints.Key;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.border.CompoundBorder;
import javax.swing.SwingConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;


public class CryTools extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldData;
	private   JTextField textFieldSHA1;
	private   JTextField textFieldSHA224;
	private  JTextField textFieldSHA256;
	private  JTextField textFieldSHA384;
	private   JTextField textFieldSHA512;
	private  JTextField textFieldSHA3224;
	private   JTextField textFieldSHA3256;
	private   JTextField textFieldSHA3384;
	private  JTextField textFieldSHA3512;
	private JComboBox comboBoxData;
	private JButton btnChoiceFile;
	
	
	private  JCheckBox chckbxSHA1;
	private  JCheckBox chckbxSHA224;
	private  JCheckBox chckbxSHA256;
	private  JCheckBox chckbxSHA384;
	private  JCheckBox chckbxSHA512;
	private  JCheckBox chckbxSHA3224;
	private  JCheckBox chckbxSHA3256;
	private  JCheckBox chckbxSHA3384;
	private  JCheckBox chckbxSHA3512;
	public File file;   //设置选择的文件为全局变量
	public int DataSelected=1; //0代表选择字符串摘要，1代表选择文件摘要。初始值为1；
	private JRadioButton rdbtnAES;
	private JRadioButton rdbtnDES;
	private JComboBox comboBoxBitLength;
	private JComboBox comboBoxFillPlan;
	private JComboBox comboBoxRunMode;
	private JTextField textFieldToSignFile;
	private JTextField textFieldsignValueFile;
	public File toSignFile;
	public File signValueFile;
	private JButton btnNewButton;
	private JLabel lblNewLabel_2;
	private String signAlgorithmstring;//eg SHAwithRSA
	private String signAlgorithmstring1;//eg RSA
	private int sign=0;
	private JPasswordField passwordFieldMAC1;
	private JTextField textFieldMACSourceData;
	private JButton btnSelectMACFile;
	private JRadioButton rdbtnMACString;
	private int mac=0;
	private String macString;
	private File toMACFile;
	private File macValueFile=null;
	private JPasswordField passwordFieldMAC2;
	private char[] macpassword1;
	private char[] macpassword2;
	private JLabel lblMac;
	private JTextField textFieldMAC;
	private JTextField textFiledFile1;
	private JTextField textFiledFile2;
	private JTextField textFiledFile3;
	private JTextField textFiledFile4;
	private File file1;
	private File file2;
	private File file3;
	private File file4;
	
	
	
	
	public static void main(String[] args) {
		Security.addProvider(new BouncyCastleProvider());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CryTools frame = new CryTools();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	
	public CryTools() {
		setForeground(Color.GREEN);
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\\u56FE\u6807\\-528e3ab4a15d6649.jpg"));
		setTitle("\u5BC6\u7801\u5B66\u7EFC\u5408\u5DE5\u5177");
		setBackground(Color.CYAN);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 429, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 395, 456);
		contentPane.add(tabbedPane);
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new CompoundBorder());
		tabbedPane.addTab("HASH", null, panel, null);
		tabbedPane.setDisabledIconAt(0, new ImageIcon("C:\\Users\\\u603B\u88C1\\Desktop\\-528e3ab4a15d6649.jpg"));
		tabbedPane.setBackgroundAt(0, Color.WHITE);
		panel.setLayout(null);
		
			comboBoxData = new JComboBox();
			comboBoxData.setBackground(new Color(50, 205, 50));
			comboBoxData.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					 if (e.getStateChange() == ItemEvent.SELECTED) {
						    JComboBox jcb = (JComboBox) e.getSource();
						    String n=(String) (jcb.getSelectedItem());
						    if(n.equals("String"))   //如果选择的条目等于string，则设置选择文件的按钮为不可显示
						    {
						    	textFieldData.setEditable(true);
						    	 btnChoiceFile.setVisible(false);
						    	 DataSelected=0;   //设置标记为0；
						    }
						    else
						    {
						    	btnChoiceFile.setVisible(true);
						    	 DataSelected=1;  //设置标记为1；
						    	 textFieldData.setEditable(false);
						    }   
					}
				}
			});
			comboBoxData.setToolTipText("");
			comboBoxData.setModel(new DefaultComboBoxModel(new String[] {"File", "String"}));
			comboBoxData.setBounds(21, 10, 93, 21);
			panel.add(comboBoxData);
			
			JLabel lblData = new JLabel("data:");
			lblData.setBounds(21, 41, 30, 21);
			panel.add(lblData);
			
			textFieldData = new JTextField();
			textFieldData.setBackground(new Color(245, 222, 179));
			textFieldData.setEditable(false);
			textFieldData.setBounds(49, 41, 189, 21);
			panel.add(textFieldData);
			textFieldData.setColumns(10);
			
			btnChoiceFile = new JButton("\u9009\u62E9\u6587\u4EF6");
			btnChoiceFile.setBackground(new Color(255, 192, 203));
			btnChoiceFile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser fileChooser = new JFileChooser("D:");
					if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						textFieldData.setText(fileChooser.getSelectedFile().getPath());
						file = fileChooser.getSelectedFile();
						//弹出文件选择框，选择文件之后将值赋给全局变量file
				}
				}
			});
			
			btnChoiceFile.setBounds(248, 40, 93, 23);
			panel.add(btnChoiceFile);
			
			chckbxSHA1 = new JCheckBox("SHA-1");
			chckbxSHA1.setBackground(Color.WHITE);
			chckbxSHA1.setBounds(21, 75, 60, 23);
			panel.add(chckbxSHA1);
			
			textFieldSHA1 = new JTextField();
			textFieldSHA1.setBackground(new Color(240, 230, 140));
			textFieldSHA1.setEditable(false);
			textFieldSHA1.setBounds(123, 75, 257, 21);
			panel.add(textFieldSHA1);
			textFieldSHA1.setColumns(10);
			
			chckbxSHA224 = new JCheckBox("SHA-224");
			chckbxSHA224.setBackground(Color.WHITE);
			chckbxSHA224.setBounds(21, 108, 93, 23);
			panel.add(chckbxSHA224);
			
			chckbxSHA256 = new JCheckBox("SHA-256");
			chckbxSHA256.setBackground(Color.WHITE);
			chckbxSHA256.setBounds(21, 141, 93, 23);
			panel.add(chckbxSHA256);
			
			chckbxSHA384 = new JCheckBox("SHA-384");
			chckbxSHA384.setBackground(Color.WHITE);
			chckbxSHA384.setBounds(21, 174, 93, 23);
			panel.add(chckbxSHA384);
			
			chckbxSHA512 = new JCheckBox("SHA-512");
			chckbxSHA512.setBackground(Color.WHITE);
			chckbxSHA512.setBounds(21, 207, 93, 23);
			panel.add(chckbxSHA512);
			
			chckbxSHA3224 = new JCheckBox("SHA3-224");
			chckbxSHA3224.setBackground(Color.WHITE);
			chckbxSHA3224.setBounds(21, 240, 93, 23);
			panel.add(chckbxSHA3224);
			
			chckbxSHA3256 = new JCheckBox("SHA3-256");
			chckbxSHA3256.setBackground(Color.WHITE);
			chckbxSHA3256.setBounds(21, 273, 93, 23);
			panel.add(chckbxSHA3256);
			
			chckbxSHA3384 = new JCheckBox("SHA3-384");
			chckbxSHA3384.setBackground(Color.WHITE);
			chckbxSHA3384.setBounds(21, 306, 93, 23);
			panel.add(chckbxSHA3384);
			
			chckbxSHA3512 = new JCheckBox("SHA3-512");
			chckbxSHA3512.setBackground(Color.WHITE);
			chckbxSHA3512.setBounds(21, 339, 93, 23);
			panel.add(chckbxSHA3512);
			
			textFieldSHA224 = new JTextField();
			textFieldSHA224.setBackground(new Color(240, 230, 140));
			textFieldSHA224.setEditable(false);
			textFieldSHA224.setBounds(123,108 , 257, 21);
			panel.add(textFieldSHA224);
			textFieldSHA224.setColumns(10);
			textFieldSHA256 = new JTextField();
			textFieldSHA256.setBackground(new Color(240, 230, 140));
			textFieldSHA256.setEditable(false);
			textFieldSHA256.setBounds(123, 141, 257, 21);
			panel.add(textFieldSHA256);
			textFieldSHA256.setColumns(10);
			
			textFieldSHA384 = new JTextField();
			textFieldSHA384.setBackground(new Color(240, 230, 140));
			textFieldSHA384.setForeground(new Color(240, 230, 140));
			textFieldSHA384.setEditable(false);
			textFieldSHA384.setBounds(123, 174, 257, 21);
			panel.add(textFieldSHA384);
			textFieldSHA384.setColumns(10);
			
			textFieldSHA512 = new JTextField();
			textFieldSHA512.setBackground(new Color(240, 230, 140));
			textFieldSHA512.setForeground(new Color(240, 230, 140));
			textFieldSHA512.setEditable(false);
			textFieldSHA512.setBounds(123, 207, 257, 21);
			panel.add(textFieldSHA512);
			textFieldSHA512.setColumns(10);
			
			textFieldSHA3224 = new JTextField();
			textFieldSHA3224.setBackground(new Color(240, 230, 140));
			textFieldSHA3224.setForeground(new Color(240, 230, 140));
			textFieldSHA3224.setEditable(false);
			textFieldSHA3224.setBounds(123, 240, 257, 21);
			panel.add(textFieldSHA3224);
			textFieldSHA3224.setColumns(10);
			
			textFieldSHA3256 = new JTextField();
			textFieldSHA3256.setBackground(new Color(240, 230, 140));
			textFieldSHA3256.setForeground(new Color(240, 230, 140));
			textFieldSHA3256.setEditable(false);
			textFieldSHA3256.setBounds(123, 273, 257, 21);
			panel.add(textFieldSHA3256);
			textFieldSHA3256.setColumns(10);
			
			textFieldSHA3384 = new JTextField();
			textFieldSHA3384.setBackground(new Color(240, 230, 140));
			textFieldSHA3384.setForeground(new Color(240, 230, 140));
			textFieldSHA3384.setEditable(false);
			textFieldSHA3384.setBounds(123, 306, 257, 21);
			panel.add(textFieldSHA3384);
			textFieldSHA3384.setColumns(10);
			
			textFieldSHA3512 = new JTextField();
			textFieldSHA3512.setBackground(new Color(240, 230, 140));
			textFieldSHA3512.setForeground(new Color(240, 230, 140));
			textFieldSHA3512.setEditable(false);
			textFieldSHA3512.setBounds(123, 339, 257, 21);
			panel.add(textFieldSHA3512);
			textFieldSHA3512.setColumns(10);
			
			JButton btnCalc = new JButton("\u8BA1\u7B97");
			btnCalc.setBackground(new Color(255, 192, 203));
			Hashcalc hashcalc=new Hashcalc();
			btnCalc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(textFieldData.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "请输入待处理数据");
					}
					
					hashcalc.setBoolFalse();
					hashcalc.setTextFileNull();
					hashcalc.setCheckBox();
					if (DataSelected==0) {
						int count=0;
						for (int i = 0; i < 9; i++) {
							if (hashcalc.isSelected[i]==true) {
								count++;
								try {
									MessageDigest md = MessageDigest.getInstance(hashcalc.cStrings[i]);
									byte[] msg = textFieldData.getText().getBytes();
									md.update(msg);
									byte[] hashValue = md.digest();
									hashcalc.jTextFields[i].setText(Hex.toHexString(hashValue).toUpperCase());
								} catch (NoSuchAlgorithmException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								}
							}
						if(count==0)JOptionPane.showMessageDialog(null, "请选中哈希算法");
					}
					else {
						int count=0;
						for (int i = 0; i < 9; i++) {
							if (hashcalc.isSelected[i]==true) {
								count++;
								try {
									FileInputStream fis = new FileInputStream(file);
									//水表-消息摘要对象
									MessageDigest md = MessageDigest.getInstance(hashcalc.cStrings[i]);
									//装了水表的水管-消息摘要输入流
									DigestInputStream dis = new DigestInputStream(fis, md);
									byte[] buffer = new byte[4096];
									//int n=0;

									while(dis.read(buffer) != -1);
									
									byte[] digest = md.digest();
									hashcalc.jTextFields[i].setText(new HexBinaryAdapter().marshal(digest));
									dis.close();
								} catch (NoSuchAlgorithmException | IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								}
							}
						if(count==0)JOptionPane.showMessageDialog(null, "请选中哈希算法");
						
					}
					}
				});
			btnCalc.setBounds(32, 377, 93, 23);
			panel.add(btnCalc);
			
			JButton buttonClear = new JButton("\u6E05\u7A7A");
			buttonClear.setBackground(new Color(255, 192, 203));
			buttonClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					textFieldData.setText("");
					hashcalc.setTextFileNull();     //点击清空，则清空所有的编辑框
					hashcalc.setBoolFalse();
					hashcalc.setChxSelected();
					
				}
			});
			
			buttonClear.setBounds(229, 377, 93, 23);
			panel.add(buttonClear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("\u6587\u4EF6\u52A0\u5BC6", null, panel_1, null);
		tabbedPane.setForegroundAt(1, new Color(0, 0, 0));
		tabbedPane.setBackgroundAt(1, Color.WHITE);
		panel_1.setLayout(null);
		
		rdbtnAES = new JRadioButton("AES");
		rdbtnAES.setBackground(Color.PINK);
		rdbtnAES.setSelected(true);
		rdbtnAES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnDES.setSelected(false);
				comboBoxBitLength.removeAllItems();
				comboBoxBitLength.addItem(128);
				comboBoxBitLength.addItem(192);
				comboBoxBitLength.addItem(256);
			}
		});
		rdbtnAES.setBounds(61, 24, 121, 23);
		panel_1.add(rdbtnAES);
		
		rdbtnDES = new JRadioButton("DES");
		rdbtnDES.setBackground(Color.PINK);
		rdbtnDES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnAES.setSelected(false);
				comboBoxBitLength.removeAllItems();
				comboBoxBitLength.addItem(64);
			}
		});
		rdbtnDES.setBounds(244, 24, 121, 23);
		panel_1.add(rdbtnDES);
		
		JLabel label = new JLabel("\u957F\u5EA6");
		label.setFont(new Font("方正姚体", Font.PLAIN, 12));
		label.setBounds(22, 53, 39, 15);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("\u586B\u5145\u65B9\u6848");
		label_1.setFont(new Font("方正姚体", Font.PLAIN, 12));
		label_1.setBounds(156, 53, 54, 15);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("\u8FD0\u884C\u6A21\u5F0F");
		label_2.setFont(new Font("方正姚体", Font.PLAIN, 12));
		label_2.setBounds(292, 53, 54, 15);
		panel_1.add(label_2);
		
		comboBoxBitLength = new JComboBox();
		comboBoxBitLength.setBackground(new Color(51, 204, 102));
		comboBoxBitLength.setFont(new Font("Arial Narrow", Font.ITALIC, 12));
		comboBoxBitLength.setModel(new DefaultComboBoxModel(new String[] {"128", "192", "256"}));
		comboBoxBitLength.setBounds(26, 78, 54, 21);
		panel_1.add(comboBoxBitLength);
		
		comboBoxFillPlan = new JComboBox();
		comboBoxFillPlan.setBackground(new Color(51, 204, 51));
		comboBoxFillPlan.setFont(new Font("Arial Narrow", Font.ITALIC, 12));
		comboBoxFillPlan.setModel(new DefaultComboBoxModel(new String[] {"PKCS5Padding", "ISO10126Padding", "NoPadding"}));
		comboBoxFillPlan.setBounds(123, 78, 131, 21);
		panel_1.add(comboBoxFillPlan);
		
		comboBoxRunMode = new JComboBox();
		comboBoxRunMode.setBackground(new Color(51, 255, 51));
		comboBoxRunMode.setFont(new Font("Arial Narrow", Font.ITALIC, 12));
		comboBoxRunMode.setModel(new DefaultComboBoxModel(new String[] {"ECB", "CBC", "CTR", "OFB", "CFB"}));
		comboBoxRunMode.setBounds(292, 78, 61, 21);
		panel_1.add(comboBoxRunMode);
		
		JButton btnEncryptFile = new JButton("\u52A0\u5BC6\u6587\u4EF6");
		btnEncryptFile.setIcon(new ImageIcon("C:\\Users\\\u603B\u88C1\\Desktop\\\u56FE\u6807\\t019a8b50e884c80266.jpg"));
		btnEncryptFile.setBackground(new Color(0, 139, 139));
		btnEncryptFile.setFont(new Font("华文细黑", Font.ITALIC, 13));
		btnEncryptFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String algorithm="";
				if (rdbtnAES.isSelected()) {
					algorithm="AES";
				}
				if(rdbtnDES.isSelected()){
					algorithm="DES";
				} 
				EncryptFile encryptFile=new EncryptFile(algorithm,comboBoxBitLength.getSelectedItem().toString(),
						comboBoxFillPlan.getSelectedItem().toString(),
						comboBoxRunMode.getSelectedItem().toString());
				encryptFile.setSize(new Dimension(550, 400));
				encryptFile.setVisible(true);
				
			}
		});
		btnEncryptFile.setBounds(10, 160, 155, 169);
		panel_1.add(btnEncryptFile);
		
		JButton btnDecrytFile = new JButton("\u89E3\u5BC6\u6587\u4EF6");
		btnDecrytFile.setIcon(new ImageIcon("C:\\Users\\\u603B\u88C1\\Desktop\\\u56FE\u6807\\t012286ca474085b506.jpg"));
		btnDecrytFile.setBackground(new Color(0, 139, 139));
		btnDecrytFile.setFont(new Font("华文细黑", Font.ITALIC, 13));
		btnDecrytFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DecryptFile decryptFile=new DecryptFile();
				decryptFile.setSize(new Dimension(550, 400));
				decryptFile.setVisible(true);
				
			}
		});
		btnDecrytFile.setBounds(191, 160, 169, 169);
		panel_1.add(btnDecrytFile);
		
		JLabel label_5 = new JLabel("\u52A0\u5BC6\u6587\u4EF6");
		label_5.setBounds(22, 338, 54, 15);
		panel_1.add(label_5);
		
		JLabel label_6 = new JLabel("\u89E3\u5BC6\u6587\u4EF6");
		label_6.setBounds(278, 338, 54, 15);
		panel_1.add(label_6);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("\u6570\u5B57\u7B7E\u540D", null, panel_2, null);
		tabbedPane.setBackgroundAt(2, Color.WHITE);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5F85\u7B7E\u540D\u6587\u4EF6\uFF1A");
		lblNewLabel.setBounds(34, 165, 86, 15);
		panel_2.add(lblNewLabel);
		
		textFieldToSignFile = new JTextField();
		textFieldToSignFile.setEditable(false);
		textFieldToSignFile.setBounds(124, 162, 152, 21);
		panel_2.add(textFieldToSignFile);
		textFieldToSignFile.setColumns(10);
		
		JButton btnToSignFile = new JButton("\u6D4F\u89C8");
		btnToSignFile.setBackground(Color.ORANGE);
		btnToSignFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser("D:");
				if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					toSignFile=fileChooser.getSelectedFile();
					textFieldToSignFile.setText(toSignFile.getPath());
			}
			}
		});
		btnToSignFile.setBounds(291, 161, 93, 23);
		panel_2.add(btnToSignFile);
		
		JButton btnSignValueFile = new JButton("\u6D4F\u89C8");
		btnSignValueFile.setBackground(Color.ORANGE);
		btnSignValueFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser dlg = new JFileChooser("D:");
				FileNameExtensionFilter filter = new FileNameExtensionFilter(  
			            "文本文件(*.txt)", "txt");
				dlg.setFileFilter(filter);
				dlg.setDialogTitle("保存文件");
				int result = dlg.showSaveDialog(null); // 打开"打开文件"对话框
				if (result == JFileChooser.APPROVE_OPTION) {
					//如果此时在这里选择文件，之后在建立密文文件时，直接抹掉选择的文件的数据，一般选择自定义文件名字，系统会自动生成这个文件
				signValueFile = dlg.getSelectedFile();
				textFieldsignValueFile.setText(signValueFile.getPath());
				}
			}
		});
		btnSignValueFile.setBounds(291, 192, 93, 23);
		panel_2.add(btnSignValueFile);
		
		JLabel lblNewLabel_1 = new JLabel("\u7B7E\u540D\u503C\u6587\u4EF6\uFF1A");
		lblNewLabel_1.setBounds(34, 194, 88, 15);
		panel_2.add(lblNewLabel_1);
		
		textFieldsignValueFile = new JTextField();
		textFieldsignValueFile.setEditable(false);
		textFieldsignValueFile.setBounds(123, 193, 153, 21);
		panel_2.add(textFieldsignValueFile);
		textFieldsignValueFile.setColumns(10);
		
		JButton btnSign = new JButton("\u7B7E\u540D");
		btnSign.setIcon(new ImageIcon("C:\\Users\\\u603B\u88C1\\Desktop\\\u56FE\u6807\\t01a408d7ba9c5d1ec2.jpg"));
		btnSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					
						KeyStore keyStore = KeyStore.getInstance("JCEKS");
						char[] password = "123456".toCharArray();
						FileInputStream fis = new FileInputStream("mynewkeys.keystore");
						keyStore.load(fis, password);
						KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(password);
						KeyStore.PrivateKeyEntry privateKeyEntry = (PrivateKeyEntry) keyStore.getEntry("mykey", protParam);
						
						
						PrivateKey privateKey = null;
						if (sign==1) {
							RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKeyEntry.getPrivateKey();
							privateKey=rsaPrivateKey;
						}
						else if (sign==2) {
							DSAPrivateKey dsaPrivateKey=(DSAPrivateKey) privateKeyEntry.getPrivateKey();
							privateKey=dsaPrivateKey;
						}
						else {
							ECPrivateKey ecPrivateKey=(ECPrivateKey)privateKeyEntry.getPrivateKey();
							privateKey=ecPrivateKey;
						}
						SignAndVerify.signFile(toSignFile, privateKey, signValueFile, signAlgorithmstring, signAlgorithmstring1);
						JOptionPane.showMessageDialog(null, "签名成功");
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "签名失败");
				}
				
			}
		});
		btnSign.setBounds(10, 225, 139, 112);
		panel_2.add(btnSign);
		
		JButton btnNewButton_1 = new JButton("\u7B7E\u540D\u9A8C\u8BC1");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\\u603B\u88C1\\Desktop\\\u56FE\u6807\\t01fdf50c8c91e63ab3.jpg"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerifySign verifySign=new VerifySign();
				verifySign.setSize(new Dimension(550, 400));
				verifySign.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(221, 225, 139, 112);
		panel_2.add(btnNewButton_1);
		
		btnNewButton = new JButton("\u751F\u6210\u65B0\u7684\u5BC6\u94A5\u5E93\u548C\u6570\u5B57\u8BC1\u4E66");
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(signAlgorithmstring1);
					KeyPair keyPair = keyPairGenerator.generateKeyPair();
					// 生成自签名数字证书
					String subjectDN = "CN = Yang OU = cauc O = cauc L = tj S = tj C = cn";
					
					Certificate certificate= GenerateCertificate.selfSign(keyPair, subjectDN, signAlgorithmstring);
					
					// 将密钥对（私钥和自签名数字证书）存入密钥库文件
					KeyStore keyStore = KeyStore.getInstance("JCEKS");
					char[] passWord = "123456".toCharArray();
					//keyStore.setKeyEntry("mykeykind", keyPair.getPrivate(), passWord, certificates);
					keyStore.load(null, passWord);
					//keyStore.setKeyEntry("mykey", keyPair.getPrivate().getEncoded(), new Certificate[] { certificate });
					keyStore.setKeyEntry("mykey", keyPair.getPrivate(), passWord, new Certificate[] { certificate });
					FileOutputStream fos = new FileOutputStream("mynewkeys.keystore");
					keyStore.store(fos, passWord);
					JOptionPane.showMessageDialog(null, "新的密钥库及数字证书生成成功");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(34, 81, 252, 23);
		panel_2.add(btnNewButton);
		
		lblNewLabel_2 = new JLabel("\u5982\u4E0D\u70B9\u51FB\u6B64\u6309\u94AE\uFF0C\u5C06\u4F7F\u7528\u5DF2\u5B58\u5728\u7684\u5BC6\u94A5\u5E93\u6587\u4EF6");
		lblNewLabel_2.setBounds(34, 114, 279, 15);
		panel_2.add(lblNewLabel_2);
		
		JComboBox comboBoxsignAlgorithm = new JComboBox();
		comboBoxsignAlgorithm.setBackground(Color.PINK);
		comboBoxsignAlgorithm.setSelectedItem("SHA224withRSA");
		signAlgorithmstring="SHA224withRSA";
		signAlgorithmstring1="RSA";
		sign=1;
		comboBoxsignAlgorithm.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
				signAlgorithmstring=comboBoxsignAlgorithm.getSelectedItem().toString();
				int a=comboBoxsignAlgorithm.getSelectedIndex();
				if (a>=0&&a<=5) {
					sign=1;
					signAlgorithmstring1="RSA";
				}
				else if (a>=6&&a<=9) {
					sign=2;
					signAlgorithmstring1="DSA";
				}
				else {
					sign=3;
					signAlgorithmstring1="ECDSA";	
				}
				
			}
			}
			
		});
		comboBoxsignAlgorithm.setModel(new DefaultComboBoxModel(new String[] {"SHA224withRSA", "SHA256withRSA", "SHA384withRSA", "SHA512withRSA", "RIPEMD128withRSA", "RIPEMD160withRSA", "SHA224withDSA", "SHA256withDSA", "SHA384withDSA", "SHA512withDSA", "SHA1withECDSA", "SHA224withECDSA", "SHA256withECDSA", "SHA384withECDSA", "SHA512withECDSA"}));
		comboBoxsignAlgorithm.setBounds(34, 24, 192, 21);
		panel_2.add(comboBoxsignAlgorithm);
		
		JLabel lblNewLabel_3 = new JLabel("\u5982\u4E0D\u9009\u62E9\uFF0C\u5C06\u4F7F\u7528\u5DF2\u4FDD\u5B58\u7684\u7B7E\u540D\u7B97\u6CD5");
		lblNewLabel_3.setBounds(34, 56, 306, 15);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\uFF08\u9009\u62E9\u7B7E\u540D\u7B97\u6CD5\uFF09");
		lblNewLabel_4.setBounds(236, 27, 124, 15);
		panel_2.add(lblNewLabel_4);
		
		JLabel label_7 = new JLabel("\u7B7E\u540D\u751F\u6210");
		label_7.setBounds(20, 347, 54, 15);
		panel_2.add(label_7);
		
		JLabel label_8 = new JLabel("\u7B7E\u540D\u9A8C\u8BC1");
		label_8.setBounds(231, 347, 54, 15);
		panel_2.add(label_8);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("MAC", null, panel_3, null);
		tabbedPane.setBackgroundAt(3, Color.WHITE);
		panel_3.setLayout(null);

		JComboBox comboBoxSelectMAC = new JComboBox();
		comboBoxSelectMAC.setBackground(Color.PINK);
		comboBoxSelectMAC.setSelectedItem("HmacMD2");
		macString="HmacMD2";
		comboBoxSelectMAC.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					macString=comboBoxSelectMAC.getSelectedItem().toString();
				}
			}
		});
		comboBoxSelectMAC.setModel(new DefaultComboBoxModel(new String[] {"HmacMD2", "HmacMD4", "HmacMD5", "HmacSHA1", "HmacSHA224", "HmacSHA256", "HmacSHA384", "HmacSHA512"}));
		comboBoxSelectMAC.setBounds(150, 80, 112, 21);
		panel_3.add(comboBoxSelectMAC);
		
		JRadioButton rdbtnMACFile = new JRadioButton("File");
		rdbtnMACFile.setBackground(Color.LIGHT_GRAY);
		rdbtnMACFile.setSelected(true);
		
		rdbtnMACFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mac=0;
				btnSelectMACFile.setVisible(true);
			    textFieldMACSourceData.setEditable(false);
				rdbtnMACString.setSelected(false);
			}
		});
		rdbtnMACFile.setBounds(41, 24, 121, 23);
		panel_3.add(rdbtnMACFile);
		
		rdbtnMACString = new JRadioButton("String");
		rdbtnMACString.setBackground(Color.LIGHT_GRAY);
		rdbtnMACString.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mac=1;
				btnSelectMACFile.setVisible(false);
			    textFieldMACSourceData.setEditable(true);
				rdbtnMACFile.setSelected(false);
			}
		});
		rdbtnMACString.setBounds(175, 24, 121, 23);
		panel_3.add(rdbtnMACString);
		
		passwordFieldMAC1 = new JPasswordField();
		passwordFieldMAC1.setBounds(96, 159, 178, 21);
		panel_3.add(passwordFieldMAC1);
		
		JLabel lblNewLabel_5 = new JLabel("\u53E3\u4EE4\uFF1A");
		lblNewLabel_5.setBounds(51, 164, 54, 15);
		panel_3.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("\uFF08MAC\u7B97\u6CD5\uFF09");
		lblNewLabel_6.setBounds(46, 83, 93, 15);
		panel_3.add(lblNewLabel_6);
		
		JLabel label_3 = new JLabel("\u6570\u636E");
		label_3.setBounds(51, 127, 40, 15);
		panel_3.add(label_3);
		
		btnSelectMACFile = new JButton("\u6D4F\u89C8");
		btnSelectMACFile.setBackground(Color.ORANGE);
		btnSelectMACFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser("D:");
				if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					toMACFile=fileChooser.getSelectedFile();
					textFieldMACSourceData.setText(toMACFile.getPath());
			}
			}
		});
		btnSelectMACFile.setBounds(272, 120, 93, 23);
		panel_3.add(btnSelectMACFile);
		
		textFieldMACSourceData = new JTextField();
		textFieldMACSourceData.setEditable(false);
		textFieldMACSourceData.setBounds(97, 121, 165, 21);
		panel_3.add(textFieldMACSourceData);
		textFieldMACSourceData.setColumns(10);
		
		JButton btnCalcMAC = new JButton("\u8BA1\u7B97MAC");
		btnCalcMAC.setBackground(Color.LIGHT_GRAY);
		btnCalcMAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					macpassword1=passwordFieldMAC1.getPassword();
					macpassword2=passwordFieldMAC2.getPassword();
					
					
						
					
					if (new String(macpassword1).equals("")||new String(macpassword2).equals("")) {
						JOptionPane.showMessageDialog(null, "请检查两个口令是否都输入");
					}
					else{
						
				
					if (Arrays.equals(macpassword2, macpassword1)) {
						Mac mac1;
						try {
							
							MessageDigest md = MessageDigest.getInstance("SHA3-512");
							byte[] keyValue = new String(macpassword1).getBytes();
							SecretKeySpec key = new SecretKeySpec(keyValue,  macString);
							
							mac1 = Mac.getInstance(key.getAlgorithm());
							mac1.init(key);
							
								
							
							if (mac==0) {
								try {
									if (toMACFile==null) {
										JOptionPane.showMessageDialog(null, "请选择待签名文件");
									}
									else{
									FileInputStream fis = new FileInputStream(toMACFile);
									int n=0;

									byte[] buffer = new byte[64];
									while((n=fis.read(buffer)) != -1)
									{
										mac1.update(buffer, 0, n);
									}
									byte []macCode=new byte[mac1.getMacLength()];
									macCode=mac1.doFinal();
									
									textFieldMAC.setText(new HexBinaryAdapter().marshal(macCode));
									}
									
								} catch (IllegalStateException | IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
							
							else {
								String message=textFieldMACSourceData.getText().toString();
								if (message.equals("")) {
									JOptionPane.showMessageDialog(null, "请输入待签名数据");
									
								}
								else{
								byte []maccode=mac1.doFinal(message.getBytes());
								textFieldMAC.setText(new HexBinaryAdapter().marshal(maccode));
								}
								
							}
						} catch (InvalidKeyException | NoSuchAlgorithmException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					} else {
						JOptionPane.showMessageDialog(null, "两次口令输入不一致");
					}
					}
					
					

				
			}
		});
		btnCalcMAC.setBounds(46, 282, 93, 48);
		panel_3.add(btnCalcMAC);
		
		JLabel label_4 = new JLabel("\u518D\u6B21\u8F93\u5165\u53E3\u4EE4");
		label_4.setBounds(10, 206, 80, 15);
		panel_3.add(label_4);
		
		passwordFieldMAC2 = new JPasswordField();
		passwordFieldMAC2.setBounds(96, 203, 178, 21);
		panel_3.add(passwordFieldMAC2);
		
		lblMac = new JLabel("MAC");
		lblMac.setBounds(51, 243, 40, 15);
		panel_3.add(lblMac);
		
		textFieldMAC = new JTextField();
		textFieldMAC.setBackground(SystemColor.inactiveCaptionBorder);
		textFieldMAC.setEditable(false);
		textFieldMAC.setBounds(96, 240, 178, 21);
		panel_3.add(textFieldMAC);
		textFieldMAC.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("\u6027\u80FD\u5206\u6790\u56FE\u8868", null, panel_4, null);
		tabbedPane.setBackgroundAt(4, Color.WHITE);
		panel_4.setLayout(null);
		
		JLabel label_9 = new JLabel("\u6587\u4EF61");
		label_9.setBounds(37, 50, 54, 15);
		panel_4.add(label_9);
		
		JButton btnSelectedFile1 = new JButton("\u6D4F\u89C8");
		btnSelectedFile1.setBackground(Color.PINK);
		btnSelectedFile1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser("D:");
				if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFiledFile1.setText(fileChooser.getSelectedFile().getPath());
					file1=fileChooser.getSelectedFile();
					
				}
			}
		});
		btnSelectedFile1.setBounds(234, 46, 93, 23);
		panel_4.add(btnSelectedFile1);
		
		textFiledFile1 = new JTextField();
		textFiledFile1.setEditable(false);
		textFiledFile1.setFont(new Font("方正姚体", Font.PLAIN, 12));
		textFiledFile1.setText("\u8BF7\u9009\u62E910M\u5DE6\u53F3\u7684\u6587\u4EF6");
		textFiledFile1.setBounds(77, 50, 147, 21);
		panel_4.add(textFiledFile1);
		textFiledFile1.setColumns(10);
		
		JLabel label_10 = new JLabel("\u6587\u4EF62");
		label_10.setBounds(37, 82, 37, 15);
		panel_4.add(label_10);
		
		textFiledFile2 = new JTextField();
		textFiledFile2.setEditable(false);
		textFiledFile2.setFont(new Font("方正姚体", Font.PLAIN, 12));
		textFiledFile2.setText("\u8BF7\u9009\u62E9100M\u5DE6\u53F3\u7684\u6587\u4EF6");
		textFiledFile2.setBounds(77, 79, 147, 21);
		panel_4.add(textFiledFile2);
		textFiledFile2.setColumns(10);
		
		textFiledFile3 = new JTextField();
		textFiledFile3.setEditable(false);
		textFiledFile3.setFont(new Font("方正姚体", Font.PLAIN, 12));
		textFiledFile3.setText("\u8BF7\u9009\u62E9500M\u5DE6\u53F3\u7684\u6587\u4EF6");
		textFiledFile3.setBounds(77, 113, 147, 21);
		panel_4.add(textFiledFile3);
		textFiledFile3.setColumns(10);
		
		JLabel label_11 = new JLabel("\u6587\u4EF63");
		label_11.setBounds(37, 116, 37, 15);
		panel_4.add(label_11);
		
		JLabel label_12 = new JLabel("\u6587\u4EF64");
		label_12.setBounds(37, 151, 37, 15);
		panel_4.add(label_12);
		
		textFiledFile4 = new JTextField();
		textFiledFile4.setEditable(false);
		textFiledFile4.setFont(new Font("方正姚体", Font.PLAIN, 12));
		textFiledFile4.setText("\u8BF7\u9009\u62E91G\u5DE6\u53F3\u7684\u6587\u4EF6");
		textFiledFile4.setBounds(77, 148, 147, 21);
		panel_4.add(textFiledFile4);
		textFiledFile4.setColumns(10);
		
		JButton btnSelectedFile2 = new JButton("\u6D4F\u89C8");
		btnSelectedFile2.setBackground(Color.PINK);
		btnSelectedFile2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser("D:");
				if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFiledFile2.setText(fileChooser.getSelectedFile().getPath());
					file2=fileChooser.getSelectedFile();
				}
			}
		});
		btnSelectedFile2.setBounds(234, 79, 93, 23);
		panel_4.add(btnSelectedFile2);
		
		JButton btnSelectedFile3 = new JButton("\u6D4F\u89C8");
		btnSelectedFile3.setBackground(Color.PINK);
		btnSelectedFile3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser("D:");
				if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFiledFile3.setText(fileChooser.getSelectedFile().getPath());
					file3=fileChooser.getSelectedFile();
				}
			}
		});
		btnSelectedFile3.setBounds(234, 112, 93, 23);
		panel_4.add(btnSelectedFile3);
		
		JButton btnSelectedFile4 = new JButton("\u6D4F\u89C8");
		btnSelectedFile4.setBackground(Color.PINK);
		btnSelectedFile4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser("D:");
				if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFiledFile4.setText(fileChooser.getSelectedFile().getPath());
					file4=fileChooser.getSelectedFile();
				}
			}
		});
		btnSelectedFile4.setBounds(234, 147, 93, 23);
		panel_4.add(btnSelectedFile4);
		
		JButton btnSeeHASHChart = new JButton("\u67E5\u770BHASH\u6027\u80FD\u56FE\u8868");
		btnSeeHASHChart.setBackground(Color.GREEN);
		btnSeeHASHChart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String []HashAlgorithmNameTest={"SHA1","SHA-256","SHA-384","SHA-512"};
				long [][]HashTime=new long[4][4];    //第一个代表算法，第二个代表文件
				File []fileCollection={file1,file2,file3,file4};
				if (file1==null||file2==null||file3==null||file3==null) {
					JOptionPane.showMessageDialog(null, "请检查四个文件是否均已经输入");
				}
				else{
					long [] TwentyTimes=new long[20];
					for (int i = 0; i < 4; i++) {     //算法名大循环
						System.out.println(HashAlgorithmNameTest[i]+"对四个文件求哈希值所用时间如下：");
						for (int j = 0; j < 4; j++) {   //文件循环
							try {
								FileInputStream fis = new FileInputStream(fileCollection[j]);
								MessageDigest md = MessageDigest.getInstance(HashAlgorithmNameTest[i]);
								DigestInputStream dis = new DigestInputStream(fis, md);
								for (int k = 0; k < TwentyTimes.length; k++) {
									long startTime=System.currentTimeMillis();   //获取开始时间
									byte[] buffer = new byte[1024];
									while((dis.read(buffer)) != -1);
									
									md.digest();
									long endTime=System.currentTimeMillis(); //获取结束时间
									TwentyTimes[k]=endTime-startTime;
								}
								long averageTime=0;
								for (int k = 0; k < TwentyTimes.length; k++) {
									averageTime=TwentyTimes[k]+averageTime;
								}
								averageTime=averageTime/20;
								HashTime[i][j]=averageTime;
								int a=j+1;
								System.out.print("File"+a+":");
								System.out.println(HashTime[i][j]+"ms");
								dis.close();
							} catch (NoSuchAlgorithmException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					StandardChartTheme mChartTheme = new StandardChartTheme("CN");
					mChartTheme.setLargeFont(new Font("黑体", Font.BOLD, 20));
					mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));
					mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
					ChartFactory.setChartTheme(mChartTheme);		
					XYSeriesCollection mCollection = HashChart.GetCollection1(HashTime,fileCollection);
					JFreeChart mChart = ChartFactory.createXYLineChart(
				    "HASH算法性能研究",
					 "文件大小（M）",
					 "计算摘要所用时间（ms）",				
					  mCollection,
					  PlotOrientation.VERTICAL,
					  true, 
					  true, 
					  false);
					
					 XYPlot plot=mChart.getXYPlot();
					 plot.setBackgroundPaint(Color.WHITE);
		                plot.setRangeGridlinePaint(Color.BLUE);//纵坐标格线颜色
		                plot.setDomainGridlinePaint(Color.BLACK);//横坐标格线颜色
		                plot.setDomainGridlinesVisible(true);//显示横坐标格线
		                plot.setRangeGridlinesVisible(true);//显示纵坐标格线
					  
					 XYLineAndShapeRenderer renderer=(XYLineAndShapeRenderer) plot.getRenderer();  
		                renderer.setShapesFilled(Boolean.TRUE);//在数据点显示实心的小图标
		                renderer.setShapesVisible(true);//设置显示小图标
		                XYItemRenderer xyitem = plot.getRenderer(); 
		                xyitem.setBaseItemLabelsVisible(true);
		                xyitem.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER)); 
		                xyitem.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
		                xyitem.setBaseItemLabelFont(new Font("Dialog", 1, 12)); 
		                plot.setRenderer(xyitem);
					 
					  ChartFrame mChartFrame = new ChartFrame("HASH算法性能研究", mChart);
					  mChartFrame.pack();
					  mChartFrame.setVisible(true);	 
					
				}
				 
			}
			
		});
		btnSeeHASHChart.setBounds(10, 240, 167, 79);
		panel_4.add(btnSeeHASHChart);
		
		JButton btnSeeAESChart = new JButton("\u67E5\u770BAES\u52A0\u5BC6\u6027\u80FD\u56FE\u8868");
		btnSeeAESChart.setBackground(Color.GREEN);
		btnSeeAESChart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int []AESAlgorithmLength={128,192,256};
				long [][]AESTime=new long[3][4];    //第一个代表算法，第二个代表文件
				File []fileCollection={file1,file2,file3,file4};
				if (file1==null||file2==null||file3==null||file3==null) {
					JOptionPane.showMessageDialog(null, "请检查四个文件是否均已经输入");
				}
				else {
					for (int i = 0; i < 3; i++) {     //三种长度循环
						System.out.println(AESAlgorithmLength[i]+"的AES对四个文件加密所用时间如下：");
						for (int j = 0; j < 4; j++) {   //文件循环
							try {
								long startTime=System.currentTimeMillis();   //获取开始时间
								FileInputStream fis = new FileInputStream(fileCollection[j]);
								FileOutputStream fos = new FileOutputStream("aaa.enc");
								KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");
		                        keyGenerator.init(AESAlgorithmLength[i]);
		                        java.security.Key key=keyGenerator.generateKey();
		                        Cipher cipher=Cipher.getInstance("AES/OFB/PKCS5Padding");
		                        cipher.init(Cipher.ENCRYPT_MODE, key);
		                        CipherInputStream cis = new CipherInputStream(fis, cipher);
		    					byte[] buffer = new byte[2048];
		    					int n = 0;
		    					while((n = cis.read(buffer)) != -1) {
		    						fos.write(buffer, 0, n);
		    					}
		    					fos.close();
		    					cis.close();
								long endTime=System.currentTimeMillis();   //获取开始时间			
								AESTime[i][j]=endTime-startTime;
								int a=j+1;
								System.out.print("File"+a+":");
								System.out.println(AESTime[i][j]+"ms");
							} catch (NoSuchAlgorithmException | IOException | NoSuchPaddingException | InvalidKeyException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					StandardChartTheme mChartTheme = new StandardChartTheme("CN");
					mChartTheme.setLargeFont(new Font("黑体", Font.BOLD, 20));
					mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));
					mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
					ChartFactory.setChartTheme(mChartTheme);		
					XYSeriesCollection mCollection = AESChart.GetCollection1(AESTime,fileCollection);
					JFreeChart mChart = ChartFactory.createXYLineChart(
				    "AES不同长度性能研究",
					 "文件大小（M）",
					 "计算摘要所用时间（ms）",				
					  mCollection,
					  PlotOrientation.VERTICAL,
					  true, 
					  true, 
					  false);
					
					 XYPlot plot=mChart.getXYPlot();
					 plot.setBackgroundPaint(Color.WHITE);
		                plot.setRangeGridlinePaint(Color.BLUE);//纵坐标格线颜色
		                plot.setDomainGridlinePaint(Color.BLACK);//横坐标格线颜色
		                plot.setDomainGridlinesVisible(true);//显示横坐标格线
		                plot.setRangeGridlinesVisible(true);//显示纵坐标格线
					  
					 XYLineAndShapeRenderer renderer=(XYLineAndShapeRenderer) plot.getRenderer();  
		                renderer.setShapesFilled(Boolean.TRUE);//在数据点显示实心的小图标
		                renderer.setShapesVisible(true);//设置显示小图标
		                XYItemRenderer xyitem = plot.getRenderer(); 
		                xyitem.setBaseItemLabelsVisible(true);
		                xyitem.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER)); 
		                xyitem.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
		                xyitem.setBaseItemLabelFont(new Font("Dialog", 1, 12)); 
		                plot.setRenderer(xyitem);
					 
					  ChartFrame mChartFrame = new ChartFrame("AES不同长度性能研究", mChart);
					  mChartFrame.pack();
					  mChartFrame.setVisible(true);
					
				}
				
			}
		});
		btnSeeAESChart.setBounds(187, 240, 174, 79);
		panel_4.add(btnSeeAESChart);
		
		
				
		
	}

	public class Hashcalc        //哈希计算必要的数据类
	{
		public JTextField []jTextFields={textFieldSHA1,textFieldSHA224,textFieldSHA256,textFieldSHA384,textFieldSHA512,
				textFieldSHA3224,textFieldSHA3256,textFieldSHA3384,textFieldSHA3512};
		public JCheckBox[] jCheckBoxs={chckbxSHA1,chckbxSHA224,chckbxSHA256,chckbxSHA384,chckbxSHA512,chckbxSHA3224,
				chckbxSHA3256,chckbxSHA3384,chckbxSHA3512};
		public boolean[] isSelected={false,false,false,false,false,false,false,false,false};
		public String[] cStrings={"SHA1","SHA-224","SHA-256","SHA-384","SHA-512","SHA3-224","SHA3-256","SHA-384","SHA3-512"};
		
		public void setTextFileNull() {       //设置所有的哈希输出窗口为空
			for (int i = 0; i < 9; i++) {
				jTextFields[i].setText("");	
			}
			
		}
		public void setCheckBox()      //判断选择框，如果为有对号，则标记为true
		{
			for (int i = 0; i < 9; i++) {
				if (jCheckBoxs[i].isSelected()) {
					isSelected[i]=true;
				}
			}
		}
		public void setBoolFalse() {  //设置状态量为均为false
			for (int i = 0; i < 9; i++) {
					isSelected[i]=false;
			}
		}
		public void setChxSelected() {
			for (int i = 0; i < 9; i++) {
				jCheckBoxs[i].setSelected(false);
		}
			
		}
	}
}

