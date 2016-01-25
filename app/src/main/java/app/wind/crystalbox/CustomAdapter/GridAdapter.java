package app.wind.crystalbox.CustomAdapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import app.wind.crystalbox.Models.ItemModel;
import app.wind.crystalbox.Models.QueueItemModel;
import app.wind.crystalbox.R;

/**
 * Created by Miki on 6/12/2015.
 */
public class GridAdapter extends BaseAdapter {

    Context mContext;
    //String[][] item;
    ArrayList<ItemModel> item;
    String Type;

    public GridAdapter(Context c,ArrayList<ItemModel> item,String Type) {
        mContext = c;
        this.item = item;
        this.Type = Type;

    }


    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_layout, null);
            TextView textItem = (TextView) grid.findViewById(R.id.grid_text);
            ItemModel im = item.get(position);
            textItem.setText(im.getName());




            switch (Type){
                case "Hot":
                    textItem.setBackgroundColor(mContext.getResources().getColor(R.color.Hot));
                    break;
                case "Ice":
                    textItem.setBackgroundColor(mContext.getResources().getColor(R.color.Ice));
                    break;
                case "Frappe":
                    textItem.setBackgroundColor(mContext.getResources().getColor(R.color.Frappe));
                    break;
                case "Cake":
                    textItem.setBackgroundColor(mContext.getResources().getColor(R.color.Cake));
                    break;
                case "Food":
                    textItem.setBackgroundColor(mContext.getResources().getColor(R.color.Food));
                    break;
                case "Alcohol":
                    textItem.setBackgroundColor(mContext.getResources().getColor(R.color.Alcohol));
                    break;
                case "Other":
                    textItem.setBackgroundColor(mContext.getResources().getColor(R.color.Other));
                    break;
            }


           // textItem.setBackgroundColor(mContext.getResources().getIdentifier(Type,"color",mContext.getPackageName())); ใช้ ตัวแปร ใน R.id

        } else {
            grid = (View) convertView;
        }
        return grid;
    }
}
