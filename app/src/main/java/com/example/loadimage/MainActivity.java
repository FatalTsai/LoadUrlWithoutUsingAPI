package com.example.loadimage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private Button btUrl, btLoad;
    private String url;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btUrl = (Button) findViewById(R.id.btUrl);


        /* 顯示url */
        url ="http://192.168.151.10:3000/home/coder01/work/src/test/lux.png";
        //url = "http://i.imgur.com/WPydEEx.jpg"; // 圖片網址  be
        btUrl.setText(url); // 顯示網址
        btUrl.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
                        .parse(url));
                startActivity(browserIntent); // 使用網頁開啟
            }
        });


        new DownloadImageTask((ImageView) findViewById(R.id.ivLoad))
                .execute(url); // 載入圖片*/



    }

    /* AsyncTask執行下載任務 */
    @SuppressLint("NewApi")
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream(); // 從網址上下載
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result); // 下載完成後載入結果
        }
    }
}