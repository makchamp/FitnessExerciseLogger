package makchamp.workoutlog;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class FragmentLegs extends Fragment {

    protected static ArrayList<LogBox> logBoxesLegs = new ArrayList<>();
    private Adapter2 adapter;
    private Button emptyLogButn;
    protected static LogBox chosenLogBox;

    public String path = Environment.getExternalStorageDirectory().toString() + "/FitnessLog";


    public FragmentLegs() {



    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_workout_log_all, null);
        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView_log_all);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter2(logBoxesLegs, this.getActivity());
        recyclerView.setAdapter(adapter);
        emptyLogButn = rootView.findViewById(R.id.emptyLogButn);


        if (logBoxesLegs.isEmpty()) {
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

        adapter.setOnItemClickListener(new Adapter2.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                chosenLogBox = logBoxesLegs.get(pos);

                FragmentAll.title.setName(chosenLogBox.getExerciseName());

                Intent toHistory = new Intent(getActivity(), History.class);

                toHistory.putParcelableArrayListExtra("ExerciseHistory",chosenLogBox.getExercises());
                startActivity(toHistory);
            }


        });

        return rootView;

    }

    @Override
    public void onStop() {
        super.onStop();
        write();

    }

    @Override
    public void onStart() {
        super.onStart();
         read();
    }

    public static FragmentLegs newInstance(ArrayList<LogBox> logBoxes) {

        Bundle args = new Bundle();

        args.putParcelableArrayList("logBoxes", logBoxes);

        FragmentLegs fragment = new FragmentLegs();
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

    public void read() {

        File file = new File(path + "/DataL.txt");

        ObjectInputStream objectinputstream = null;
        FileInputStream in = null;

        try {
            in = new FileInputStream(file);
            objectinputstream = new ObjectInputStream(in);
            if (logBoxesLegs.isEmpty())
                logBoxesLegs = (ArrayList<LogBox>) objectinputstream.readObject();
            if(WorkoutLog.addedExercises.isEmpty())
                WorkoutLog.addedExercises = (ArrayList<String>) objectinputstream.readObject();



        } catch (Exception e) {

        } finally {


            try {
                if (objectinputstream != null)
                    objectinputstream.close();

            } catch (IOException e) {
            }
        }
    }

    public void write() {

        File file = new File(path + "/DataL.txt");

        ObjectOutputStream oOut = null;
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(file);
            oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(logBoxesLegs);
            oOut.writeObject(WorkoutLog.addedExercises);

        } catch (Exception e) {

        } finally {

            try {

                if (oOut != null)
                    oOut.close();
            } catch (IOException e) {

            }

        }

    }



}
