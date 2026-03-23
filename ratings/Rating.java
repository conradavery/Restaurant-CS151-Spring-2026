package ratings;

public class Rating {

    private int stars;
    private String message;
    private String name;

    public Rating(String name, int stars, String message) {
        this.name = name;
        this.stars = stars;
        this.message = message;
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
