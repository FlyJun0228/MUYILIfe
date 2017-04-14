package com.example.administrator.muyilife;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageLoader {
	private ImageView mImageView;
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			mImageView.setImageBitmap((Bitmap) msg.obj);
		}
	};
	public void showImageByThread(ImageView imageView, final String url)
	{
		mImageView = imageView;
		new Thread(){
			@Override
			public void run() {
				super.run();
		        Bitmap bitmap = getImage(url);
	Message message = Message.obtain();
				message.obj = bitmap;
				handler.sendMessage(message);
			}
		}.start();
	}
	public Bitmap getImage(String urlString)
	{
		try{
			HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
			connection.setRequestMethod("GET");
			InputStream	is = new BufferedInputStream(connection.getInputStream());
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			return bitmap;
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return null;
	}


}
