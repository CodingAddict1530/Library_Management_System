package model;

import Util.DBUtil;
import Util.DateTimeUtil;

import java.time.OffsetDateTime;

/**
 * Represents a Book
 */
public class Book {

    // Attributes of a book object
    private final int book_id;
    private String title;
    private String description;
    private int number_of_pages;
    private OffsetDateTime date_added;
    private String genre;
    private int author_id;

    /**
     * Instantiates a Book object with all properties set to parameters
     *
     * @param book_id the id of the Book from database
     * @param title the title of the Book from database
     * @param description the description of the Book from database
     * @param number_of_pages the number of pages of the Book from database
     * @param date_added the date and time the book was added in the database
     * @param genre the genre of the Book from database
     * @param author_id the id of the author of the Book from database
     */
    public Book(int book_id, String title, String description, int number_of_pages,
                OffsetDateTime date_added, String genre, int author_id) {
        this.book_id = book_id;
        this.title = title;
        this.description = description;
        this.number_of_pages = number_of_pages;
        this.date_added = date_added;
        this.genre = genre;
        this.author_id = author_id;
    }

    /**
     * Instantiates a Book object with minimum parameters to create a new book
     * Sets book_id to -1 as dummy data
     * book_id is set automatically by the database
     * date_added is set to null, will be the date and time it actually gets stored in the database
     *
     * @param title title of the new book
     * @param description description of the new book
     * @param number_of_pages number of pages of the new book
     * @param genre genre of the new book
     * @param author_id id of the author of the new book
     */
    public Book(String title, String description, int number_of_pages,
                String genre, int author_id) {
        this(-1, title, description, number_of_pages, null, genre, author_id);
    }

    /**
     * Retrieves the id of the book
     *
     * @return the id of the book
     */
    public int getBook_id() {
        return book_id;
    }

    /**
     * Retrieves the title of the book
     *
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Updates the title of the book to the passed value
     *
     * @param title the new title of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the description of the book
     *
     * @return the description of the book
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates the description of the book to the passed value
     *
     * @param description the new description of the book
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the number of pages of the book
     *
     * @return the number of pages of the book
     */
    public int getNumber_of_pages() {
        return number_of_pages;
    }

    /**
     * Updates the number of pages of the book to the passed value
     *
     * @param number_of_pages the new number of pages of the book
     */
    public void setNumber_of_pages(int number_of_pages) {
        this.number_of_pages = number_of_pages;
    }

    /**
     * Retrieves the date and time the book was added in the database
     *
     * @return the date and time the book was added or null if not added yet
     */
    public OffsetDateTime getDate_added() {
        return date_added;
    }

    /**
     * Retrieves the genre of the book
     *
     * @return the genre of the book
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Updates the genre of the book to the passed value
     *
     * @param genre the new genre of the book
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Retrieves the id of the author of the book
     *
     * @return the id of the author of the book
     */
    public int getAuthor_id() {
        return author_id;
    }

    /**
     * Updates the id of the author of the book to the passed value
     *
     * @param author_id the new id of the author
     */
    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    /**
     * Adds the book to the database
     *
     * @return the number of rows affected or the error message if any occurs
     */
    public String addToDatabase() {
        String sql = "INSERT INTO public.book (title, description, number_of_pages, date_added, " +
                "genre, author_id) VALUES (?, ?, ?, ?, ?, ?)";

        // Set the date added to the current time
        this.date_added = OffsetDateTime.now();

        try {

            // Use executeUpdate() method from DBUtil to save the book in the database
            return String.valueOf(DBUtil.executeUpdate(sql, DBUtil.getPostreSQLURL(), this.title,
                    this.description, this.number_of_pages, this.date_added, this.genre, this.author_id));

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * Save the changes made to the book in the database
     *
     * @return the number of rows affected or the error message if any occurs
     */
    public String saveChanges() {
        String sql = "UPDATE public.book SET " +
                "title = ?, " +
                "description = ?, " +
                "number_of_pages = ?, " +
                "genre = ?, " +
                "author_id = ? " +
                "WHERE book_id = ?";
        try {

            // Use executeUpdate() method from DBUtil to save the changes in the database
            return String.valueOf(DBUtil.executeUpdate(sql, DBUtil.getPostreSQLURL(), this.title,
                    this.description, this.number_of_pages, this.genre, this.author_id, this.book_id));

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * Delete the book from the database
     *
     * @return the number of rows affected or the error message if any occurs
     */
    public String delete() {
        String sql = "DELETE FROM public.book WHERE book_id = ?";
        try {

            // Use executeUpdate() method from DBUtil to delete the book from the database
            return String.valueOf(DBUtil.executeUpdate(sql, DBUtil.getPostreSQLURL(), this.book_id));

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * Returns a string representation of the book
     *
     * @return a string representation of the book
     */
    @Override
    public String toString() {

        // Use DateTimeUtil.formatToIso() To convert the date into a readable format
        return "Book{" +
                "book_id = " + book_id +
                ", title = '" + title + '\'' +
                ", description = '" + description + '\'' +
                ", number_of_pages = " + number_of_pages +
                ", date_added = " + DateTimeUtil.formatToCustom3(date_added) +
                ", genre = '" + genre + '\'' +
                ", author_id = " + author_id +
                '}';
    }
}
