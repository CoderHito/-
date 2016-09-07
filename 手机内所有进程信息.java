/**
	 * 得到手机所有的运行进程信息
	 * 
	 * @param context
	 *            上下文
	 * @return
	 */
	public static List<TaskInfo> getAllTaskInfos(Context context) {
		List<TaskInfo> taskInfos = new ArrayList<>();
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		PackageManager pm = context.getPackageManager();
		List<RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
		for (RunningAppProcessInfo processInfo : processInfos) {
			TaskInfo taskInfo = new TaskInfo();
			// 包名
			String packName = processInfo.processName;
			taskInfo.setPackName(packName);
			MemoryInfo memoryInfo = am
					.getProcessMemoryInfo(new int[] { processInfo.pid })[0];
			// 这个应用在内存中的大小
			long meminfoSize = memoryInfo.getTotalPrivateDirty() * 1024;
			taskInfo.setMeminfosize(meminfoSize);
			try {
				// 图标
				Drawable icon = pm.getPackageInfo(packName, 0).applicationInfo
						.loadIcon(pm);
				taskInfo.setIcon(icon);
				// 软件名称
				String name = pm.getPackageInfo(packName, 0).applicationInfo
						.loadLabel(pm).toString();
				taskInfo.setName(name);
				int flag = pm.getPackageInfo(packName, 0).applicationInfo.flags;
				if ((flag & ApplicationInfo.FLAG_SYSTEM) == 0) {
					// 用户进程
					taskInfo.setUser(true);
				} else {
					// 系统进程
					taskInfo.setUser(false);
				}
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// 为空的时候，使用包名及默认的图片
				taskInfo.setName(packName);
				taskInfo.setIcon(context.getResources().getDrawable(
						R.drawable.ic_launcher));
			}

			taskInfos.add(taskInfo);
		}

		return taskInfos;
	}