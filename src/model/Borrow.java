package model;

import Util.DBUtil;
import Util.DateTimeUtil;

import java.time.OffsetDateTime;

/**
 * Embodies the action of a user borrowing a book
 */
public class Borrow {

    // Attributes of a borrow object
    private final int borrowing_id;
    private int book_id;
    private int user_id;
    private OffsetDateTime borrowing_date;
    private OffsetDateTime expected_return_date;
    private OffsetDateTime actual_return_date;

    /**
     * Instantiates a Borrow object with all properties set to parameters
     *
     * @param borrowing_id the id of the Borrow from database
     * @param book_id the id of the book being borrowed
     * @param user_id the id of the user borrowing
     * @param borrowing_date the date and time the book was borrowed
     * @param expected_return_date the expected return date
     * @param actual_return_date the actual date and time the book is returned
     */
    public Borrow(int borrowing_id, int book_id, int user_id, OffsetDateTime borrowing_date,
                  OffsetDateTime expected_return_date, OffsetDateTime actual_return_date) {
        this.borrowing_id = borrowing_id;
        this.book_id = book_id;
        this.user_id = user_id;
        this.borrowing_date = borrowing_date;
        this.expected_return_date = expected_return_date;
        this.actual_return_date = actual_return_date;
    }

    /**
     * Instantiates a Borrow object with minimum parameters to create a new borrow
     * Sets borrowing_id to -1 as dummy data
     * borrowing_id is set automatically by the database
     * borrowing_date is set to null, will be the date and time it actually gets stored in the database
     * actual_return_date is set to null, will be the date and time it actually gets returned
     *
     * @param book_id the id of the book being borrowed
     * @param user_id the id of the user borrowing
     * @param expected_return_date the expected return date
     */
    public Borrow(int book_id, int user_id, OffsetDateTime expected_return_date) {
        this(-1, book_id, user_id, null, expected_return_date,
                null);
    }

    /**
     * Retrieves the id of the Borrow object
     *
     * @return the id of the Borrow object
     */
    public int getBorrowing_id() {
        return borrowing_id;
    }

    /**
     * Retrieves the id of the book being borrowed
     *
     * @return the id of the book being borrowed
     */
    public int getBook_id() {
        return book_id;
    }

    /**
     * Updates the id of the book that was borrowed to the passed value
     *
     * @param book_id the new id of the book that was borrowed
     */
    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    /**
     * Retrieves the id of the user borrowing the book
     *
     * @return the id of the user borrowing the book
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * Updates the id of the user borrowing the book to the passed value
     *
     * @param user_id the id of the user borrowing the book
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * Retrieves the date and time the book was borrowed
     *
     * @return the date and time the book was borrowed
     */
    public OffsetDateTime getBorrowing_date() {
        return borrowing_date;
    }

    /**
     * Retrieves the date and time the book is expected to be returned
     *
     * @return the date and time the book is expected to be returned
     */
    public OffsetDateTime getExpected_return_date() {
        return expected_return_date;
    }

    /**
     * Updates the expected return date to the passed value
     *
     * @param expected_return_date the new expected return date
     */
    public void setExpected_return_date(OffsetDateTime expected_return_date) {
        this.expected_return_date = expected_return_date;
    }

    /**
     * Retrieves the actual date the book was returned
     *
     * @return the actual date the book was returned
     */
    public OffsetDateTime getActual_return_date() {
        return actual_return_date;
    }

    /**
     * Adds the borrow object to the database
     *
     * @return the number of rows affected or the error message if any occurs
     */
    public String addToDatabase() {
        String sql = "INSERT INTO public.borrow (book_id, user_id, borrowing_date, " +
                "expected_return_date) VALUES (?, ?, ?, ?)";

        // Set the date added to the current time
        this.borrowing_date = OffsetDateTime.now();

        try {

            // Use executeUpdate() method from DBUtil to save the borrow object in the database
            return String.valueOf(DBUtil.executeUpdate(sql, DBUtil.getPostreSQLURL(), this.book_id,
                    this.user_id, this.borrowing_date, this.expected_return_date));

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * Marks the actual time the book is returned
     *
     * @return the number of rows affected or the error message if any occurs
     */
    public String returnBook() {
        String sql = "UPDATE public.borrow SET actual_return_date = ? WHERE borrowing_id = ?";
        try {
            this.actual_return_date = OffsetDateTime.now();

            // Use executeUpdate() method from DBUtil to save the changes in the database
            return String.valueOf(DBUtil.executeUpdate(sql, DBUtil.getPostreSQLURL(),
                    this.actual_return_date, this.borrowing_id));

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * Save the changes made to the borrow object in the database
     *
     * @return the number of rows affected or the error message if any occurs
     */
    public String saveChanges() {
        String sql = "UPDATE public.borrow SET " +
                "book_id = ?, " +
                "user_id = ?, " +
                "expected_return_date = ? " +
                "WHERE borrowing_id = ?";
        try {

            // Use executeUpdate() method from DBUtil to save the changes in the database
            return String.valueOf(DBUtil.executeUpdate(sql, DBUtil.getPostreSQLURL(), this.book_id,
                    this.user_id, this.expected_return_date, this.borrowing_id));

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * Delete the borrow object from the database
     *
     * @return the number of rows affected or the error message if any occurs
     */
    public String delete() {
        String sql = "DELETE FROM public.borrow WHERE borrowing_id = ?";
        try {

            // Use executeUpdate() method from DBUtil to delete the book from the database
            return String.valueOf(DBUtil.executeUpdate(sql, DBUtil.getPostreSQLURL(), this.borrowing_id));

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * Returns a string representation of the borrow object
     *
     * @return  a string representation of the borrow object
     */
    @Override
    public String toString() {

        // Use DateTimeUtil.formatToIso() To convert the date into a readable format
        return "Borrow{" +
                "borrowing_id = " + borrowing_id +
                ", book_id = " + book_id +
                ", user_id = " + user_id +
                ", borrowing_date = " + DateTimeUtil.formatToCustom3(borrowing_date) +
                ", expected_return_date = " + DateTimeUtil.formatToCustom3(expected_return_date) +
                ", actual_return_date = " + ((actual_return_date != null) ?
                DateTimeUtil.formatToCustom3(actual_return_date) : "NULL") +
                '}';
    }
}
