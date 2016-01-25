package app.wind.crystalbox.Models;

/**
 * Created by Miki on 6/24/2015.
 */
public class QueueItemModel {
    int id;
    String name;
    String option;
    String bill;
    String status;
    int price;
    int qty;
    String table;




    public QueueItemModel(int id, String name, String option, String bill,  String status, int price,int qty, String table) {
        this.id = id;
        this.name = name;
        this.option = option;
        this.bill = bill;
        this.status = status;
        this.price = price;
        this.qty = qty;
        this.table = table;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() { return id;  }

    public String getName() {
        return name;
    }

    public String getOption() {
        return option;
    }

    public int getPrice() {
        return price;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getBill() {
        return bill;
    }

    public String getStatus() {
        return status;
    }

    public String getTable() {
        return table;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }
}
