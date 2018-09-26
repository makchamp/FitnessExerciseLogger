package makchamp.workoutlog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        Button myLogButn = (Button) findViewById(R.id.myLogButn);
       // Button newExerciseButn = (Button) findViewById(R.id.newExerciseButn);
        Button searchExercisesButn = (Button) findViewById(R.id.searchExercisesButn);
        Button settingsButn = (Button)findViewById(R.id.settingsButn);


       myLogButn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent i1 = new Intent(getApplicationContext(), WorkoutLog.class);
                startActivity(i1);
            }
        });



       /* newExerciseButn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent i3 = new Intent(getApplicationContext(), AddExercise.class);
                startActivity(i3);
            }
        });
*/
        searchExercisesButn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent i4 = new Intent(getApplicationContext(), addToLog.class);
                startActivity(i4);
            }
        });

        settingsButn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent i5 = new Intent(getApplicationContext(), Settings.class);
                startActivity(i5);
            }
        });






        }


}
