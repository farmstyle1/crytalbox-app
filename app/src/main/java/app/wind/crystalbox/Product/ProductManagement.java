package app.wind.crystalbox.Product;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import app.wind.crystalbox.R;


public class ProductManagement extends Activity {
    private Button btnHot;
    private Button btnIce;
    private Button btnFrappe;
    private Button btnCake;
    private Button btnFood;
    private Button btnAlcohal;
    private Button btnOther;
    private Button btnOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management);


        btnHot = (Button)findViewById(R.id.hot);
        btnHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductManagement.this, ProductManagementGet.class);
                intent.putExtra("type", "Hot");
                startActivity(intent);
            }
        });

        btnIce = (Button)findViewById(R.id.ice);
        btnIce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductManagement.this,ProductManagementGet.class);
                intent.putExtra("type","Ice");
                startActivity(intent);
            }
        });

        btnFrappe = (Button)findViewById(R.id.frappe);
        btnFrappe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductManagement.this,ProductManagementGet.class);
                intent.putExtra("type","Frappe");
                startActivity(intent);
            }
        });

        btnCake = (Button)findViewById(R.id.cake);
        btnCake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductManagement.this,ProductManagementGet.class);
                intent.putExtra("type","Cake");
                startActivity(intent);
            }
        });

        btnFood = (Button)findViewById(R.id.food);
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductManagement.this,ProductManagementGet.class);
                intent.putExtra("type","Food");
                startActivity(intent);
            }
        });

        btnAlcohal = (Button)findViewById(R.id.alcohal);
        btnAlcohal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductManagement.this,ProductManagementGet.class);
                intent.putExtra("type","Alcohol");
                startActivity(intent);
            }
        });

        btnOther = (Button)findViewById(R.id.other);
        btnOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductManagement.this,ProductManagementGet.class);
                intent.putExtra("type","Other");
                startActivity(intent);
            }
        });

        btnOption = (Button)findViewById(R.id.option);
        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductManagement.this,ProductManagementGet.class);
                intent.putExtra("type","Option");
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_management, menu);
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
