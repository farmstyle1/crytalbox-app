package app.wind.crystalbox.CustomAdapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import app.wind.crystalbox.DB.MyDBHelper;
import app.wind.crystalbox.Models.BillItemModel;
import app.wind.crystalbox.R;

/**
 * Created by Miki on 6/23/2015.
 */
public class BillItemAdapter extends BaseAdapter {


    Context mContext;
    ArrayList<BillItemModel> billist;
    String[][] billItem;
    MyDBHelper dbHelper;
    SQLiteDatabase db;

    int Total= 0;


    public BillItemAdapter(Context context, ArrayList<BillItemModel> billist) {
        this.billist = billist;

        mContext = context;


    }

    @Override
    public int getCount() {
        return billist.size();
    }

    @Override
    public Object getItem(int position) {
        return billist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView txtName;
        TextView txtOption;
        TextView txtPrice;
        TextView txtQty;
        Button btnDel;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        dbHelper = new MyDBHelper(mContext);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {


            convertView = inflater.inflate(R.layout.activity_column_bill, null);
            holder = new ViewHolder();


            holder.txtName = (TextView) convertView.findViewById(R.id.ColName);
            holder.txtOption = (TextView) convertView.findViewById(R.id.ColOption);
            holder.txtPrice = (TextView) convertView.findViewById(R.id.ColPrice);
            holder.txtQty = (TextView)convertView.findViewById(R.id.ColQty);
            holder.btnDel = (Button) convertView.findViewById(R.id.btnDel);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

            final BillItemModel b = billist.get(position);
            holder.txtName.setText(b.getName());
            holder.txtOption.setText(b.getOption());
            holder.txtQty.setText(String.valueOf(b.getQty()));
            holder.txtPrice.setText(String.valueOf(b.getPrice()));





            //txtPrice.setText(String.valueOf(testValue));

            holder.btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dbHelper.deleteBillItem(b.getId());
                    billist.remove(position);
                    notifyDataSetChanged();


                }

            });




        return convertView;
    }


}
