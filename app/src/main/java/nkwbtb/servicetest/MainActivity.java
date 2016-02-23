package nkwbtb.servicetest;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button start = (Button) findViewById(R.id.btnStart);
        final Button end = (Button) findViewById(R.id.btnEnd);

        final Intent intent = new Intent(this, MyService.class);
        final PendingIntent pi = PendingIntent.getService(MainActivity.this, 0 , intent, 0);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager aManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
                aManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 0, 500, pi);
                start.setEnabled(false);
                end.setEnabled(true);
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager aManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
                aManager.cancel(pi);
                start.setEnabled(true);
                end.setEnabled(false);
            }
        });
    }

}
