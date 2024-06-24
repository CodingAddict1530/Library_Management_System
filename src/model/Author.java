package model;

import Util.DBUtil;
import Util.DateTimeUtil;

import java.time.OffsetDateTime;

/**
 * Represents an Author
 */
public class Author {

    // Attributes of an author object
    private final int author_id;
    private String first_name;
    private String last_name;
    private OffsetDateTime date_added;

    /**
     * Instantiates an Author object with all properties set to parameters
     *
     * @param author_id the id of the Author from database
     * @param first_name the first name of the Author from database
     * @param last_name the last name of the Author from database
     * @param date_added the date and time the Author was added to the database
     */
    public Author(int author_id, String first_name, String last_name, OffsetDateTime date_added) {
        this.author_id = author_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_added = date_added;
    }

    /**
     * Instantiates an Author object with minimum parameters to create a new author
     * Sets author_id to -1 as dummy data
     * author_id is set automatically by the database
     * date_added is set to null, will be the date and time it actually gets stored in the database
     *
     * @param first_name the first name of the new author
     * @param last_name the last name of the new author
     */
    public Author(String first_name, String last_name) {
        this(-1, first_name, last_name, null);
    }

    /**
     * Retrieves the id of the author
     *
     * @return the id of the author
     */
    public int getAuthor_id() {
        return author_id;
    }

    /**
     * Retrieves the first name of the author
     *
     * @return the first name of the author
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Updates the first name of the author to the passed value
     *
     * @param first_name the new first name
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Retrieves the last name of the author
     *
     * @return the last name of the author
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Updates the last name of the author to the passed value
     *
     * @param last_name the new last name
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Retrieves the date and time the author was added to the database
     *
     * @return the date and time the author was added or null if not added yet
     */
    public OffsetDateTime getDate_added() {
        return date_added;
    }

    /**
     * Adds the author to the database
     *
     * @return the number of rows affected or the error message if any occurs
     */
    public String addToDatabase() {
        String sql = "INSERT INTO public.author (first_name, last_name, date_added) " +
                "VALUES (?, ?, ?)";

        // Set the date added to the current time
        this.date_added = OffsetDateTime.now();

        try {

            // Use executeUpdate() method from DBUtil to save the author in the database
            return String.valueOf(DBUtil.executeUpdate(sql, DBUtil.getPostreSQLURL(), this.first_name,
                    this.last_name, this.date_added));

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * Save the changes made to the author in the database
     *
     * @return the number of rows affected or the error message if any occurs
     */
    public String saveChanges() {
        String sql = "UPDATE public.author SET " +
                "first_name = ?, " +
                "last_name = ? " +
                "WHERE author_id = ?";
        try {

            // Use executeUpdate() method from DBUtil to save the changes in the database
            return String.valueOf(DBUtil.executeUpdate(sql, DBUtil.getPostreSQLURL(), this.first_name,
                    this.last_name, this.author_id));

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * Delete the author from the database
     *
     * @return the number of rows affected or the error message if any occurs
     */
    public String delete() {
        String sql = "DELETE FROM public.author WHERE author_id = ?";
        try {

            // Use executeUpdate() method from DBUtil to delete the author from the database
            return String.valueOf(DBUtil.executeUpdate(sql, DBUtil.getPostreSQLURL(), this.author_id));

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * Returns a string representation of the author
     *
     * @return a string representation of the author
     */
    @Override
    public String toString() {

        // Use DateTimeUtil.formatToIso() To convert the date into a readable format
        return "Author{" +
                "author_id = " + author_id +
                ", first_name = '" + first_name + '\'' +
                ", last_name = '" + last_name + '\'' +
                ", date_added = " + DateTimeUtil.formatToCustom3(date_added) +
                '}';
    }
}
