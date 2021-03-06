package makchamp.workoutlog;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;

public class Exercise implements Parcelable, Serializable, Comparable<Exercise>{

    private String name;
    private String[] category;
    private String date;
    private double weight;
    private int reps;
    private String notes;
    private boolean custom;

    public Exercise() {
    }

    public Exercise(String name) {
        this.name = name;
    }

    public Exercise(String name, String[] category) {
        this.name = name;
        this.category = category;
    }

    public Exercise(String name, double weight, int reps) {
        this.name = name;
        this.weight = weight;
        this.reps = reps;
    }

    public Exercise(String name, String[] category, String date, double weight, int reps, String notes, boolean custom) {
        this.name = name;
        this.category = category;
        this.date = date;
        this.weight = weight;
        this.reps = reps;
        this.notes = notes;
        this.custom = custom;
    }

    protected Exercise(Parcel in) {
        name = in.readString();
        category = in.createStringArray();
        weight = in.readDouble();
        date = in.readString();
        reps = in.readInt();
        notes = in.readString();
        custom = in.readByte() != 0;
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    public String getName() {
        return name;
    }

    public String[] getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public double getWeights() {
        return weight;
    }

    public int getReps() {
        return reps;
    }
    public boolean getCustom(){
        return custom;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setWeights(double weights) {
        this.weight = weights;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
    public void setCustom(boolean custom){
        this.custom = custom;
    }
    public void setCategory(String[] category){
        this.category = category;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isCustom() {
        return custom;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {


        parcel.writeString(name);
        parcel.writeStringArray(category);
        parcel.writeDouble(weight);
        parcel.writeString(date);
        parcel.writeInt(reps);
        parcel.writeString(notes);
        parcel.writeByte((byte) (custom ? 1 : 0));
    }

    @Override
    public int compareTo(@NonNull Exercise exercise) {

        int c = 0;

        if(weight < exercise.weight)
            c = 1;
       else if(weight > exercise.weight)
            c=-1;

        else if(weight == exercise.weight){

            if(reps < exercise.reps)
                c=1;
            else if (reps > exercise.reps)
                c=-1;
            else
                c=0;
        }


        return c;
    }
}
