/**
 * This code is based on the YouTube video https://www.youtube.com/watch?v=5Bo-ESPkpxI in
 * Prabeesh R K's channel "Android Studio Tutorial - 67 - Download Image Using AsyncTask"
 *
 */
package com.grantmarch.imagedownload;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView imageView;

    String image_url = "http://i.imgur.com/OY2zNEb.jpg";

    public void downloadImage(View view) {

        Log.i("Info", "Button pressed");

        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute(image_url);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.image_view);
    }

    class DownloadTask extends AsyncTask<String, Integer, String> {

        ProgressDialog progressDialog;

        /**
         * Set up a ProgressDialog
         */
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Download in progress...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
            progressDialog.show();

        }

        /**
         *  Background task
         */
        @Override
        protected String doInBackground(String... params) {
            String path = params[0];
            int file_length;

            Log.i("Info: path", path);
            try {
                URL url = new URL(path);
                URLConnection urlConnection = url.openConnection();
                urlConnection.connect();
                file_length = urlConnection.getContentLength();

                /**
                 * Create a folder
                 */
                File new_folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "myfolder");
                if (!new_folder.exists()) {
                    if (new_folder.mkdir()) {
                        Log.i("Info", "Folder succesfully created");
                    } else {
                        Log.i("Info", "Failed to create folder");
                    }
                } else {
                    Log.i("Info", "Folder already exists");
                }

                /**
                 * Create an output file to store the image for download
                 */
                File output_file = new File(new_folder, "downloaded_image.jpg");
                OutputStream outputStream = new FileOutputStream(output_file);

                InputStream inputStream = new BufferedInputStream(url.openStream(), 8192);
                byte [] data = new byte[1024];
                int total = 0;
                int count;
                while ((count = inputStream.read(data)) != -1) {
                    total += count;

                    outputStream.write(data, 0, count);
                    int progress = 100 * total / file_length;
                    publishProgress(progress);

                    Log.i("Info", "Progress: " + Integer.toString(progress));
                }
                inputStream.close();
                outputStream.close();

                Log.i("Info", "file_length: " + Integer.toString(file_length));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Download complete.";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.hide();
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "myfolder");
            File output_file = new File(folder, "downloaded_image.jpg");
            String path = output_file.toString();
            imageView.setImageDrawable(Drawable.createFromPath(path));
            Log.i("Info", "Path: " + path);
        }
    }

}
