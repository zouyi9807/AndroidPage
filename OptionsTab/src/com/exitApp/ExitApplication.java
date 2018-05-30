package com.exitApp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class ExitApplication extends Application {

	private List<Activity> activityList = new ArrayList<Activity>();
	private static ExitApplication instance;

	private ExitApplication() {
	}

	// only example mode
	public static ExitApplication getInstance() {
		if (null == instance) {
			instance = new ExitApplication();
		}
		return instance;

	}

	// add activity
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	//reload exit methoed
	public void exit() {
		int count=activityList.size();
		for(int i=count-1;i>=1;i--)
		{
			activityList.get(i).finish();
		}
		
//		for (Activity activity : activityList) {
//			activity.finish();
//		}

		
		
		System.exit(0);

	}
}