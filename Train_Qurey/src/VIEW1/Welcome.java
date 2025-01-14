package VIEW1;

import com.example.train_qurey.MainActivity;
import com.example.train_qurey.R;

import android.R.color;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class Welcome extends SurfaceView implements SurfaceHolder.Callback {

	int[] bitlogo;
	Bitmap[]  bitmap;
	Paint paint;
	int screenWidth = 600;
	int screenHight = 480;
	MainActivity activity;
    Bitmap currentLogo;
    int currentAlpha = 0;
	int currentX,currentY;
	public Welcome(MainActivity activity) {
		super(activity);
		this.activity = activity;
		this.getHolder().addCallback(this);
		// TODO Auto-generated constructor stub
		Resources res = this.getResources();
		paint = new Paint();
		bitlogo = new int[]{R.drawable.baina,R.drawable.bnkjs};
		int number = bitlogo.length;
		bitmap = new Bitmap[number];
		for(int i=0;i < number;i++){
			bitmap[i] = BitmapFactory.decodeResource(res, bitlogo[i]);
		}
	}
	
	public void onDraw(Canvas canvas){
		paint.setColor(color.black);
		paint.setAlpha(255);
		canvas.drawRect(0,0,screenWidth, screenHight,paint);
		if(currentLogo == null)return;
		paint.setAlpha(currentAlpha);
		canvas.drawBitmap(currentLogo, 0,0, paint);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		new Thread(){
			@SuppressLint("WrongCall")
			public void run(){
				for(Bitmap bm:bitmap){
					currentLogo = bm;
					currentX = screenWidth/2 - currentLogo.getWidth()/2;
					currentY = screenHight/2 - currentLogo.getHeight()/2;
					for(int i=255;i >-10;i=i-10){
						currentAlpha = i;
						if(currentAlpha < 0)
							currentAlpha = 0;
					
					SurfaceHolder myHolder = Welcome.this.getHolder();
					Canvas canvas = myHolder.lockCanvas();
					try{
						synchronized(myHolder){
							onDraw(canvas);
						}
					}catch(Exception e){
						e.printStackTrace();
					}finally{
						if(canvas != null){
							myHolder.unlockCanvasAndPost(canvas);
						}
					}
					try{
						if(i == 255){
							Thread.sleep(1000);
						}
						Thread.sleep(50);
					}catch(Exception e){
	                   e.printStackTrace();
					}
				}
				}
               activity.hd.sendEmptyMessage(1);
			}
		}.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
}
