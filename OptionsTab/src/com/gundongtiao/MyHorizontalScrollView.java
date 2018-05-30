package com.gundongtiao;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gundong.adapter.HorizontalScrollAdapter;



public class MyHorizontalScrollView extends HorizontalScrollView implements
OnClickListener {
  
	private Activity mContext;
	private ImageView leftImage;
	private ImageView rightImage;
	private int windowWitdh = 0;
	private HorizontalScrollAdapter mAdapter;

	private LinearLayout mContainer;
	private OnItemClickListener mOnClickListener;

	private Map<View, Integer> mViewPos = new HashMap<View, Integer>();
	

	private int mCurrentIndex;
	
	public MyHorizontalScrollView(Context context) {
		super(context);
	}
	public MyHorizontalScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public void setOnItemClickListener(OnItemClickListener mOnClickListener)
	{
		this.mOnClickListener = mOnClickListener;
	}
	@Override
	public void onClick(View v)
	{
		if (mOnClickListener != null)
		{
//			mContainer.removeAllViews();
			Log.i("CCCCCCCCCCCCCCCC",
					String.valueOf(mViewPos==null));
			mOnClickListener.onClick(v,mViewPos.get(v));
		}
	
	}

	
	public interface OnItemClickListener
	{
		void onClick(View view, int pos);
	}
	

	public void initDatas(HorizontalScrollAdapter mAdapter,int size)
	{
		
		this.mAdapter = mAdapter;
		
		mContainer = (LinearLayout) getChildAt(0);
		mContainer.removeAllViews();
		mViewPos.clear();
	
		for(int i=0;i<size;i++)
		{
			
        
			final View view = mAdapter.getView(i, null, mContainer);
			view.setOnClickListener(this);
			mViewPos.put(view, i);
			mContainer.addView(view);
		}
		/*
		Log.i("IIIIIIIIIIIIIIIIInit",
				String.valueOf(mViewPos==null));
*/
	}

	//设置水平滚动条宽度
	public void setParams( ImageView leftImage,
			ImageView rightImage, Activity context){
		this.mContext = context;
		
		this.leftImage = leftImage;
		this.rightImage = rightImage;
		DisplayMetrics dm = new DisplayMetrics();
		this.mContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
		windowWitdh = dm.widthPixels;
	}
	
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		
		
		if(!mContext.isFinishing() && mContainer != null 
				&& leftImage != null && rightImage != null){
			
			if(mContainer.getWidth() < windowWitdh){
				leftImage.setVisibility(View.GONE);
				rightImage.setVisibility(View.GONE);
			}else{
				if(l == 0){
					leftImage.setVisibility(View.GONE);
					rightImage.setVisibility(View.VISIBLE);
				}else if(mContainer.getWidth() - l == windowWitdh){
					leftImage.setVisibility(View.VISIBLE);
					rightImage.setVisibility(View.GONE);
				}else{
					leftImage.setVisibility(View.VISIBLE);
					rightImage.setVisibility(View.VISIBLE);
				}
			}
		}
		
		
	}
	
	
}
