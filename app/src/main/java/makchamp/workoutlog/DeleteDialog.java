package makchamp.workoutlog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

public class DeleteDialog extends AppCompatDialogFragment {

    protected static boolean toDelete = false;


    public DeleteDialog() {



        }



    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to delete your entire log for this exercise ? ");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                int pos = getArguments().getInt("position");

                toDelete = true;


            }
            });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {



            }
        });


        return builder.create();
    }
}
