import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class Master extends Person {
    private int experience; // Стаж
    private int brigadeNumber; // Номер бригади
    private String masterStatus; // Стан аварії
    private List<Accident> accidents; // Список аварій, пов'язаних із майстром
    private List<Map<String, Object>> earnings;  // Список об'єктів, що містять суму та дату заробітку

    @JsonCreator
    public Master(@JsonProperty("fullName") String fullName,
                  @JsonProperty("address") String address,
                  @JsonProperty("phoneNumber") String phoneNumber,
                  @JsonProperty("departmentNumber") int departmentNumber,
                  @JsonProperty("experience") int experience,
                  @JsonProperty("brigadeNumber") int brigadeNumber,
                  @JsonProperty("masterStatus") String masterStatus,
                  @JsonProperty("earnings") List<Map<String, Object>> earnings) {
        super(fullName, address, phoneNumber, departmentNumber);
        this.experience = experience;
        this.brigadeNumber = brigadeNumber;
        this.masterStatus = masterStatus;
        this.earnings = earnings;
    }

    public List<Map<String, Object>> getEarnings() {
        return earnings;
    }

    public int getBrigadeNumber() {
        return brigadeNumber;
    }


    @Override
    public String toString() {
        return "Master{" +
                "fullName='" + getFullName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", departmentNumber=" + getDepartmentNumber() +
                ", experience=" + experience +
                ", brigadeNumber=" + brigadeNumber +
                ", earnings=" + earnings +
                ", masterStatus='" + masterStatus + '\'' +
                '}';
    }
}
