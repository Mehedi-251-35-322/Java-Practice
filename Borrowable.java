public interface Borrowable {
    void borrowBook(Book book, Member member) throws BookUnavailableException;
    void returnBook(Book book, Member member);
}
