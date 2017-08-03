package tk.rynkbit.household.db.facade;

import android.content.Context;

import java.util.List;

import tk.rynkbit.household.db.DBContract;
import tk.rynkbit.household.models.DaoMaster;
import tk.rynkbit.household.models.DaoSession;
import tk.rynkbit.household.models.Room;


public class RoomFacade {
    private static DaoSession daoSession;

    public RoomFacade(Context context) {
        if(daoSession == null){
            DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(context, DBContract.NAME);
            DaoMaster master = new DaoMaster(openHelper.getWritableDb());
            daoSession = master.newSession();
        }
    }

    public List<Room> getRooms(){
        return daoSession.getRoomDao().loadAll();
    }

    public Room insert(Room room){
        long rows = daoSession.getRoomDao().insert(room);
        room.__setDaoSession(daoSession);

        return rows > 0 ? room : null;
    }
}
