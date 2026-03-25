package ratings;

import utilities.SystemLimits;
import utilities.exceptions.MaxInstancesException;

public class Rating {

    private int stars;
    private String message;
    private String name;
    private static int ratingCount = 0;

    public Rating(String name, int stars, String message) throws MaxInstancesException {
        this.name = name;
        this.stars = stars;
        this.message = message;
        if (ratingCount >= SystemLimits.MAXIMUM_INSTANCES) {
            throw new MaxInstancesException("More than 100 ratings have been created");
        }
        ratingCount++;
    }

    public void printRating() {
        System.out.println(this);
    }

    public void changeRating(int stars) {
        this.stars = stars;
    }

    public void changeMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.stars + "/5" + "\n" + message + "\n";
    }

    public int getStars() {
        return this.stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
