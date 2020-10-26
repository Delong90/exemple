package orderingProducts.service;

import orderingProducts.dto.ProductDto;
import orderingProducts.exceptions.ItemNotFoundException;
import orderingProducts.dto.OrdersDto;
import orderingProducts.dto.ItemDto;
import orderingProducts.model.Orders;
import orderingProducts.model.Item;
import orderingProducts.model.Product;
import orderingProducts.repository.OrdersRepository;


import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersService {
    private OrdersRepository ordersRepository = new OrdersRepository();


    public OrdersDto getByOrders (int idUser)throws SQLException, ItemNotFoundException{
        Orders orders = ordersRepository.getByOrders(idUser);
        return OrdersDto.builder()
                .orders(orders.getOrders()).
                build();
    }

    public ArrayList<ProductDto> getProducts ()throws SQLException, ItemNotFoundException{

        ArrayList<Product> arr = ordersRepository.getProducts();
        ArrayList<ProductDto> arrDto = new ArrayList<>();
        for (Product product:
             arr) {

            arrDto.add(ProductDto.builder()
                    .id(product.getId())
                    .title(product.getTitle())
                    .cost(product.getCost())
                    .build());
        }
        return arrDto;
    }


//    public ItemDto getByItems ()throws SQLException, ItemNotFoundException {
//        Item item = ordersRepository.getByItems();
//        return ItemDto.builder()
//                .title(item.getTitle())
//                .cost(item.getCost())
//                .quantity(item.getQuantity())
//                .build();
//    }

}