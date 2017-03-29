package pt.ulisboa.tecnico.cmov.timetask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TimerAsyncTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTimerTask();
    }

    private void initTimerTask() {
        TextView timerTv = (TextView) findViewById(R.id.timer_tv);
        int currSeconds = Integer.parseInt(timerTv.getText().toString());
        timerTask = new TimerAsyncTask(timerTv, currSeconds);
    }

    public void onStartButtonClick(View view) {
        if (timerTask == null) {
            initTimerTask();
        }
        if (timerTask.getStatus() == AsyncTask.Status.RUNNING) {
            // do nothing
            return;
        }
        timerTask.execute();
    }

    public void onStopButtonClick(View view) {
        if (timerTask != null) {
            timerTask.cancel(true);
            timerTask = null;
        }
    }

    public void onResetButtonClick(View view) {
        if (timerTask != null) {
            timerTask.cancel(true);
            timerTask = null;
        }
        TextView timerTv = (TextView) findViewById(R.id.timer_tv);
        timerTv.setText("0");
    }
}
