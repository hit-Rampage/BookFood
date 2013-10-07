package com.example.bookfood.dialog;

import java.io.IOException;
import java.net.URL;

import android.app.Activity;
import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookfood.R;
import com.example.bookfood.database.dataBaseHelper;
import com.example.bookfood.metadata.bookfoodDatabaseMetadata;

/**
 * 点击某一Item弹出dialog
 * 
 * @Project BookFood
 * @Package com.example.bookfood.dialog
 * @Class aboutusDialog
 * @Date 2012-9-8 下午8:01:32
 * @author upaman
 * @version
 * @since
 */
public class detailMenuDialog extends Activity {

	private dataBaseHelper dbHelper = null;
	private SQLiteDatabase db = null;

	private Button confirm_cart;
	private EditText item_num_edit;
	private TextView item_name;
	private TextView item_price;

	private String recieved_name;
	private String recieved_price;
	private String recieved_pic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.detail_dialog);
		setSize();
		findView();
		setView();
	}

	/**
	 * 设置view
	 */
	private void setView() {
		// 设置菜品名及价格显示
		Bundle bundle = getIntent().getExtras();
		recieved_name = bundle.getString("nameOfItem");
		recieved_price = bundle.getString("priceOfItem");
		recieved_pic = bundle.getString("picOfItem");
		item_name.setText(recieved_name);
		item_price.setText(recieved_price + "元");
		loadImage("http://122.159.46.58:8084/____manage/" + recieved_pic,
				R.id.item_pic);
		// 设置提交按钮响应
		confirm_cart.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (!("".equals(item_num_edit.getText().toString().trim())))// 判断是否输入
					insertIntoCart();
				tip_show();

			}

			// 插入至cart数据表
			private void insertIntoCart() {
				// 获取用户输入份数
				int input_num = Integer.parseInt(item_num_edit.getText()
						.toString());
				// 计算总价
				double total_price = input_num
						* Double.parseDouble(recieved_price);
				// 获取数据库
				dbHelper = new dataBaseHelper(detailMenuDialog.this);
				db = dbHelper.getReadableDatabase();
				// 插入数据
				try {
					insert(recieved_name, input_num, total_price);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				db.close();
			}

			/**
			 * 根据加入结果提示结果
			 */
			private void tip_show() {

				if ("".equals(item_num_edit.getText().toString().trim()))
					Toast.makeText(detailMenuDialog.this, " 请输入需要的菜品数量",
							Toast.LENGTH_SHORT).show();
				else {
					Toast.makeText(detailMenuDialog.this, "已成功添加至您的购物车中",
							Toast.LENGTH_SHORT).show();
					finish();// 退出对话框
				}
			}

		});

	}

	protected void insert(String str, int i, double j) {

		ContentValues values = new ContentValues();
		values.put(bookfoodDatabaseMetadata.ITEM_NAME, str);
		values.put(bookfoodDatabaseMetadata.ITEM_NUM, i);
		values.put(bookfoodDatabaseMetadata.ITEM_PRICE, j);
		db.insertOrThrow(bookfoodDatabaseMetadata.CART_TABLE_NAME, null, values);
	}

	/**
	 * 定义view
	 */
	private void findView() {

		confirm_cart = (Button) findViewById(R.id.confrim_intoCart);
		item_num_edit = (EditText) findViewById(R.id.item_num_edit);
		item_name = (TextView) findViewById(R.id.item_name);
		item_price = (TextView) findViewById(R.id.item_price);
	}

	private Handler handler = new Handler();

	private void loadImage(final String url, final int id) {
		handler.post(new Runnable() {
			public void run() {
				Drawable drawable = null;
				Bitmap bitmap = null;
				Bitmap resizeBmp=null;
				try {
					drawable = Drawable.createFromStream(
							new URL(url).openStream(), "image.gif");
					BitmapDrawable dtob = (BitmapDrawable) drawable;
					bitmap = dtob.getBitmap();
					Matrix matrix = new Matrix();
					matrix.postScale(1.6f, 1.6f); // 长和宽放大缩小的比例
					resizeBmp = Bitmap.createBitmap(bitmap, 0, 0,
							140, 100, matrix, true);
                  
				} catch (IOException e) {
					Log.d("test", e.getMessage());
				}
				if (drawable == null) {
					Log.d("test", "null drawable");
				} else {
					Log.d("test", "not null drawable");
				}
				((ImageView) detailMenuDialog.this.findViewById(id))
						.setImageBitmap(resizeBmp);
			}
		});
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
