package entities;

public class Books {
    private int bookId;
    private String title;

    private int releaseDate;
    private int authorId;
    private String authorName;

    public Books(int bookId, String title, int releaseDate, int authorId, String authorName) {
        this.bookId = bookId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public Books(int bookId, String title, int releaseDate) {
        this.bookId = bookId;
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public Books(int bookId, String title, String authorName) {
        this.bookId = bookId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        if (releaseDate == 0 && authorId == 0) {
            return "Books{" +
                    "BookId = " + bookId +
                    ", Title = '" + title + '\'' +
                    ", Forfatternavn = '" + authorName + '\'' +
                    " };";
        } else {
            return "Books{" +
                    "BookId = " + bookId +
                    ", Title = '" + title + '\'' +
                    ", ReleaseDate = " + releaseDate +
                    ", AuthorId = " + authorId +
                    '}';
        }
    }
}
