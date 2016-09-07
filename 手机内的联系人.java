private void filData() {
		new Thread() {
			public void run() {

				names = new ArrayList<String>();
				details = new ArrayList<ArrayList<String>>();
				Cursor cursor = getContentResolver().query(
						ContactsContract.Contacts.CONTENT_URI, null, null,
						null, null);
				while (cursor.moveToNext()) {
					// ��ȡ��ϵ�˵�ID
					String contactId = cursor.getString(cursor
							.getColumnIndex(ContactsContract.Contacts._ID));
					// ��ȡ��ϵ�˵�����
					String name = cursor
							.getString(cursor
									.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
					names.add(name);
					// ������ϵ�˵ĵ绰����
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
						detail.add("�绰����" + phoneNumber);
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
		// ���cursor�����������˵�id
		Cursor cursor = resolver.query(raw_contacts_uri,
				new String[] { "contact_id" }, null, null, null);
		while (cursor.moveToNext()) {
			String contact_id = cursor.getString(0);
			if (contact_id != null) {
				// ������һ����
				Map<String, String> map = new HashMap<String, String>();
				// ����˵�������Ϣdata1���Լ���Ϣ���������ʲô
				Cursor dataCursor = resolver.query(data_uri, new String[] {
						"data1", "mimetype" }, "raw_contact_id=?",
						new String[] { contact_id }, null);
				// ѭ������������˵���Ϣ��ѯ���
				while (dataCursor.moveToNext()) {
					String data1 = dataCursor.getString(0);
					String mimetype = dataCursor.getString(1);

					System.out.println("data1:" + data1 + "mimetype:"
							+ mimetype);
					// ��mimetype���в鿴������Щ��Ϣ
					if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
						// �绰����
						map.put("number", data1);
					} else if ("vnd.android.cursor.item/name".equals(mimetype)) {
						// ����
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