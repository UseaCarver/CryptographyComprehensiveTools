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
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class DecryptFile extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDecryptFile;
	private JPasswordField passwordDecrypt;
	private File decryptFile;
	private File plainTextFile;
	private char []dePassword={};

	

	
	public DecryptFile() {
		setTitle("\u89E3\u5BC6\u6587\u4EF6");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u5F85\u89E3\u5BC6\u7684\u6587\u4EF6");
		label.setBounds(25, 51, 101, 15);
		contentPane.add(label);
		
		textFieldDecryptFile = new JTextField();
		textFieldDecryptFile.setEditable(false);
		textFieldDecryptFile.setBounds(121, 48, 180, 21);
		contentPane.add(textFieldDecryptFile);
		textFieldDecryptFile.setColumns(10);
		
		JButton btnSelectDecryptFile = new JButton("\u6D4F\u89C8");
		btnSelectDecryptFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser dlg = new JFileChooser("D:");
				FileNameExtensionFilter filter = new FileNameExtensionFilter(  
			            "加密文件(*.enc)", "enc");
				dlg.setFileFilter(filter);
				int result = dlg.showOpenDialog(null); // 打开"打开文件"对话框
				if (result == JFileChooser.APPROVE_OPTION) {
					//如果此时在这里选择文件，之后在建立密文文件时，直接抹掉选择的文件的数据，一般选择自定义文件名字，系统会自动生成这个文件
				decryptFile = dlg.getSelectedFile();
				textFieldDecryptFile.setText(decryptFile.getPath());
			}
			}
		});
		btnSelectDecryptFile.setBounds(309, 47, 93, 23);
		contentPane.add(btnSelectDecryptFile);
		
		JLabel label_1 = new JLabel("\u89E3\u5BC6\u53E3\u4EE4");
		label_1.setBounds(27, 105, 54, 15);
		contentPane.add(label_1);
		
		passwordDecrypt = new JPasswordField();
		passwordDecrypt.setBounds(121, 102, 186, 21);
		contentPane.add(passwordDecrypt);
		
		JButton btnDecrypt = new JButton("\u6267\u884C\u89E3\u5BC6");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dePassword=passwordDecrypt.getPassword();
			    if (decryptFile==null) {
			    	JOptionPane.showMessageDialog(null, "请选择密文文件");
					JFileChooser dlg = new JFileChooser("D:");
					FileNameExtensionFilter filter = new FileNameExtensionFilter(  
				            "加密文件(*.enc)", "enc");
					dlg.setFileFilter(filter);
					dlg.setDialogTitle("打开文件");
					int result = dlg.showOpenDialog(null); // 打开"打开文件"对话框
					if (result == JFileChooser.APPROVE_OPTION) {
						//如果此时在这里选择文件，之后在建立密文文件时，直接抹掉选择的文件的数据，一般选择自定义文件名字，系统会自动生成这个文件
					decryptFile = dlg.getSelectedFile();
					textFieldDecryptFile.setText(decryptFile.getPath());
				}
			    }
			    else if (dePassword.length==0) {
			    	JOptionPane.showMessageDialog(null, "请输入解密口令");
				}
			    else if (plainTextFile==null) {
			    	JOptionPane.showMessageDialog(null, "请选择明文文件");
			    	JFileChooser dlg = new JFileChooser("D:");
					int result = dlg.showSaveDialog(null); // 打开"打开文件"对话框
					dlg.setDialogTitle("保存文件");
					if (result == JFileChooser.APPROVE_OPTION) {
						//如果此时在这里选择文件，之后在建立密文文件时，直接抹掉选择的文件的数据，一般选择自定义文件名字，系统会自动生成这个文件
					plainTextFile = dlg.getSelectedFile();
					}
					}
			    else {
					
					MyFileEncryptor.decryptFile(decryptFile, plainTextFile, new String(dePassword));
					JOptionPane.showMessageDialog(null, "解密成功");
				}
			}
		});
		btnDecrypt.setBounds(25, 164, 93, 60);
		contentPane.add(btnDecrypt);
		
		JButton btnSavePlainTextFile = new JButton("\u660E\u6587\u6587\u4EF6\u53E6\u5B58\u4E3A");
		btnSavePlainTextFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser dlg = new JFileChooser("D:");
				int result = dlg.showSaveDialog(null); // 打开"打开文件"对话框
				dlg.setDialogTitle("保存文件");
				if (result == JFileChooser.APPROVE_OPTION) {
					//如果此时在这里选择文件，之后在建立密文文件时，直接抹掉选择的文件的数据，一般选择自定义文件名字，系统会自动生成这个文件
				plainTextFile = dlg.getSelectedFile();
			}
			}
		});
		btnSavePlainTextFile.setBounds(156, 183, 126, 23);
		contentPane.add(btnSavePlainTextFile);
	}
}
