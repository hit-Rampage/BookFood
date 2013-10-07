package com.example.bookfood;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.bookfood.dataTrans.Dish;
import com.example.bookfood.dataTrans.JsonParse;
import com.example.bookfood.database.dataBaseHelper;
import com.example.bookfood.menu.beverageMenuActivity;
import com.example.bookfood.menu.noodleMenuActivity;
import com.example.bookfood.menu.riceMenuActivity;
import com.example.bookfood.menu.stirMenuActivity;
import com.example.bookfood.metadata.bookfoodDatabaseMetadata;
import com.example.bookfood.user.cartActivity;
import com.example.bookfood.user.leaveMessageActivity;
import com.example.bookfood.user.logonActivity;

/**
 * 主页面
 * 
 * @Project BookFood
 * @Package com.example.bookfood
 * @Class MainActivity
 * @Date 2012-9-8 下午8:02:10
 * @author upaman
 * @version
 * @since
 */
public class MainActivity extends Activity {

	private Button rice_btn;
	private Button stir_btn;
	private Button noodle_btn;
	private Button beverage_btn;
	private Button logon_btn;
	private Button aboutus_btn;
	private Button feedback_btn;
	private Button cart_btn;

	private List<Dish> dishes;
	private static final String urlPath = "http://10.42.0.1:8084/____manage/PostServlet";
	private static final String TAG = "MainActivity";

	private dataBaseHelper dbHelper = null;
	private SQLiteDatabase db = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findView();
		setListeners();
		getLatestMenu();
	}

	private void getLatestMenu() {
		if(doUpdate())
			Toast.makeText(MainActivity.this, "更新菜单成功",
					Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(MainActivity.this, "由于网络问题菜单更新失败",
					Toast.LENGTH_SHORT).show();
	}

	private boolean doUpdate() {
		try {
			// 得到Json解析成功之后数据
			dishes = JsonParse.getListPerson(urlPath);
			// 获取数据库
			dbHelper = new dataBaseHelper(MainActivity.this);
			db = dbHelper.getWritableDatabase();
			// 删除旧数据库
			db.execSQL("DELETE FROM " + bookfoodDatabaseMetadata.RICE_TABLE_NAME
					+ ";");
			db.execSQL("DELETE FROM " + bookfoodDatabaseMetadata.STIR_TABLE_NAME
					+ ";");
			db.execSQL("DELETE FROM " + bookfoodDatabaseMetadata.NOODLE_TABLE_NAME
					+ ";");
			db.execSQL("DELETE FROM " + bookfoodDatabaseMetadata.BEVERAGE_TABLE_NAME
					+ ";");
			for (int i = 0; i < dishes.size(); i++) {
				if (dishes.get(i).getType().equals("盖饭"))
					insertTOrice(dishes.get(i).getName(), dishes.get(i)
							.getPrice(),dishes.get(i).getPic());
				if (dishes.get(i).getType().equals("面食"))
					insertTOnoodle(dishes.get(i).getName(), dishes.get(i)
							.getPrice(),dishes.get(i).getPic());
				if (dishes.get(i).getType().equals("小炒"))
					insertTOstir(dishes.get(i).getName(), dishes.get(i)
							.getPrice(),dishes.get(i).getPic());
				if (dishes.get(i).getType().equals("饮料"))
					insertTObeverage(dishes.get(i).getName(), dishes.get(i)
							.getPrice(),dishes.get(i).getPic());
			}
			db.close();
			return true;
		} catch (Exception e) {
			Log.i(TAG, e.toString());// DDMS中显示提示
            return false;
		}
	}

	private void insertTOrice(String name, double price, String pic) {
	
		ContentValues values = new ContentValues();
		values.put(bookfoodDatabaseMetadata.RICE_NAME, name);
		values.put(bookfoodDatabaseMetadata.RICE_PRICE, price);
		values.put(bookfoodDatabaseMetadata.RICE_PIC, pic);
		db.insertOrThrow(bookfoodDatabaseMetadata.RICE_TABLE_NAME, null, values);
	}

	private void insertTOstir(String name, double price, String pic) {

		ContentValues values = new ContentValues();
		values.put(bookfoodDatabaseMetadata.STIR_NAME, name);
		values.put(bookfoodDatabaseMetadata.STIR_PRICE, price);
		values.put(bookfoodDatabaseMetadata.STIR_PIC, pic);
		db.insertOrThrow(bookfoodDatabaseMetadata.STIR_TABLE_NAME, null, values);
	}

	private void insertTOnoodle(String name, double price, String pic) {
		
		ContentValues values = new ContentValues();
		values.put(bookfoodDatabaseMetadata.NOODLE_NAME, name);
		values.put(bookfoodDatabaseMetadata.NOODLE_PRICE, price);
		values.put(bookfoodDatabaseMetadata.NOODLE_PIC, pic);
		db.insertOrThrow(bookfoodDatabaseMetadata.NOODLE_TABLE_NAME, null, values);
	}

	private void insertTObeverage(String name, double price, String pic) {
		
		ContentValues values = new ContentValues();
		values.put(bookfoodDatabaseMetadata.BEVERAGE_NAME, name);
		values.put(bookfoodDatabaseMetadata.BEVERAGE_PRICE, price);
		values.put(bookfoodDatabaseMetadata.BEVERAGE_PIC, pic);
		db.insertOrThrow(bookfoodDatabaseMetadata.BEVERAGE_TABLE_NAME, null, values);
	}

	private void findView() {
		// TODO Auto-generated method stub
		rice_btn = (Button) findViewById(R.id.rice_btn);
		stir_btn = (Button) findViewById(R.id.stir_btn);
		noodle_btn = (Button) findViewById(R.id.noodle_btn);
		beverage_btn = (Button) findViewById(R.id.beverage_btn);
		logon_btn = (Button) findViewById(R.id.logon_btn);
		aboutus_btn = (Button) findViewById(R.id.aboutus_btn);
		feedback_btn = (Button) findViewById(R.id.feedback_btn);
		cart_btn = (Button) findViewById(R.id.cart_btn);
	}

	/**
	 * 设置各个按钮的监听器
	 */

	private void setListeners() {
		// TODO Auto-generated method stub

		/**
		 * 点击按钮打开“盖饭”菜单
		 */
		rice_btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, riceMenuActivity.class);
				startActivity(intent);

			}
		});
		/**
		 * 点击按钮打开“小炒”菜单
		 */
		stir_btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, stirMenuActivity.class);
				startActivity(intent);

			}
		});
		/**
		 * 点击按钮打开“面食”菜单
		 */
		noodle_btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, noodleMenuActivity.class);
				startActivity(intent);

			}
		});
		/**
		 * 点击按钮打开“饮料”菜单
		 */
		beverage_btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, beverageMenuActivity.class);
				startActivity(intent);

			}
		});
		/**
		 * 点击按钮打开“购物车”页面
		 */
		cart_btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, cartActivity.class);
				startActivity(intent);

			}
		});
		/**
		 * 点击按钮打开“用户登录”页面
		 */
		logon_btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, logonActivity.class);
				startActivity(intent);

			}
		});
		/**
		 * 点击按钮打开“关于我们”窗口
		 */
		aboutus_btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle(R.string.aboutus);
				builder.setMessage("手机网络订餐系统手机客户端Beta1.0版本\n\n\n"
						+ "开发者：何家琪 杨晨浩 潘柯宇\n" + "联系我们：hitteamwork@gmail.com");

				builder.create().show();
			}
		});
		/**
		 * 点击按钮打开“反馈”页面
		 */
		feedback_btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, leaveMessageActivity.class);
				startActivity(intent);

			}
		});
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
