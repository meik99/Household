package tk.rynkbit.household.db.facade;

import android.content.Context;

import tk.rynkbit.household.db.DBContract;
import tk.rynkbit.household.models.DaoMaster;
import tk.rynkbit.household.models.DaoSession;
import tk.rynkbit.household.models.Step;


public class StepFacade {

    private static DaoSession daoSession;

    public StepFacade(Context context) {
        if (daoSession == null){
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, DBContract.NAME);
            DaoMaster master = new DaoMaster(devOpenHelper.getWritableDb());
            daoSession = master.newSession();
        }
    }

    public Step insert (Step step){
        long rows = daoSession.insert(step);

        return rows > 0 ? step : null;
    }
}
