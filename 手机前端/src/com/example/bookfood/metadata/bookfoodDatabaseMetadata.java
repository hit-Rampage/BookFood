package com.example.bookfood.metadata;

/**
 * Meta Data of the bookfood database. <br/>
 * This class provides static information like table name, column names and uri,<br/>
 * so, no need to construct any instance.<br/>
 * 
 * @Project BookFood
 * @Package com.example.boofood.metadata
 * @Class bookfoodDatabaseMetadata
 * @Date 2012-9-24 下午9:36:10
 * @author upaman
 * @version
 * @since
 */
public class bookfoodDatabaseMetadata {
	/**
	 * Database name
	 */
	public static final String DATABASE_NAME = "bookfood.db";
	/**
	 * Database version
	 */
	public static final int DATABASE_VERSION = 1;
	
	/**
	 *Table names
	 */
	public static final String RICE_TABLE_NAME = "rice_table";
	public static final String STIR_TABLE_NAME = "stir_table";
	public static final String NOODLE_TABLE_NAME = "noodle_table";
	public static final String BEVERAGE_TABLE_NAME = "beverage_table";
    public static final String CART_TABLE_NAME = "cart_table";
    public static final String USER_TABLE_NAME = "user_table";

    /**
	 * rice table names
	 */
	public static final String RICE_ID = "rice_id";
	public static final String RICE_NAME = "rice_name";
	public static final String RICE_PRICE = "rice_price";
	public static final String RICE_PIC = "rice_pic";
	/**
	 * stir table names
	 */
	public static final String STIR_ID = "stir_id";
	public static final String STIR_NAME = "stir_name";
	public static final String STIR_PRICE = "stir_price";
	public static final String STIR_PIC = "stir_pic";
	/**
	 * noodle table names
	 */
	public static final String NOODLE_ID = "noodle_id";
	public static final String NOODLE_NAME = "noodle_name";
	public static final String NOODLE_PRICE = "noodle_price";
	public static final String NOODLE_PIC = "noodle_pic";
	/**
	 * beverage table names
	 */
	public static final String BEVERAGE_ID = "beverage_id";
	public static final String BEVERAGE_NAME = "beverage_name";
	public static final String BEVERAGE_PRICE = "beverage_price";
	public static final String BEVERAGE_PIC = "beverage_pic";
	
	/**
	 * CART table names
	 */
	public static final String ITEM_ID = "item_id";
	public static final String ITEM_NAME = "item_name";
	public static final String ITEM_NUM = "item_num";
	public static final String ITEM_PRICE = "item_price";
	/**
	 * User table names
	 */
	public static final String USER_ID = "user_id";
	public static final String USER_NAME = "user_name";
	public static final String USER_PASSWORD = "user_password";
	public static final String USER_PHONE = "user_phone";
	public static final String USER_ADDRESS = "user_address";

	}
