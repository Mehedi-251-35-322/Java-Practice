import java.io.*;
import java.util.ArrayList;

public class Library implements Borrowable {

    private ArrayList<Book> bookList;
    private final ArrayList<Member> memberList;

    public Library() {
        bookList = new ArrayList<>();
        memberList = new ArrayList<>();
    }

    // Add Book
    public void addBook(Book book) {
        bookList.add(book);
        System.out.print("Book added Successfully");
    }

    // Register Member
    public void addMember(Member member) {
        memberList.add(member);
        System.out.print("Member added Successfully");
    }

    // Book Available Check
    public void displayAvailableBookDetails() {
        System.out.print("<<<< Available Book Details >>>>\n");
        boolean found = false;

        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).isAvailabilityStatus()) {
                bookList.get(i).displayShowBookInfo();
                System.out.println("***********************************");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No book available for this Moment.");
        }
    }

    // Search Book by Title
    public void searchBook(String title) throws BookNotFoundException {
        System.out.println("<<<< Search Results >>>>");
        boolean found = false;

        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getTitle().equalsIgnoreCase(title)) {
                bookList.get(i).displayShowBookInfo();
                System.out.println("*********************************");
                found = true;
                break;
            }
        }
        if (!found) {
            throw new BookNotFoundException("Error: This " + title + " was not found.");
        }
    }

    // Search By Book ID
    public Book findBookById(String bookID) {
        for (Book book : bookList) {
            if (book.getBookID().equalsIgnoreCase(bookID)) {
                return book;
            }
        }
        return null;
    }

    // Search By Member ID
    public Member findMemberById(String memberID) {
        for (Member member : memberList) {
            if (member.getMemberID().equalsIgnoreCase(memberID)) {
                return member;
            }
        }
        return null;
    }


    //Borrow Book


    @Override
    public void borrowBook(Book book, Member member) throws BookUnavailableException {
        if (book.isAvailabilityStatus()) {
            book.setAvailabilityStatus(false);
            System.out.println(member.getMemberName() + " borrowed " + book.getTitle() + " book Successfully. ");
        } else {
            throw new BookUnavailableException("Sorry, This " + book.getTitle() + " is already borrowed.");
        }
    }

    // Return Book

    @Override
    public void returnBook(Book book, Member member) {
        if (!book.isAvailabilityStatus()) {
            book.setAvailabilityStatus(true);
            System.out.println(member.getMemberName() + " returned " + book.getTitle() + " book successfully. ");
        } else {
            System.out.println("This book is already available in the library.");
        }
    }

    // File handling call from FileHandling class
    public void saveBooksToFile(String fileName) {
        FileHandling.saveBooksToFile(fileName, this.bookList);
    }

    public void loadBooksFromFile(String fileName) {
        this.bookList = FileHandling.loadBooksFromFile(fileName);
    }
}
