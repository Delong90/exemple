package orderingProducts.dto;

import lombok.*;
import orderingProducts.model.Item;

import java.util.ArrayList;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class OrdersDto {
    private Map<Integer, ArrayList<Item>> orders;

    @Override
    public String toString() {
        return "Список предыдущих заказов{"
                + orders +
                '}';
    }
}

