package com.example.progressbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	private Handler 	handler;
	private ProgressBar mProgressBar;
	private AlertDialog.Builder mBuilder;
	private AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        handler 		= new Handler();
        mProgressBar 	= (ProgressBar) findViewById(R.id.progressbar);
        mBuilder 		= new AlertDialog.Builder(this);
		mBuilder.setTitle("Notice");
		mBuilder.setCancelable(true);
		mBuilder.setMessage("Finished!!");
		mBuilder.setPositiveButton("Ok", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mProgressBar.setProgress(0);
				dialog.dismiss();
			}
		});
		mDialog = mBuilder.create();
    }
    
    public void startProgress(View v){
    	new Thread(new Task()).start();
    }
    
    class Task implements Runnable{

		@Override
		public void run() {
			for (int i = 0 ; i <= 1000; i++){
				final int value = i;
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						mProgressBar.setProgress(value);
					}
				});
			}
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					mDialog.show();
				}
			});
		}
    }
}
