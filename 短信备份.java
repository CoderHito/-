
/**
工具类中定义了一个回调接口，高效方便
*/
new Thread() {
				public void run() {
					try {
						SmsBackupUtils.smsBackUp(AToolsActivity.this,
								file.getAbsolutePath(),
								new SmsBackUpCallBack() {

									@Override
									public void callBackProgress(int progress) {
										dialog.setProgress(progress);
									}

									@Override
									public void callBackBefore(int total) {
										dialog.setMax(total);
									}
								});
						// Toast.makeText(this, "短信备份成功", 0).show();
						dialog.dismiss();
					} catch (Exception e) {
						e.printStackTrace();
						dialog.dismiss();
						// Toast.makeText(this, "短信备份失败", 0).show();
					}
				};
			}.start();


			
//工具类
public class SmsBackupUtils {

	/**
	 * 短信备份回调接口
	 * 
	 * @author Hito
	 * 
	 */
	public interface SmsBackUpCallBack {
		/**
		 * 备份前回调 获得短信总数
		 * 
		 * @param total
		 */
		public void callBackBefore(int total);

		/**
		 * 获得短信备份的进度
		 * 
		 * @param progress
		 */
		public void callBackProgress(int progress);
	}

	/**
	 * 短信备份
	 * 
	 * @param context
	 *            上下文
	 * @param path
	 *            保存的路径
	 * @throws Exception
	 */
	public static void smsBackUp(Context context, String path,
			SmsBackUpCallBack callBack) throws Exception {
		ContentResolver resolver = context.getContentResolver();
		XmlSerializer serializer = Xml.newSerializer();
		File file = new File(path);
		FileOutputStream os = new FileOutputStream(file);
		serializer.setOutput(os, "utf-8");// 设置参数
		serializer.startDocument("utf-8", true);

		serializer.startTag(null, "smss");

		// 把所有的短信备份
		Uri uri = Uri.parse("content://sms");
		Cursor cursor = resolver.query(uri, new String[] { "address", "date",
				"type", "body" }, null, null, null);
		// 设置总条数
		// dialog.setMax(cursor.getCount());
		callBack.callBackBefore(cursor.getCount());
		int progress = 0;
		while (cursor.moveToNext()) {
			serializer.startTag(null, "sms");

			serializer.startTag(null, "address");
			String address = cursor.getString(0);
			serializer.text(address);
			serializer.endTag(null, "address");

			serializer.startTag(null, "date");
			String date = cursor.getString(1);
			serializer.text(date);
			serializer.endTag(null, "date");

			serializer.startTag(null, "type");
			String type = cursor.getString(2);
			serializer.text(type);
			serializer.endTag(null, "type");

			serializer.startTag(null, "body");
			String body = cursor.getString(3);
			serializer.text(body);
			serializer.endTag(null, "body");

			serializer.endTag(null, "sms");
			progress++;
			// dialog.setProgress(progress);
			callBack.callBackProgress(progress);
			SystemClock.sleep(200);
		}
		cursor.close();
		serializer.endTag(null, "smss");
		serializer.endDocument();

	}
}
