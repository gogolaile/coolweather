package com.example.daydel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


public class WelcomeView extends SurfaceView  implements SurfaceHolder.Callback{


	int[] logo = {R.drawable.dukea,R.drawable.dukeb};
	Paint paint = new Paint();
	int CurrentX,CurrentY;
    int screenWidth = 640;//exActivity.getScreenwidth();
    int screenHeight = 320;// exActivity.getScreenheight();
	Bitmap[] logo_bitmap ;
	int currentAlpha;
	int currentId = 0;
	public WelcomeView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.getHolder().addCallback(this);
        initMap();

	}

    private void initMap(){
    	Resources res = this.getResources();
    	logo_bitmap = new Bitmap[logo.length];
    	for(int i=0;i < logo.length;i++){
    		logo_bitmap[i] = BitmapFactory.decodeResource(res, logo[i]);
    	}
    }
    
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		paint.setColor(Color.BLACK);
		paint.setAlpha(255);
		canvas.drawRect(0, 0, screenWidth, screenHeight, paint);
		int startX = (screenWidth - logo_bitmap[currentId].getWidth())/2;
		int startY = (screenHeight - logo_bitmap[currentId].getHeight())/2;
		paint.setAlpha(currentAlpha);
		canvas.drawBitmap(logo_bitmap[currentId],startX, startY, paint);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		new Thread(){
			@SuppressLint("WrongCall")
			public void run(){
				for(;currentId < logo_bitmap.length;currentId++){
					for(int i = 255;i > -10;i = i -10){
						currentAlpha = i;
						if(currentAlpha < 0){
							currentAlpha = 0;
						}
					
					SurfaceHolder myholder = WelcomeView.this.getHolder();
					Canvas canvas = myholder.lockCanvas();
					try{
						synchronized (myholder) {
							onDraw(canvas);
						}
					}catch(Exception e){
						e.printStackTrace();
					}finally{
						if(canvas != null){
							myholder.unlockCanvasAndPost(canvas);
						}
					}
					try{if( i == 255){
						Thread.sleep(1000);
					}
					Thread.sleep(50);
					
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		exActivity.getactivity().hd.sendEmptyMessage(0);	
	}
		}.start();
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	

}
