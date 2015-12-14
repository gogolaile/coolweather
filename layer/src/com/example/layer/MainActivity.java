package com.example.layer;


import android.app.Activity;
import android.app.AlertDialog;

import android.content.Context;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;


public class MainActivity extends Activity {

	    ImageButton imButton;  
	    ImageSwitcher imageSwitcher;  
	    Gallery gallery;  
	    int imagePosition;  
	    /** 
	     * ���е�ͼ��ͼƬ 
	     */  
	    private  int[] images   
	            = new int[]{ 
	        R.drawable.menu_calendar,R.drawable.menu_converter,R.drawable.menu_icon_alarm  
	        ,R.drawable.menu_icon_pedometer,R.drawable.menu_icon_personalsafety};  
	    AlertDialog imageChooseDialog;  
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.main);  
	        initImageChooseDialog(); 
	  

	    }  
	        
	      
	    //��ʼ���Ի���  
	    private void initImageChooseDialog() {  

	        gallery = (Gallery)findViewById(R.id.img_gallery);  
	        gallery.setAdapter(new ImageAdapter(this));//����gallery���ݡ�  
	        gallery.setSelection(images.length/2);
	        gallery.setUnselectedAlpha(0.3f);
            gallery.setSpacing(-20);
	        imageSwitcher = (ImageSwitcher)findViewById(R.id.imageswitch);  
	        imageSwitcher.setFactory(new ImageViewFactory(this));  
	        gallery.setOnItemSelectedListener(new OnItemSelectedListener() {
			  
	  
	            @Override  
	            public void onItemSelected(AdapterView<?> parent, View view,  
	                    int position, long id) {  
	                // TODO Auto-generated method stub  
	                ImageView imageview =(ImageView)view;
	                imagePosition=position;  
	               ((ImageView)view).setImageResource(images[position%images.length]);
	                view.setLayoutParams(new Gallery.LayoutParams(80*2, 80*2));
	                if(position > 1){
	                for(int i=-2;i < 3;i++){
	                	ImageView local_imageview = (ImageView)parent.getChildAt(i+position); 
	                	System.out.println("position-----"+position);
	                	if(i != 0){
//	                		Matrix matrix1 = new Matrix();	                			                		 
	                		int matrix = 1,toYDelta;
	                		matrix =Math.abs(i);
	                		System.out.println("matrix+position:"+matrix+"  "+position);
	                		if(local_imageview == null)return;
	                		local_imageview.setLayoutParams(new Gallery.LayoutParams(160/(matrix+1), 160/(matrix+1)));
//	                		toYDelta = (imageview.getHeight() - local_imageview.getHeight())/2;
//	                		System.out.println("Dy-----"+toYDelta); 
//	                		 matrix1.postTranslate(0,toYDelta);
//	                		 local_imageview.setImageMatrix(matrix1);
	                	}
	                }
	                }
	                imageSwitcher.setImageResource(images[position%images.length]);  
	                
	            }  
	  
	            @Override  
	            public void onNothingSelected(AdapterView<?> parent) {  
	                // TODO Auto-generated method stub  
	                  
	            }  
	        });  

	    }  
	      
	    /** 
	     * ���Adapter���ʾData��Adapter��View,Adapter��ʾ Data��View֮���Ŧ�� 
	     * ÿ��ʾһ�����ݶ������������. 
	     */  
	    class ImageAdapter extends BaseAdapter{  
	        private Context context;  
	          
	        public ImageAdapter(Context context) {  
	            this.context = context;  
	        }  
	          
	        /** 
	         * ��ʾ�����������������ǿɹ�ѡ���ͼƬ��������ʾ��+�Ĵ������ڴ�����ʱ�������С� 
	         */  
	        @Override  
	        public int getCount() {  
	            // TODO Auto-generated method stub  
	//System.out.println("BaseAdapter-----getCount:"+images.length);  
	           // return images.length;  
	              return Integer.MAX_VALUE;
	        }  
	  
	        @Override  
	        public Object getItem(int position) {  
	            // TODO Auto-generated method stub  
	//System.out.println("BaseAdapter-----getItem:"+position);  
	            return images[position%images.length];  
	        }  
	  
	        @Override  
	        public long getItemId(int position) {  
	            // TODO Auto-generated method stub  
	//System.out.println("BaseAdapter-----getItemId:"+position);  
	            return position;  
	        }  
	          
	        /** 
	         * �����Լ�������ͼ��Ҳ������xml�ļ�����ж��壬�����ﶨ�� ����gallery������ݵĸ�ʽ ���� 
	         * ����position��ʾ��ǰѡ���е�λ�á� 
	         * ÿ��ʾһ�����ݶ���ִ��һ�� 
	         */  
	        @Override  
	        public View getView(int position, View convertView, ViewGroup parent) {  
	//System.out.println("BaseAdapter-----position:"+position);  
	            ImageView iv = new ImageView(context);  
	            iv.setImageResource(images[position%images.length]);  
	            iv.setAdjustViewBounds(true);  
	            iv.setLayoutParams(new Gallery.LayoutParams(80*2,80*2));  	            
	            return iv;  
	        }  
	    }  
	    class ImageViewFactory implements ViewFactory{  
	        Context context;  
	        public ImageViewFactory(Context context) {  
	            this.context = context;  
	        }  
	  
	        @Override  
	        public View makeView() {  
	            ImageView iv = new ImageView(context);  
	            //������ʾ�Ĵ�С �����ֲ�����  
	            iv.setLayoutParams(new ImageSwitcher.LayoutParams(90, 90));  
	            return iv;  
	        }  
	          
	    }  
	}  
