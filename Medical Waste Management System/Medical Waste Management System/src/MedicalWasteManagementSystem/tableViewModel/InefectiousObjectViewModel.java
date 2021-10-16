package MedicalWasteManagementSystem.tableViewModel;

/**
 * Created by v.Kodithuwakku on 9/18/2021.
 * Inefectious Object ViewModel for generating pie chart
 */
public class InefectiousObjectViewModel {

    private String objecName;
    private float wasteAmount;

    public InefectiousObjectViewModel(String objecName, float wasteAmount) {
        this.objecName = objecName;
        this.wasteAmount = wasteAmount;
    }

    public String getObjecName() {
        return objecName;
    }

    public void setObjecName(String objecName) {
        this.objecName = objecName;
    }

    public float getWasteAmount() {
        return wasteAmount;
    }

    public void setWasteAmount(float wasteAmount) {
        this.wasteAmount = wasteAmount;
    }
}
