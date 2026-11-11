public class Book {
    private String bookID;
    private String title;
    private String author;
    private String category;
    private boolean availabilityStatus;

    public Book(String bookID, String title, String author, String category, boolean availabilityStatus) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.category = category;
        this.availabilityStatus = availabilityStatus;
    }

    // Getter and Setter Method Apply
    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    //Display Data
    public void displayShowBookInfo() {
        String status = isAvailabilityStatus() ? "Available" : "Unavailable";
        System.out.println("Book ID: " + bookID);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Category: " + category);
        System.out.println("Availability Status: " + status);
    }

    //file Handling
    public String getFileData() {
        return bookID + "," + title + "," + author + "," + category + "," + availabilityStatus;
    }

}