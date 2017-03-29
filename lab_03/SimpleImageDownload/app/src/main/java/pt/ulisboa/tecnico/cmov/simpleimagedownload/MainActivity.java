package pt.ulisboa.tecnico.cmov.simpleimagedownload;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements Handler.Callback {

    private Handler handler = new Handler(this);


    private class DownloadImageTask extends AsyncTask<URL, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(URL... urls) {
            Bitmap image = null;
            try {
                image = BitmapFactory.decodeStream(urls[0].openStream());
                if (image != null) {
                    Log.i("DL", "Successfully retrieved file!");
                    sendMessage("Successfully retrieved file!");
                } else {
                    sendMessage("Failed decoding file from stream");
                    Log.i("DL", "Failed decoding file from stream");
                }
            } catch (Exception e) {
                sendMessage("Failed downloading file!");
                Log.i("DL", "Failed downloading file!");
                e.printStackTrace();
            }
            return image;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bmp) {
            ImageView imgView = (ImageView)findViewById(R.id.img_view);
            imgView.setImageBitmap(bmp);
            super.onPostExecute(bmp);

        }

        public void sendMessage(String what) {
            Bundle bundle = new Bundle();
            bundle.putString("status", what);
            Message message = new Message();
            message.setData(bundle);
            handler.sendMessage(message);
        }
    }


    public void startDownload(View source) {
        try {
            URL imageUrl = new URL("https", "upload.wikimedia.org", "/wikipedia/commons/thumb/8/86/Putin_with_flag_of_Russia.jpg/220px-Putin_with_flag_of_Russia.jpg");
            new DownloadImageTask().execute(imageUrl);
            TextView statusText = (TextView) findViewById(R.id.status);
            statusText.setText("Download started...");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean handleMessage(Message message) {
        String text = message.getData().getString("status");
        TextView statusText = (TextView) findViewById(R.id.status);
        statusText.setText(text);
        return true;
    }
}