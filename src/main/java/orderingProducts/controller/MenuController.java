package orderingProducts.controller;

import orderingProducts.dto.OrdersDto;
import orderingProducts.dto.ProductDto;
import orderingProducts.dto.UserDto;
import orderingProducts.exceptions.ItemNotFoundException;
import orderingProducts.repository.UserRepository;
import orderingProducts.service.OrdersService;
import orderingProducts.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuController {
    private static final String  START_MENU =  "-------------------------\n 1. Вход в личный кабинет\n 2. Создание аккаунта\n e. Закрытие программы\n-------------------------";
    private static final String  USER_MENU =  "-------------------------\n1. Просмотр списка предыдущих заказов\n2. Оформление нового заказа\ne. Предыдущее меню\n-------------------------";

    private UserService userService = new UserService();
    private OrdersService ordersService = new OrdersService();

    public void start() {
        String in = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!"e".equals(in)) {
            try {
                System.out.println(START_MENU);
                in = reader.readLine();
                switch (in) {
                    case "1":
                        startSession();
                        break;
                    case "2":
                        startCreature();
                        break;
                    case "e":
                        continue;
                }
            } catch (ItemNotFoundException | IOException e) {
                System.err.println("Невалидный ввод данных: " + e.getMessage());
            }
        }

        }

    public void startSession(){
        String in = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!"e".equals(in)) {
            try {
                    System.out.println("Введите имя пользователя(user), либо 'e' для выхода в предыдущее меню:");
                    in = reader.readLine();
                    if ("e".equals(in)){start();}
                    else {
                        UserDto userDto = userService.getByUser(in);
                    if (userDto != null) {
                        System.out.println("Введите пароль(password):");
                        in = reader.readLine();
                        if (userService.checkPassword(in, userDto)) {
                            startUserSession(userDto.getUser(),userDto.getId());
                            in = "e";
                        } else {
                            System.err.println("Пароль введён неверно");
                        }
                    }
                    }
                } catch(ItemNotFoundException e){
                    System.err.println("Данной учётной записи нет в базе данных: " + e.getMessage());
                } catch(IOException | SQLException e){
                    e.printStackTrace();
                }

        }

    }


    private void startCreature() {
        String inName = "";
        String inPassword = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                System.out.println("Введите новое имя пользователя(user), либо 'e' для выхода в предыдущее меню:");
                inName = reader.readLine();
                if ("e".equals(inName)) {
                    start();
                }
                else {
                    System.out.println("Введите пароль(password):");
                    inPassword = reader.readLine();
                   UserDto userDto = userService.newUser(inName, inPassword);
                   System.out.println("Новый аккаунт создан успешно, добро пожаловать!");
                  startUserSession(userDto.getUser(),userDto.getId());
                }

            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }


    }

    private void startUserSession(String user,int id) {
        String in = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!"e".equals(in)) {
            try {
                System.out.println(USER_MENU);
                in = reader.readLine();
                switch (in) {
                    case "1":
                        orders(id);
                        break;
                    case "2":
                        newOrder();
                        break;
                    case "e":

                        break;
                }
            } catch (ItemNotFoundException | IOException | SQLException e) {
                System.err.println("Невалидный ввод данных: " + e.getMessage());
            }
        }
    }

    private void orders(int idUser) {
        try {
            System.out.println("-------------------------\nРанее оформленные заказы:\n-------------------------");
            OrdersDto ordersDto = ordersService.getByOrders(idUser);
            System.out.println(ordersDto.toString());
        }catch (ItemNotFoundException e) {
            System.err.println("Заказы отсутствуют");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void newOrder() throws SQLException {
        try {
            System.out.println("-------------------------\nОформление нового заказа:\n-------------------------");
            System.out.println("-------------------------\nСписок доступных товаров:\n-------------------------");
            ArrayList<ProductDto> arr = ordersService.getProducts();
            System.out.println(arr.toString());

            String in = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (!"e".equals(in)) {
                System.out.println("Введите id товара, для завершения заказа нажмите 'e'");
                in = reader.readLine();
                if (!"e".equals(in)){

                }
            }


        } catch (SQLException | ItemNotFoundException | IOException e) {
            e.printStackTrace();
        }
//        while (!"e".equals(in)) {
//            try {
//
//            }
//        }
    }
}
