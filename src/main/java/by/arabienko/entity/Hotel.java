package by.arabienko.entity;

/**
 * Class describing the entity Hotel.
 */
public class Hotel {
    private String price;
    private String hotelName;
    private Address hotelAddress;

    public Hotel() {
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Address getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(Address hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "price='" + price + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", hotelAddress=" + hotelAddress +
                '}';
    }


    /**
     * Inner class that contains
     * the Address information for the Hotel entity.
     */
    public class Address {
        private String addressLine;
        private String city;
        private String country;
        private String state;

        public Address() {
        }

        public String getAddressLine() {
            return addressLine;
        }

        public void setAddressLine(String addressLine) {
            this.addressLine = addressLine;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "addressLine='" + addressLine + '\'' +
                    ", city='" + city + '\'' +
                    ", country='" + country + '\'' +
                    ", state='" + state + '\'' +
                    '}';
        }
    }
}
