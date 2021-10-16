package MedicalWasteManagementSystem.model;

/**
 * Created on 9/15/2021.
 * Infectious Waste Model
 */
public class Infectious_Waste {
    private String type_of_waste;
    private String object;
    private String type_of_container;
    private float wasteperday;

    public Infectious_Waste() {
    }
    public Infectious_Waste(String type_of_waste, String object, String type_of_container, float wasteperday) {
        this.type_of_waste = type_of_waste;
        this.object = object;
        this.type_of_container = type_of_container;
        this.wasteperday = wasteperday;
    }

    public String getType_of_waste() {
        return type_of_waste;
    }

    public void setType_of_waste(String type_of_waste) {
        this.type_of_waste = type_of_waste;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getType_of_container() {
        return type_of_container;
    }

    public void setType_of_container(String type_of_container) {
        this.type_of_container = type_of_container;
    }

    public float getWasteperday() {
        return wasteperday;
    }

    public void setWasteperday(float wasteperday) {
        this.wasteperday = wasteperday;
    }

}
