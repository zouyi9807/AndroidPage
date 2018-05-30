package com.example.optiontab;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.donghua.adapter.ViewPagerFragmentPagerAdapter;
import com.donghua.pagetransformer.AccordionTransformer;
import com.donghua.pagetransformer.*;
import com.example.fragments.*;
import com.exitApp.ExitApplication;
import com.gundong.adapter.HorizontalScrollAdapter;
import com.gundongtiao.MyHorizontalScrollView;
import com.gundongtiao.MyHorizontalScrollView.OnItemClickListener;


public class MainActivity extends FragmentActivity implements OnClickListener {

	private Button exit;// exit

	public List<Fragment> fragments = new ArrayList<Fragment>();
	private MyHorizontalScrollView myhorizontalScrollView;
	public HorizontalScrollAdapter horizontalScrollAdapter;

	private Animation operatingAnim;

	private ViewPager viewPager; // huadong xiaoguo
	private ViewPagerFragmentPagerAdapter viewPagerAdapter;
	private static int changedPosition;// gundongtiao changed position
	private static int currentPosition;// gundongtiao current position

	// apater
	private String[] mTitles;
	private int[] mImgIds;
	private int[] mImgIdsChange;
	private int everywidth = 0;// options display count
	private int curpos = 0;
	private int bottomHeight = 0;

	// left right imageview
	private ImageView iv_nav_left, iv_nav_right;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		// init activity
		initActivity();
		// init fragment
		initFragments();

		// apater init parameter
		initmTitles();
		initmImgIds();
		initmImgIdsChange();
		initeverywidth();
		initBottomHeight();
		initMyHorizontalScrollView();

		// init huadong xiaoguo
		initViewPager();
		moveToExit();
		
		// set Animation
		operatingAnim = AnimationUtils.loadAnimation(this, R.anim.tip);
		LinearInterpolator linearInterpolator = new LinearInterpolator();
		operatingAnim.setInterpolator(linearInterpolator);

		// exitActivity and then exit program
		ExitApplication.getInstance().addActivity(this);
	}

	private void initActivity() {
		// exit
		exit = (Button) findViewById(R.id.exit);
		exit.setOnClickListener(this);
	}

	private void initFragments() {
		// set fragments
		fragments.add(new A());
		fragments.add(new B());
		fragments.add(new C());
		fragments.add(new D());
		fragments.add(new E());
	}

	private void initmTitles() {
		mTitles = new String[] { "a.title", "b.title", "c.title", "d.title",
				"e.title", "exitApp" };
	}

	private void initmImgIds() {
		mImgIds = new int[] { R.drawable.a, R.drawable.b, R.drawable.c,
				R.drawable.d, R.drawable.e, R.drawable.exit_normal };
	}

	private void initmImgIdsChange() {
		mImgIdsChange = new int[] { R.drawable.aa, R.drawable.bb,
				R.drawable.cc, R.drawable.dd, R.drawable.ee,
				R.drawable.exit_normal };
	}

	private void initeverywidth() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int cardinality = 4;// set options display count

		if (mImgIds.length < 4) {
			cardinality = mImgIds.length;
		}

		everywidth = dm.widthPixels / cardinality;
	}

	private void initBottomHeight() {
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		// int width = metric.widthPixels; // 屏幕宽度（像素）
		// int height = metric.heightPixels; // 屏幕高度（像素）
		bottomHeight = metric.heightPixels / 8;
	}

	private void initMyHorizontalScrollView() {
		// init apater
		horizontalScrollAdapter = new HorizontalScrollAdapter(
				getApplicationContext(), mTitles, mImgIds, mImgIdsChange,
				everywidth, curpos, bottomHeight);

		// set left right imageview
		iv_nav_left = (ImageView) findViewById(R.id.iv_nav_left);
		iv_nav_right = (ImageView) findViewById(R.id.iv_nav_right);
		myhorizontalScrollView = (MyHorizontalScrollView) findViewById(R.id.horizontalScrollView);
		// set params
		myhorizontalScrollView.setParams(iv_nav_left, iv_nav_right, this);

		// init datas
		myhorizontalScrollView.initDatas(horizontalScrollAdapter,
				mTitles.length);
	}

	// init huadong xiaoguo
	private void initViewPager() {

		viewPager = (ViewPager) findViewById(R.id.viewpager);

		viewPagerAdapter = new ViewPagerFragmentPagerAdapter(
				getSupportFragmentManager(), fragments);

		viewPager.setAdapter(viewPagerAdapter);
		// set donghua xiaoguo
		viewPager.setPageTransformer(true, new CubeTransformer());
		// set lister
		viewPager.setOnPageChangeListener(new MyPagerOnPageChangeListener());

	}

	private class MyPagerOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int position) {

			if (changedPosition == 0) {
				resetHorizontalScrollView(position);

				// if out of the options display count then arrow to right
				if (position > currentPosition && position >= 4) {
					myhorizontalScrollView.arrowScroll(View.FOCUS_RIGHT);
				} else if (position < currentPosition && position <= 4) {
					myhorizontalScrollView.arrowScroll(View.FOCUS_LEFT);
				}

			}
			changedPosition = 0;
			currentPosition = position;
		}
	}

	// reset gundongtiao
	public void resetHorizontalScrollView(int position) {
		// set weizhi
		horizontalScrollAdapter.setCurpos(position);
		// notify data change
		horizontalScrollAdapter.notifyDataSetChanged();
		// reinit
		myhorizontalScrollView.initDatas(horizontalScrollAdapter,
				mTitles.length);

	}

	// set exit
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.exit:
			this.finish();
			break;
		default:
			break;
		}
	}

	// touch the exit menu on the gundongtiao
	private void moveToExit() {
		myhorizontalScrollView
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onClick(View view, int position) {

						if (position == 5)// the menu on gundongtiao is exit
						{
							// create AlertDialog for exit the app
							AlertDialog.Builder ad = new AlertDialog.Builder(
									MainActivity.this);
							ad.setIcon(R.drawable.ic_launcher);
							ad.setTitle(R.string.exitApp);
							ad.setMessage(R.string.exitMessage);
							ad.setPositiveButton(R.string.ok,
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog, int i) {
											// exit
											ExitApplication.getInstance()
													.exit();
										}
									});
							ad.setNegativeButton(R.string.cancel,
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog, int i) {
										}
									});
							ad.show();

						} else {
							// reset gundongtiao
							resetHorizontalScrollView(position);

							changedPosition = 1;

							viewPager.setCurrentItem(position);

						}

					}
				});

	}
}
