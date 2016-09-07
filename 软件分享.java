	/**
	 * 分享软件---分享到微博-微信-陌陌-qq
	 */
	private void shareApp() {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.SEND");
		intent.setType("text/plain");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.putExtra(Intent.EXTRA_TEXT, "推荐一款最近使用的" + appInfo.getName()
				+ ",下载地址:http://www.appchina.com/" + appInfo.getPackName());
		startActivity(intent);
	}