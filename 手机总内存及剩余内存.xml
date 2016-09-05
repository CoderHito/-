
	/**
	 * 得到可用内存
	 * 
	 * @param context
	 * @return
	 */
	public static long getAvailRam(Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo outInfo = new MemoryInfo();
		am.getMemoryInfo(outInfo);
		return outInfo.availMem;
	}

	/**
	 * 得到总的内存
	 * 
	 * @param context
	 * @return
	 */
	public static long getTotalRam(Context context) {
		// 下面代码只支出4.1及以上版本,如果要支持低版本的话，需要去读取meminfo
		// ActivityManager am = (ActivityManager) context
		// .getSystemService(Context.ACTIVITY_SERVICE);
		// MemoryInfo outInfo = new MemoryInfo();
		// am.getMemoryInfo(outInfo);
		StringBuffer buffer = null;
		try {
			File file = new File("proc/meminfo");
			FileInputStream fis = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			buffer = new StringBuffer();
			for (char c : line.toCharArray()) {
				if (c >= '0' && c <= '9') {
					buffer.append(c);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		return Integer.valueOf(buffer.toString()) * 1024;
	}