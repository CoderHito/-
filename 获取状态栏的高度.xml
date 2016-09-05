	import java.lang.reflect.Field;
	/**
	 * 用于获取任务栏的高
	 * 
	 * @return
	 */
	private int getStatusBarHeight() {
		if (statusBarHeight == 0) {
			try {
				Class clazz = Class.forName("com.android.internal.R$dimen");
				Object object = clazz.newInstance();
				int height = Integer.parseInt(clazz
						.getField("status_bar_height").get(object).toString());
				statusBarHeight = getResources().getDimensionPixelSize(height);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Log.e(TAG, statusBarHeight + "");
		return statusBarHeight;
	}