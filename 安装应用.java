/**
 * 安装应用
 */
private void installApk(File t) {
	Intent intent = new Intent();
	intent.setAction("android.intent.action.VIEW");
	intent.setDataAndType(Uri.fromFile(t),
			"application/vnd.android.package-archive");
	startActivity(intent);
}