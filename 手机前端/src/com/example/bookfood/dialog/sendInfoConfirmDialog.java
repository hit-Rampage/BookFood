package com.example.bookfood.dialog;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookfood.R;
import com.example.bookfood.database.dataBaseHelper;
import com.example.bookfood.metadata.bookfoodDatabaseMetadata;
import com.example.bookfood.user.cartActivity;

/**
 * 点击“提交订单”弹出的自定义dialog
 * 
 * @Project BookFood
 * @Package com.example.bookfood.dialog
 * @Class sendInfoConfirmDialog
 * @Date 2012-9-27 下午10:15:34
 * @author upaman
 * @version
 * @since
 */
public class sendInfoConfirmDialog extends Activity {

	private EditText username_edit;
	private EditText address_edit;
	private EditText phone_edit;
	private Button send_btn;

	private dataBaseHelper dbHelper = null;
	private SQLiteDatabase db = null;
	private Cursor myCursor = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.info_dialog);
		setSize();
		findView();
		setView();
	}

	private void setView() {

		// 设置提交按钮
		send_btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				if ("".equals(address_edit.getText().toString().trim()))
					Toast.makeText(sendInfoConfirmDialog.this, "请输入正确的配送地址",
							Toast.LENGTH_SHORT).show();
				else if ("".equals(phone_edit.getText().toString().trim()))
					Toast.makeText(sendInfoConfirmDialog.this, "请输入正确的联系电话",
							Toast.LENGTH_SHORT).show();
				else {
					if (sendTOserver()) {
						// 清空cart列表
						dbHelper = new dataBaseHelper(
								sendInfoConfirmDialog.this);
						db = dbHelper.getReadableDatabase();
						db.execSQL("DELETE FROM "
								+ bookfoodDatabaseMetadata.CART_TABLE_NAME
								+ ";");
						db.close();
						Intent intent = new Intent();
						intent.setClass(sendInfoConfirmDialog.this,
								cartActivity.class);
						startActivity(intent);
						finish();// 退出当前对话框
					} else
						Toast.makeText(sendInfoConfirmDialog.this,
								"对不起，由于网络问题，订单没有发送成功", Toast.LENGTH_SHORT)
								.show();
				}

			}
		});
	}
    //发送订单至服务器
	protected boolean sendTOserver() {
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			HttpPost post = new HttpPost(
					"http://122.159.46.58:8084/____manage/GetServlet");
			// 打包顾客信息
			String getUsername = username_edit.getText().toString();
			String getAddress = address_edit.getText().toString();
			String getPhone = phone_edit.getText().toString();
			String getTime = getCurrentTime();
			String sendType = "order"; 
			JSONObject obj = new JSONObject();
			obj.put("UserName", getUsername);
			obj.put("Address", getAddress);
			obj.put("Telephone", getPhone);
			obj.put("OrderTime", getTime);
			NameValuePair user_info = new BasicNameValuePair("user_info",
					obj.toString());
			NameValuePair send_type = new BasicNameValuePair("send_type", sendType);
			list.add(user_info);
			list.add(send_type);

			// 准备订单信息
			dbHelper = new dataBaseHelper(sendInfoConfirmDialog.this);
			db = dbHelper.getReadableDatabase();
			// 定义筛选字段
			String[] cartProjection = new String[] {
					bookfoodDatabaseMetadata.ITEM_ID + " as _id",
					bookfoodDatabaseMetadata.ITEM_NAME,
					bookfoodDatabaseMetadata.ITEM_NUM,
					bookfoodDatabaseMetadata.ITEM_PRICE };
			// 查询表并获得游标
			JSONArray jsonArray = new JSONArray();
			String item_name;
			int item_num;
			int item_price;
			myCursor = db.query(bookfoodDatabaseMetadata.CART_TABLE_NAME,
					cartProjection, null, null, null, null, null);
			int item_count = myCursor.getCount();
			myCursor.moveToLast();
			while (item_count > 0) {
				JSONObject item_obj = new JSONObject();
				item_name = myCursor.getString(myCursor
						.getColumnIndex(bookfoodDatabaseMetadata.ITEM_NAME));
				item_num = myCursor.getInt(myCursor
						.getColumnIndex(bookfoodDatabaseMetadata.ITEM_NUM));
				item_price = myCursor.getInt(myCursor
						.getColumnIndex(bookfoodDatabaseMetadata.ITEM_PRICE));
				item_obj.put("DishName", item_name);
				item_obj.put("DishCount", item_num);
				item_obj.put("DishPrice", item_price);
				jsonArray.put(item_obj);
				item_count--;
				myCursor.moveToPrevious();
			}
			myCursor.close();
			db.close();
			NameValuePair order_info = new BasicNameValuePair("order_info",
					jsonArray.toString());
			list.add(order_info);
			// 打包发送
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,
					"UTF-8");
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == 200) {
				Toast.makeText(sendInfoConfirmDialog.this,
						"   已成功发送订单\n" + "我们将马上安排配送", Toast.LENGTH_SHORT)
						.show();
			} else
				Toast.makeText(sendInfoConfirmDialog.this, "对不起，网络连接错误",
						Toast.LENGTH_SHORT).show();

		}

		catch (Exception e) {
			Toast.makeText(sendInfoConfirmDialog.this, "对不起，网络未连接",
					Toast.LENGTH_SHORT).show();
		}
		return true;
	}
//获取系统当前时间
	private String getCurrentTime() {
		Formatter mFormatter = new Formatter();
		Time time = new Time();
		time.setToNow();
		return mFormatter.format("%d-%02d-%02d %02d:%02d:%02d", time.year,
				time.month + 1, time.monthDay, time.hour, time.minute,
				time.second).toString();
	}

	private void findView() {

		send_btn = (Button) findViewById(R.id.send_btn);
		username_edit = (EditText) findViewById(R.id.usercall_edit);
		address_edit = (EditText) findViewById(R.id.address_edit);
		phone_edit = (EditText) findViewById(R.id.phone_edit);
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
