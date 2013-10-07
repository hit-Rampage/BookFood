package com.example.bookfood.user;

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

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookfood.R;
import com.example.bookfood.dialog.registerDialog;

/**
 * 用户登录
 * 
 * @Project BookFood
 * @Package com.example.bookfood.logon
 * @Class logonActivity
 * @Date 2012-9-8 下午8:49:53
 * @author upaman
 * @version
 * @since
 */
public class logonActivity extends Activity {

	private EditText username_edit;
	private EditText password_edit;
	private Button login_btn;
	private Button logout_btn;
	private TextView register_link;
	private TextView login_username_label;
	private TextView login_password_label;

	private String input_username;
	private String input_password;

	boolean isExisted;
	int isOn = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		findView();
		setView();
	}

	/**
	 * 设置view
	 */
	private void setView() {
		// 声明sharepreferences
		SharedPreferences getSignstate = getSharedPreferences("signstate_file",
				MODE_PRIVATE);
		final SharedPreferences.Editor signState_editor = getSignstate.edit();
		// 读取sharepreferences
		SharedPreferences settings = getSharedPreferences("signstate_file",
				MODE_PRIVATE);
		isOn = settings.getInt("saved_signstate", 0);

		if (isOn == 1) {
			logout_btn.setVisibility(View.VISIBLE);
			username_edit.setEnabled(false);
			password_edit.setEnabled(false);
			login_btn.setVisibility(View.INVISIBLE);
		} else {
			logout_btn.setVisibility(View.INVISIBLE);
			username_edit.setEnabled(true);
			password_edit.setEnabled(true);
			login_btn.setVisibility(View.VISIBLE);
		}
		// 点击“注册”跳出注册对话框
		register_link.setClickable(true);
		register_link.setFocusable(true);
		register_link.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(logonActivity.this, registerDialog.class);
				startActivity(intent);
			}
		});
		login_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				input_username = username_edit.getText().toString();
				input_password = password_edit.getText().toString();
				if ("".equals(username_edit.getText().toString().trim()))
					Toast.makeText(logonActivity.this, "用户名不能为空",
							Toast.LENGTH_SHORT).show();
				else if ("".equals(password_edit.getText().toString().trim()))
					Toast.makeText(logonActivity.this, "密码不能为空",
							Toast.LENGTH_SHORT).show();
				else {
					if (testify()) {
						Toast.makeText(logonActivity.this, "已成功登录",
								Toast.LENGTH_SHORT).show();
						signState_editor.putInt("saved_signstate", 1);
						signState_editor.commit();

						// 保存用户名的输入值
						SharedPreferences getSavedUsername = getSharedPreferences(
								"userName_file", MODE_PRIVATE);
						SharedPreferences.Editor userName_editor = getSavedUsername
								.edit();
						userName_editor.putString("saved_username",
								input_username);
						userName_editor.commit();
						finish();
					} else {
						username_edit.setText("");
						password_edit.setText("");
					}
				}
			}
		});
		logout_btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				signState_editor.putInt("saved_signstate", 0);
				signState_editor.commit();
				logout_btn.setVisibility(View.INVISIBLE);
				username_edit.setEnabled(true);
				password_edit.setEnabled(true);
				login_btn.setVisibility(View.VISIBLE);
				Toast.makeText(logonActivity.this, "注销成功", Toast.LENGTH_SHORT)
						.show();
			}
		});

	}

	// 进行用户名登录匹配
	protected boolean testify() {
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			HttpPost post = new HttpPost(
					"http://122.159.46.58:8084/____manage/GetServlet");
			String sendType = "logon";
			NameValuePair user_name = new BasicNameValuePair("user_name",
					input_username);
			NameValuePair user_password = new BasicNameValuePair(
					"user_password", input_password);
			NameValuePair send_type = new BasicNameValuePair("send_type",
					sendType);
			list.add(user_name);
			list.add(user_password);
			list.add(send_type);
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,
					"UTF-8");
			post.setEntity(entity);
			HttpResponse response = client.execute(post);

			if (response.getStatusLine().getStatusCode() == 200) {
				InputStream in = response.getEntity().getContent();
				String str = readString(in);
				if (str.equals("登陆许可"))
					return true;
				else {
					Toast.makeText(logonActivity.this, "用户名或者密码输入错误",
							Toast.LENGTH_SHORT).show();
					return false;
				}

			} else
				Toast.makeText(logonActivity.this, "对不起，网络连接错误",
						Toast.LENGTH_SHORT).show();
			return false;
		}

		catch (Exception e) {
			Toast.makeText(logonActivity.this, "对不起，网络未连接", Toast.LENGTH_SHORT)
					.show();
			return false;
		}
	}

	// 读数据
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
	 * 定义view
	 */
	private void findView() {
		// TODO Auto-generated method stub
		username_edit = (EditText) findViewById(R.id.login_username_edit);
		password_edit = (EditText) findViewById(R.id.login_password_edit);
		login_btn = (Button) findViewById(R.id.signin_btn);
		logout_btn = (Button) findViewById(R.id.signout_btn);
		register_link = (TextView) findViewById(R.id.register_link);
		login_username_label = (TextView) findViewById(R.id.login_username_input);
		login_password_label = (TextView) findViewById(R.id.login_password_input);
	}
}
