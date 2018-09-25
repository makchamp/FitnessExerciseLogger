package makchamp.workoutlog;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> implements Filterable{

    private ArrayList<Exercise> exerciseList;
    private ArrayList<Exercise> copyExerciseList;
    private OnItemClickListener clickListener;



    public Adapter(ArrayList<Exercise> exerciseList){

        this.exerciseList = exerciseList;
        copyExerciseList = new ArrayList<Exercise>(exerciseList);


    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_box, parent, false);
        viewHolder vh = new viewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder,  int position) {

        Exercise exercise = exerciseList.get(position);
        holder.exerciseTxt.setText(exercise.getName());

        String categories = "";
        String[] categoriesArr = exercise.getCategory();


        for(int i = 0; i < categoriesArr.length; i++){

            if(i < categoriesArr.length-1)
            categories += (categoriesArr[i] + ", ");
            else if (i == categoriesArr.length-1)
                categories += categoriesArr[i];

        }

       holder.categoryTxt.setText(categories);

    }



    @Override
    public int getItemCount() {

        return exerciseList.size();
    }


    @Override
    public Filter getFilter() {
        return filt;
    }

    private Filter filt = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Exercise> filtered = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0){
                filtered.addAll(copyExerciseList);
            }
            else{
                String chars = charSequence.toString().trim().toLowerCase();

                for (Exercise i : copyExerciseList){

                    if(i.getName().toLowerCase().contains(chars)){
                        filtered.add(i);
                    }
                }
            }
            FilterResults filteredResults = new FilterResults();
            filteredResults.values = filtered;

            return filteredResults ;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            exerciseList.clear();
            exerciseList.addAll((ArrayList<Exercise>)filterResults.values);
            notifyDataSetChanged();

        }
    };

    public void setOnItemClickListener(OnItemClickListener clickListener){

     this.clickListener = clickListener;
    }


    public interface OnItemClickListener {

        void onItemClick( int pos);
    }



    public class viewHolder extends RecyclerView.ViewHolder {

        private TextView exerciseTxt;
        private TextView categoryTxt;


        public viewHolder(final View itemView) {
            super(itemView);
            exerciseTxt = itemView.findViewById(R.id.exerciseTxt);
            categoryTxt = itemView.findViewById(R.id.categoryTxt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   if(clickListener != null){

                       if(getAdapterPosition() != -1)
                           clickListener.onItemClick(getAdapterPosition());


                   }

                }
            });
        }
    }


}
