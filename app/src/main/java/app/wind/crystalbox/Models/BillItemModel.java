package app.wind.crystalbox.Models;

/**
 * Created by Miki on 6/24/2015.
 */
public class BillItemModel {
    int id;
    String name;
    String option;
    int price;
    int qty;




    public BillItemModel(int id, String name, String option, int price, int qty) {
        this.id = id;
        this.name = name;
        this.option = option;
        this.price = price;
        this.qty = qty;

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

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }
}
