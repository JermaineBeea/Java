package za.co.wtc.tutorial;

public class Book {
    /**The Book class represents a book in a library or bookstore application.
     * It encapsulates essential attributes of a book:
     *
     * TODO: implement this class by making use of tests and library logic.
    **/

    public String title;
    public String author;
    public boolean checkout = false;
    public boolean available = true;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {return title;}

    public String getAuthor() {return author;}

    public boolean checkout() {return checkout;}

    public void setCheckout(boolean checkout) {this.checkout = checkout;}

    public boolean isAvailable() {return available;}

}
