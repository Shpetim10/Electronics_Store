package View.InventoryManagementView;

import View.Design;
import javafx.scene.control.CheckBox;

public class InventoryListView implements Design {
    private int id;
    private String name;
    private int quantity;
    private double costPrice;
    private double sellingPrice;
    private String brand;
    private CheckBox selected; // This will be used for the CheckBox selection state

    public InventoryListView(int id, String name, int quantity, double costPrice, double sellingPrice, String brand) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.brand = brand;
        this.selected = createCheckBox(); // Default selection to false
    }

    // Getters and setters for the properties
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isSelected() {
        return selected.isSelected();
    }

    public void setSelected(boolean selected) {
        this.selected.setSelected(selected);
    }
}
