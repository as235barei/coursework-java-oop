public class Client  extends Person{
    private String accidentId;


    public Client(String fullName, String address, String phoneNumber, int departmentNumber, int experience, int brigadeNumber, double earnings, String accidentId, String masterStatus) {
        super(fullName, address, phoneNumber, departmentNumber);
        this.accidentId=accidentId;
    }

    public String getAccidentId() {
        return accidentId;
    }
    public void setAccidentId(String accidentId) {
        this.accidentId = accidentId;
    }
}
