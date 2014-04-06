package com.jorgelavin.imageslider;


import com.textswitcher.R;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {

	TextSwitcher textToSwitch;
	int currentImage = 0;
	GestureDetector gestureDetector;
	ImageSwitcher  imageToSwitch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final String[] TEXTS = { "Soy un texto", "Modificame por lo que quieras","y no te olvides de agradecer"};
	    final int[] IMAGES = { R.drawable.img1, R.drawable.img2,
	            R.drawable.img3 };

	    
	  
		
		
	    textToSwitch = (TextSwitcher) findViewById(R.id.textSwitcher);
	    textToSwitch.setFactory(new ViewFactory() {
	            @Override
	            public View makeView() {
	                TextView textView = new TextView(MainActivity.this);
	                textView.setGravity(Gravity.CENTER);
	               
	                textView.setTextColor(Color.parseColor("#bdbdbd"));
	                return textView;
	            }
	        });
	 
	    textToSwitch.setInAnimation(this, android.R.anim.fade_in);
	    textToSwitch.setOutAnimation(this, android.R.anim.fade_out);

		// specify the in/out animations you wish to use
		//tv.setInAnimation(getApplicationContext(), android.R.anim.slide_in_left);
		//tv.setOutAnimation(getApplicationContext(), android.R.anim.slide_out_right);
	        
	        imageToSwitch = (ImageSwitcher) findViewById(R.id.imageSwitcher);
	        imageToSwitch.setFactory(new ViewFactory() {
	            @Override
	            public View makeView() {
	                ImageView imageView = new ImageView(MainActivity.this);
	                return imageView;
	            }
	        });
	        imageToSwitch.setInAnimation(this, android.R.anim.slide_in_left);
	        imageToSwitch.setOutAnimation(this, android.R.anim.slide_out_right);
	        
	        
	      
	

	    imageToSwitch.setBackgroundResource(IMAGES[0]);
	    textToSwitch.setText(TEXTS[0]);


		class MyGestureDetector extends SimpleOnGestureListener {

			final String TAG = MyGestureDetector.class.getSimpleName();

			// for touch left or touch right events
			private static final int SWIPE_MIN_DISTANCE = 80;   //default is 120
			private static final int SWIPE_MAX_OFF_PATH = 400;
			private static final int SWIPE_THRESHOLD_VELOCITY = 70;

			@Override
			public boolean onSingleTapConfirmed(MotionEvent e) {

				return super.onSingleTapConfirmed(e);
			}

			@Override
			public boolean onDown(MotionEvent e) {

				return true;
			}

			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				try {
					if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
						return false;
					if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
						onHorizonTouch(true);  // left
					}  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
						onHorizonTouch(false); // right
					}
				} catch (Exception e) {
					// nothing
				}
				return false;
			}

			void onHorizonTouch(Boolean toLeft) {

				if(!toLeft) {
					
					if(currentImage>0){
					currentImage--;}
					
					if(currentImage==0){
						imageToSwitch.setInAnimation(AnimationUtils.loadAnimation(
									MainActivity.this, android.R.anim.fade_in));
						imageToSwitch.setOutAnimation(AnimationUtils.loadAnimation(
									MainActivity.this, android.R.anim.fade_out));
						
							MainActivity.this.imageToSwitch.setBackgroundResource(IMAGES[0]);
							MainActivity.this.textToSwitch.setText(TEXTS[0]);
							}
					else if(currentImage==1){
						imageToSwitch.setInAnimation(AnimationUtils.loadAnimation(
								MainActivity.this, android.R.anim.fade_in));
						imageToSwitch.setOutAnimation(AnimationUtils.loadAnimation(
								MainActivity.this, android.R.anim.fade_out));
				
						MainActivity.this.imageToSwitch.setBackgroundResource(IMAGES[1]);
						MainActivity.this.textToSwitch.setText(TEXTS[1]);
						}
			
				}
				if(toLeft) {
					
					if(currentImage<2){
					currentImage++;}
					
					if(currentImage==1){
						imageToSwitch.setInAnimation(AnimationUtils.loadAnimation(
							MainActivity.this, android.R.anim.fade_in));
						imageToSwitch.setOutAnimation(AnimationUtils.loadAnimation(
							MainActivity.this, android.R.anim.fade_out));
			
					MainActivity.this.imageToSwitch.setBackgroundResource(IMAGES[1]);
					MainActivity.this.textToSwitch.setText(TEXTS[1]);
					
					}else if(currentImage==2){
						imageToSwitch.setInAnimation(AnimationUtils.loadAnimation(
									MainActivity.this, android.R.anim.fade_in));
						imageToSwitch.setOutAnimation(AnimationUtils.loadAnimation(
									MainActivity.this, android.R.anim.fade_out));
							MainActivity.this.imageToSwitch.setBackgroundResource(IMAGES[2]);
							MainActivity.this.textToSwitch.setText(TEXTS[2]);
					
						
						
					}
					
					
				}
			
					
				
			
			
			
			}
			
			
			
		}

		
		
		imageToSwitch.setInAnimation(AnimationUtils.loadAnimation(this,
		android.R.anim.fade_in));
		imageToSwitch.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
		
		
		//start for touch events  Gesture detection
		gestureDetector = new GestureDetector(new MyGestureDetector());
		View.OnTouchListener gestureListener = new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (gestureDetector.onTouchEvent(event)) {
					return true;
				}
				return false;
			}
		};
		imageToSwitch.setOnTouchListener(gestureListener);
	}
}
