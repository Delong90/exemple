package orderingProducts.repository;

import orderingProducts.exceptions.ItemNotFoundException;
import orderingProducts.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;

public class UserRepository {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/projectmatushkin?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;

    public  Connection getConnection() throws SQLException {

        if (connection == null) {
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            this.connection = connection;
        }

        return connection;
    }

    public User getByUser(String userName) throws SQLException {

        PreparedStatement preparedStatement =
                getConnection().prepareStatement("select * from users where user=?;");
        preparedStatement.setString(1, userName);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next())
            throw new ItemNotFoundException("User");

        int id = resultSet.getInt("id_users");
        String user = resultSet.getString("user");
        String password = resultSet.getString("password");

        return new User(id, user, password);
    }

    public User newUser(String inName, String inPassword) throws SQLException{
        String inPasswordHex = DigestUtils.sha256Hex(inPassword);
//        String query = String.format("insert into users value (null, '%s', '%s')",
//                inName,
//                inPasswordHex);
//        Connection connection = getConnection();
//        Statement statement = connection.createStatement();
//        statement.execute(query);


//        String query = String.format("insert into users value (null, '%s', '%s')",
//                inName,
//                inPasswordHex);
//
//        PreparedStatement preparedStatement =
//                getConnection().prepareStatement("insert into users value (null,?,?);");
//        preparedStatement.setString(1, inName);
//        preparedStatement.setString(2, inPasswordHex);
//
//
//
//        preparedStatement.execute(query);


        String query = "insert into users value (null, ?, ?);";
        PreparedStatement preparedStatement =
                getConnection().prepareStatement(query);
        preparedStatement.setString(1, inName);
        preparedStatement.setString(2, inPasswordHex);
        preparedStatement.executeUpdate();


        return getByUser(inName);

    }
}
