package pt.ulisboa.tecnico.cmov.timetask;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;


public class TimerAsyncTask extends AsyncTask<Void, Integer, Void> {

    private TextView timer;
    private int numSeconds = 0;

    public TimerAsyncTask(TextView timer, int currSeconds) {
        this.timer = timer;
        this.numSeconds = currSeconds;
    }

    @Override
    protected Void doInBackground(Void... params) {
        while(! this.isCancelled()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            numSeconds++;
            publishProgress(numSeconds);

        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        Integer seconds = values[0];
        timer.setText(seconds.toString());
    }

}
