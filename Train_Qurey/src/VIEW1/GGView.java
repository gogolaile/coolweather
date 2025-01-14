package VIEW1;



import com.example.train_qurey.R;
import com.example.train_qurey.R.color;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

public class GGView extends View{

	int[] mapId;
	Bitmap[] bitmap;
	Paint paint;
	int currentX,currentY;
	int currentId = 0;
	Boolean initFlag = true;
	Boolean workFlag = true;
	public GGView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.mapId = new int[]{R.drawable.adv1,R.drawable.adv2,R.drawable.adv3,R.drawable.adv4,R.drawable.adv5,R.drawable.adv6,R.drawable.adv7,R.drawable.adv8,R.drawable.adv9};
		paint = new Paint();
	    paint.setFlags(Paint.ANTI_ALIAS_FLAG);
		initBitmap();
		new Thread(){
		public void	run(){
			while(workFlag){
				currentId = (currentId+1)%mapId.length;
				postInvalidate();
				try{
					Thread.sleep(3000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		}.start();
	}
	public void initBitmap(){
		Resources res = this.getResources();
		int number = mapId.length;
		bitmap = new Bitmap[number];
		for(int i=0;i < number;i++){
			bitmap[i] = BitmapFactory.decodeResource(res, mapId[i]);
		}
	}
        public void onDraw(Canvas canvas){
        	if(initFlag){
        		currentX = this.getWidth();
        		currentY = this.getHeight();
        	}

        	 Matrix matrix = new Matrix();    
             matrix.postScale(2f, 2f);
             Bitmap bitmap_tmp = Bitmap.createBitmap(bitmap[currentId], 0, 0, bitmap[currentId].getWidth(), bitmap[currentId].getHeight(),matrix,true);
        	 int StartX = (currentX-bitmap_tmp.getWidth())/2;
        	 int StartY = (currentY-bitmap_tmp.getHeight())/2;
        	canvas.drawARGB(128, 128, 128, 128);
        	canvas.drawBitmap(bitmap_tmp,StartX,StartY,paint);
        }


}
