package entities;

public class Author {
    private int forfatterId;
    private String navn;

    public Author(int forfatterId, String navn) {
        this.forfatterId = forfatterId;
        this.navn = navn;
    }

    public int getForfatterId() {
        return forfatterId;
    }

    public String getNavn() {
        return navn;
    }

    public void setForfatterId(int forfatterId) {
        this.forfatterId = forfatterId;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {
        return "Author{" +
                "ForfatterId = " + forfatterId +
                "\nNavn = " + navn + '\'' +
                '}';
    }
}
