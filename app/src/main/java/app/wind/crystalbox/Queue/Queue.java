package app.wind.crystalbox.Queue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import app.wind.crystalbox.CustomAdapter.QueueAdapter;
import app.wind.crystalbox.DB.MyDBHelper;
import app.wind.crystalbox.Models.QueueItemModel;
import app.wind.crystalbox.R;


public class Queue extends Activity {

    MyDBHelper dbHelper;
    ArrayList<QueueItemModel> Queue;
    ListView listViewQueue;
    QueueAdapter queueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);


        Button servedBtn = (Button) findViewById(R.id.servedBtn);
        servedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Queue.this, Served.class);
                startActivity(intent);
            }
        });

        dbHelper = new MyDBHelper(Queue.this);
        Queue = dbHelper.getQueue();
        if (Queue != null) {

            queueAdapter = new QueueAdapter(Queue.this, Queue);
            listViewQueue = (ListView) findViewById(R.id.listViewQueue);
            listViewQueue.setAdapter(queueAdapter);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_queue, menu);
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
