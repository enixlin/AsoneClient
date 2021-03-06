package cn.enixlin.jmrc.asone.ui;

import java.awt.EventQueue;
import java.awt.Label;

import javafx.scene.control.ComboBox;

import javax.imageio.ImageIO;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JEditorPane;

import java.awt.Canvas;

import javax.swing.JPanel;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;

import sun.misc.BASE64Encoder;
import cn.enixlin.jmrc.asone.util.CryptUtils;
import cn.enixlin.jmrc.asone.util.Tools;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.net.URI;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JProgressBar;

import java.awt.TextArea;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class LoginWindow {

	private JFrame frame;
	private String cookie="";
	private JTextField structionCode;
	private JLabel label_varifyCode;
	private JLabel lblNewLabel;
	private JTextField UserName;
	private JTextField password;
	private JTextField varifyCode;
	private JTextField textField_4;
	private ArrayList<BasicNameValuePair> UserLoginInfo;
	private CloseableHttpClient client;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
		// getVilitdyImage();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 1202, 602);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// getVilitdyImage();
				try {
					// System.out.println(new
					// CryptUtils().GetMD5Code(textField_2.getText()));
					// System.out.println(EncoderPwdByMd5(textField_2.getText()));
					setUserLoginInfo();
					// System.out.println(new
					// Tools().jsMD5(password.getText()));
					// System.out.println(textField_2.getText().toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(315, 111, 178, 60);
		frame.getContentPane().add(btnNewButton);

		lblNewLabel = new JLabel("机构代码");
		lblNewLabel.setBounds(15, 32, 81, 21);
		frame.getContentPane().add(lblNewLabel);

		structionCode = new JTextField();
		structionCode.setText("440700036600");
		structionCode.setBounds(111, 29, 189, 27);
		frame.getContentPane().add(structionCode);
		structionCode.setColumns(10);

		JLabel label = new JLabel("用户");
		label.setBounds(15, 111, 81, 21);
		frame.getContentPane().add(label);

		UserName = new JTextField();
		UserName.setText("ba");
		UserName.setColumns(10);
		UserName.setBounds(111, 108, 189, 27);
		frame.getContentPane().add(UserName);

		JLabel label_1 = new JLabel("密码");
		label_1.setBounds(15, 150, 81, 21);
		frame.getContentPane().add(label_1);

		password = new JTextField();
		password.setText("123456Gj");
		password.setColumns(10);
		password.setBounds(111, 147, 189, 27);
		frame.getContentPane().add(password);

		JLabel label_2 = new JLabel("验证码");
		label_2.setBounds(15, 69, 81, 21);
		frame.getContentPane().add(label_2);

		varifyCode = new JTextField();
		varifyCode.setColumns(10);
		varifyCode.setBounds(111, 66, 189, 27);
		frame.getContentPane().add(varifyCode);

		label_varifyCode = new JLabel("点击刷新");
		label_varifyCode.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_varifyCode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getVilitdyImage();
			}
		});
		label_varifyCode.setBounds(367, 68, 126, 21);
		frame.getContentPane().add(label_varifyCode);

		JLabel label_3 = new JLabel("用户清单");
		label_3.setBounds(541, 35, 81, 21);
		frame.getContentPane().add(label_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(628, 32, 394, 27);
		frame.getContentPane().add(textField_4);

		JButton btnNewButton_1 = new JButton("打开清单");
		btnNewButton_1.setBounds(1041, 32, 123, 50);
		frame.getContentPane().add(btnNewButton_1);

		JLabel label_4 = new JLabel("处理进度");
		label_4.setBounds(541, 146, 81, 21);
		frame.getContentPane().add(label_4);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(628, 156, 394, 14);
		frame.getContentPane().add(progressBar);

		JButton button = new JButton("开始处理");
		button.setBounds(1041, 145, 123, 29);
		frame.getContentPane().add(button);

		TextArea textArea = new TextArea();
		textArea.setBounds(15, 204, 1139, 332);
		frame.getContentPane().add(textArea);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "互联网", "内网" }));
		comboBox.setBounds(367, 31, 126, 27);
		frame.getContentPane().add(comboBox);

		JLabel label_5 = new JLabel("连接到");
		label_5.setBounds(315, 32, 81, 21);
		frame.getContentPane().add(label_5);

		JLabel label_6 = new JLabel("验证码");
		label_6.setBounds(315, 71, 81, 21);
		frame.getContentPane().add(label_6);

		// 生成一个httpclient对象
		client = HttpClients.createDefault();

		// 在初始化界面时,取验证码
		getVilitdyImage();

