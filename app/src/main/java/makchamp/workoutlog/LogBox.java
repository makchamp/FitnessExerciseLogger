package makchamp.workoutlog;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class LogBox implements Parcelable, Serializable{

    private String exerciseName;
    private String[] category;
    private ArrayList<Exercise> exercises;

    public LogBox() {
    }

    public LogBox(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public LogBox(String exerciseName, String[] category, ArrayList<Exercise> exercises) {
        this.exerciseName = exerciseName;
        this.category = category;
        this.exercises = exercises;
    }

    public LogBox(LogBox lb){

        exerciseName = lb.exerciseName;
        category = lb.category;
        exercises = lb.exercises;
    }

    protected LogBox(Parcel in) {
        exerciseName = in.readString();
        category = in.createStringArray();
        exercises = in.createTypedArrayList(Exercise.CREATOR);

    }

    public static final Creator<LogBox> CREATOR = new Creator<LogBox>() {
        @Override
        public LogBox createFromParcel(Parcel in) {
            return new LogBox(in);
        }

        @Override
        public LogBox[] newArray(int size) {
            return new LogBox[size];
        }
    };

    public String getExerciseName() {
        return exerciseName;
    }

    public String[] getCategory() {
        return category;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setCategory(String[] category) {
        this.category = category;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(exerciseName);
        parcel.writeStringArray(category);
    }
}
