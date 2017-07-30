package tk.rynkbit.household.room;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import tk.rynkbit.household.R;
import tk.rynkbit.household.models.DaoMaster;
import tk.rynkbit.household.models.DaoSession;
import tk.rynkbit.household.models.Room;

/**
 * Created by michael on 30.07.17.
 */

public class RoomFragment extends Fragment {
    private static final String TAG = RoomFragment.class.getSimpleName();

    FloatingActionButton fabAddRoom;
    ListView listRooms;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(container != null){
//            container.removeAllViews();
        }
        View view = inflater.inflate(R.layout.fragment_rooms, container, false);

        fabAddRoom = (FloatingActionButton) view.findViewById(R.id.fabAddRoom);
        listRooms = (ListView) view.findViewById(R.id.listRooms);

        fabAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddRoom = new Intent(v.getContext(), RoomDetailActivity.class);
                v.getContext().startActivity(intentAddRoom);
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initRooms();
    }

    private void initRooms() {
        DaoSession daoSession =
                new DaoMaster(
                        new DaoMaster.DevOpenHelper(
                                this.getActivity().getApplicationContext(), "householdDB")
                                .getWritableDb())
                        .newSession();
        List<Room> rooms = daoSession.queryBuilder(Room.class)
                .build().list();
    }
}
