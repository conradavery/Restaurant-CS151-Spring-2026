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
        if(ratingCount > SystemLimits.MAXIMUM_INSTANCES){
            throw new MaxInstancesException("More than 100 ratings have been created");
        }
        ratingCount ++;
    }

    public void printRating() {
        System.out.println(this.name + " - " + this.stars + "/5");
        System.out.println(message);
    }

    public void changeRating(int stars) {
        this.stars = stars;
    }

    public void changeMessage(String message) {
        this.message = message;
    }
}
