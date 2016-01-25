package app.wind.crystalbox.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.wind.crystalbox.Models.BillItemModel;
import app.wind.crystalbox.Models.BillModel;
import app.wind.crystalbox.Models.ItemModel;
import app.wind.crystalbox.Models.OptionModel;
import app.wind.crystalbox.Models.PdfItemModel;
import app.wind.crystalbox.Models.QueueItemModel;

/**
 * Created by Miki on 4/28/2015.
 */
public class MyDBHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private static final String DB_NAME = "crystalbox";
    private static final int DB_VERSION = 1;

    // Price Table
    public static final String TABLE_PRICE = "price";
    public static final String PRICE_ID = "id";
    public static final String PRICE_NAME = "pName";
    public static final String PRICE_PRICE = "pPrice";
    public static final String PRICE_TYPE = "pType";
    // Bill Table
    public static final String TABLE_BILL = "bill";
    public static final String BILL_ID = "id";
    public static final String BILL_DATE = "bDate";
    public static final String BILL_QUEUE = "bQueue";
    public static final String BILL_STATUS = "bStatus";
    public static final String BILL_TOTAL = "bTotal";
    // Table Table
    public static final String TABLE_TABLE = "tablelist";
    public static final String TABLE_ID = "id";
    public static final String TABLE_NAME = "tName";
    // Table Item
    public static final String TABLE_ITEM = "item";
    public static final String ITEM_ID = "id";
    public static final String ITEM_NAME = "iName";
    public static final String ITEM_OPTION = "iOption";
    public static final String ITEM_BILL = "iBill";
    public static final String ITEM_STATUS = "iStatus";
    public static final String ITEM_PRICE = "iPrice";
    public static final String ITEM_QTY = "iQty";


    public static String TAG = "Simple";

    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PRICE + "( " + PRICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PRICE_NAME + " TEXT," + PRICE_PRICE + " INTEGER," + PRICE_TYPE + " TEXT);");
        db.execSQL("CREATE TABLE " + TABLE_BILL + "( " + BILL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                BILL_DATE + " TEXT," + BILL_QUEUE + " TEXT," + BILL_STATUS + " TEXT," + BILL_TOTAL + " TEXT);");
        db.execSQL("CREATE TABLE " + TABLE_TABLE + "( " + TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TABLE_NAME + " TEXT);");
        db.execSQL("CREATE TABLE " + TABLE_ITEM + "( " + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ITEM_NAME + " TEXT," + ITEM_OPTION + " TEXT," + ITEM_BILL + " TEXT," + ITEM_STATUS + " TEXT," +
                ITEM_PRICE + " INTEGER," + ITEM_QTY + " INTEGER);");
        Log.i(TAG, "Create Table ");


        // ContentValues cv = new ContentValues();
        // cv.put(COL_NAME, "Latte Hot");
        // cv.put(COL_PRICE, 50);
        // db.insert(TABLE_NAME,null,cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        switch (oldVersion) {

            //case 1:Log.i(TAG,"Add Type");

        }


    }

    public void addProduct(String product, Integer price, String type) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PRICE_NAME, product);
        values.put(PRICE_PRICE, price);
        values.put(PRICE_TYPE, type);
        db.insert(TABLE_PRICE, null, values);
        db.close();


    }

    public void deleteProduct(String id) {
        db = this.getWritableDatabase();
        db.delete(TABLE_PRICE, "id =" + id, null);

        db.close();


    }

    public ArrayList<HashMap<String, String>> SelectAllData(String type) {
        // TODO Auto-generated method stub

        try {

            ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;
            db = this.getReadableDatabase(); // Read Data
            Cursor cursor = db.rawQuery("SELECT " + PRICE_ID + "," + PRICE_NAME + "," + PRICE_PRICE + " FROM "
                    + TABLE_PRICE + " WHERE pType ='" + type + "'", null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<String, String>();
                        map.put("ID", cursor.getString(0));
                        map.put("Name", cursor.getString(1));
                        map.put("Price", cursor.getString(2));
                        MyArrList.add(map);

                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return MyArrList;

        } catch (Exception e) {
            return null;
        }

    }

    public void addTable(String table) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TABLE_NAME, table);
        db.insert(TABLE_TABLE, null, values);

        db.close();


    }


    public ArrayList<String> SelectTable() {
        try {
            ArrayList<String> MyArrList = new ArrayList<String>();
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT " + TABLE_NAME + " FROM " + TABLE_TABLE, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        MyArrList.add(cursor.getString(cursor.getColumnIndex(TABLE_NAME)));
                    } while (cursor.moveToNext());
                }

            }
            cursor.close();
            db.close();
            return MyArrList;

        } catch (Exception e) {
            return null;
        }

    }

    public String[] SelectOption() {
        try {
            String arrData[] = null;
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT " + PRICE_NAME + " FROM " + TABLE_PRICE + " WHERE " + PRICE_TYPE + " = 'Option'", null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()];
                    int i = 0;
                    do {
                        arrData[i] = cursor.getString(0);
                        i++;
                    } while (cursor.moveToNext());

                }
            }
            cursor.close();
            db.close();
            return arrData;

        } catch (Exception e) {
            return null;
        }

    }

    public void deleteTable(String tablename) {
        db = this.getWritableDatabase();
        db.delete(TABLE_TABLE, TABLE_NAME + " = '" + tablename + "'", null);

        db.close();


    }

    public String[][] SelectAllTypeItem(String type) {
        // TODO Auto-generated method stub

        try {
            String arrData[][] = null;
            db = this.getReadableDatabase(); // Read Data

            String strSQL = "SELECT * FROM " + TABLE_PRICE + " WHERE pType ='" + type + "'";
            Cursor cursor = db.rawQuery(strSQL, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()][cursor.getColumnCount()];
                    /***
                     *  [x][0] = ID
                     *  [x][1] = Name
                     *  [x][2] = Price
                     */
                    int i = 0;
                    do {
                        arrData[i][0] = cursor.getString(0);
                        arrData[i][1] = cursor.getString(1);
                        arrData[i][2] = cursor.getString(2);
                        arrData[i][3] = cursor.getString(3);
                        i++;
                    } while (cursor.moveToNext());

                }
            }
            cursor.close();
            db.close();
            return arrData;

        } catch (Exception e) {
            return null;
        }

    }

    public long addBill(String date) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BILL_DATE, date);
        values.put(BILL_STATUS, "Done");
        values.put(BILL_QUEUE, "No Table");
        long id = db.insert(TABLE_BILL, null, values);

        db.close();
        return id;

    }

    public void addItemToBill(String name, String option, String billid, int price, int qty) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ITEM_NAME, name);
        values.put(ITEM_OPTION, option);
        values.put(ITEM_BILL, billid);
        values.put(ITEM_STATUS, "Serve");
        values.put(ITEM_PRICE, price);
        values.put(ITEM_QTY, qty);
        db.insert(TABLE_ITEM, null, values);

        db.close();


    }

    public String[][] SelectBillItem(String billid) {
        // TODO Auto-generated method stub

        try {
            String arrData[][] = null;
            db = this.getReadableDatabase(); // Read Data

            String strSQL = "SELECT * FROM " + TABLE_ITEM + " WHERE " + ITEM_BILL + " ='" + billid + "'";
            Cursor cursor = db.rawQuery(strSQL, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()][cursor.getColumnCount()];


                    int i = 0;
                    do {
                        arrData[i][0] = cursor.getString(0);
                        arrData[i][1] = cursor.getString(1);
                        arrData[i][2] = cursor.getString(2);
                        arrData[i][5] = cursor.getString(5);
                        i++;
                    } while (cursor.moveToNext());

                }
            }
            cursor.close();
            db.close();
            return arrData;

        } catch (Exception e) {
            return null;
        }

    }

    public void deleteBillItem(int itemid) {
        db = this.getWritableDatabase();
        db.delete(TABLE_ITEM, ITEM_ID + " = '" + itemid + "'", null);

        db.close();


    }

    public void updateBill(String billid, int Total, String Table, String Status) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BILL_TOTAL, Total);
        values.put(BILL_QUEUE, Table);
        values.put(BILL_STATUS, Status);
        db.update(TABLE_BILL, values, BILL_ID + " = " + billid, null);
        db.close();


    }

    public void updateItemBill(int id, String Status) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ITEM_STATUS, Status);
        db.update(TABLE_ITEM, values, ITEM_ID + " = " + id, null);

        db.close();


    }

    // Best Practic to query Data to ArrayList and use Model Value
    public ArrayList<BillItemModel> getBillItems(String billid) {

        ArrayList<BillItemModel> bmItem = new ArrayList<BillItemModel>();
        db = this.getWritableDatabase();
        String strSQL = "SELECT * FROM " + TABLE_ITEM + " WHERE " + ITEM_BILL + " ='" + billid + "'";
        Cursor cursor = db.rawQuery(strSQL, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    BillItemModel bm = new BillItemModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(5), cursor.getInt(6));


                    bmItem.add(bm);
                } while (cursor.moveToNext());
            }
        }


        cursor.close();
        db.close();

        return bmItem;
    }

    // Best Practic to query Data to ArrayList and use Model Value
    public ArrayList<BillModel> getBill(String date) {

        ArrayList<BillModel> bmNo = new ArrayList<BillModel>();
        db = this.getWritableDatabase();
        String strSQL = "SELECT * FROM " + TABLE_BILL + " WHERE " + BILL_DATE + " ='" + date + "' AND " + BILL_STATUS + " = 'Done' ORDER BY " + BILL_ID + " DESC";
        Cursor cursor = db.rawQuery(strSQL, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    BillModel bm = new BillModel(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));


                    bmNo.add(bm);
                } while (cursor.moveToNext());
            }
        }


        cursor.close();
        db.close();

        return bmNo;
    }

    public ArrayList<BillModel> getBillReport(String date) {

        ArrayList<BillModel> bmNo = new ArrayList<BillModel>();
        db = this.getWritableDatabase();
        String strSQL = "SELECT * FROM " + TABLE_BILL + " WHERE " + BILL_DATE + " ='" + date + "' AND " + BILL_STATUS + " = 'Done' " +
                "OR " + BILL_STATUS + " = 'Pay' ORDER BY " + BILL_ID + " DESC";
        Cursor cursor = db.rawQuery(strSQL, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    BillModel bm = new BillModel(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));


                    bmNo.add(bm);
                } while (cursor.moveToNext());
            }
        }


        cursor.close();
        db.close();

        return bmNo;
    }

    // Best Practic to query Data to ArrayList and use Model Value
    public ArrayList<QueueItemModel> getQueue() {

        ArrayList<QueueItemModel> qModel = new ArrayList<QueueItemModel>();
        db = this.getWritableDatabase();
        String strSQL = "SELECT * FROM " + TABLE_ITEM + " i INNER JOIN " + TABLE_BILL + " b ON i." + ITEM_BILL + " = b." + BILL_ID + " WHERE " +
                ITEM_STATUS + " = 'Serve' ORDER BY " + ITEM_ID;
        Cursor cursor = db.rawQuery(strSQL, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    QueueItemModel qm = new QueueItemModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                            cursor.getString(4), cursor.getInt(5), cursor.getInt(6), cursor.getString(9));
                    qModel.add(qm);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return qModel;
    }

    public ArrayList<QueueItemModel> getServed() {

        ArrayList<QueueItemModel> qModel = new ArrayList<QueueItemModel>();
        db = this.getWritableDatabase();
        String strSQL = "SELECT * FROM " + TABLE_ITEM + " i INNER JOIN " + TABLE_BILL + " b ON i." + ITEM_BILL + " = b." + BILL_ID + " WHERE " +
                ITEM_STATUS + " = 'Done' ORDER BY " + ITEM_ID;
        Cursor cursor = db.rawQuery(strSQL, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    QueueItemModel qm = new QueueItemModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                            cursor.getString(4), cursor.getInt(5), cursor.getInt(6), cursor.getString(9));
                    qModel.add(qm);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return qModel;
    }


    public ArrayList<ItemModel> queryItemType(String type) {

        ArrayList<ItemModel> itemModel = new ArrayList<ItemModel>();
        db = this.getWritableDatabase();
        String strSQL = "SELECT * FROM " + TABLE_PRICE + " WHERE " + PRICE_TYPE + " = '" + type + "'";
        Cursor cursor = db.rawQuery(strSQL, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ItemModel im = new ItemModel(cursor.getString(0), cursor.getString(1), cursor.getInt(2));


                    itemModel.add(im);
                } while (cursor.moveToNext());
            }
        }


        cursor.close();
        db.close();

        return itemModel;
    }

    public ArrayList<OptionModel> queryItemOption() {

        ArrayList<OptionModel> optionModel = new ArrayList<OptionModel>();
        db = this.getWritableDatabase();
        String strSQL = "SELECT * FROM " + TABLE_PRICE + " WHERE " + PRICE_TYPE + " = 'Option'";
        Cursor cursor = db.rawQuery(strSQL, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    OptionModel om = new OptionModel(cursor.getString(0), cursor.getString(1), cursor.getInt(2));


                    optionModel.add(om);
                } while (cursor.moveToNext());
            }
        }


        cursor.close();
        db.close();

        return optionModel;
    }

    // query for export file csv
    public List<String[]> queryItemReport(String date) {

        try {
            List<String[]> MyArrList = new ArrayList<String[]>();
            db = this.getReadableDatabase();
            String strSQL = "SELECT * FROM " + TABLE_ITEM + " i INNER JOIN " + TABLE_BILL + " b ON i." + ITEM_BILL + " = b." + BILL_ID + " WHERE " +
                    BILL_DATE + " = '" + date + "'  ORDER BY " + ITEM_ID;
            Cursor cursor = db.rawQuery(strSQL, null);
            //Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BILL + " WHERE " + BILL_DATE + " = '" + date + "'", null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        //MyArrList.add(cursor.getInt(cursor.getColumnIndex(BILL_ID)),cursor.getString(cursor.getColumnIndex(BILL_QUEUE)));
                        MyArrList.add(new String[]{cursor.getString(3),
                                cursor.getString(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(6),
                                cursor.getString(5)});

                    } while (cursor.moveToNext());
                }

            }
            cursor.close();
            db.close();
            return MyArrList;

        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<PdfItemModel> queryPdfReport(String date) {


        ArrayList<PdfItemModel> pdfModel = new ArrayList<PdfItemModel>();
        db = this.getReadableDatabase();
        String strSQL = "SELECT * FROM " + TABLE_ITEM + " i INNER JOIN " + TABLE_BILL + " b ON i." + ITEM_BILL + " = b." + BILL_ID + " WHERE " +
                BILL_DATE + " = '" + date + "' AND " + BILL_STATUS + " = 'Pay' ORDER BY " + ITEM_ID;
        Cursor cursor = db.rawQuery(strSQL, null);
        //Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BILL + " WHERE " + BILL_DATE + " = '" + date + "'", null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    PdfItemModel pdfm = new PdfItemModel(cursor.getInt(3),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getInt(6),
                            cursor.getInt(5));


                    pdfModel.add(pdfm);
                } while (cursor.moveToNext());
            }

        }
        cursor.close();
        db.close();
        return pdfModel;


    }

}
