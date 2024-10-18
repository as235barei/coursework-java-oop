import java.util.List;

public class Accident {

    private String typeAccident; // Тип аварії
    private String dateAccident; // Дата аварії
    private double decisionCost; // Вартість усунення аварії
    private String idAccident; // Id аварії
    private String addressAccident; // Тип аварії
    private String statusAccident; // Стан аварії

    public Accident(String typeAccident, String dateAccident, double decisionCost, String idAccident, String addressAccident, String statusAccident) {
        this.typeAccident = typeAccident;
        this.dateAccident = dateAccident;
        this.decisionCost = decisionCost;
        this.idAccident = idAccident;
        this.addressAccident = addressAccident;
        this.statusAccident = statusAccident;
    }

    public String getTypeAccident() {
        return typeAccident;
    }
    public void setTypeAccident(String typeAccident) {
        this.typeAccident = typeAccident;
    }
    public String getDateAccident() {
        return dateAccident;
    }
    public void setDateAccident(String dateAccident) {
        this.dateAccident = dateAccident;
    }
    public double getDecisionCost() {
        return decisionCost;
    }
    public void setDecisionCost(double decisionCost) {
        this.decisionCost = decisionCost;
    }
    public String getIdAccident() {
        return idAccident;
    }
    public void setIdAccident(String idAccident) {
        this.idAccident = idAccident;
    }
    public String getAddressAccident() {
        return addressAccident;
    }
    public void setAddressAccident(String addressAccident) {
        this.addressAccident = addressAccident;
    }
    public String getStatusAccident() {
        return statusAccident;
    }
    public void setStatusAccident(String statusAccident) {
        this.statusAccident = statusAccident;
    }

    public static int countAccidents(List<Accident> accidents) {
        return accidents.size();
    }

    @Override
    public String toString() {
        return "Accident{" +
                "typeAccident='" + typeAccident + '\'' +
                ", dateAccident='" + dateAccident + '\'' +
                ", decisionСost=" + decisionCost +
                ", idAccident='" + idAccident + '\'' +
                ", addressAccident='" + addressAccident + '\'' +
                ", statusAccident='" + statusAccident + '\'' +
                '}';
    }


}
