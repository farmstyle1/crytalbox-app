package app.wind.crystalbox.Table;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import app.wind.crystalbox.DB.MyDBHelper;
import app.wind.crystalbox.R;

public class TableManagement extends Activity {
    MyDBHelper dbHelper;
    SQLiteDatabase db;
    ArrayList<String> tableList;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_management);
        btnAdd = (Button)findViewById(R.id.addTable);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TableManagement.this, TableAdd.class);
                startActivity(intent);
                finish();
            }
        });






        ListView listView = (ListView)findViewById(R.id.listTable);
        dbHelper = new MyDBHelper(this);
        db = dbHelper.getWritableDatabase();
        tableList = dbHelper.SelectTable();

            final ArrayAdapter<String> adapterDir = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tableList);

            listView.setAdapter(adapterDir);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    final String tableName = adapterDir.getItem(position).toString();
                    AlertDialog.Builder builder = new AlertDialog.Builder(TableManagement.this);
                    builder.setTitle(getString(R.string.delete_table_title));
                    builder.setMessage(getString(R.string.delete_table_message));
                    builder.setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dbHelper.deleteTable(tableName);
                            finish();

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
        getMenuInflater().inflate(R.menu.menu_table_management, menu);
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
