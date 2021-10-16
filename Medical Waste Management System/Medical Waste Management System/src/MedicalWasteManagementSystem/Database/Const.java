package MedicalWasteManagementSystem.Database;

/**
 * Created on 9/14/2021.
 * Set of Constants Used in the Project
 */
public class Const {
    //Tables
    public static final String LOGIN_TABLE = "login";
    public static final String INFECTIOUS_WASTE_TABLE = "infectious_waste";
    public static final String NON_INFECTIOUS_WASTE_TABLE = "non_infectious_waste";

    //Column Names Login
    public static final String USER_NAME = "name";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";

    //Column Names Waste
    public static final String WASTE_ID = "id";
    public static final String TYPE_OF_WASTE = "type_of_waste";
    public static final String OBJECT = "object";
    public static final String TYPE_OF_CONTAINER = "type_of_container";
    public static final String WASTE_PER_DAY = "wasteperday";
}
