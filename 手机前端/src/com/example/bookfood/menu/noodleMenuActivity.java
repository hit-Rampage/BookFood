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
 * “面食类”菜单
 * 
 * @Project BookFood
 * @Package com.example.bookfood.menu
 * @Class noodleMenuActivity
 * @Date 2012-9-8 下午8:05:24
 * @author upaman
 * @version
 * @since
 */
public class noodleMenuActivity extends Activity {

	private dataBaseHelper dbHelper = null;
	private SQLiteDatabase db = null;
	private Cursor myCursor = null;
	private SimpleCursorAdapter adapter = null;
	private ListView lv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.noodlemenu_layout);
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
		lv.setOnItemClickListener(new noodleItemClickListener());
		if (db.isOpen())
			db.close();
	}

	/**
	 * 定义view
	 */
	private void findView() {
		// TODO Auto-generated method stub
		lv = (ListView) findViewById(R.id.listview_noodle);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		// 获取数据库
		dbHelper = new dataBaseHelper(noodleMenuActivity.this);
		db = dbHelper.getReadableDatabase();
		myCursor = db.query(bookfoodDatabaseMetadata.NOODLE_TABLE_NAME, null,
				null, null, null, null, null);
		int item_count = myCursor.getCount();
		if (item_count == 0) {
			// 插入数据
			try {
				insert("水饺", 8);
				insert("意大利炒面", 10);
				insert("炸酱面", 10);
				insert("油泼面", 10);
				insert("牛肉拉面", 12);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		myCursor.close();

	}

	private void insert(String name1, int name2) {
		ContentValues values = new ContentValues();
		values.put(bookfoodDatabaseMetadata.NOODLE_NAME, name1);
		values.put(bookfoodDatabaseMetadata.NOODLE_PRICE, name2);
		db.insertOrThrow(bookfoodDatabaseMetadata.NOODLE_TABLE_NAME, null, values);
	}

	/**
	 * 初始化适配器
	 */
	private void initAdapter() {
		// 定义筛选字段
		String[] noodleMenuProjection = new String[] {
				bookfoodDatabaseMetadata.NOODLE_ID + " as _id",
				bookfoodDatabaseMetadata.NOODLE_NAME,
				bookfoodDatabaseMetadata.NOODLE_PIC,
				bookfoodDatabaseMetadata.NOODLE_PRICE };
		// 查询表并获得游标
		myCursor = db.query(bookfoodDatabaseMetadata.NOODLE_TABLE_NAME,
				noodleMenuProjection, null, null, null, null, null);
		// 定义映射字段
		String[] from = new String[] { bookfoodDatabaseMetadata.NOODLE_NAME,
				bookfoodDatabaseMetadata.NOODLE_PRICE };
		int[] to = new int[] { R.id.list_menu_name, R.id.list_menu_price };
		// 设置adapter
		adapter = new SimpleCursorAdapter(noodleMenuActivity.this,
				R.layout.menu_list, myCursor, from, to);

	}

	private final class noodleItemClickListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			Cursor tempCursor = (Cursor) lv.getItemAtPosition(position);
			String str_item_name = String
					.valueOf(tempCursor.getString(tempCursor
							.getColumnIndex(bookfoodDatabaseMetadata.NOODLE_NAME)));
			String str_item_price = String.valueOf(tempCursor.getDouble(tempCursor
					.getColumnIndex(bookfoodDatabaseMetadata.NOODLE_PRICE)));
			String str_item_pic = String
					.valueOf(tempCursor.getString(tempCursor
							.getColumnIndex(bookfoodDatabaseMetadata.NOODLE_PIC)));
			Bundle bundle = new Bundle();
			bundle.putString("nameOfItem", str_item_name);
			bundle.putString("picOfItem", str_item_pic);
			bundle.putString("priceOfItem", str_item_price);
			Intent intent = new Intent();
			intent.putExtras(bundle);
			intent.setClass(noodleMenuActivity.this, detailMenuDialog.class);
			startActivity(intent);
		}
	}
}
