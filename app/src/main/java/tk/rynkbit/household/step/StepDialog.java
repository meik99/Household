package tk.rynkbit.household.step;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import tk.rynkbit.household.R;
import tk.rynkbit.household.db.DBContract;
import tk.rynkbit.household.db.facade.StepFacade;
import tk.rynkbit.household.models.DaoMaster;
import tk.rynkbit.household.models.DaoSession;
import tk.rynkbit.household.models.Step;

public class StepDialog extends AlertDialog implements DialogInterface.OnClickListener{
    private final EditText editStepName;

    private List<StepDialogListener> stepDialogListenerList;

    public StepDialog(Context context) {
        super(context);

        @SuppressLint("InflateParams") View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_step, null);

        editStepName = (EditText) dialogView.findViewById(R.id.editStepName);
        stepDialogListenerList = new LinkedList<>();

        setTitle("Enter Step");
        setView(dialogView);
        setButton(Dialog.BUTTON_POSITIVE, "Add", this);
        setButton(Dialog.BUTTON_NEGATIVE, "Cancel", this);
    }

    public void addStepDialogListener(StepDialogListener stepDialogListener){
        if(stepDialogListener != null){
            stepDialogListenerList.add(stepDialogListener);
        }
    }

    public void removeStepDialogListener(StepDialogListener stepDialogListener){
        stepDialogListenerList.remove(stepDialogListener);
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i == Dialog.BUTTON_POSITIVE){
            if(!editStepName.getText().toString().isEmpty()){
                Step step = new Step();
                step.setName(editStepName.getText().toString());

                for (StepDialogListener listener :
                        stepDialogListenerList) {
                    listener.onSuccess(step);
                }
            }else{
                Toast.makeText(getContext(), "Stepname cannot be empty", Toast.LENGTH_SHORT)
                        .show();
                dialogInterface.dismiss();
            }
        }
    }
}
