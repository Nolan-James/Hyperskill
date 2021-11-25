
class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        if (firstName != null) {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        if (lastName != null) {
            this.lastName = lastName;
        }
    }

    public String getFullName() {
        if (this.firstName == "" && this.lastName == "") {
            return "Unknown";
        }
        if (this.firstName != null && this.lastName != null) {
            return firstName + " " + lastName;
        }

        if (this.firstName == "") {
            return this.lastName;
        } else if (this.lastName == "") {
            return this.firstName;
        } else {
            return "Unknown";
        }
    }
}