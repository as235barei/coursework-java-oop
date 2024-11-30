import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Brigade {
    private int numberBrigade;
    private List<Master> masters;

    @JsonCreator
    public Brigade(@JsonProperty("numberBrigade") int numberBrigade,
                   @JsonProperty("masters") List<Master> masters) {
        this.numberBrigade = numberBrigade;
        this.masters = masters;
    }

    // Кількість майстрів у бригаді
    public int getMastersCount() {
        return masters.size();
    }

    @Override
    public String toString() {
        return "Brigade{" +
                "numberBrigade=" + numberBrigade +
                ", masters=" + masters +
                '}';
    }

}