package com.donghua.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class ViewPagerFragmentPagerAdapter extends FragmentPagerAdapter {
	private List<Fragment> mFragments;
	public ViewPagerFragmentPagerAdapter(FragmentManager fm,List<Fragment> mFragments) {
		super(fm);
	this.mFragments=mFragments;	
	}

	@Override
	public int getCount()
	{
		return mFragments.size();
	}

	@Override
	public Fragment getItem(int arg0)
	{
		return mFragments.get(arg0);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		return super.instantiateItem(container, position);
	}

	

}
