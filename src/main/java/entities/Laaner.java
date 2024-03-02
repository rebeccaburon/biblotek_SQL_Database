package entities;

public class Laaner {
    private int laanerId;
    private String name;

    private String address;

    private int zip;

    public Laaner(int laanerId, String name, String address, int zip) {
        this.laanerId = laanerId;
        this.name = name;
        this.address = address;
        this.zip = zip;
    }

    public Laaner(String name, String address, int zip) {
        this.name = name;
        this.address = address;
        this.zip = zip;
    }

    public int getLaanerId() {
        return laanerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getZip() {
        return zip;
    }

    public void setLaanerId(int laanerId) {
        this.laanerId = laanerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Laaner{" +
                "laanerId=" + laanerId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", zip=" + zip +
                '}';
    }
}
