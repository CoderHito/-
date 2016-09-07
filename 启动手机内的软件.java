	/**
	 * 启动应用
	 */
	private void startApp2() {
		PackageManager pm = getPackageManager();
		Intent intent = pm.getLaunchIntentForPackage(appInfo.getPackName());
		startActivity(intent);
	}
	
	
	
	
	/**
	 * 启动应用
	 */
	private void startApp() {
		Intent intent = new Intent();
		PackageManager pm = getPackageManager();
		try {
			PackageInfo packageInfo = pm.getPackageInfo(appInfo.getPackName(),
					PackageManager.GET_ACTIVITIES);
			ActivityInfo[] activities = packageInfo.activities;
			if (activities != null && activities.length > 0) {
				// 得到第一个Activity
				ActivityInfo activityInfo = activities[0];
				// 全类名
				String name = activityInfo.name;
				intent.setClassName(appInfo.getPackName(), name);
				startActivity(intent);
			} else {
				Toast.makeText(AppManagerActivity.this, "没有页面", 0).show();
			}
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}