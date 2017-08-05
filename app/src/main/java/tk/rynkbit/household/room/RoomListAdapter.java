package tk.rynkbit.household.room;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import tk.rynkbit.household.R;
import tk.rynkbit.household.db.DBContract;
import tk.rynkbit.household.models.DaoMaster;
import tk.rynkbit.household.models.DaoSession;
import tk.rynkbit.household.models.Room;
import tk.rynkbit.household.models.Step;


class RoomListAdapter extends ArrayAdapter<Room>{
    RoomListAdapter(Context context) {
        super(context, R.layout.item_room);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Room room = this.getItem(position);
        View v;

        if(room != null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.item_room, parent, false);
            v.setTag(room);
            TextView txtRoomName = (TextView) v.findViewById(R.id.txtRoom);
            final ListView listSteps = (ListView) v.findViewById(R.id.listSteps);

            txtRoomName.setText(room.getName());
            listSteps.setAdapter(new ArrayAdapter<Step>(getContext(), android.R.layout.simple_list_item_1));

            v.setOnClickListener(new View.OnClickListener() {
                @SuppressWarnings("unchecked")
                @Override
                public void onClick(View view) {
                    if(listSteps.getAdapter().getCount() <= 0){
                        ((ArrayAdapter<Step>)listSteps.getAdapter()).addAll(room.getSteps());
                        ((ArrayAdapter<Step>)listSteps.getAdapter()).notifyDataSetChanged();
                        
                    }else{
                        ((ArrayAdapter<Step>)listSteps.getAdapter()).clear();
                    }
                }
            });
            return v;
        }

        throw new RuntimeException("Could not create View");
    }
}
