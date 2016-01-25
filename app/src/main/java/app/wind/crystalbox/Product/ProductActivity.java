package app.wind.crystalbox.Product;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import app.wind.crystalbox.DB.MyDBHelper;
import app.wind.crystalbox.R;


public class ProductActivity extends Activity {

    MyDBHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;
    public static String TAG = "Simple";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ListView listView1 = (ListView) findViewById(R.id.list1);

        dbHelper = new MyDBHelper(this);
        db = dbHelper.getWritableDatabase();

        cursor = db.rawQuery("SELECT " + dbHelper.PRICE_NAME + "," + dbHelper.PRICE_PRICE + " FROM "
                + dbHelper.TABLE_PRICE,null);
        Log.i(TAG, "Query");
        ArrayList<String> listArray = new ArrayList<String>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            listArray.add(cursor.getString(cursor.getColumnIndex(dbHelper.PRICE_NAME))
                    +"\n"+"Price :"+cursor.getString(cursor.getColumnIndex(dbHelper.PRICE_PRICE)));
            cursor.moveToNext();
        }

        ArrayAdapter<String> adapterDir = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listArray);
        listView1.setAdapter(adapterDir);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
