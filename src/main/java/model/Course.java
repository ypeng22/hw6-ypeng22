package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represent a Course.
 */
public class Course {

    @SerializedName(value = "OfferingName")
    private String offeringName;

    @SerializedName(value = "Title")
    private String title;

    @SerializedName(value = "Description")
    private String description;

    @SerializedName(value = "SectionName")
    private String sectionnum;

    @SerializedName(value = "SectionDetails")
    private List<Details> details;

    /**
     * Construct a Course.
     *
     * @param offeringName The course alphanumeric code.
     * @param title The course Title.
     */
    public Course(String offeringName, String title, String sectionnum) {
        this.offeringName = offeringName;
        this.title = title;
        this.sectionnum = sectionnum;
    }

    public Course(String offeringName, String title, String descp, String sectionnum) {
        this.offeringName = offeringName;
        this.title = title;
        this.description = descp;
        this.sectionnum = sectionnum;
    }

    //public String getDetails() {
     //   return details.getDesc();
    //}

    public String getSN() {
        return sectionnum;
    }
    /**
     * Get the title of the course.
     *
     * @return title of the course.
     */
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return details.get(0).getDesc();
        //return details;
    }

    /**
     * Get the course alphanumeric code.
     *
     * @return the course alphanumeric code.
     */
    public String getOfferingName() {
        return offeringName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(offeringName, title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return offeringName.equals(course.offeringName) && title.equals(course.title);
    }

    @Override
    public String toString() {
        return offeringName + " " + title;
    }
}