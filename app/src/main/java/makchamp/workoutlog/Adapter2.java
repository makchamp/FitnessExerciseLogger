package makchamp.workoutlog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.viewHolder>{

    protected static  ArrayList<LogBox> logBoxList;
    private int mExpandedPosition =  -1;
    private OnItemClickListener clickListener;
    private Context context;
    protected Exercise forTitle;



    public Adapter2(ArrayList<LogBox> alllogBoxList, Context context){

        this.logBoxList = new ArrayList<>(alllogBoxList);
        this.context = context;


    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.log_box, null);
        viewHolder viewHolder = new viewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder,  final int position) {

        LogBox logBox = logBoxList.get(position);
        if(logBox != null) {
            holder.exerciseName.setText(logBox.getExerciseName());

            if(logBox.getExercises().size() == 0){
                holder.weight.setText("N/A");
                holder.reps.setText("N/A");

            }
            else {

                //Show highlight lift

                holder.weight.setText(Double.toString(logBox.getExercises().get(logBox.getExercises().size()-1).getWeight()));
                holder.reps.setText(Integer.toString(logBox.getExercises().get(logBox.getExercises().size()-1).getReps()));

            }
        }



        final boolean expanded = (position == mExpandedPosition);

        holder.moreOptions.setVisibility(expanded ? View.VISIBLE: View.GONE);
        holder.itemView.setActivated(expanded);

        if (expanded) {
            mExpandedPosition = position;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mExpandedPosition = (expanded ? -1 : position);

                notifyItemChanged(position);
                notifyItemChanged(mExpandedPosition);

            }
        });


    }





    @Override
    public int getItemCount() {

        return logBoxList.size();
    }



    public void setOnItemClickListener(OnItemClickListener clickListener){

        this.clickListener = clickListener;
    }


    public interface OnItemClickListener {

        void onItemClick(int pos);


    }



    public AlertDialog deleteDialog(final int pos){


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure you want to delete your entire log for this exercise ? ");

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                LogBox toDelete;

                toDelete = logBoxList.get(pos);
                Iterator<LogBox> iterator;

              for(String j : toDelete.getCategory()){

                  if(j.equals("Chest")) {
                     iterator = FragmentChest.logBoxesChest.iterator();

                      while(iterator.hasNext()){

                          LogBox it = iterator.next();

                          if(toDelete.getExerciseName().equals(it.getExerciseName()))
                              iterator.remove();
                      }

                  }
                  if(j.equals("Arms")){
                      iterator = FragmentArms.logBoxesArms.iterator();

                      while(iterator.hasNext()){

                          LogBox it = iterator.next();

                          if(toDelete.getExerciseName().equals(it.getExerciseName()))
                              iterator.remove();
                      }

                  }
                  if(j.equals("Back")){
                      iterator = FragmentBack.logBoxesBack.iterator();

                      while(iterator.hasNext()){

                          LogBox it = iterator.next();

                          if(toDelete.getExerciseName().equals(it.getExerciseName()))
                              iterator.remove();
                      }

                  }
                  if(j.equals("Legs")){
                      iterator = FragmentLegs.logBoxesLegs.iterator();

                      while(iterator.hasNext()){

                          LogBox it = iterator.next();

                          if(toDelete.getExerciseName().equals(it.getExerciseName()))
                              iterator.remove();
                      }

                  }
                  if(j.equals("Shoulders")){

                      iterator = FragmentShoulders.logBoxesShoulders.iterator();

                      while(iterator.hasNext()){

                          LogBox it = iterator.next();

                          if(toDelete.getExerciseName().equals(it.getExerciseName()))
                              iterator.remove();
                      }

                  }

              }

               iterator = WorkoutLog.logBoxesALL.iterator();

                while(iterator.hasNext()){

                    LogBox it = iterator.next();

                    if(toDelete.getExerciseName().equals(it.getExerciseName()))
                        iterator.remove();
                }


               WorkoutLog.addedExercises.remove(logBoxList.get(pos).getExerciseName());
                logBoxList.remove(pos);
                notifyItemRemoved(pos);
                notifyDataSetChanged();

                Toast.makeText(context, toDelete.getExerciseName() + " has been removed from your log", Toast.LENGTH_SHORT).show();

            }
        });

        return  builder.create();
    }



    public class viewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView exerciseName;
        private TextView weight;
        private TextView reps;
        private RelativeLayout moreOptions;
        private Button viewHistory;
        private ImageButton delete;
        private RecyclerView recyclerView;
        private Button emptyLogButn;
        private ImageButton add;


        public viewHolder(final View itemView) {
            super(itemView);


            recyclerView = itemView.findViewById(R.id.recyclerView_log_all);
            cardView =  itemView.findViewById(R.id.logCardBox);
            exerciseName = itemView.findViewById(R.id.logBox_exerciseName);
            weight = itemView.findViewById(R.id.logBox_weight_value);
            reps = itemView.findViewById(R.id.logBox_reps_value);
            moreOptions = itemView.findViewById(R.id.logBox_options);
            viewHistory = itemView.findViewById(R.id.logBox_viewHistory);
            delete = itemView.findViewById(R.id.logBox_delete);
            emptyLogButn = itemView.findViewById(R.id.emptyLogButn);
            add = itemView.findViewById(R.id.logBox_add);


            viewHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(clickListener != null){

                        if(getAdapterPosition() != -1)
                        clickListener.onItemClick(getAdapterPosition());


                    }

                }
            });
            

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(clickListener != null){

                        if(getAdapterPosition() != -1){

                           AlertDialog deleteConfirm = deleteDialog(getAdapterPosition());
                           deleteConfirm.show();

                        }

                    }
                }

            });

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(clickListener != null){

                        if(getAdapterPosition() != -1) {

                            LogBox y = logBoxList.get(getAdapterPosition());

                            Intent toStats = new Intent(context, Stats.class);
                            toStats.putExtra("ExerciseName", y.getExerciseName());
                            toStats.putExtra("CategoryList", y.getCategory());
                            toStats.putExtra("fromLog", true);
                            context.startActivity(toStats);

                        }
                    }



                }
            });




         }


   }
}



