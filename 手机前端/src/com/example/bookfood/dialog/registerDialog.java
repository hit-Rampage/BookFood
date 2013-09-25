package com.example.bookfood.dialog;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookfood.R;

/**
 * 用户注册对话框
 * 
 * @Project BookFood
 * @Package com.example.bookfood.dialog
 * @Class registerDialog
 * @Date 2012-11-2 下午1:09:03
 * @author upaman
 * @version
 * @since
 */
public class registerDialog extends Activity {

	private EditText register_username_edit;
	private EditText register_password_edit;
	private EditText register_password_again_edit;
	private Button submit_register_btn;

	private String input_username;
	private String input_password;
	private String input_password_again;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register_dialog);
		setSize();
		findView();
		setView();
	}

	private void setView() {
		// TODO Auto-generated method stub
		submit_register_btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if ("".equals(register_username_edit.getText().toString()
						.trim()))
					Toast.makeText(registerDialog.this, "请输入用户名",
							Toast.LENGTH_SHORT).show();
				else if ("".equals(register_password_edit.getText().toString()
						.trim()))
					Toast.makeText(registerDialog.this, "请输入密码",
							Toast.LENGTH_SHORT).show();
				else if ("".equals(register_password_again_edit.getText()
						.toString().trim()))
					Toast.makeText(registerDialog.this, "请再次输入密码",
							Toast.LENGTH_SHORT).show();
				else {
					boolean flag = sendRigiserInfo();
					if (flag)
						finish();// 退出当前对话框
				}
			}
		});
	}

	// 发送用户注册信息
	protected boolean sendRigiserInfo() {
		// 获取用户输入
		input_username = register_username_edit.getText().toString();
		input_password = register_password_edit.getText().toString();
		input_password_again = register_password_again_edit.getText()
				.toString();
		// 判断两次密码输入是否一致
		if (input_password.equals(input_password_again)) {
			try {
				DefaultHttpClient client = new DefaultHttpClient();
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				HttpPost post = new HttpPost(
						"http://10.42.0.1:8084/____manage/GetServlet");
				String sendType = "register";
				JSONObject obj = new JSONObject();
				obj.put("UserName", input_username);
				obj.put("Password", input_password);
				NameValuePair register_info = new BasicNameValuePair(
						"register_info", obj.toString());
				NameValuePair send_type = new BasicNameValuePair("send_type",
						sendType);
				list.add(register_info);
				list.add(send_type);
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,
						"UTF-8");
				post.setEntity(entity);
				HttpResponse response = client.execute(post);
				if (response.getStatusLine().getStatusCode() == 200) {
					InputStream in = response.getEntity().getContent();
					String str = readString(in);
					if (str.equals("注册许可")) {
						Toast.makeText(registerDialog.this, "已成功注册",
								Toast.LENGTH_SHORT).show();
						return true;
					}
					else if(str.equals("重复")){
						Toast.makeText(registerDialog.this, "已存在该用户名",
								Toast.LENGTH_SHORT).show();
						register_username_edit.setText("");
						register_password_edit.setText("");
						register_password_again_edit.setText("");
						return false;
					}
				} else
					Toast.makeText(registerDialog.this, "对不起，网络连接错误",
							Toast.LENGTH_SHORT).show();
				return false;
			}

			catch (Exception e) {
				Toast.makeText(registerDialog.this, "对不起，网络未连接",
						Toast.LENGTH_SHORT).show();
				return false;
			}
		} else {
			Toast.makeText(registerDialog.this, "两次输入密码不一致", Toast.LENGTH_SHORT)
					.show();
			register_password_edit.setText("");
			register_password_again_edit.setText("");
			return false;
		}
	}

	private void findView() {
		// TODO Auto-generated method stub
		submit_register_btn = (Button) findViewById(R.id.send_btn);
		register_username_edit = (EditText) findViewById(R.id.register_username_edit);
		register_password_edit = (EditText) findViewById(R.id.register_password_edit);
		register_password_again_edit = (EditText) findViewById(R.id.register_password_again_edit);
	}

	protected String readString(InputStream in) throws Exception {
		byte[] data = new byte[1024];
		int length = 0;
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		while ((length = in.read(data)) != -1) {
			bout.write(data, 0, length);
		}
		return new String(bout.toByteArray(), "UTF-8");

	}

	/**
	 * 设置屏幕大小
	 */
	private void setSize() {
		WindowManager m = getWindowManager();
		// 为获取屏幕宽、高
		Display d = m.getDefaultDisplay();
		// 获取对话框当前的参数值
		android.view.WindowManager.LayoutParams p = getWindow().getAttributes();
		p.height = (int) (d.getHeight() * 0.8); // 高度设置为屏幕的0.8
		p.width = (int) (d.getWidth() * 0.9); // 宽度设置为屏幕的0.9
		getWindow().setAttributes(p);
	}

}
