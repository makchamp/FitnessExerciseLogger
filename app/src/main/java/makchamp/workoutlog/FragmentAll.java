package makchamp.workoutlog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


public class FragmentAll extends Fragment {


    protected static Exercise title = new Exercise();
    protected static Adapter2 adapter2;
    private Button emptyLogButn;
    protected static LogBox chosenLogBox = new LogBox("Exercise");



    public FragmentAll() {

    }

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);


    }


    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View rootView =  inflater.inflate(R.layout.fragment_workout_log_all, container, false);
        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView_log_all);
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter2 = new Adapter2(WorkoutLog.logBoxesALL, this.getActivity());
        recyclerView.setAdapter(adapter2);
        emptyLogButn = rootView.findViewById(R.id.emptyLogButn);



        if (WorkoutLog.logBoxesALL.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyLogButn.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyLogButn.setVisibility(View.GONE);

        }


        emptyLogButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent toAddLog = new Intent(getContext(), addToLog.class);
                startActivity(toAddLog);
            }
        });


               adapter2.setOnItemClickListener(new Adapter2.OnItemClickListener() {
                    @Override
                    public void onItemClick(int pos) {
                       chosenLogBox = Adapter2.logBoxList.get(pos);

                        title.setName(chosenLogBox.getExerciseName());



                        Intent toHistory = new Intent(getActivity(), History.class);
                        toHistory.putParcelableArrayListExtra("ExerciseHistory",chosenLogBox.getExercises());

                        startActivity(toHistory);
                    }



                });


        return rootView;

    }





        public static FragmentAll newInstance(ArrayList<LogBox> logBoxes) {

             Bundle args = new Bundle();

                 args.putParcelableArrayList("logBoxes", logBoxes);

                  FragmentAll fragment = new FragmentAll();
                     fragment.setArguments(args);

          return fragment;
        }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();



        }
    }




}
