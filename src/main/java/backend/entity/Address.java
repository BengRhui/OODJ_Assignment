package backend.entity;

/**
 * Class {@code Address} is used to represent addresses of:<br>
 * - Customer's delivery address<br>
 * - Address of food court
 *
 * @author Beng Rhui (TP068495)
 */
public class Address {

    /**
     * Attributes involved in address (Malaysian ones). <br>
     * A list containing all states in Malaysia is also provided.
     */
    public final String[] STATE_OPTIONS = {
            "Perlis",
            "Penang",
            "Kelantan",
            "Kedah",
            "Terengganu",
            "Pahang",
            "Perak",
            "Negeri Sembilan",
            "Melaka",
            "Johor",
            "Selangor",
            "Sabah",
            "Sarawak",
            "WP Kuala Lumpur",
            "WP Putrajaya",
            "WP Labuan"
    };
    private String addressLine1;
    private String addressLine2;
    private String postcode;
    private String city;
    private String state;

    /**
     * Constructor used to create an instance of {@code Address}
     *
     * @param addressLine1 The first line of address, typically house / unit number and street name
     * @param addressLine2 The second line of address, typically name of residential area
     * @param postcode     A 5-digit identifier to represent the area of residence
     * @param city         The city of the residence
     * @param state        The state of the residence
     */
    public Address(String addressLine1, String addressLine2, String postcode, String city, String state) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.postcode = postcode;
        this.city = city;
        this.state = state;
    }

    /**
     * Getters and setters for {@code Address} class.
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * A method to print out the full address
     *
     * @return A string representation of an {@code Address} object
     */
    @Override
    public String toString() {
        return addressLine1 + ",\n" +
                addressLine2 + ",\n" +
                postcode + " " + city + ", " + state;
    }
}
