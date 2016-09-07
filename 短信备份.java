
/**
�������ж�����һ���ص��ӿڣ���Ч����
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
						// Toast.makeText(this, "���ű��ݳɹ�", 0).show();
						dialog.dismiss();
					} catch (Exception e) {
						e.printStackTrace();
						dialog.dismiss();
						// Toast.makeText(this, "���ű���ʧ��", 0).show();
					}
				};
			}.start();


			
//������
public class SmsBackupUtils {

	/**
	 * ���ű��ݻص��ӿ�
	 * 
	 * @author Hito
	 * 
	 */
	public interface SmsBackUpCallBack {
		/**
		 * ����ǰ�ص� ��ö�������
		 * 
		 * @param total
		 */
		public void callBackBefore(int total);

		/**
		 * ��ö��ű��ݵĽ���
		 * 
		 * @param progress
		 */
		public void callBackProgress(int progress);
	}

	/**
	 * ���ű���
	 * 
	 * @param context
	 *            ������
	 * @param path
	 *            �����·��
	 * @throws Exception
	 */
	public static void smsBackUp(Context context, String path,
			SmsBackUpCallBack callBack) throws Exception {
		ContentResolver resolver = context.getContentResolver();
		XmlSerializer serializer = Xml.newSerializer();
		File file = new File(path);
		FileOutputStream os = new FileOutputStream(file);
		serializer.setOutput(os, "utf-8");// ���ò���
		serializer.startDocument("utf-8", true);

		serializer.startTag(null, "smss");

		// �����еĶ��ű���
		Uri uri = Uri.parse("content://sms");
		Cursor cursor = resolver.query(uri, new String[] { "address", "date",
				"type", "body" }, null, null, null);
		// ����������
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
