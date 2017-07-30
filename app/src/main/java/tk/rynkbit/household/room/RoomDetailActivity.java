package tk.rynkbit.household.room;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tk.rynkbit.household.R;

public class RoomDetailActivity extends AppCompatActivity{
    RoomDetailController roomDetailController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        roomDetailController = new RoomDetailController(this);
    }
}

