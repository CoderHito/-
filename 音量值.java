// 得到当前音量和最大音量值
		am = (AudioManager) getSystemService(AUDIO_SERVICE);
		// 得到当前音量大小
		currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		// 最大音量值 范围0-15之间
		maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);