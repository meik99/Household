package tk.rynkbit.household.room;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import tk.rynkbit.household.R;
import tk.rynkbit.household.models.DaoMaster;
import tk.rynkbit.household.models.DaoSession;
import tk.rynkbit.household.models.Room;

import org.greenrobot.greendao.query.Query;

/**
 * Created by michael on 30.07.17.
 */

public class RoomDetailController implements View.OnClickListener {
    private final RoomDetailActivity mRoomDetailActivity;

    private final Button btnSave;
    private final EditText editName;

    public RoomDetailController(RoomDetailActivity roomDetailActivity) {
        mRoomDetailActivity = roomDetailActivity;
        btnSave = (Button) mRoomDetailActivity.findViewById(R.id.btnSave);
        editName = (EditText) mRoomDetailActivity.findViewById(R.id.editRoomName);

        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnSave){
            if(editName.getText().toString().isEmpty() == false){
                DaoSession daoSession = new DaoMaster(
                        new DaoMaster.DevOpenHelper(mRoomDetailActivity, "householdDB")
                                .getWritableDb())
                        .newSession();
                Room room = new Room();

                room.setName(editName.getText().toString());
                daoSession.insert(room);

                daoSession = null;
                mRoomDetailActivity.onBackPressed();
            }
        }
    }
}
