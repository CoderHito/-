	/**
	 * �������---����΢��-΢��-İİ-qq
	 */
	private void shareApp() {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.SEND");
		intent.setType("text/plain");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.putExtra(Intent.EXTRA_TEXT, "�Ƽ�һ�����ʹ�õ�" + appInfo.getName()
				+ ",���ص�ַ:http://www.appchina.com/" + appInfo.getPackName());
		startActivity(intent);
	}