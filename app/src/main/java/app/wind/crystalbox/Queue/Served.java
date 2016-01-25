package app.wind.crystalbox.Queue;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import app.wind.crystalbox.CustomAdapter.ServedAdapter;
import app.wind.crystalbox.DB.MyDBHelper;
import app.wind.crystalbox.Models.QueueItemModel;
import app.wind.crystalbox.R;

public class Served extends Activity {
    private MyDBHelper dbHelper;
    private ArrayList<QueueItemModel> Served;
    private ListView listViewServed;
    private ServedAdapter ServedAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_served);
        dbHelper = new MyDBHelper(Served.this);
        Served = dbHelper.getServed();
        if(Served != null){
            ServedAdapter = new ServedAdapter(Served.this,Served);
            listViewServed = (ListView)findViewById(R.id.listViewServed);
            listViewServed.setAdapter(ServedAdapter);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_served, menu);
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
