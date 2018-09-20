package makchamp.workoutlog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    protected  static  ArrayList<Exercise> exerciseHistoryList = new ArrayList<>();
    private RecyclerView recycleView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter3 adapter;
    PopupMenu popupMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Exercise History");

        //load();

        Intent intent = getIntent();
        TextView title = (TextView)findViewById(R.id.exTitle);
        title.setText(FragmentAll.title.getName());

        exerciseHistoryList = intent.getParcelableArrayListExtra("ExerciseHistory");
        adapter = new Adapter3(exerciseHistoryList, this);
        layoutManager = new LinearLayoutManager(this);
        recycleView = findViewById(R.id.recyclerView_history);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(adapter);
        recycleView.setHasFixedSize(true);






        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


 /*   public void save(){

        SharedPreferences sharedPreferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String toJson = gson.toJson(exerciseHistoryList);
        editor.putString("historyList", toJson);
        editor.apply();

    }

    public void load(){

        SharedPreferences sharedPreferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String fromJson = sharedPreferences.getString("historyList", null);
        Type type = new TypeToken<ArrayList<Exercise>>() {}.getType();
        exerciseHistoryList = gson.fromJson(fromJson, type);

        if(exerciseHistoryList == null)
            exerciseHistoryList = new ArrayList<>();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        save();
    }*/
}
