// �õ���ǰ�������������ֵ
		am = (AudioManager) getSystemService(AUDIO_SERVICE);
		// �õ���ǰ������С
		currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		// �������ֵ ��Χ0-15֮��
		maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);