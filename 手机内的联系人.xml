private void filData() {
		new Thread() {
			public void run() {

				names = new ArrayList<String>();
				details = new ArrayList<ArrayList<String>>();
				Cursor cursor = getContentResolver().query(
						ContactsContract.Contacts.CONTENT_URI, null, null,
						null, null);
				while (cursor.moveToNext()) {
					// 获取联系人的ID
					String contactId = cursor.getString(cursor
							.getColumnIndex(ContactsContract.Contacts._ID));
					// 获取联系人的姓名
					String name = cursor
							.getString(cursor
									.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
					names.add(name);
					// 查找联系人的电话号码
					Cursor phones = getContentResolver().query(
							ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID
									+ " = " + contactId, null, null);
					ArrayList<String> detail = new ArrayList<>();
					while (phones.moveToNext()) {
						String phoneNumber = phones
								.getString(phones
										.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
						detail.add("电话号码" + phoneNumber);
					}
					phones.close();
					details.add(detail);
				}

				handler.sendEmptyMessage(0);
			};
		}.start();
	}
	
	**********************
	
	
	private List<Map<String, String>> getAllContents() {
		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		ContentResolver resolver = getContentResolver();

		Uri raw_contacts_uri = Uri
				.parse("content://com.android.contacts/raw_contacts");
		Uri data_uri = Uri.parse("content://com.android.contacts/data");
		// 这个cursor保存了所有人的id
		Cursor cursor = resolver.query(raw_contacts_uri,
				new String[] { "contact_id" }, null, null, null);
		while (cursor.moveToNext()) {
			String contact_id = cursor.getString(0);
			if (contact_id != null) {
				// 这里有一个人
				Map<String, String> map = new HashMap<String, String>();
				// 这个人的所有信息data1，以及信息所代表的是什么
				Cursor dataCursor = resolver.query(data_uri, new String[] {
						"data1", "mimetype" }, "raw_contact_id=?",
						new String[] { contact_id }, null);
				// 循环结束，这个人的信息查询完毕
				while (dataCursor.moveToNext()) {
					String data1 = dataCursor.getString(0);
					String mimetype = dataCursor.getString(1);

					System.out.println("data1:" + data1 + "mimetype:"
							+ mimetype);
					// 从mimetype表中查看具体哪些信息
					if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
						// 电话号码
						map.put("number", data1);
					} else if ("vnd.android.cursor.item/name".equals(mimetype)) {
						// 姓名
						map.put("name", data1);
					}
				}
				dataCursor.close();
				if (map.get("name") != null && map.get("number") != null) {
					maps.add(map);
				}
			}
		}
		cursor.close();

		return maps;
	}