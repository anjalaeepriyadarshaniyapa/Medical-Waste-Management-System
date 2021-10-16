package MedicalWasteManagementSystem.tableViewModel;

/**
 * Created by v.Kodithuwakku on 9/16/2021.
 * View model for generating Table
 */
public class WasteTableViewModel {

    Integer id;
    Float wasteperday;
    String type_of_waste;
    String object;
    String type_of_container;

    public WasteTableViewModel(Integer id, String type_of_waste, String object, String type_of_container, Float wasteperday) {
        this.id = id;
        this.wasteperday = wasteperday;
        this.type_of_waste = type_of_waste;
        this.object = object;
        this.type_of_container = type_of_container;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getWasteperday() {
        return wasteperday;
    }

    public void setWasteperday(Float wasteperday) {
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


}


