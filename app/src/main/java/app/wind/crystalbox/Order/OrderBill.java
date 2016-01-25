package app.wind.crystalbox.Order;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.wind.crystalbox.CustomAdapter.BillItemAdapter;
import app.wind.crystalbox.CustomAdapter.BillItemAdapter1;
import app.wind.crystalbox.DB.MyDBHelper;
import app.wind.crystalbox.Models.BillItemModel;
import app.wind.crystalbox.Models.Item;
import app.wind.crystalbox.R;

public class OrderBill extends FragmentActivity {

    private Button btnAddItem;
    private EditText etxtTable;
    private Button btnDone;
    private TextView txtBillNo;
    MyDBHelper dbHelper;
    SQLiteDatabase db;
    ArrayList<String> tableList;
    String[] tables;
    String[][] billItem;
    String billid;
    BillItemAdapter1 billAdapter;
    String gettable;

    ListView listViewBill;
    ArrayList<BillItemModel> Billlist;
    int total=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_bill);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            billid = bundle.getString("billid");
            gettable = bundle.getString("table");
        }
        if(gettable!=null){
            etxtTable = (EditText)findViewById(R.id.etxtTable);
            etxtTable.setText(gettable);

        }


        TextView txtBillNo = (TextView) findViewById(R.id.txtBillNo);
        txtBillNo.setText(billid);
        etxtTable = (EditText) findViewById(R.id.etxtTable);

        Billlist = new ArrayList<BillItemModel>();
        dbHelper = new MyDBHelper(OrderBill.this);
        Billlist = dbHelper.getBillItems(billid);






       if (Billlist != null) {


            BillItemAdapter billAdapter = new BillItemAdapter(OrderBill.this, Billlist); // TESTADD
            //BillItemAdapter billAdapter = new BillItemAdapter(OrderBill.this,billItem);
            listViewBill = (ListView) findViewById(R.id.listBillItem);
            listViewBill.setAdapter(billAdapter);






        }

        Button btnPay = (Button)findViewById(R.id.btnPay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                total = 0;
                int i = 0;
                for (i = 0; i < Billlist.size(); i++) {
                    BillItemModel b = Billlist.get(i);
                    total += b.getPrice();
                }
                final String getTable = etxtTable.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderBill.this);
                builder.setTitle(getString(R.string.pay_title));
                builder.setMessage("\nที่นั่ง " + getTable + "\n\nยอดรวม " + String.valueOf(total) + " บาท\n");
                builder.setPositiveButton("ชำระรายการ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper = new MyDBHelper(getApplicationContext());
                        dbHelper.updateBill(billid,total,getTable,"Pay");
                        finish();

                    }
                });
                builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();


            }
        });

        btnDone = (Button) findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String getTable = etxtTable.getText().toString();
                    dbHelper = new MyDBHelper(getApplicationContext());
                    dbHelper.updateBill(billid, 0, getTable, "Done");
                    finish();



            }
        });






        btnAddItem = (Button) findViewById(R.id.btnADD);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getTable = etxtTable.getText().toString();
                Intent intent = new Intent(OrderBill.this, OrderAddItem.class);
                intent.putExtra("billid", billid);
                intent.putExtra("table", getTable);
                startActivity(intent);
                finish();
            }
        });




        etxtTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new MyDBHelper(OrderBill.this);
                db = dbHelper.getWritableDatabase();
                tableList = dbHelper.SelectTable();
                tables = tableList.toArray(new String[tableList.size()]); // เปลี่ยน Object ให้เป็น Array
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderBill.this);
                builder.setTitle(getString(R.string.choose_table));
                builder.setItems(tables, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selected = tables[which];
                        etxtTable.setText(selected);


                    }
                });
                builder.setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order_bill, menu);
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
