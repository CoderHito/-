	/**
	 * �õ��ֻ����н��̵�����
	 * 
	 * @param context
	 * @return
	 */
	public static int getRunningProgressCount(Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		return am.getRunningAppProcesses().size();
	}