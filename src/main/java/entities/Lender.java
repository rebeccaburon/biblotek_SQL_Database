package entities;

public class Lender {

    private int bookId;
    private int lenderId;
    private String lenderName;
    private int date;

    public Lender(int bookId, int lenderId, int date, String lenderName) {
        this.bookId = bookId;
        this.lenderId = lenderId;
        this.date = date;
        this.lenderName = lenderName;

    }

    public Lender(int bookId, int lenderId, String lenderName) {
        this.bookId = bookId;
        this.lenderId = lenderId;
        this.lenderName = lenderName;
    }

    public int getBookId() {
        return bookId;
    }

    public int getLenderId() {
        return lenderId;
    }

    public int getDate() {
        return date;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setLenderId(int lenderId) {
        this.lenderId = lenderId;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Lender{" +
                "BookId=" + bookId +
                ", LenderId=" + lenderId +
                ", Lender Name='" + lenderName + '\'' +
                '}';
    }
}
