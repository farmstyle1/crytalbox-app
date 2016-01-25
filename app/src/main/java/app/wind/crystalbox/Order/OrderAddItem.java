package app.wind.crystalbox.Order;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import app.wind.crystalbox.CustomAdapter.BillItemAdapter;
import app.wind.crystalbox.R;

public class OrderAddItem extends FragmentActivity {

    private Button btnHot;
    private Button btnIce;
    private Button btnFrappe;
    private Button btnCake;
    private Button btnFood;
    private Button btnAlcohol;
    private Button btnOther;
    private Button btnDone;
    String billid;
    String gettable;
    BillItemAdapter billItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_add_item);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            billid = bundle.getString("billid");
            gettable = bundle.getString("table");


        }


        btnHot = (Button) findViewById(R.id.hot);
        btnHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OrderListItem fragmentOrderListItem = new OrderListItem();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.line3, fragmentOrderListItem);
                transaction.commit();
                Bundle bundle = new Bundle();
                bundle.putString("type", "Hot");
                bundle.putString("billid", billid);
                fragmentOrderListItem.setArguments(bundle);
                //Toast.makeText(getApplication(),"1",Toast.LENGTH_SHORT).show();

            }
        });

        btnIce = (Button) findViewById(R.id.ice);
        btnIce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OrderListItem fragmentOrderListItem = new OrderListItem();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.line3, fragmentOrderListItem);
                transaction.commit();
                Bundle bundle = new Bundle();
                bundle.putString("type", "Ice");
                bundle.putString("billid", billid);
                fragmentOrderListItem.setArguments(bundle);
                //Toast.makeText(getApplication(),"2",Toast.LENGTH_SHORT).show();

            }
        });

        btnFrappe = (Button) findViewById(R.id.frappe);
        btnFrappe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OrderListItem fragmentOrderListItem = new OrderListItem();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.line3, fragmentOrderListItem);
                transaction.commit();
                Bundle bundle = new Bundle();
                bundle.putString("type", "Frappe");
                bundle.putString("billid", billid);
                fragmentOrderListItem.setArguments(bundle);
                //Toast.makeText(getApplication(),"2",Toast.LENGTH_SHORT).show();

            }
        });

        btnCake = (Button) findViewById(R.id.cake);
        btnCake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OrderListItem fragmentOrderListItem = new OrderListItem();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.line3, fragmentOrderListItem);
                transaction.commit();
                Bundle bundle = new Bundle();
                bundle.putString("type", "Cake");
                bundle.putString("billid", billid);
                fragmentOrderListItem.setArguments(bundle);
                //Toast.makeText(getApplication(),"2",Toast.LENGTH_SHORT).show();

            }
        });

        btnFood = (Button) findViewById(R.id.food);
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OrderListItem fragmentOrderListItem = new OrderListItem();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.line3, fragmentOrderListItem);
                transaction.commit();
                Bundle bundle = new Bundle();
                bundle.putString("type", "Food");
                bundle.putString("billid", billid);
                fragmentOrderListItem.setArguments(bundle);
                //Toast.makeText(getApplication(),"2",Toast.LENGTH_SHORT).show();

            }
        });

        btnAlcohol = (Button) findViewById(R.id.alcohal);
        btnAlcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OrderListItem fragmentOrderListItem = new OrderListItem();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.line3, fragmentOrderListItem);
                transaction.commit();
                Bundle bundle = new Bundle();
                bundle.putString("type", "Alcohol");
                bundle.putString("billid", billid);
                fragmentOrderListItem.setArguments(bundle);
                //Toast.makeText(getApplication(),"2",Toast.LENGTH_SHORT).show();

            }
        });

        btnOther = (Button) findViewById(R.id.other);
        btnOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OrderListItem fragmentOrderListItem = new OrderListItem();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.line3, fragmentOrderListItem);
                transaction.commit();
                Bundle bundle = new Bundle();
                bundle.putString("type", "Other");
                bundle.putString("billid", billid);
                fragmentOrderListItem.setArguments(bundle);
                //Toast.makeText(getApplication(),"2",Toast.LENGTH_SHORT).show();

            }
        });

        btnDone = (Button)findViewById(R.id.addItemDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderAddItem.this, OrderBill.class);
                intent.putExtra("billid",billid);
                intent.putExtra("table",gettable);
                startActivity(intent);
                finish();
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order_add_item, menu);
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
