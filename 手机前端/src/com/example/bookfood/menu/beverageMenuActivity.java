package com.example.bookfood.menu;

import com.example.bookfood.R;
import com.example.bookfood.database.dataBaseHelper;
import com.example.bookfood.dialog.detailMenuDialog;
import com.example.bookfood.metadata.bookfoodDatabaseMetadata;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;

/**
 * “饮料类”菜单
 * 
 * @Project BookFood
 * @Package com.example.bookfood.menu
 * @Class beverageMenuActivity
 * @Date 2012-9-8 下午8:05:41
 * @author upaman
 * @version
 * @since
 */
public class beverageMenuActivity extends Activity {

	private dataBaseHelper dbHelper = null;
	private SQLiteDatabase db = null;
	private Cursor myCursor = null;
	private SimpleCursorAdapter adapter = null;
	private ListView lv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.beveragemenu_layout);
		initData();
		initAdapter();
		findView();
		setView();
	}

	/**
	 * 设置view
	 */
	private void setView() {
		// TODO Auto-generated method stub
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new beverageItemClickListener());
		if (db.isOpen())
			db.close();
	}

	/**
	 * 定义view
	 */
	private void findView() {
		// TODO Auto-generated method stub
		lv = (ListView) findViewById(R.id.listview_beverage);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		// 获取数据库
		dbHelper = new dataBaseHelper(beverageMenuActivity.this);
		db = dbHelper.getReadableDatabase();
		myCursor = db.query(bookfoodDatabaseMetadata.BEVERAGE_TABLE_NAME, null,
				null, null, null, null, null);
		int item_count = myCursor.getCount();
		if (item_count == 0) {
			// 插入数据
			try {
				insert("矿泉水", 1);
				insert("花生露", 2);
				insert("可乐", 3);
				insert("果粒橙", 5);
				insert("雪碧", 3);
				insert("美年达", 3);
				insert("豆浆", 2);
				insert("苹果汁", 6);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		myCursor.close();

	}

	private void insert(String name1, int name2) {
		ContentValues values = new ContentValues();
		values.put(bookfoodDatabaseMetadata.BEVERAGE_NAME, name1);
		values.put(bookfoodDatabaseMetadata.BEVERAGE_PRICE, name2);
		db.insertOrThrow(bookfoodDatabaseMetadata.BEVERAGE_TABLE_NAME, null,
				values);
	}

	/**
	 * 初始化适配器
	 */
	private void initAdapter() {
		// 定义筛选字段
		String[] noodleMenuProjection = new String[] {
				bookfoodDatabaseMetadata.BEVERAGE_ID + " as _id",
				bookfoodDatabaseMetadata.BEVERAGE_NAME,
				bookfoodDatabaseMetadata.BEVERAGE_PIC,
				bookfoodDatabaseMetadata.BEVERAGE_PRICE };
		// 查询表并获得游标
		myCursor = db.query(bookfoodDatabaseMetadata.BEVERAGE_TABLE_NAME,
				noodleMenuProjection, null, null, null, null, null);
		// 定义映射字段
		String[] from = new String[] { bookfoodDatabaseMetadata.BEVERAGE_NAME,
				bookfoodDatabaseMetadata.BEVERAGE_PRICE };
		int[] to = new int[] { R.id.list_menu_name, R.id.list_menu_price };
		// 设置adapter
		adapter = new SimpleCursorAdapter(beverageMenuActivity.this,
				R.layout.menu_list, myCursor, from, to);

	}

	private final class beverageItemClickListener implements
			OnItemClickListener {

		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			Cursor tempCursor = (Cursor) lv.getItemAtPosition(position);
			String str_item_name = String
					.valueOf(tempCursor.getString(tempCursor
							.getColumnIndex(bookfoodDatabaseMetadata.BEVERAGE_NAME)));
			String str_item_price = String
					.valueOf(tempCursor.getDouble(tempCursor
							.getColumnIndex(bookfoodDatabaseMetadata.BEVERAGE_PRICE)));
			String str_item_pic = String
					.valueOf(tempCursor.getString(tempCursor
							.getColumnIndex(bookfoodDatabaseMetadata.BEVERAGE_PIC)));
			Bundle bundle = new Bundle();
			bundle.putString("nameOfItem", str_item_name);
			bundle.putString("priceOfItem", str_item_price);
			bundle.putString("picOfItem", str_item_pic);
			Intent intent = new Intent();
			intent.putExtras(bundle);
			intent.setClass(beverageMenuActivity.this, detailMenuDialog.class);
			startActivity(intent);
		}
	}
}
