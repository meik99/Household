package tk.rynkbit.household.room;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import tk.rynkbit.household.R;
import tk.rynkbit.household.db.DBContract;
import tk.rynkbit.household.db.facade.RoomFacade;
import tk.rynkbit.household.db.facade.StepFacade;
import tk.rynkbit.household.models.DaoMaster;
import tk.rynkbit.household.models.DaoSession;
import tk.rynkbit.household.models.Room;
import tk.rynkbit.household.models.Step;
import tk.rynkbit.household.models.StepDao;
import tk.rynkbit.household.step.StepDialog;
import tk.rynkbit.household.step.StepDialogListener;

import org.greenrobot.greendao.query.Query;

import java.lang.reflect.Array;

@SuppressWarnings("unchecked")
class RoomDetailController implements View.OnClickListener, StepDialogListener {
    private final RoomDetailActivity mRoomDetailActivity;

    private final Button btnSave;
    private final EditText editName;
    private final FloatingActionButton fabAddStep;
    private final ListView listSteps;

    RoomDetailController(RoomDetailActivity roomDetailActivity) {
        mRoomDetailActivity = roomDetailActivity;
        btnSave = (Button) mRoomDetailActivity.findViewById(R.id.btnSave);
        editName = (EditText) mRoomDetailActivity.findViewById(R.id.editRoomName);
        fabAddStep = (FloatingActionButton) mRoomDetailActivity.findViewById(R.id.fabAddStep);
        listSteps = (ListView) mRoomDetailActivity.findViewById(R.id.listSteps);

        listSteps.setAdapter(new ArrayAdapter<Step>(mRoomDetailActivity, android.R.layout.simple_list_item_1));

        btnSave.setOnClickListener(this);
        fabAddStep.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnSave){
            if(!editName.getText().toString().isEmpty()){
                RoomFacade roomFacade = new RoomFacade(mRoomDetailActivity);
                StepFacade stepFacade = new StepFacade(mRoomDetailActivity);
                Room room = new Room();

                room.setName(editName.getText().toString());
                roomFacade.insert(room);

                ArrayAdapter<Step> arrayAdapter = (ArrayAdapter<Step>) listSteps.getAdapter();
                long count = arrayAdapter.getCount();

                for (int i = 0; i < count; i++){
                    Step step = arrayAdapter.getItem(i);
                    if(step != null){
                        stepFacade.insert(step);
                        room.getSteps().add(step);
                    }
                }

                room.update();

                mRoomDetailActivity.onBackPressed();
            }
        }else if(v == fabAddStep){
            StepDialog dialog = new StepDialog(v.getContext());
            dialog.addStepDialogListener(this);
            dialog.show();
        }
    }

    @Override
    public void onSuccess(Step step) {
        ((ArrayAdapter<Step>)listSteps.getAdapter()).add(step);
    }
}
