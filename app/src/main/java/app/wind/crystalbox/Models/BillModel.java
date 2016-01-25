package app.wind.crystalbox.Models;

import java.util.Date;

/**
 * Created by Miki on 6/24/2015.
 */
public class BillModel {
    String id;
    String date;
    String table;
    String status;
    int total;




    public BillModel(String id, String date,  String table, String status, int total) {
        this.id = id;
        this.date = date;
        this.table = table;
        this.status = status;
        this.total = total;

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTable(String table) {this.table = table;}

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getId() {return id;}

    public String getDate() {
        return date;
    }

    public String getTable() {return table;}

    public String getStatus() {
        return status;
    }

    public int getTotal() {
        return total;
    }
}
