import java.util.List;

public class Brigade {
    private int numberBrigade;
    private List<Master> masters;

    public Brigade(int numberBrigade, List<Master> masters) {
        this.numberBrigade = numberBrigade;
        this.masters = masters;
    }

    public int getNumberBrigade() {
        return numberBrigade;
    }
    public void setNumberBrigade(int numberBrigade) {
        this.numberBrigade = numberBrigade;
    }

    public List<Master> getMasters() {
        return masters;
    }
    public void setMasters(List<Master> masters) {
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