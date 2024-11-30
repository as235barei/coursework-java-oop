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

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getDepartmentNumber() {
        return departmentNumber;
    }

}
