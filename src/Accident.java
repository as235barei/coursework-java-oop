import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class Accident {

    private String typeAccident;       // Тип аварії
    private LocalDate dateAccident;       // Дата аварії
    private double decisionCost;       // Вартість усунення аварії
    private String idAccident;         // Id аварії
    private String addressAccident;    // Адреса аварії
    private String statusAccident;     // Стан аварії

    // Конструктор; Використання анотацій Jackson:
    @JsonCreator
    public Accident(@JsonProperty("typeAccident") String typeAccident,
                    @JsonProperty("dateAccident") LocalDate dateAccident,
                    @JsonProperty("decisionCost") double decisionCost,
                    @JsonProperty("idAccident") String idAccident,
                    @JsonProperty("addressAccident") String addressAccident,
                    @JsonProperty("statusAccident") String statusAccident) {
        this.typeAccident = typeAccident;
        this.dateAccident = dateAccident;
        this.decisionCost = decisionCost;
        this.idAccident = idAccident;
        this.addressAccident = addressAccident;
        this.statusAccident = statusAccident;
    }

    // Гетери та сетери
    public String getTypeAccident() {
        return typeAccident;
    }

    public LocalDate getDateAccident() {
        return dateAccident;
    }

    public double getDecisionCost() {
        return decisionCost;
    }

    public String getIdAccident() {
        return idAccident;
    }

    public void setAddressAccident(String addressAccident) {
        this.addressAccident = addressAccident;
    }

    // Метод для конвертації об'єкта в рядок
    @Override
    public String toString() {
        return "Accident{" +
                "typeAccident='" + typeAccident + '\'' +
                ", dateAccident='" + dateAccident + '\'' +
                ", decisionCost=" + decisionCost +
                ", idAccident='" + idAccident + '\'' +
                ", addressAccident='" + addressAccident + '\'' +
                ", statusAccident='" + statusAccident + '\'' +
                '}';
    }
}
