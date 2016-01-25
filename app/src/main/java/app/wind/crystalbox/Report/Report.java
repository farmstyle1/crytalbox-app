package app.wind.crystalbox.Report;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import app.wind.crystalbox.CustomAdapter.BillManagementAdapter;
import app.wind.crystalbox.CustomAdapter.ReportAdapter;
import app.wind.crystalbox.DB.MyDBHelper;
import app.wind.crystalbox.Models.BillModel;
import app.wind.crystalbox.Models.PdfItemModel;
import app.wind.crystalbox.Models.QueueItemModel;
import app.wind.crystalbox.R;

public class Report extends Activity {


    public static String date;
    ArrayList<QueueItemModel> Queue;
    ArrayList<PdfItemModel> itemReport;
    ArrayList<BillModel> BillNo;
    ListView listViewBill;

    MyDBHelper dbHelper;
    PdfWriter writer;
    int total = 0;
    int Qty = 0;
    private Calendar mCalendar;
    private DatePickerDialog mDatePicker;
    private int mYear;
    private int mMonth;
    private int mDay;
    static final int DATE_DIALOG_ID = 0;

    private EditText etxtDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


        etxtDate = (EditText) findViewById(R.id.etxtDate);
        etxtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        mCalendar = Calendar.getInstance();
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        updateCurrentDate();


        Button btnSearch = (Button)findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = etxtDate.getText().toString();
                dbHelper = new MyDBHelper(Report.this);
                BillNo = dbHelper.getBillReport(date);
                if (BillNo != null) {

                    ReportAdapter billAdapter = new ReportAdapter(Report.this,BillNo);
                    listViewBill = (ListView)findViewById(R.id.listViewBill);
                    listViewBill.setAdapter(billAdapter);

                }


            }
        });





        Button btnExport = (Button) findViewById(R.id.btnExport);
        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = etxtDate.getText().toString();
                dbHelper = new MyDBHelper(Report.this);
                itemReport = dbHelper.queryPdfReport(date);

                //File createrDirect = new File("/storage/sdcard0/CrystalBox/");
                //createrDirect.mkdirs();
                String state = Environment.getExternalStorageState();
                if (Environment.MEDIA_MOUNTED.equals(state)) {


                    final File extStorage = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Crystalbox");
                    if (!extStorage.mkdirs()) {
                        Log.i("TAG", "Directory not created");

                    }
                    new Thread(new Runnable() {
                        @Override
                        public void run() {


                            try {
                                BaseFont bf = BaseFont.createFont("assets/font/THSarabun.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                                Font font = new Font(bf, 12);
                                Document document = new Document();
                                PdfWriter.getInstance(document, new FileOutputStream(extStorage.toString() + "/" + date + ".pdf"));
                                //ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase(date), document.right(), document.top(), 0);
                                document.open();


                                Paragraph p = new Paragraph("CrystalBox", font);
                                p.setAlignment(Element.ALIGN_CENTER);
                                Paragraph paragraph = new Paragraph(" ");
                                Paragraph subDate = new Paragraph("วันที่ " + date, font);



                                PdfPTable table = new PdfPTable(6);
                                table.setWidthPercentage(100);
                                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);


                                PdfPCell col = new PdfPCell(new Phrase("ลำดับ", font));
                                col.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table.addCell(col);

                                PdfPCell col1 = new PdfPCell(new Phrase("เลขบิล", font));
                                col1.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table.addCell(col1);


                                PdfPCell col2 = new PdfPCell(new Phrase("สินค้า", font));
                                col2.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table.addCell(col2);

                                PdfPCell col3 = new PdfPCell(new Phrase("Option", font));
                                col3.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table.addCell(col3);

                                PdfPCell col4 = new PdfPCell(new Phrase("จำนวน", font));
                                col4.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table.addCell(col4);

                                PdfPCell col5 = new PdfPCell(new Phrase("ราคา", font));
                                col5.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table.addCell(col5);
                                int i = 0;
                                for (i = 0; i < itemReport.size(); i++) {


                                    PdfItemModel b = itemReport.get(i);
                                    total += b.getPrice();
                                    Qty += b.getQty();

                                    table.addCell(String.valueOf(i + 1));
                                    table.addCell(String.valueOf(b.getBill()));

                                    //PdfPCell gName = new PdfPCell(new Phrase(b.getName(),font));
                                    table.addCell(new Phrase(b.getName(), font));
                                    table.addCell(new Phrase(b.getOption(), font));
                                    table.addCell(String.valueOf(b.getQty()));
                                    table.addCell(String.valueOf(b.getPrice()));
                                    //table.addCell(b.getBill(),font);
                                    //table.addCell(b.getName(),font);
                                    //table.addCell(b.getOption(),font);
                                    //table.addCell(b.getQty(),font);
                                    //table.addCell(b.getPrice(),font);


                                }
                                Paragraph subPrice = new Paragraph("ยอดรวม " + total, font);
                                Paragraph subQty = new Paragraph("จำนวนรายการ " + Qty, font);

                                document.add(p);
                                document.add(subDate);
                                document.add(subPrice);
                                document.add(subQty);
                                document.add(paragraph);
                                document.add(table);
                                document.close();
                            } catch (Exception e) {
                            }


                        }
                    }).start();
                    Toast.makeText(getApplication(), "ออกรายงานสำเร็จ", Toast.LENGTH_SHORT).show();


                }


            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }

    private void updateCurrentDate() {
        etxtDate.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(checkDigit(mDay)).append("-")
                        .append(checkDigit(mMonth + 1)).append("-")
                        .append(mYear));
    }


    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateCurrentDate();

                }
            };

    public String checkDigit(int number)
    {
        return number<=9?"0"+number:String.valueOf(number);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_report, menu);
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
