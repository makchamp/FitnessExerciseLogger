package makchamp.workoutlog;

public class exercise_box {

    private String exercise;
    private boolean isClicked;

    public exercise_box(String exercise, boolean isClicked) {
        this.exercise = exercise;
        this.isClicked = isClicked;
    }

    public String getExercise() {
        return exercise;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }
}
