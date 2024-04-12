public class Contact {
    // Private attributes representing the information of a contact
    private String name;
    private String phone;
    private String email;
    private String address;

    // Constructor that initializes a Contact object with the provided information
    public Contact(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    // Access methods to get and set the name of the contact
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Access methods to get and set the phone number of the contact
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Access methods to get and set the email of the contact
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Access methods to get and set the address of the contact
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // toString method to represent the Contact object as a formatted string
    public String toString() {
        return "Name: " + name + ", Phone: " + phone + ", Email: " + email + ", Address: " + address;
    }
}
