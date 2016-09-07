/**
	 * 加载视频，在子线程中运行
	 */
	private void getAllVideo() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				videoItems = new ArrayList<VideoItem>();
				// 把手机里面的所有视频信息读取出来
				ContentResolver contentResolver = getContentResolver();
				Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				String projection[] = { MediaStore.Video.Media.TITLE,// 标题
						MediaStore.Video.Media.DURATION,// 时长
						MediaStore.Video.Media.SIZE,// 视频文件的大小
						MediaStore.Video.Media.DATA // 视频在sd卡的绝对地址-播放
				};
				Cursor cursor = contentResolver.query(uri, projection, null,
						null, null);
				while (cursor.moveToNext()) {
					VideoItem item = new VideoItem();

					String title = cursor.getString(0);
					String duration = cursor.getString(1);
					long size = cursor.getLong(2);
					String data = cursor.getString(3);

					item.setTitle(title);
					item.setSize(size);
					item.setData(data);
					item.setDuration(duration);

					videoItems.add(item);

				}
				mHandler.sendEmptyMessage(0);
			}
		}).start();
	}