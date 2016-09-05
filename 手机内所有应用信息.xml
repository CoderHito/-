public class AppInfoProvider {
	/**
	 * 得到手机所有应用信息
	 * 
	 * @param context
	 *            上下文
	 * @return
	 */
	public static List<AppInfo> getAllAppInfos(Context context) {
		List<AppInfo> appInfos = new ArrayList<>();

		PackageManager pm = context.getPackageManager();
		List<PackageInfo> packageInfos = pm.getInstalledPackages(0);

		for (PackageInfo info : packageInfos) {
			AppInfo appInfo = new AppInfo();
			// 包名
			String packName = info.packageName;
			Drawable icon = info.applicationInfo.loadIcon(pm);
			// 应用名
			String name = info.applicationInfo.loadLabel(pm).toString();

			int flags = info.applicationInfo.flags;

			if ((flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
				// 用户程序
				appInfo.setUser(true);
			} else {
				// 系统程序
				appInfo.setUser(false);
			}

			if ((flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) == 0) {
				// 内部程序
				appInfo.setRom(true);
			} else {
				// 外部程序
				appInfo.setRom(false);
			}
			appInfo.setIcon(icon);
			appInfo.setPackName(packName);
			appInfo.setName(name);
			appInfos.add(appInfo);
		}

		return appInfos;
	}
}
