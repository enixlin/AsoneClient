package cn.enixlin.jmrc.asone.ui;

import java.awt.EventQueue;
import java.awt.Label;

import javafx.scene.control.ComboBox;

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
	private CookieStore cookieStore;
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
		btnNewButton.setBounds(315, 32, 200, 99);
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
		label.setBounds(15, 71, 81, 21);
		frame.getContentPane().add(label);

		UserName = new JTextField();
		UserName.setText("ba");
		UserName.setColumns(10);
		UserName.setBounds(111, 68, 189, 27);
		frame.getContentPane().add(UserName);

		JLabel label_1 = new JLabel("密码");
		label_1.setBounds(15, 110, 81, 21);
		frame.getContentPane().add(label_1);

		password = new JTextField();
		password.setText("123456Gj");
		password.setColumns(10);
		password.setBounds(111, 107, 189, 27);
		frame.getContentPane().add(password);

		JLabel label_2 = new JLabel("验证码");
		label_2.setBounds(15, 149, 81, 21);
		frame.getContentPane().add(label_2);

		varifyCode = new JTextField();
		varifyCode.setColumns(10);
		varifyCode.setBounds(111, 146, 189, 27);
		frame.getContentPane().add(varifyCode);

		label_varifyCode = new JLabel("");
		label_varifyCode.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_varifyCode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getVilitdyImage();
			}
		});
		label_varifyCode.setBounds(315, 149, 200, 21);
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
		comboBox.setBounds(628, 104, 200, 27);
		frame.getContentPane().add(comboBox);

		cookieStore = new BasicCookieStore();
		client = HttpClients.createDefault();
	}

	/**
	 * 设置cookieStore
	 * 
	 * @return
	 */
	public CookieStore setCookieStore(CloseableHttpResponse response) {
		Header[] headers = response.getAllHeaders();

		for (int n = 0, len = headers.length; n < len; n++) {

			cookieStore.addCookie(new BasicClientCookie(headers[n].getName(),
					headers[n].getValue()));
		}
		System.out.println(cookieStore);
		return cookieStore;

	}

	/**
	 * 设定请求头
	 * 
	 * @param headers
	 * @return
	 */
	public void setRequestHeader(ArrayList<BasicNameValuePair> headers,
			HttpGet httpGet) {
		int flag = 0;
		// 添加http headers
		if (headers != null && headers.size() > 0) {
			for (int n = 0, len = headers.size(); n < len; n++) {
				httpGet.addHeader(headers.get(n).getName(), headers.get(n)
						.getValue());
			}
		}
	}

	protected void getVilitdyImage() {
		// TODO Auto-generated method stub
		// 使用get方法连接服务器
		String netType = (String) comboBox.getSelectedItem().toString();
		String url = "";
		if (netType == "互联网") {

			url = "http://asone.safesvc.gov.cn/asone/jsp/code.jsp?refresh="
					+ Math.random();
		} else {
			url = "http://asone.safe/asone/jsp/code.jsp?refresh="
					+ Math.random();
		}
		HttpGet httpGet = new HttpGet(url);
		HttpClient client = new DefaultHttpClient();
		FileOutputStream fos;
		try {
			// 客户端开始向指定的网址发送请求
			CloseableHttpResponse response = (CloseableHttpResponse) client
					.execute(httpGet);
			setCookieStore(response);

			InputStream inputStream = response.getEntity().getContent();
			File file = new File("D:\\jj");
			if (!file.exists()) {
				file.mkdirs();
			}
 
			fos = new FileOutputStream("g:\\jj\\test.jpg");
			byte[] data = new byte[1024];
			int len = 0;
			while ((len = inputStream.read(data)) != -1) {
				fos.write(data, 0, len);
			}
			fos.close();
			response.close();
			// 设置验证码
			label_varifyCode.setIcon(new ImageIcon("g:\\jj\\test.jpg"));
			System.out.println("done");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean setUserLoginInfo() {

		Tools tools = new Tools();

		try {
			UserLoginInfo = new ArrayList();
			UserLoginInfo.add(new BasicNameValuePair("structionCode",
					structionCode.getText()));
			UserLoginInfo.add(new BasicNameValuePair("password", tools
					.jsMD5(password.getText())));
			UserLoginInfo.add(new BasicNameValuePair("UserName", UserName
					.getText()));
			UserLoginInfo.add(new BasicNameValuePair("varifyCode", varifyCode
					.getText()));
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("set done");
		return false;
	}
}