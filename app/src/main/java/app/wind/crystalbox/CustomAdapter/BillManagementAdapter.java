package app.wind.crystalbox.CustomAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import app.wind.crystalbox.Models.BillModel;
import app.wind.crystalbox.Order.OrderBill;
import app.wind.crystalbox.R;

/**
 * Created by Miki on 6/23/2015.
 */
public class BillManagementAdapter extends BaseAdapter {


    Context mContext;
    ArrayList<BillModel> billno;
    MyDBHelper dbHelper;



    public BillManagementAdapter(Context context, ArrayList<BillModel> billno) {
        this.billno = billno;
        mContext = context;


    }

    @Override
    public int getCount() {
        return billno.size();
    }

    @Override
    public Object getItem(int position) {
        return billno.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView txtId;
        TextView txtTable;
        Button btnEditBill;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        dbHelper = new MyDBHelper(mContext);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {


            convertView = inflater.inflate(R.layout.column_bill_management, null);
            holder = new ViewHolder();


            holder.txtId = (TextView) convertView.findViewById(R.id.billID);
            holder.txtTable = (TextView) convertView.findViewById(R.id.billTable);
            holder.btnEditBill = (Button) convertView.findViewById(R.id.btnEditBill);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

            BillModel b = billno.get(position);

            holder.txtId.setText("บิลที่     "+b.getId());
            holder.txtTable.setText(b.getTable());

            final String billid = b.getId();
            final String table = b.getTable();

            holder.btnEditBill.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, OrderBill.class);
                    intent.putExtra("billid", billid);
                    intent.putExtra("table", table);
                    mContext.startActivity(intent);
                    ((Activity)mContext).finish();
                }
            });











        return convertView;
    }


}
