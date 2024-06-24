package model;

import Util.DBUtil;
import Util.DateTimeUtil;

import java.time.OffsetDateTime;

/**
 * Represents a User
 */
public class User {

    //Attributes of a user
    private final int user_id;
    private String first_name;
    private String last_name;
    private OffsetDateTime date_added;
    private boolean booking_record;

    /**
     * Instantiates a User object with all properties set to parameters
     *
     * @param user_id the id of the User from database
     * @param first_name the first name of the User from database
     * @param last_name the last name of the User from database
     * @param date_added the date and time the user was added to the database
     * @param booking_record the booking record of the User from database
     */
    public User(int user_id, String first_name, String last_name, OffsetDateTime date_added,
                boolean booking_record) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_added = date_added;
        this.booking_record = booking_record;
    }

    /**
     * Instantiates a User object with minimum parameters to create a new user
     * Sets user_id to -1 as dummy data
     * user_id is set automatically by the database
     * date_added is set to null, will be the date and time it actually gets stored in the database
     * Sets booking_record to true by default meaning a good booking record
     *
     * @param first_name the first name of the new user
     * @param last_name the last name of the new user
     */
    public User(String first_name, String last_name) {
        this(-1,first_name,last_name,null, true);
    }

    /**
     * Retrieves the id of the user
     *
     * @return the id of the user
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * Retrieves the firs name of the user
     *
     * @return the first name of the user
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Updates the first name of the user to the passed value
     *
     * @param first_name the new first name of the user
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Retrieves the last name of the user
     *
     * @return the last name of the user
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Updates the last name of the user to the passed value
     *
     * @param last_name the new last name of the user
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Retrieves the date the user was added in the database
     *
     * @return the date the user was added in the database
     */
    public OffsetDateTime getDate_added() {
        return date_added;
    }

    /**
     * Retrieves the booking record of the user
     *
     * @return true is the booking record is good, otherwise false
     */
    public boolean getBooking_record() {
        return booking_record;
    }

    /**
     * Updates the booking record to the passed value
     *
     * @param booking_record the new booking record
     */
    public void setBooking_record(boolean booking_record) {
        this.booking_record = booking_record;
    }

    /**
     * Adds the user to the database
     *
     * @return the number of rows affected or the error message if any occurs
     */
    public String addToDatabase() {
        String sql = "INSERT INTO public.user (first_name, last_name, date_added, booking_record)" +
                " VALUES (?, ?, ?, ?)";

        // Set the date added to the current time
        this.date_added = OffsetDateTime.now();

        try {

            // Use executeUpdate() method from DBUtil to save the user in the database
            return String.valueOf(DBUtil.executeUpdate(sql, DBUtil.getPostreSQLURL(), this.first_name,
                    this.last_name, this.date_added, this.booking_record));

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * Save the changes made to the user in the database
     *
     * @return the number of rows affected or the error message if any occurs
     */
    public String saveChanges() {
        String sql = "UPDATE public.user SET " +
                "first_name = ?, " +
                "last_name = ?, " +
                "booking_record = ? " +
                "WHERE user_id = ?";
        try {

            // Use executeUpdate() method from DBUtil to save the changes in the database
            return String.valueOf(DBUtil.executeUpdate(sql, DBUtil.getPostreSQLURL(), this.first_name,
                    this.last_name, this.booking_record, this.user_id));

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * Delete the user from the database
     *
     * @return the number of rows affected or the error message if any occurs
     */
    public String delete() {
        String sql = "DELETE FROM public.user WHERE user_id = ?";
        try {

            // Use executeUpdate() method from DBUtil to delete the user from the database
            return String.valueOf(DBUtil.executeUpdate(sql, DBUtil.getPostreSQLURL(), this.user_id));

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * Returns a string representation of the user
     *
     * @return a string representation of the user
     */
    @Override
    public String toString() {

        // Use DateTimeUtil.formatToIso() To convert the date into a readable format
        return "User{" +
                "user_id=" + user_id +
                ", first_name = '" + first_name + '\'' +
                ", last_name = '" + last_name + '\'' +
                ", date_added = " + DateTimeUtil.formatToCustom3(date_added) +
                ", booking_record = " + ((booking_record) ? "Clean" : "Not Clean") +
                '}';
    }
}
