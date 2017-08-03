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
import tk.rynkbit.household.db.facade.RoomFacade;
import tk.rynkbit.household.models.Room;


public class RoomFragment extends Fragment {
    private static final String TAG = RoomFragment.class.getSimpleName();

    FloatingActionButton fabAddRoom;
    ListView listRooms;
    RoomListAdapter roomListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rooms, container, false);

        fabAddRoom = (FloatingActionButton) view.findViewById(R.id.fabAddRoom);
        listRooms = (ListView) view.findViewById(R.id.listRooms);
        roomListAdapter = new RoomListAdapter(view.getContext());

        listRooms.setAdapter(roomListAdapter);

        fabAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddRoom = new Intent(v.getContext(), RoomDetailActivity.class);
                v.getContext().startActivity(intentAddRoom);
            }
        });

        initRooms(view);

        return view;
    }

    @Override
    public void onResume() {
        initRooms(getView());

        super.onResume();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initRooms(View view) {
        RoomFacade roomFacade = new RoomFacade(view.getContext());
        List<Room> rooms = roomFacade.getRooms();
        Room[] array = new Room[rooms.size()];

        array = rooms.toArray(array);
        roomListAdapter.clear();
        roomListAdapter.addAll(array);
    }
}
