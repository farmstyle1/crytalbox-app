package app.wind.crystalbox.CustomAdapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.wind.crystalbox.Models.OptionModel;
import app.wind.crystalbox.Models.QueueItemModel;
import app.wind.crystalbox.R;

/**
 * Created by Miki on 6/21/2015.
 */
public class DialogListviewAdapter extends BaseAdapter {
    //String[][] option;
    ArrayList<OptionModel> option;
    Context mContext;

    public DialogListviewAdapter(Context context,ArrayList<OptionModel> option) {
        this.option = option;
        mContext = context;


    }

    @Override
    public int getCount() {
        return option.size();
    }

    @Override
    public Object getItem(int position) {
        return option.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        CheckBox checkbox;
        TextView optionname;
        Button btnEditBill;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null) {


            convertView = inflater.inflate(R.layout.dialog_custom_layout, null);
            holder = new ViewHolder();
            holder.checkbox = (CheckBox) convertView.findViewById(R.id.checkBoxOption);
            holder.optionname = (TextView) convertView.findViewById(R.id.textViewOption);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
            OptionModel om = option.get(position);
            holder.optionname.setText(om.getName());
        return convertView;
    }
}
