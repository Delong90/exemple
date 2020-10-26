package orderingProducts.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ItemDto {
    private String title;
    private double cost;
    private int quantity;

    @Override
    public String toString() {
        return "ItemDto{" +
                "title='" + title + '\'' +
                ", cost=" + cost +
                ", quantity=" + quantity +
                '}';
    }
}
