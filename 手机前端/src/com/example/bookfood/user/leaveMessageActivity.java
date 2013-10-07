package com.example.bookfood.user;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceManager;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookfood.R;

/**
 * 用户留言界面
 * 
 * @Project BookFood
 * @Package com.example.bookfood.user
 * @Class leaveMessageActivity
 * @Date 2012-9-28 上午9:33:15
 * @author upaman
 * @version
 * @since
 */
public class leaveMessageActivity extends Activity {

	private Button putforward_btn;
	private EditText message_body;
	
	 //SharedPreferences recieved_username;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leavemessage_layout);
		findView();
		setView();
	}

	private void setView() {
		
		
		putforward_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if ("".equals(message_body.getText().toString().trim()))
					Toast.makeText(leaveMessageActivity.this, "留言内容不能为空",
							Toast.LENGTH_SHORT).show();
				else {
					if (sendMessage(message_body.getText().toString())) {
						Toast.makeText(leaveMessageActivity.this,
								"成功提交，多谢您的宝贵建议", Toast.LENGTH_SHORT).show();
						finish();
					}
				}
			}
		});
	}

	protected boolean sendMessage(String string) {
		// TODO Auto-generated method stub
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			HttpPost post = new HttpPost(
					"http://10.42.0.1:8084/____manage/GetServlet");
			SharedPreferences getusername = getSharedPreferences("userName_file", MODE_PRIVATE);
		    String getUsername = getusername.getString("saved_username", null);
		    
		    SharedPreferences settings = getSharedPreferences("signstate_file",
					MODE_PRIVATE);
			int isOn = settings.getInt("saved_signstate", 0);
			if(isOn == 0)
				getUsername = "匿名";
		    
			String sendType = "feedback";
			String getMessage = message_body.getText().toString();
			String getTime = getCurrentTime();
			JSONObject obj = new JSONObject();
			obj.put("UserName", getUsername);
			obj.put("SendNote", getMessage);
			obj.put("SendTime", getTime);
			NameValuePair feedback_info = new BasicNameValuePair("feedback_info", obj.toString());
			NameValuePair send_type = new BasicNameValuePair("send_type",sendType);
			list.add(feedback_info);
			list.add(send_type);
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,
					"UTF-8");
			post.setEntity(entity);
			HttpResponse response = client.execute(post);

			if (response.getStatusLine().getStatusCode() == 200)
				return true;
			else
				Toast.makeText(leaveMessageActivity.this, "对不起，网络连接错误",
						Toast.LENGTH_SHORT).show();
			return false;
		}

		catch (Exception e) {
			Toast.makeText(leaveMessageActivity.this, "对不起，网络未连接",
					Toast.LENGTH_SHORT).show();
			return false;
		}
	}
	private String getCurrentTime() {
		Formatter mFormatter = new Formatter();
		Time time = new Time();
		time.setToNow();
		return mFormatter.format("%d-%02d-%02d %02d:%02d:%02d", time.year,
				time.month + 1, time.monthDay, time.hour, time.minute,
				time.second).toString();
	}
	private void findView() {
		// TODO Auto-generated method stub
		putforward_btn = (Button) findViewById(R.id.putforward);
		message_body = (EditText) findViewById(R.id.message_body);

	}

}
