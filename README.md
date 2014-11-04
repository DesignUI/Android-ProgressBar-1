Android-ProgressBar
===================

Progress Bar using runnable, handler, and thread.

Handler helps with the update or communication of the UI thread, while thread helps to use a another thread that does not uses the main thread. Runnable makes this possible.

Next code starts a new Thread and instantiates the class Task.
```Java
public void startProgress(View v){
    	new Thread(new Task()).start();
}
```

The Task class implements Runnable,
```Java
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
```
Thread.sleep(1) makes sure that each millisecond is delayed the run, this runs 1000 times by the for-loop, then a Dialog is shown.

Note that the Dialog is implemented in the Main-thread, and asked to show by the handler, that it was also implemented in the Main-thread.

```Java
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
```
