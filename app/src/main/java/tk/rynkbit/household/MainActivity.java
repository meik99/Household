package tk.rynkbit.household;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import tk.rynkbit.household.room.RoomFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mFrameLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    openRooms();

                    return true;
//                case R.id.navigation_dashboard:
//                    return true;
//                case R.id.navigation_notifications:
//                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFrameLayout = (FrameLayout) findViewById(R.id.content);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        openRooms();
    }

    private void openRooms(){
        FragmentTransaction transaction =
                MainActivity.this.getFragmentManager().beginTransaction();
        transaction.add(R.id.container, new RoomFragment());
        transaction.commit();
    }

}
