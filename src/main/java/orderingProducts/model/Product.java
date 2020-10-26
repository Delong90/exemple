package orderingProducts.model;
import lombok.*;


@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Product {
    private int id;
    private String title;
    private double cost;

    public Product(int id, String title, double cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }
}
