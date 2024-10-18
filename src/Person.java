public abstract class Person {
    private String fullName;
    private String address;
    private String phoneNumber;
    private int departmentNumber;

    public Person(String fullName, String address, String phoneNumber, int departmentNumber) {
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.departmentNumber = departmentNumber;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDepartmentNumber() {
        return departmentNumber;
    }
    public void setDepartmentNumber(int departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

}
