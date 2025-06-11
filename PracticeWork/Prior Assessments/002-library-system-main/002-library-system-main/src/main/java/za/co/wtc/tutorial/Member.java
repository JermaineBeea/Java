package za.co.wtc.tutorial;
import java.util.List;


public class Member {

    /**
     * Constructor to initialize a Member object with name and ID.
     * Initializes an empty list of borrowed books.
     *
     *
     * TODO: Implement this class by going through Library logic and following tests.
     */

     public String name;
     public int id;
     private List<Book> borrowBooks;

     public Member(String name, int memberId) {
        this.name = name;
        this.id = memberId;
     }

     public String getName() {
        return name;
     }

     public int getId() {
        return id;
     }

     public List<Book> getBorrowedBooks() {
        return borrowBooks;
     }

     public void borrowBook(Book book) {
        if (!book.checkout) {
            book.setCheckout(true);
            borrowBooks.add(book);
        }
     }

     public void returnBook(Book book) {
        if (borrowBooks.contains(book)) {
            book.setCheckout(false);
            borrowBooks.remove(book);
        }
     }

}
