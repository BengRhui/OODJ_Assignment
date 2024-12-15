package backend.entity;

import java.util.Arrays;

/**
 * Class {@code Address} is used to represent addresses of:<br>
 * - Customer's delivery address<br>
 * - Address of food court
 *
 * @author Beng Rhui (TP068495)
 */
public class Address {

    /**
     * Attributes involved in address (Malaysian ones).
     */
    private String addressLine1;
    private String addressLine2;
    private String postcode;
    private String city;
    private State state;

    /**
     * Constructor used to create an instance of {@code Address}
     *
     * @param addressLine1 The first line of address, typically house / unit number and street name
     * @param addressLine2 The second line of address, typically name of residential area
     * @param postcode     A 5-digit identifier to represent the area of residence
     * @param city         The city of the residence
     * @param state        The state of the residence
     */
    public Address(String addressLine1, String addressLine2, String postcode, String city, State state) {
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
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
                postcode + " " + city + ", " + state.toString();
    }

    /**
     * Enum {@code State} represents all the possible states in Malaysia.
     */
    public enum State {

        /**
         * The 13 states in Malaysia, including three federal territories.
         */
        PERLIS, PENANG, KELANTAN, KEDAH, TERENGGANU, PAHANG, PERAK, NEGERI_SEMBILAN, MELAKA, JOHOR, SELANGOR, SABAH,
        SARAWAK, WP_KL, WP_PUTRAJAYA, WP_LABUAN;

        /**
         * A method to return all the state options in string array.
         */
        public final static String[] STATE_OPTIONS = Arrays.stream(State.values())  // Obtain the values of State
                .map(State::toString)                                               // Map it to the output of toString
                .toArray(String[]::new);                                            // Output as a new String array

        /**
         * A method to get the respective State fields from the string input.
         *
         * @param state String input that represents state
         * @return The state field corresponding to input
         */
        public static State getFromString(String state) {
            return Arrays.stream(State.values())                                          // Get values of State
                    .filter(itemInList -> itemInList.toString().equalsIgnoreCase(state))  // Filter based on input
                    .findFirst()                                                          // Find the first occurrence
                    .orElse(null);                                                        // Returns null if not match
        }

        /**
         * A method to return the string value of the states.
         *
         * @return The string representation of states
         */
        @Override
        public String toString() {
            return switch (this) {
                case PERLIS -> "Perlis";
                case PENANG -> "Penang";
                case KELANTAN -> "Kelantan";
                case KEDAH -> "Kedah";
                case TERENGGANU -> "Terengganu";
                case PAHANG -> "Pahang";
                case PERAK -> "Perak";
                case NEGERI_SEMBILAN -> "Negeri Sembilan";
                case MELAKA -> "Melaka";
                case JOHOR -> "Johor";
                case SELANGOR -> "Selangor";
                case SABAH -> "Sabah";
                case SARAWAK -> "Sarawak";
                case WP_KL -> "WP Kuala Lumpur";
                case WP_PUTRAJAYA -> "WP Putrajaya";
                case WP_LABUAN -> "WP Labuan";
            };
        }
    }
}
