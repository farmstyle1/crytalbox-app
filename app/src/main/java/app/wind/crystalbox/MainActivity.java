package app.wind.crystalbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import app.wind.crystalbox.Bill.BillManagement;
import app.wind.crystalbox.DB.MyDBHelper;
import app.wind.crystalbox.Order.OrderBill;
import app.wind.crystalbox.Product.ProductManagement;
import app.wind.crystalbox.Queue.Queue;
import app.wind.crystalbox.Report.Report;
import app.wind.crystalbox.Table.TableManagement;


public class MainActivity extends Activity {


    MyDBHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;
    ArrayList<String> tableList;
    String[] tables;
    public static String TAG = "Simple";
    public static String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = dateFormat.format(calendar.getTime());


        Button btnOrder = (Button) findViewById(R.id.order);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.crebill_title));
                builder.setMessage("วันที่  " + date);
                builder.setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper = new MyDBHelper(getApplicationContext());
                        String billId = String.valueOf(dbHelper.addBill(date)); // แปลง long เป็น String
                        Intent intent = new Intent(MainActivity.this, OrderBill.class);
                        intent.putExtra("billid", billId);
                        startActivity(intent);

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

        Button btnBill = (Button) findViewById(R.id.bill);
        btnBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BillManagement.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

        Button btnQueue = (Button) findViewById(R.id.queue);
        btnQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Queue.class);
                startActivity(intent);
            }
        });

        Button btnReport = (Button) findViewById(R.id.report);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Report.class);
                startActivity(intent);

            }
        });

        Button btnProduct = (Button) findViewById(R.id.product);
        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProductManagement.class);
                startActivity(intent);
            }
        });

        Button btnTable = (Button) findViewById(R.id.table);
        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TableManagement.class);
                startActivity(intent);
            }
        });


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
