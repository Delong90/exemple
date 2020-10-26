package orderingProducts.repository;

import orderingProducts.dto.OrdersDto;
import orderingProducts.exceptions.ItemNotFoundException;
import orderingProducts.model.Item;
import orderingProducts.model.Orders;
import orderingProducts.model.Product;
import orderingProducts.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrdersRepository {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/projectmatushkin?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;

    public Connection getConnection() throws SQLException {

        if (connection == null) {
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            this.connection = connection;
        }

        return connection;
    }

    public Orders getByOrders(int idUser)throws SQLException{
        PreparedStatement preparedStatement =
                getConnection().prepareStatement("select o.id_orders from orders o join users u on u.id_users = ? and o.id_user = ?;");
        preparedStatement.setInt(1, idUser);
        preparedStatement.setInt(2, idUser);

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Integer> numbersOrders = new ArrayList<>();


        while(resultSet.next()) {
            int id_orders = resultSet.getInt("id_orders");
                        numbersOrders.add(id_orders);
        }

        Map<Integer, ArrayList<Item>> map = fillingOrders(numbersOrders);


        Orders orders = new Orders(map);
        return orders;
    }



    public Map<Integer, ArrayList<Item>> fillingOrders(ArrayList<Integer> numbersOrders) throws SQLException{
        Map<Integer, ArrayList<Item>> map = new HashMap<>();
        for (Integer i:numbersOrders) {

                PreparedStatement preparedStatement =
                        getConnection().prepareStatement("select p.title,p.cost, oi.quantity from order_items oi join orders o join products p on oi.id_order = ? and o.id_orders=? and oi.id_product=p.id_products;");
                preparedStatement.setInt(1, i);
                preparedStatement.setInt(2, i);

                ResultSet resultSet = preparedStatement.executeQuery();
                ArrayList<Item> orders = new ArrayList<>();

                while(resultSet.next()) {
                    String title = resultSet.getString("title");
                    double cost = resultSet.getDouble("cost");
                    int quantity = resultSet.getInt("quantity");
                    Item item = new Item(title,cost,quantity);
                    orders.add(item);
                }

            map.put(i,orders);
            }

            return map;
        }
//        if (map.isEmpty()){ throw new ItemNotFoundException("предыдущие заказы отсутствуют");}

    public ArrayList<Product> getProducts()throws SQLException{
        ArrayList<Product> arr = new ArrayList<>();
        PreparedStatement preparedStatement =
                getConnection().prepareStatement("select * from products;");


        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            int id = resultSet.getInt("id_products");
            String title = resultSet.getString("title");
            double cost = resultSet.getDouble("cost");
            Product product = new Product(id, title, cost);
            arr.add(product);
        }



        return arr;
    }

    }


