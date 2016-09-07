/**
	 * 得到功能清单中版本名称
	 * 
	 * 包管理器
	 * 
	 * @return
	 */
	private String getVersionName() {
		// 包管理器
		PackageManager pm = getPackageManager();
		try {
			// 功能清单文件信息
			PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
			return packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "";
		}
	}