//		cookieStore = new BasicCookieStore();
	}

	/**
	 * 设置cookieStore
	 * 
	 * @return
	 */
	public void setCookie(CloseableHttpResponse response) {
		Header[] headers = response.getAllHeaders();
		
		for (int n = 0, len = headers.length; n < len; n++) {
			if(headers[n].getName().equals("Set-Cookie")) {
				cookie=cookie+";"+headers[n].getValue();
			}
		}
		System.out.println("done set cookie");
	}
	
	
	public ArrayList<BasicNameValuePair> initRequestHeaders(String cookie,String ContentLength,String Referer){
		ArrayList<BasicNameValuePair> headers=new ArrayList<>();
		headers.add(new BasicNameValuePair("Accept", "image/png, image/svg+xml, image/*;q=0.8, */*;q=0.5"));
		headers.add(new BasicNameValuePair("Referer", Referer));
		headers.add(new BasicNameValuePair("Accept-Language", "zh-CN"));
		headers.add(new BasicNameValuePair("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729)"));
		headers.add(new BasicNameValuePair("Content-Type", "application/x-www-form-urlencoded"));
		headers.add(new BasicNameValuePair("Accept-Encoding", "gzip, deflate"));
		headers.add(new BasicNameValuePair("Host", "asone.safe"));
		headers.add(new BasicNameValuePair("Content-Length", ContentLength));
		headers.add(new BasicNameValuePair("DNT", "1"));
		headers.add(new BasicNameValuePair("Connection", "Keep-Alive"));	
		headers.add(new BasicNameValuePair("Cache-Control", "no-cache"));	
		headers.add(new BasicNameValuePair("Cookie",cookie));	
		return headers;
	}


	protected void getVilitdyImage() {
		// TODO Auto-generated method stub
		SwingWorker<String, String> sw = new SwingWorker<String, String>() {

			@Override
			protected void done() {
				// TODO Auto-generated method stub
				// 设置验证码
				try {
					label_varifyCode.setIcon(new ImageIcon(ImageIO.read(new File("resource/varifyImage.jpg"))));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("done");
				super.done();
			}

			@Override
			protected String doInBackground() throws Exception {
				// 使用get方法连接服务器
				String netType = (String) comboBox.getSelectedItem().toString();
				String url = "";
				if (netType == "互联网") {

					url = "http://asone.safesvc.gov.cn/asone/jsp/code.jsp?refresh=" + Math.random();
				} else {
					url = "http://asone.safe/asone/jsp/code.jsp?refresh=" + Math.random();
				}
				HttpGet httpGet = new HttpGet(url);
				FileOutputStream fos;
				try {
					// 客户端开始向指定的网址发送请求
					CloseableHttpResponse response = (CloseableHttpResponse) client.execute(httpGet);
					setCookie(response);

					InputStream inputStream = response.getEntity().getContent();
					File file = new File("resource/varifyImage.jpg");
					if (!file.exists()) {
						file.createTempFile("varifyImage", "jpg");
					}

					fos = new FileOutputStream("resource/varifyImage.jpg");
					byte[] data = new byte[1024];
					int len = 0;
					while ((len = inputStream.read(data)) != -1) {
						fos.write(data, 0, len);
					}
					fos.close();
					response.close();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// TODO Auto-generated method stub
				return null;
			}

		};
		sw.execute();

	}

	public boolean setUserLoginInfo() {
		Tools tools = new Tools();
		try {
			UserLoginInfo = new ArrayList();
			UserLoginInfo.add(new BasicNameValuePair("structionCode", structionCode.getText()));
			UserLoginInfo.add(new BasicNameValuePair("password", tools.jsMD5(password.getText())));
			UserLoginInfo.add(new BasicNameValuePair("UserName", UserName.getText()));
			UserLoginInfo.add(new BasicNameValuePair("varifyCode", varifyCode.getText()));
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("set done");
		return false;
	}
	
	public void CheckCode() {
		SwingWorker<String, String> sw=new SwingWorker<String,String>(){

			@Override
			protected String doInBackground() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected void done() {
				// TODO Auto-generated method stub
				super.done();
			}
			
		
			
		};
		
		sw.execute();
	}
	
	
	
}
