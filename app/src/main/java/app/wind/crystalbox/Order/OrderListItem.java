package app.wind.crystalbox.Order;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.ArrayList;

import app.wind.crystalbox.CustomAdapter.DialogListviewAdapter;
import app.wind.crystalbox.CustomAdapter.GridAdapter;
import app.wind.crystalbox.DB.MyDBHelper;
import app.wind.crystalbox.Models.ItemModel;
import app.wind.crystalbox.Models.OptionModel;
import app.wind.crystalbox.R;

public class OrderListItem extends Fragment {

    GridView gridItem;
    MyDBHelper dbHelper;
    SQLiteDatabase db;
    //String[][] itemList;
    //String[][] itemOption;
    ArrayList<ItemModel> itemQuery;
    ArrayList<OptionModel> optionQuery;
    String item;
    String select = "";
    int qty=0;
    int pprice = 0;
    int itemprice = 0;

    @Override // Override method ของ Fragment มา ใช้ onCreateView
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {


        Bundle bundle = this.getArguments(); // รับตัวแปรมาจาก Bundle
        String Type = bundle.getString("type");
        final String billid = bundle.getString("billid");


        dbHelper = new MyDBHelper(getActivity()); // สร้าง Object class MyDBHelper ขึ้นมา
        //itemList = dbHelper.SelectAllTypeItem(Type); // เรียกใช้ Method SelectAllData ใน class MyDBHelper
        itemQuery = dbHelper.queryItemType(Type);
        //itemOption = dbHelper.SelectOption(); อันเก่า  ดึงแค่ ชื่อ option มาแล้วส่งไปยัง DialogAdapter
        //itemOption = dbHelper.SelectAllTypeItem("Option"); // อันใหม่ ดึงข้อมูล option มาทั้งหมด
        optionQuery = dbHelper.queryItemOption();


        View rootView = inflater.inflate(R.layout.activity_order_list_item, container, false); // เชื่อมเข้ากับ xml ของตัวเอง เหมือนๆ setContentView


        if (itemQuery != null) {
            // Update CustomAdpter Gridview
            GridAdapter adapter = new GridAdapter(getActivity(), itemQuery, Type);
            gridItem = (GridView) rootView.findViewById(R.id.GridItem);
            gridItem.setAdapter(adapter);

            gridItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    ItemModel im = itemQuery.get(position);
                    item = im.getName();
                    itemprice = im.getPrice();


                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    final View dialogView = inflater.inflate(R.layout.dialog_custom_listview_layout, container, false);


                    final ListView dialogListview = (ListView) dialogView.findViewById(R.id.listViewDialog);
                    final EditText etxtOption = (EditText) dialogView.findViewById(R.id.etxtOtherOption);


                    DialogListviewAdapter customDialog = new DialogListviewAdapter(getActivity(), optionQuery);
                    dialogListview.setAdapter(customDialog);
                    dialogListview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    dialogListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkBoxOption);
                            checkbox.setChecked(!checkbox.isChecked());  //  เมื่อ click ที่ตัว List  ให้ checkbox  ติ๊กเครื่องหมาย


                            int cchoice = dialogListview.getCount();
                            SparseBooleanArray sparseBooleanArray = dialogListview.getCheckedItemPositions();  // เช็คดูว่า List เลือก Item ไหนบ้าง
                            pprice = 0;
                            select = "";
                            for (int i = 0; i < cchoice; i++) {
                                if (sparseBooleanArray.get(i)) {
                                    OptionModel om = optionQuery.get(i);
                                    select += om.getName() + "\n";
                                    pprice += om.getPrice();

                                }
                            }


                        }
                    });


                    builder.setTitle(getString(R.string.choose_option));
                    builder.setView(dialogView);

                    final NumberPicker np = (NumberPicker)dialogView.findViewById(R.id.numberpicker1);
                    np.setMaxValue(100);
                    np.setMinValue(1);

                    builder.setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            itemprice += pprice;
                            select += etxtOption.getText().toString();
                            qty = np.getValue();
                            itemprice = itemprice*qty;
                            //Toast.makeText(getActivity(), String.valueOf(np.getValue()), Toast.LENGTH_SHORT).show();
                            dbHelper = new MyDBHelper(getActivity());
                            dbHelper.addItemToBill(item, select, billid, itemprice,qty);
                            itemprice = 0;
                            pprice = 0;
                            qty = 0;
                            select = "";

                        }
                    });
                    builder.setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            itemprice = 0;
                            pprice = 0;
                            qty = 0;
                            select = "";
                            dialog.cancel();
                        }
                    });

                    builder.create();
                    builder.show();


                }
            });
        }
        return rootView;
    }

}
