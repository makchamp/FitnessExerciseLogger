package makchamp.workoutlog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adapter3 extends RecyclerView.Adapter<Adapter3.viewHolder> {

    private ArrayList<Exercise> exerciseList;
    private ArrayList<Exercise> exerciseListCopy;
    private int mExpandedPosition =  -1;
    private OnItemClickListener clickListener;
    private Context context;
    private LogBox logBox;


    public Adapter3(LogBox logBox) {
        this.logBox = logBox;
    }

    public Adapter3(ArrayList<Exercise> exList, Context context){

        this.exerciseList = new ArrayList<>(exList);
        this.context = context;


    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_box, null);
        viewHolder viewHolder = new viewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder,  final int position) {

        Exercise exercise = exerciseList.get(position);
        if(exercise != null) {
            holder.weight.setText(Double.toString(exercise.getWeight()));
            holder.reps.setText(Integer.toString(exercise.getReps()));
            holder.notes.setText(exercise.getNotes());

            if (holder.date == null)
                holder.date.setText("Date N/A");

        }




      /*  final boolean expanded = (position == mExpandedPosition);

        holder.viewNotes.setVisibility(expanded ? View.VISIBLE: View.GONE);
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
*/

    }



    @Override
    public int getItemCount() {

        return exerciseList.size();
    }

    public void setOnItemClickListener(OnItemClickListener clickListener){

        this.clickListener = clickListener;
    }



    public interface OnItemClickListener {

        void onItemClick( int pos);
    }


    public AlertDialog deleteDialog(final int pos){


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure you want to delete this record ? ");

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Exercise toDelete = exerciseList.get(pos);
                String[] categories = toDelete.getCategory();

                History.exerciseHistoryList.remove(toDelete);
                exerciseList.remove(pos);
                notifyItemRemoved(pos);
                notifyDataSetChanged();

            // This is very bad design. Eventually needs to be changed for the same object across the fragments.


                for(String j : categories) {

                    if (j.equals("Chest")) {

                        for (LogBox k : FragmentChest.logBoxesChest) {

                            if (k.getExerciseName().equals(toDelete.getName()))
                                k.setExercises(exerciseList);

                        }
                    }
                        if (j.equals("Arms")) {


                            for (LogBox k : FragmentArms.logBoxesArms) {

                                if (k.getExerciseName().equals(toDelete.getName()))
                                    k.setExercises(exerciseList);
                            }

                        }
                        if (j.equals("Back")) {

                            for (LogBox k : FragmentBack.logBoxesBack) {

                                if (k.getExerciseName().equals(toDelete.getName()))
                                    k.setExercises(exerciseList);
                            }


                        }
                        if (j.equals("Legs")) {

                            for (LogBox k : FragmentLegs.logBoxesLegs) {

                                if (k.getExerciseName().equals(toDelete.getName()))
                                    k.setExercises(exerciseList);
                            }


                        }

                        if (j.equals("Shoulders")) {

                            for (LogBox k : FragmentShoulders.logBoxesShoulders) {

                                if (k.getExerciseName().equals(toDelete.getName()))
                                    k.setExercises(exerciseList);
                            }

                        }
                    }


                    for (LogBox k : WorkoutLog.logBoxesALL) {

                        if (k.getExerciseName().equals(toDelete.getName()))
                            k.setExercises(exerciseList);
                    }

                    Toast.makeText(context, " Record has been removed from your log", Toast.LENGTH_SHORT).show();



            }
        });

        return  builder.create();
    }



    public LogBox getLogBox() {
        return logBox;
    }

    public void setLogBox(LogBox logBox) {
        this.logBox = logBox;
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView weight;
        private TextView reps;
        private TextView notes;
        private ImageButton imageButton;
        private TextView date;
        private RelativeLayout viewNotes;
        private MenuItem edit;
        private MenuItem delete;
        private MenuItem toggleNotes;


        public viewHolder(final View itemView) {
            super(itemView);


            cardView =  itemView.findViewById(R.id.historyCardBox);
            weight = itemView.findViewById(R.id.history_weight_value);
            reps = itemView.findViewById(R.id.history_reps_value);
            imageButton = itemView.findViewById(R.id.spin);
            notes = itemView.findViewById(R.id.history_notes);
            viewNotes = itemView.findViewById(R.id.viewNotes);
            date = itemView.findViewById(R.id.history_date);
            edit = itemView.findViewById(R.id.history_Edit);
            delete = itemView.findViewById(R.id.history_Delete);
            toggleNotes = itemView.findViewById(R.id.history_toggle_notes);



            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final PopupMenu popupMenu = new PopupMenu(context, view);
                    MenuInflater menuInflater = popupMenu.getMenuInflater();
                    menuInflater.inflate(R.menu.history_card_menu, popupMenu.getMenu());
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupMenu.show();


                        }
                    });
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {

                                AlertDialog deleteConfirm = deleteDialog(getAdapterPosition());
                                deleteConfirm.show();
                                return true;


                        }
                    });
                }
            });





        }


    }
}



