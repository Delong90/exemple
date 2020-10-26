package orderingProducts.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Map;
@Getter
@Setter

public class Orders {
    private Map<Integer, ArrayList<Item>> orders;

    public Orders(Map<Integer, ArrayList<Item>> orders) {
        this.orders = orders;
    }
    public Orders() {
        this.orders = orders;
    }

}
