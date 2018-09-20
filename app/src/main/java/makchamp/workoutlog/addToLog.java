package makchamp.workoutlog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class addToLog extends AppCompatActivity {

    private RecyclerView recycleView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter;
    ArrayList<Exercise> exerciseList = new ArrayList<>();
    protected static Exercise temp;
    protected static boolean onBack = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_log);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAddLog = new Intent(addToLog.this, WorkoutLog.class);
                startActivity(toAddLog);
            }
        });


        // Hardcoded exercise list

        exerciseList.add(new Exercise("Arnold Dumbbell Press", new String[]{"Shoulders"}));
        exerciseList.add(new Exercise("Barbell Bench Press (declined)", new String[]{"Chest"}));
        exerciseList.add(new Exercise("Barbell Bench Press (flat)", new String[]{"Chest"}));
        exerciseList.add(new Exercise("Barbell Bench Press (inclined)", new String[]{"Chest"}));
        exerciseList.add(new Exercise("Barbell Bench Press (close-grip)", new String[]{"Chest", "Triceps"}));
        exerciseList.add(new Exercise("Barbell Curl", new String[]{"Arms"}));
        exerciseList.add(new Exercise("Barbell Overhead Press", new String[]{"Shoulders"}));
        exerciseList.add(new Exercise("Cable Cross-over (low to high)", new String[]{"Chest"}));
        exerciseList.add(new Exercise("Cable Cross-over", new String[]{"Chest"}));
        exerciseList.add(new Exercise("Cable Curl", new String[]{"Arms"}));
        exerciseList.add(new Exercise("Cable Pull-Down", new String[]{"Back"}));
        exerciseList.add(new Exercise("Cable Row", new String[]{"Back"}));
        exerciseList.add(new Exercise("Cable Skull Crusher", new String[]{"Arms"}));
        exerciseList.add(new Exercise("Chin-up (wide-grip)", new String[]{"Back", "Arms"}));
        exerciseList.add(new Exercise("Concentration Curl", new String[]{"Arms"}));
        exerciseList.add(new Exercise("Deadlift", new String[]{"Legs", "Back"}));
        exerciseList.add(new Exercise("Dip (assisted)", new String[]{"Arms", "Chest"}));
        exerciseList.add(new Exercise("Dip (close-grip)", new String[]{"Arms", "Chest"}));
        exerciseList.add(new Exercise("Dip (wide-grip)", new String[]{"Arms", "Chest"}));
        exerciseList.add(new Exercise("Dumbbell Bench Press (declined)", new String[]{"Chest"}));
        exerciseList.add(new Exercise("Dumbbell Bench Press (flat)", new String[]{"Chest"}));
        exerciseList.add(new Exercise("Dumbbell Bench Press (inclined)", new String[]{"Chest"}));
        exerciseList.add(new Exercise("Dumbbell Curl", new String[]{"Arms"}));
        exerciseList.add(new Exercise("Dumbbell Fly (declined)", new String[]{"Chest"}));
        exerciseList.add(new Exercise("Dumbbell Fly (flat)", new String[]{"Chest"}));
        exerciseList.add(new Exercise("Dumbbell Fly (inclined)", new String[]{"Chest"}));
        exerciseList.add(new Exercise("Dumbbell Overhead Press", new String[]{"Shoulders"}));
        exerciseList.add(new Exercise("Dumbbell Skull Crusher", new String[]{"Arms"}));
        exerciseList.add(new Exercise("Ez-Bar Curl", new String[]{"Arms"}));
        exerciseList.add(new Exercise("Ez-Bar Skull Crusher", new String[]{"Arms"}));
        exerciseList.add(new Exercise("Floor Press", new String[]{"Chest"}));
        exerciseList.add(new Exercise("Front Cable Raise", new String[]{"Chest", "Arms"}));
        exerciseList.add(new Exercise("Front Plate Raise", new String[]{"Shoulders", "Chest", "Back"}));
        exerciseList.add(new Exercise("Hammer Curl", new String[]{"Arms"}));
        exerciseList.add(new Exercise("Landmine Press", new String[]{"Shoulders"}));
        exerciseList.add(new Exercise("Leg Press", new String[]{"Legs"}));
        exerciseList.add(new Exercise("Lunge", new String[]{"Legs"}));
        exerciseList.add(new Exercise("Machine Bench Press (declined)", new String[]{"Chest"}));
        exerciseList.add(new Exercise("Machine Bench Press (flat)", new String[]{"Chest"}));
        exerciseList.add(new Exercise("Machine Overhead Press", new String[]{"Shoulders"}));
        exerciseList.add(new Exercise("Pullover", new String[]{"Chest", "Shoulder", "Back"}));
        exerciseList.add(new Exercise("Push-up", new String[]{"Chest", "Arms" }));
        exerciseList.add(new Exercise("Push-up (close-grip)", new String[]{"Chest", "Arms"}));
        exerciseList.add(new Exercise("Reverse Machine Fly", new String[]{"Chest", "Shoulders"}));
        exerciseList.add(new Exercise("Rope Face Pull", new String[]{"Shoulders", "Chest"}));
        exerciseList.add(new Exercise("Rope Pull-Down", new String[]{"Arms"}));
        exerciseList.add(new Exercise("Seated Calf Raise", new String[]{"Legs"}));
        exerciseList.add(new Exercise("Seated Dumbbell Curl (inclined)", new String[]{"Arms"}));
        exerciseList.add(new Exercise("Seated Wrist Curl", new String[]{"Arms"}));
        exerciseList.add(new Exercise("Seated Machine Fly", new String[]{"Chest"}));
        exerciseList.add(new Exercise("Shrugs", new String[]{"Back"}));
        exerciseList.add(new Exercise("Side Lateral Raise (one arm)", new String[]{"Shoulders"}));
        exerciseList.add(new Exercise("Side Lateral Raise (two arms)", new String[]{"Shoulders"}));
        exerciseList.add(new Exercise("Single Arm Dumbbell Row", new String[]{"Back"}));
        exerciseList.add(new Exercise("Spider Curl", new String[]{"Arms"}));
        exerciseList.add(new Exercise("Squat ", new String[]{"Legs"}));
        exerciseList.add(new Exercise("Standing Calf Raise", new String[]{"Legs"}));
        exerciseList.add(new Exercise("Straight Plate Press", new String[]{"Shoulders", "Chest"}));
        exerciseList.add(new Exercise("Upright Barbell Row", new String[]{"Back"}));
        exerciseList.add(new Exercise("Upright Dumbbell Row", new String[]{"Back"}));


        adapter = new Adapter(exerciseList);
        layoutManager = new LinearLayoutManager(this);
        recycleView = findViewById(R.id.recyclerView);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(adapter);
        recycleView.setHasFixedSize(true);



        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {


               Exercise chosenExercise = exerciseList.get(pos);

               temp = new Exercise(chosenExercise.getName(), chosenExercise.getCategory());


                Intent toStats = new Intent(addToLog.this, Stats.class);
                startActivity(toStats);


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_to_log, menu);
        MenuItem searchField = menu.findItem(R.id.action_settings_search2);
        SearchView searchView = (SearchView) searchField.getActionView();


        searchView.setIconified(false);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

              adapter.getFilter().filter(newText);

                return false;
            }
        });

        return true;
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(addToLog.this, WorkoutLog.class);
        startActivity(i);
    }

}
