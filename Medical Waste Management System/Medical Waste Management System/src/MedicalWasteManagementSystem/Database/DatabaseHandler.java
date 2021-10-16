package MedicalWasteManagementSystem.Database;

import MedicalWasteManagementSystem.model.Infectious_Waste;
import MedicalWasteManagementSystem.model.User;

import java.sql.*;

/**
 * Created on 9/14/2021.
 * DataBase Handler class
 */
public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/"
                + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    //Write
    public void insertWasteData(String tableName, Infectious_Waste infectious_Waste) {
        String insert = "INSERT INTO " + tableName + "(" + Const.TYPE_OF_WASTE + ","
                + Const.OBJECT + "," + Const.TYPE_OF_CONTAINER + "," + Const.WASTE_PER_DAY + ")"
                + "VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, infectious_Waste.getType_of_waste());
            preparedStatement.setString(2, infectious_Waste.getObject());
            preparedStatement.setString(3, infectious_Waste.getType_of_container());
            preparedStatement.setFloat(4, infectious_Waste.getWasteperday());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    //Read
    public ResultSet getUser(User user) {
        ResultSet resultSet = null;

        if (!user.getUsername().equals("") || !user.getPassword().equals("")) {
            String query = "SELECT * FROM " + Const.LOGIN_TABLE + " WHERE " +
                    Const.USER_USERNAME + "=?" + " AND " + Const.USER_PASSWORD +
                    "=?";

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please Enter Correct Credentials");
        }
        return resultSet;
    }

    public ResultSet populateTableData(String tableName) {
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + tableName;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    //Update
    public void updateRecordOfWasteTable(String tableName, int id, Infectious_Waste infectious_Waste) throws SQLException, ClassNotFoundException {

        String query = "UPDATE " + tableName + " SET " + Const.TYPE_OF_WASTE + "=?," + Const.OBJECT + "=?," + Const.TYPE_OF_CONTAINER + "=?," + Const.WASTE_PER_DAY + "=? WHERE " + Const.WASTE_ID + "=?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setString(1, infectious_Waste.getType_of_waste());
        preparedStatement.setString(2, infectious_Waste.getObject());
        preparedStatement.setString(3, infectious_Waste.getType_of_container());
        preparedStatement.setFloat(4, infectious_Waste.getWasteperday());
        preparedStatement.setInt(5, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    //Delete
    public void deleteRecordFromWasteTable(String tableName, int id) {
        String query = "DELETE FROM " + tableName + " WHERE " + Const.WASTE_ID + "=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
