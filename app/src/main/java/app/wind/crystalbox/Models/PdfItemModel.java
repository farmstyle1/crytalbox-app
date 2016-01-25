package app.wind.crystalbox.Models;

/**
 * Created by Miki on 6/24/2015.
 */
public class PdfItemModel {

    int bill;
    String name;
    String option;
    int qty;
    int price;






    public PdfItemModel(int bill, String name, String option, int qty, int price) {
       this.bill = bill;
        this.name = name;
        this.option = option;
        this.qty = qty;
        this.price = price;

    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBill() {
        return bill;
    }

    public String getName() {
        return name;
    }

    public String getOption() {
        return option;
    }

    public int getQty() {
        return qty;
    }

    public int getPrice() {
        return price;
    }
}
