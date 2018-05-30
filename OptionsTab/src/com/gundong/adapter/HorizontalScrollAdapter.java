package com.gundong.adapter;


import com.example.optiontab.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class HorizontalScrollAdapter extends BaseAdapter {

	   private int[] mImgIds;
	   private int[] mImgIdsChange;
	   private int useImageId;
	    private String[] mTitles;  
	    private Context mContext;  
	    private int everywidth;
	    private LayoutInflater mInflater;  
	    Bitmap iconBitmap;  
	    private int selectIndex = -1;  
	    private ImageView imgView;
	    private TextView txtView;
	    private LayoutParams para;
	    private  int curpos=-1;
	    private int bottomHeight;
	    
	    public HorizontalScrollAdapter(Context context, String[] mTitles, int[] mImgIds,int[] mImgIdsChange,int everywidth,int curpos,int bottomHeight){  
	        this.mContext = context;  
	        this.mImgIds = mImgIds;  
	        this.mTitles = mTitles;  
	        this.everywidth=everywidth;
	        this.curpos=curpos;
	        this.mImgIdsChange=mImgIdsChange;
	        this.bottomHeight=bottomHeight;
	        mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//LayoutInflater.from(mContext);  
	    }  
	    
	    public HorizontalScrollAdapter(Context context, String[] titles, int[] mImgIds,int[] mImgIdsChange,int everywidth,String[] mTitles,int curpos,int bottomHeight){  
	        this.mContext = context;  
	        this.mImgIds = mImgIds;  
	        this.mTitles = titles;  
	        this.everywidth=everywidth;
	        this.mTitles=mTitles;
	        this.curpos=curpos;
	        this.mImgIdsChange=mImgIdsChange;
	        this.bottomHeight=bottomHeight;
	        mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//LayoutInflater.from(mContext);  
	    }  


		@Override  
	    public int getCount() {  
	        return mImgIds.length;  
	    }  
	    @Override  
	    public Object getItem(int position) {  
	        return position;  
	    }  
	  
	    @Override  
	    public long getItemId(int position) {  
	        return position;  
	    }  
	  
	    
	    
	    public int getCurpos() {
			return curpos;
		}
		public void setCurpos(int curpos) {
			this.curpos = curpos;
		}

		@Override  
	    public View getView(int position, View convertView, ViewGroup parent) {  
	  
	        ViewHolder holder;  
	        if(convertView==null){  
	            holder = new ViewHolder();  
	           convertView = mInflater.inflate(R.layout.activity_index_gallery_item, null); 
	   
	            
	            imgView=(ImageView)convertView.findViewById(R.id.id_index_gallery_item_image); 
	            
	  
	            holder.mImage=imgView;

	            txtView=(TextView)convertView.findViewById(R.id.id_index_gallery_item_text);  
	            holder.mTitle=txtView;
	            
	            convertView.setTag(holder);  
	        }else{  
	            holder=(ViewHolder)convertView.getTag();  
	        }  
	        if(position == selectIndex){  
	            convertView.setSelected(true);  
	        }else{  
	            convertView.setSelected(false);  
	        }  
	        
	        holder.mTitle.setText(mTitles[(position+mTitles.length)%mTitles.length]);
	       
	    
	        if(position==getCurpos())
	        {
	        	
//	        	if(getCurpos()!=0)
//	        	{
	        	useImageId=mImgIdsChange[position];
	        	
	        	  
	        	  holder.mTitle.setTextColor(mContext.getResources().getColor(R.color.main_color));
//	        	}
	        }
	        else
	        {
	        	useImageId=mImgIds[position];
	        }
	        
	        
	        holder.mImage.setImageDrawable(zoomDrawable(useImageId));

	        para=new LayoutParams(everywidth,LayoutParams.MATCH_PARENT);
	        
	        convertView.setLayoutParams(para);
	        return convertView;  
	    }  
	  
	    private static class ViewHolder {
	    	private ImageView mImage;  
	        private TextView mTitle ;  
	        
	    }  

	    public void setSelectIndex(int i){  
	        selectIndex = i;  
	    } 
	    public Drawable zoomDrawable(int id)  
	     {       int h=bottomHeight*4/5;
	    	      Drawable drawable =mContext.getResources().getDrawable(id);
	              int width = drawable.getIntrinsicWidth();  
	              int height= drawable.getIntrinsicHeight();  
	              int w=(width*h)/height;
	               Bitmap oldbmp = drawableToBitmap(drawable); 
	               Matrix matrix = new Matrix();  
	              float scaleWidth = ((float)w / width);   
	              float scaleHeight = ((float)h / height);  
	               matrix.postScale(scaleWidth, scaleHeight);         
	               Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true);       
	              return new BitmapDrawable(newbmp);      
	     }  
	    
    
	    public Bitmap drawableToBitmap(Drawable drawable) 
	     {  
	              int width = drawable.getIntrinsicWidth(); 
	              int height = drawable.getIntrinsicHeight();  
	               Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888:Bitmap.Config.RGB_565;         
	               Bitmap bitmap = Bitmap.createBitmap(width, height, config);     
	               Canvas canvas = new Canvas(bitmap);        
	               drawable.setBounds(0, 0, width, height);  
	               drawable.draw(canvas);     
	              return bitmap;  
	     } 
	    
	    
}
