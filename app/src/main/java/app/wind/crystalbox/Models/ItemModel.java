package app.wind.crystalbox.Models;

/**
 * Created by Miki on 7/2/2015.
 */
public class ItemModel {
    String id;
    String name;
    int price;




    public ItemModel(String id, String name, int price){
        this.id = id;
        this.name = name;
        this.price = price;

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
