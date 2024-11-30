import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
    private List<String> accidentIds; // Тепер зберігаємо список ID аксидентів

    // Конструктор з Jackson
    @JsonCreator
    public Client(@JsonProperty("fullName") String fullName,
                  @JsonProperty("address") String address,
                  @JsonProperty("phoneNumber") String phoneNumber,
                  @JsonProperty("departmentNumber") int departmentNumber,
                  @JsonProperty("accidentIds") List<String> accidentIds) {
        super(fullName, address, phoneNumber, departmentNumber);
        this.accidentIds = accidentIds != null ? accidentIds : new ArrayList<>();
    }


    // Метод для додавання аксиденту до списку
    public void addAccidentId(String accidentId) {
        if (!accidentIds.contains(accidentId)) {
            accidentIds.add(accidentId);
        }
    }

    public List<String> getAccidentIds() {
        return accidentIds;
    }

}
