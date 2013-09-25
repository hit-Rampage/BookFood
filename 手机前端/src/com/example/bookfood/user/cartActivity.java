package com.example.bookfood.user;


import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookfood.R;
import com.example.bookfood.database.dataBaseHelper;
import com.example.bookfood.dialog.sendInfoConfirmDialog;
import com.example.bookfood.metadata.bookfoodDatabaseMetadata;

/**
 * 购物车页面
 * 
 * @Project BookFood
 * @Package com.example.bookfood.user
 * @Class cartActivity
 * @Date 2012-9-8 下午10:18:15
 * @author upaman
 * @version
 * @since
 */
public class cartActivity extends Activity {
	private dataBaseHelper dbHelper = null;
	private SQLiteDatabase db = null;
	private Cursor myCursor = null;
	private SimpleCursorAdapter adapter = null;
	private ListView lv;

	private Button send_confirm;
	private TextView sumup_label;
	private TextView sumup_price;
	private TextView price_tag;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cart_layout);
		findView();
		initAdapter();
	}

	/**
	 * 初始化适配器
	 */
	private void initAdapter() {
		// 获取数据库
		dbHelper = new dataBaseHelper(cartActivity.this);
		db = dbHelper.getReadableDatabase();
		// 定义筛选字段
		String[] cartProjection = new String[] {
				bookfoodDatabaseMetadata.ITEM_ID + " as _id",
				bookfoodDatabaseMetadata.ITEM_NAME,
				bookfoodDatabaseMetadata.ITEM_NUM,
				bookfoodDatabaseMetadata.ITEM_PRICE };
		// 查询表并获得游标
		myCursor = db.query(bookfoodDatabaseMetadata.CART_TABLE_NAME,
				cartProjection, null, null, null, null, null);
		// 定义映射字段
		String[] from = new String[] { bookfoodDatabaseMetadata.ITEM_NAME,
				bookfoodDatabaseMetadata.ITEM_NUM,
				bookfoodDatabaseMetadata.ITEM_PRICE };
		int[] to = new int[] { R.id.list_cart_name, R.id.list_cart_num,
				R.id.list_cart_price };
		// 设置adapter
		adapter = new SimpleCursorAdapter(cartActivity.this,
				R.layout.cart_list, myCursor, from, to);
		setView();
	}

	/**
	 * 设置view
	 */
	private void setView() {
		// TODO Auto-generated method stub
		lv.setEmptyView(findViewById(R.id.empty_tip));// 设置无订单提示
		lv.setAdapter(adapter);
		// 设置点击删除
		lv.setOnItemClickListener(new cartItemClickListener());
		send_confirm.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				//判断是否有订单
				if(myCursor.getCount()==0){
					
					Toast.makeText(cartActivity.this,
							"您还没有订单", Toast.LENGTH_SHORT)
							.show();
				}
				else{
				Intent intent = new Intent();
				intent.setClass(cartActivity.this, sendInfoConfirmDialog.class);
				startActivity(intent);
				finish();//关闭当前activity
				}
			}
		});
		// 计算并显示总价
		int item_count = myCursor.getCount();
		if (item_count == 0) {// 设置无订单条目时不显示总价
			sumup_label.setVisibility(View.INVISIBLE);
			sumup_price.setVisibility(View.INVISIBLE);
			price_tag.setVisibility(View.INVISIBLE);
		} 
		else {
			double total_price = 0;
			myCursor.moveToLast();
			while(item_count > 0){				
				total_price += myCursor.getDouble(myCursor
						.getColumnIndex(bookfoodDatabaseMetadata.ITEM_PRICE));				
				item_count --;
				myCursor.moveToPrevious();
			}
			sumup_price.setText(String.valueOf(total_price));
		}
		db.close();
	}

	/**
	 * 点击后删除事件处理
	 */
	private final class cartItemClickListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> arg0, View arg1,
				final int position, long arg3) {

			// 设置确认删除对话框
			new AlertDialog.Builder(cartActivity.this)
					.setTitle(R.string.delete_resureTitle)
					.setMessage(R.string.delete_resureTip)
					.setNegativeButton(R.string.cancelForDialog,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							})
					.setPositiveButton(R.string.deleteForDialog,
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									Cursor tempCursor = (Cursor) lv
											.getItemAtPosition(position);
									deleteItem(tempCursor.getInt(0));
									initAdapter();
								}
							}).show();

		}

		// 从表中删除指定id的一条数据
		private void deleteItem(int id) {
			// TODO Auto-generated method stub
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			db.execSQL("DELETE FROM "
					+ bookfoodDatabaseMetadata.CART_TABLE_NAME + " WHERE "
					+ bookfoodDatabaseMetadata.ITEM_ID + "="
					+ Integer.toString(id));
			db.close();
		}
	}

	/**
	 * 定义view
	 */
	private void findView() {
		// TODO Auto-generated method stub
		lv = (ListView) findViewById(R.id.listview_cart);
		send_confirm = (Button) findViewById(R.id.send_item);
		sumup_label = (TextView) findViewById(R.id.sum_label);
		sumup_price = (TextView) findViewById(R.id.sum_price);
		price_tag = (TextView) findViewById(R.id.price_tag);
	}

}
