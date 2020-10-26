package orderingProducts.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class Item {
    private String title;
    private double cost;
    private int quantity;

    public Item(String title, double cost, int quantity) {
        this.title = title;
        this.cost = cost;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title +
                ", cost=" + cost +
                ", quantity=" + quantity +
                '}'+ '\n';
    }
}
