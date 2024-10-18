public class Master extends Person {
    private int experience; // Стаж
    private int brigadeNumber; // Номер бригади
    private double earnings; // Заробіток
    private String masterStatus; // Стан аварії


    public Master(String fullName, String address, String phoneNumber, int departmentNumber, int experience, int brigadeNumber, double earnings,  String masterStatus) {
        super(fullName, address, phoneNumber, departmentNumber);
        this.experience = experience;
        this.brigadeNumber = brigadeNumber;
        this.earnings = earnings;
        this.masterStatus = masterStatus;
    }

    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getBrigadeNumber() {
        return brigadeNumber;
    }
    public void setBrigadeNumber(int brigadeNumber) {
        this.brigadeNumber = brigadeNumber;
    }

    public double getEarnings() {
        return earnings;
    }
    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }
    public String getMasterStatus() {
        return masterStatus;
    }
    public void setMasterStatus(String masterStatus) {
        this.masterStatus = masterStatus;
    }

}
