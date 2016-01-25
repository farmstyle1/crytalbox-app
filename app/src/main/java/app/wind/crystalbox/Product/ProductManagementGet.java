package app.wind.crystalbox.Product;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import app.wind.crystalbox.DB.MyDBHelper;
import app.wind.crystalbox.R;


public class ProductManagementGet extends Activity {

    MyDBHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;
    ArrayList<HashMap<String, String>> ItemList;


    private Button btnAdd;
    private static final String[] Action =
            {"Edit", "Delete"};
    public static String type;
    public static Integer pId;
    public static String TAG = "Simple";
    public static String select;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management_get);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = bundle.getString("type");

        }
        setTitle(type);
        btnAdd = (Button) findViewById(R.id.addProduct);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductManagementGet.this, ProductAdd.class);
                intent.putExtra("type", type);
                startActivity(intent);
                finish();
            }
        });
        ListView listView = (ListView) findViewById(R.id.listProduct);
        dbHelper = new MyDBHelper(this);
        db = dbHelper.getWritableDatabase();
        ItemList = dbHelper.SelectAllData(type);


        SimpleAdapter sAdap;
        sAdap = new SimpleAdapter(ProductManagementGet.this, ItemList, R.layout.activity_column,
                new String[]{"ID", "Name", "Price"}, new int[]{R.id.ColID, R.id.ColName, R.id.ColPrice});
        Log.i(TAG, "create adap");
        listView.setAdapter(sAdap);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                select = ItemList.get(position).get("ID").toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(ProductManagementGet.this);
                builder.setItems(Action, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String choose = Action[which];
                        if (choose == "Delete") {
                            dbHelper.deleteProduct(select);
                            finish();
                        }
                        if (choose == "Edit") {
                            Toast.makeText(ProductManagementGet.this, "Edit", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("ยกเลิก", null);
                builder.create();
                builder.show();


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_management_get, menu);
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
