import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.awt.event.ActionEvent;

public class VerifySign extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldToverifyFile;
	private JTextField textFieldSignValueFile;
	private File SelectToVerifyFile;
	private File valueFile;

	
	
	public VerifySign() {
		setTitle("\u7B7E\u540D\u9A8C\u8BC1");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u5F85\u9A8C\u8BC1\u6587\u4EF6");
		label.setBounds(40, 46, 72, 15);
		contentPane.add(label);
		
		textFieldToverifyFile = new JTextField();
		textFieldToverifyFile.setEditable(false);
		textFieldToverifyFile.setBounds(118, 43, 169, 21);
		contentPane.add(textFieldToverifyFile);
		textFieldToverifyFile.setColumns(10);
		
		JButton btnSelectToVerifyFile = new JButton("\u6D4F\u89C8");
		btnSelectToVerifyFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser dlg = new JFileChooser("D:");
				
				int result = dlg.showOpenDialog(null); // 打开"打开文件"对话框
				if (result == JFileChooser.APPROVE_OPTION) {
					//如果此时在这里选择文件，之后在建立密文文件时，直接抹掉选择的文件的数据，一般选择自定义文件名字，系统会自动生成这个文件
					SelectToVerifyFile= dlg.getSelectedFile();
				textFieldToverifyFile.setText(SelectToVerifyFile.getPath());
				}
			}
		});
		btnSelectToVerifyFile.setBounds(307, 42, 93, 23);
		contentPane.add(btnSelectToVerifyFile);
		
		JLabel label_1 = new JLabel("\u7B7E\u540D\u503C\u6587\u4EF6");
		label_1.setBounds(40, 106, 66, 15);
		contentPane.add(label_1);
		
		textFieldSignValueFile = new JTextField();
		textFieldSignValueFile.setEditable(false);
		textFieldSignValueFile.setBounds(118, 103, 169, 21);
		contentPane.add(textFieldSignValueFile);
		textFieldSignValueFile.setColumns(10);
		
		JButton btnSelectValueFile = new JButton("\u6D4F\u89C8");
		btnSelectValueFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser dlg = new JFileChooser("D:");
				FileNameExtensionFilter filter = new FileNameExtensionFilter(  
			            "文本文件(*.txt)", "txt");
				dlg.setFileFilter(filter);
				dlg.setDialogTitle("保存文件");
				int result = dlg.showSaveDialog(null); // 打开"打开文件"对话框
				if (result == JFileChooser.APPROVE_OPTION) {
					//如果此时在这里选择文件，之后在建立密文文件时，直接抹掉选择的文件的数据，一般选择自定义文件名字，系统会自动生成这个文件
				valueFile = dlg.getSelectedFile();
				textFieldSignValueFile.setText(valueFile.getPath());
				}
			}
		});
		btnSelectValueFile.setBounds(307, 102, 93, 23);
		contentPane.add(btnSelectValueFile);
		
		JButton btnBeginVerify = new JButton("\u9A8C\u8BC1");
		btnBeginVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileInputStream fisFileToVerify = new FileInputStream(valueFile);
					byte []alName=new byte[2];
					fisFileToVerify.read(alName);
					String aString=new String(alName);
					SignRunData.RunModeData0(aString);
					KeyStore keyStore = KeyStore.getInstance("JCEKS");
					char[] password = "123456".toCharArray();
					FileInputStream fis = new FileInputStream("mynewkeys.keystore");
					keyStore.load(fis, password);
					
					X509Certificate certificate = (X509Certificate) keyStore.getCertificate("mykey");
					PublicKey publicKey=null;
					
					if (SignRunData.algorithmName1.equals("RSA")) {
						RSAPublicKey rsaPublicKey = (RSAPublicKey)certificate.getPublicKey();
						publicKey=rsaPublicKey;
					}
					else if (SignRunData.algorithmName1.equals("DSA")) {
						DSAPublicKey dsaPublicKey = (DSAPublicKey) certificate.getPublicKey();
						publicKey=dsaPublicKey;
					}
					else {
						ECPublicKey ecPublicKey=(ECPublicKey)certificate.getPublicKey();
						publicKey=ecPublicKey;
					}
					boolean a;
					try {
						a = SignAndVerify.verifyFile(SelectToVerifyFile, publicKey, valueFile);
						if (a) {
							JOptionPane.showMessageDialog(null, "签名验证成功");
							
						} else {
							JOptionPane.showMessageDialog(null, "签名验证失败");
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "签名验证失败");
					}
				} catch (IOException | NoSuchAlgorithmException | CertificateException | KeyStoreException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnBeginVerify.setBounds(156, 167, 109, 57);
		contentPane.add(btnBeginVerify);
	}

}
