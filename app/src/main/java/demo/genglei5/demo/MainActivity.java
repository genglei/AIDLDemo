package demo.genglei5.demo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import demo.genglei5.aidlserver.IPeople;

public class MainActivity extends AppCompatActivity {
    private IPeople people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("demo.genglei5.AidlService");
                intent.setPackage("demo.genglei5.aidlserver");
                bindService(intent, conn, Service.BIND_AUTO_CREATE);
            }
        });
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            people = IPeople.Stub.asInterface(service);//连接建立成功，读取数据
            try {
                ((TextView) findViewById(R.id.text)).setText("name =" + people.getName() + " sex=" + people.getSex());
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            people = null;

        }
    };
}
