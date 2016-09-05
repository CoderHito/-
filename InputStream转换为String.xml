public class StreamTools {
	private static String result;

	/**
	 * @param is
	 *            输入流
	 * @return String 返回的字符串
	 * @throws Exception
	 */
	public static String readFromStream(InputStream is) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] buff = new byte[1024];

		int len = 0;

		try {
			while ((len = is.read(buff)) != -1) {
				baos.write(buff, 0, len);
			}
			result = baos.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
				baos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return result;

	}
}
