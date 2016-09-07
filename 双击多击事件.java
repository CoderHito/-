	/**
	 * 自定义双击事件
	 * 
	 * @param v
	 */
	private long firstClickTime;

	public void doubleClick(View v) {
		if (firstClickTime > 0) {
			long secondClickTime = SystemClock.uptimeMillis();
			long dTime = secondClickTime - firstClickTime;
			if (dTime < 500) {
				Toast.makeText(this, "双击事件", 0).show();
			}
			firstClickTime = 0;
			return;
		}
		System.out.println("点击了");
		firstClickTime = SystemClock.uptimeMillis();

	}

	/**
	 * 三击事件
	 * 
	 * @param v
	 */
//改变数组长度就是多击
	long mHits[] = new long[3];

	public void threeClick(View v) {
		// 复制数组
		// src 原数组,
		// srcPos 原数组开始位置,
		// dst 目标数组,
		// dstPos 目标数组开始位置,
		// length拷贝的个数

		// 三击事件，三次调用这个方法，每次都是数组往前移一位，并且最后一位赋值为当前时间
		System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
		mHits[mHits.length - 1] = SystemClock.uptimeMillis();
		if (mHits[0] >= (SystemClock.uptimeMillis() - 500)) {
			Toast.makeText(this, "33333333", 0).show();
		}
	}
