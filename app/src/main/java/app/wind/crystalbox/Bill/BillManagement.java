package app.wind.crystalbox.Bill;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import app.wind.crystalbox.CustomAdapter.BillManagementAdapter;
import app.wind.crystalbox.DB.MyDBHelper;
import app.wind.crystalbox.Models.BillItemModel;
import app.wind.crystalbox.Models.BillModel;
import app.wind.crystalbox.R;

public class BillManagement extends Activity {

    MyDBHelper dbHelper;
    ArrayList<BillModel> BillNo;
    String date;
    ListView listViewBillDate;
    BillManagementAdapter billAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_management);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            date = bundle.getString("date");

        }


        dbHelper = new MyDBHelper(BillManagement.this);
        BillNo = dbHelper.getBill(date);

        if (BillNo != null) {

            BillManagementAdapter billAdapter = new BillManagementAdapter(BillManagement.this,BillNo);
            listViewBillDate = (ListView)findViewById(R.id.listViewBillDate);
            listViewBillDate.setAdapter(billAdapter);

        }



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bill_management, menu);
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
