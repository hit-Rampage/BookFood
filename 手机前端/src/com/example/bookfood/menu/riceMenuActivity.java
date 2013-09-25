package com.example.bookfood.menu;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.app.Activity;

import com.example.bookfood.R;
import com.example.bookfood.database.dataBaseHelper;
import com.example.bookfood.dialog.detailMenuDialog;
import com.example.bookfood.metadata.bookfoodDatabaseMetadata;

/**
 * "盖饭类"菜单
 * 
 * @Project BookFood
 * @Package com.example.bookfood.menu
 * @Class riceMenuActivity
 * @Date 2012-9-8 下午8:05:03
 * @author upaman
 * @version
 * @since
 */
public class riceMenuActivity extends Activity {
	private dataBaseHelper dbHelper = null;
	private SQLiteDatabase db = null;
	private Cursor myCursor = null;
	private SimpleCursorAdapter adapter = null;
	private ListView lv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ricemenu_layout);
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
		lv.setOnItemClickListener(new riceItemClickListener());
		if (db.isOpen())
			db.close();
	}

	/**
	 * 定义view
	 */
	private void findView() {
		// TODO Auto-generated method stub
		lv = (ListView) findViewById(R.id.listview_rice);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		// 获取数据库
		dbHelper = new dataBaseHelper(riceMenuActivity.this);
		db = dbHelper.getReadableDatabase();
		myCursor = db.query(bookfoodDatabaseMetadata.RICE_TABLE_NAME, null,
				null, null, null, null, null);
		int item_count = myCursor.getCount();
		if (item_count == 0) {
			// 插入数据
			try {
				insert("地三鲜盖饭", 8);
				insert("木须柿子盖饭", 9);
				insert("鸡肉香菇盖饭",10);
				insert("肉末茄子盖饭",11);
				insert("土豆牛肉盖饭", 12);
				insert("鸡蛋柿子盖饭", 12);
				insert("青椒肉丝盖饭", 12);
				insert("鱼香肉丝盖饭", 14);
				insert("咖喱牛肉盖饭", 14);
				insert("蚝油牛肉盖饭", 16);
				insert("肉末茄子盖饭", 16);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		myCursor.close();

	}

	private void insert(String name1, int name2) {
		ContentValues values = new ContentValues();
		values.put(bookfoodDatabaseMetadata.RICE_NAME, name1);
		values.put(bookfoodDatabaseMetadata.RICE_PRICE, name2);
		db.insertOrThrow(bookfoodDatabaseMetadata.RICE_TABLE_NAME, null, values);
	}

	/**
	 * 初始化适配器
	 */
	private void initAdapter() {
		// 定义筛选字段
		String[] riceMenuProjection = new String[] {
				bookfoodDatabaseMetadata.RICE_ID + " as _id",
				bookfoodDatabaseMetadata.RICE_NAME,
				bookfoodDatabaseMetadata.RICE_PIC,
				bookfoodDatabaseMetadata.RICE_PRICE };
		// 查询表并获得游标
		myCursor = db.query(bookfoodDatabaseMetadata.RICE_TABLE_NAME,
				riceMenuProjection, null, null, null, null, null);
		// 定义映射字段
		String[] from = new String[] { bookfoodDatabaseMetadata.RICE_NAME,
				bookfoodDatabaseMetadata.RICE_PRICE };
		int[] to = new int[] { R.id.list_menu_name, R.id.list_menu_price };
		// 设置adapter
		adapter = new SimpleCursorAdapter(riceMenuActivity.this,
				R.layout.menu_list, myCursor, from, to);

	}

	private final class riceItemClickListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			Cursor tempCursor = (Cursor) lv.getItemAtPosition(position);
			String str_item_name = String
					.valueOf(tempCursor.getString(tempCursor
							.getColumnIndex(bookfoodDatabaseMetadata.RICE_NAME)));
			String str_item_price = String.valueOf(tempCursor.getDouble(tempCursor
					.getColumnIndex(bookfoodDatabaseMetadata.RICE_PRICE)));
			String str_item_pic = String
					.valueOf(tempCursor.getString(tempCursor
							.getColumnIndex(bookfoodDatabaseMetadata.RICE_PIC)));
			Bundle bundle = new Bundle();
			bundle.putString("nameOfItem", str_item_name);
			bundle.putString("picOfItem", str_item_pic);
			bundle.putString("priceOfItem", str_item_price);
			Intent intent = new Intent();
			intent.putExtras(bundle);
			intent.setClass(riceMenuActivity.this, detailMenuDialog.class);
			startActivity(intent);
		}
	}

}
