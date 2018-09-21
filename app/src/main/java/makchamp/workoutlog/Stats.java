package makchamp.workoutlog;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;



public class Stats extends AppCompatActivity {

    private boolean fromLog;
    String name;
    String[] category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add to Your Log");


        fromLog = getIntent().getBooleanExtra("fromLog", false);

         TextView exerciseName = findViewById(R.id.exerciseNameStats);
        if (!fromLog && addToLog.temp != null) {
            exerciseName.setText(addToLog.temp.getName());
            name = addToLog.temp.getName();
            category = addToLog.temp.getCategory();
        }
        else{

            name = getIntent().getStringExtra("ExerciseName");
            exerciseName.setText(name);
            category = getIntent().getStringArrayExtra("CategoryList");

        }

        fromLog = false;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            EditText weightText = (EditText) findViewById(R.id.weight_value);
            double weight;
            EditText repsText = (EditText) findViewById(R.id.reps_value);
            int reps;
            EditText dateText = (EditText) findViewById(R.id.date_value);
            Date date;
            EditText notesText = (EditText) findViewById(R.id.noteBox);
            String notes;


            @Override
            public void onClick(View view) {

                //Take User Input

                try{
                    if (weightText.getText() != null &&  weightText.getText().length() > 0)
                        weight = (Double.parseDouble(weightText.getText().toString()));
                    else
                        weight = 0;

                    if (repsText.getText() != null  && weightText.getText().length() > 0)
                        reps = (Integer.parseInt(repsText.getText().toString()));
                    else
                        reps = 0;

                    if (notesText.getText() != null && weightText.getText().length() > 0)
                        notes = notesText.getText().toString();
                }catch (NumberFormatException e){


                }


                Exercise exerciseToLog = new Exercise(name, category, null, weight, reps, notes, false);
                LogBox  logBoxToLog =  new LogBox(name, category, new ArrayList<Exercise>(Arrays.asList(exerciseToLog)));
                LogBox lb = new LogBox();



                if (!WorkoutLog.addedExercises.contains(name)) {


                    WorkoutLog.logBoxesALL.add(logBoxToLog);
                    WorkoutLog.addedExercises.add(name);

                    for(String i : exerciseToLog.getCategory()){

                        if (i.equals("Chest"))
                            FragmentChest.logBoxesChest.add(new LogBox(name, category, new ArrayList<Exercise>(Arrays.asList(exerciseToLog))));
                        if(i.equals("Arms"))
                            FragmentArms.logBoxesArms.add(new LogBox(name, category, new ArrayList<Exercise>(Arrays.asList(exerciseToLog))));
                        if(i.equals("Back"))
                            FragmentBack.logBoxesBack.add(new LogBox(name, category, new ArrayList<Exercise>(Arrays.asList(exerciseToLog))));
                        if(i.equals("Legs"))
                            FragmentLegs.logBoxesLegs.add(new LogBox(name, category, new ArrayList<Exercise>(Arrays.asList(exerciseToLog))));
                        if(i.equals("Shoulders"))
                            FragmentShoulders.logBoxesShoulders.add(new LogBox(name, category, new ArrayList<Exercise>(Arrays.asList(exerciseToLog))));

                    }

                    Toast toast1 =   Toast.makeText(getApplicationContext(), ("Added \n" + name + "\n to Log"), Toast.LENGTH_LONG);
                    TextView tv = (TextView) toast1.getView().findViewById(android.R.id.message);
                    tv.setGravity(Gravity.CENTER);
                    toast1.show();



                } else {

                    for(LogBox j : WorkoutLog.logBoxesALL){

                        if(j.getExerciseName().equals(exerciseToLog.getName())) {
                            j.getExercises().add(exerciseToLog);
                            lb= j;

                        }
                    }


                    for(String i : exerciseToLog.getCategory()){

                        if (i.equals("Chest")){

                            for(LogBox j : FragmentChest.logBoxesChest){

                                if(j.getExerciseName().equals(exerciseToLog.getName()))
                                    j.setExercises(lb.getExercises());
                            }
                        }

                        if(i.equals("Arms")){

                            for(LogBox j : FragmentArms.logBoxesArms){

                                if(j.getExerciseName().equals(exerciseToLog.getName()))
                                    j.setExercises(lb.getExercises());
                            }
                        }
                        if(i.equals("Back")){

                            for(LogBox j : FragmentBack.logBoxesBack){

                                if(j.getExerciseName().equals(exerciseToLog.getName()))
                                    j.setExercises(lb.getExercises());
                            }
                        }
                        if(i.equals("Legs")){

                            for(LogBox j : FragmentLegs.logBoxesLegs){

                                if(j.getExerciseName().equals(exerciseToLog.getName()))
                                    j.setExercises(lb.getExercises());
                            }
                        }
                        if(i.equals("Shoulders")){

                            for(LogBox j : FragmentShoulders.logBoxesShoulders){

                                if(j.getExerciseName().equals(exerciseToLog.getName()))
                                    j.setExercises(lb.getExercises());
                            }
                        }

                    }





                    Toast toast2 = Toast.makeText(getApplicationContext(), "Added Record to \n" + name + "\n History", Toast.LENGTH_LONG);
                    TextView tv2 = (TextView) toast2.getView().findViewById(android.R.id.message);
                    tv2.setGravity(Gravity.CENTER);
                    toast2.show();

                }





                Intent intent = new Intent(Stats.this, WorkoutLog.class);
                startActivity(intent);


            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public void onBackPressed() {
        super.onBackPressed();

        Intent i;
        if(fromLog){
            i = new Intent(Stats.this, WorkoutLog.class);
            startActivity(i);
        }



    }



}