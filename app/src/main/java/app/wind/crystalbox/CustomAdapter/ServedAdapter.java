package app.wind.crystalbox.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import app.wind.crystalbox.DB.MyDBHelper;
import app.wind.crystalbox.Models.QueueItemModel;
import app.wind.crystalbox.R;

/**
 * Created by Miki on 6/23/2015.
 */
public class ServedAdapter extends BaseAdapter {


    Context mContext;
    ArrayList<QueueItemModel> queue;
    MyDBHelper dbHelper;
    String serve = "Serve";
    String done = "Done";



    public ServedAdapter(Context context, ArrayList<QueueItemModel> queue) {
        this.queue = queue;
        mContext = context;


    }

    @Override
    public int getCount() {
        return queue.size();
    }

    @Override
    public Object getItem(int position) {
        return queue.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView txtItem;
        TextView txtOption;
        Button btnStatus;
        TextView txtTable;
        TextView txtQty;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;


        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {


            convertView = inflater.inflate(R.layout.column_queue, null);
            holder = new ViewHolder();


            holder.txtItem = (TextView) convertView.findViewById(R.id.queueItem);
            holder.txtOption = (TextView) convertView.findViewById(R.id.queueOption);
            holder.btnStatus = (Button) convertView.findViewById(R.id.queueStatus);
            holder.txtQty = (TextView)convertView.findViewById(R.id.queueQty);
            holder.txtTable = (TextView) convertView.findViewById(R.id.queueTable);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

            final QueueItemModel b = queue.get(position);
            holder.txtItem.setText(b.getName());
            holder.txtOption.setText(b.getOption());
            holder.txtQty.setText(String.valueOf(b.getQty()));
            holder.btnStatus.setText(b.getStatus());
            holder.txtTable.setText(b.getTable());




            holder.btnStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(b.getStatus().equals(serve)){

                        dbHelper = new MyDBHelper(mContext);
                        dbHelper.updateItemBill(b.getId(),done);
                        queue.remove(position);
                        notifyDataSetChanged();
                    }


                }
            });















        return convertView;
    }


}
