package com.example.bookfood.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bookfood.metadata.bookfoodDatabaseMetadata;

/**
 * 构建数据库
 * 
 * @Project BookFood
 * @Package com.example.bookfood.database
 * @Class dataBaseHelper
 * @Date 2012-9-17 21:13:23
 * @author upaman
 * @version
 * @since
 */
public class dataBaseHelper extends SQLiteOpenHelper {

	private static final String TAG = dataBaseHelper.class.getSimpleName();

	public dataBaseHelper(Context context) {
		super(context, bookfoodDatabaseMetadata.DATABASE_NAME, null,
				bookfoodDatabaseMetadata.DATABASE_VERSION);
	}

	/**
	 * Creates the table one by one
	 */
	@Override
	public void onCreate(SQLiteDatabase db) throws SQLException{
		// Create the rice table
		db.execSQL("CREATE TABLE IF NOT EXISTS "
				+ bookfoodDatabaseMetadata.RICE_TABLE_NAME + "("
				+ bookfoodDatabaseMetadata.RICE_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ bookfoodDatabaseMetadata.RICE_NAME + " VARCHAR NOT NULL, "
				+ bookfoodDatabaseMetadata.RICE_PIC + " VARCHAR NOT NULL, "
				+ bookfoodDatabaseMetadata.RICE_PRICE + " DOUBLE);");
		// Create the stir table
		db.execSQL("CREATE TABLE IF NOT EXISTS "
				+ bookfoodDatabaseMetadata.STIR_TABLE_NAME + "("
				+ bookfoodDatabaseMetadata.STIR_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ bookfoodDatabaseMetadata.STIR_NAME + " VARCHAR NOT NULL, "
				+ bookfoodDatabaseMetadata.STIR_PIC + " VARCHAR NOT NULL, "
				+ bookfoodDatabaseMetadata.STIR_PRICE + " DOUBLE);");
		// Create the noodle table
		db.execSQL("CREATE TABLE IF NOT EXISTS "
				+ bookfoodDatabaseMetadata.NOODLE_TABLE_NAME + "("
				+ bookfoodDatabaseMetadata.NOODLE_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ bookfoodDatabaseMetadata.NOODLE_NAME + " VARCHAR NOT NULL, "
				+ bookfoodDatabaseMetadata.NOODLE_PIC + " VARCHAR NOT NULL, "
				+ bookfoodDatabaseMetadata.NOODLE_PRICE + " DOUBLE);");
		// Create the beverage table
		db.execSQL("CREATE TABLE IF NOT EXISTS "
				+ bookfoodDatabaseMetadata.BEVERAGE_TABLE_NAME + "("
				+ bookfoodDatabaseMetadata.BEVERAGE_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ bookfoodDatabaseMetadata.BEVERAGE_NAME + " VARCHAR NOT NULL, "
				+ bookfoodDatabaseMetadata.BEVERAGE_PIC + " VARCHAR NOT NULL, "
				+ bookfoodDatabaseMetadata.BEVERAGE_PRICE + " DOUBLE);");

		// Create the Cart table
		db.execSQL("CREATE TABLE IF NOT EXISTS "
				+ bookfoodDatabaseMetadata.CART_TABLE_NAME + "("
				+ bookfoodDatabaseMetadata.ITEM_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ bookfoodDatabaseMetadata.ITEM_NAME + " VARCHAR(50), "
				+ bookfoodDatabaseMetadata.ITEM_NUM + " INTEGER, "
				+ bookfoodDatabaseMetadata.ITEM_PRICE + " DOUBLE);");
		
	}

	/**
	 * This is executed when newVersion >= oldVersion.<br/>
	 * Here I choose a simple way to upgrade, which drops the old table(destroys
	 * old data), and re-creates the table.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		// delete the tables
		db.execSQL("DROP TABLE IF EXISTS "
				+ bookfoodDatabaseMetadata.RICE_TABLE_NAME
				+";");
		db.execSQL("DROP TABLE IF EXISTS "
				+ bookfoodDatabaseMetadata.STIR_TABLE_NAME
				+";");
		db.execSQL("DROP TABLE IF EXISTS "
				+ bookfoodDatabaseMetadata.NOODLE_TABLE_NAME
				+";");
		db.execSQL("DROP TABLE IF EXISTS "
				+ bookfoodDatabaseMetadata.BEVERAGE_TABLE_NAME
				+";");
		db.execSQL("DROP TABLE IF EXISTS "
				+ bookfoodDatabaseMetadata.CART_TABLE_NAME
				+";");

		// rebuild the tables
		onCreate(db);
	}

	/**
	 * Execute the query order
	 */
	public Cursor query(String sql, String[] args) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, args);
		return cursor;
	}

}
