package demo.genglei5.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by genglei5 on 2018/1/23.
 */

public class AidlService extends Service {
    private PeopleBinder peopleBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        peopleBinder = new PeopleBinder();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return peopleBinder;
    }

    public class PeopleBinder extends IPeople.Stub {


        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getName() throws RemoteException {
            return "deep";
        }

        @Override
        public String getSex() throws RemoteException {
            return "man";
        }
    }
}
