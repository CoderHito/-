1.runOnUiThread(Runnable)              在子线程中直接使用该方法，可以更新UI 小任务


runOnUiThread(new Runnable(){//更新UI
                    @Override
                    public void run() {
                        publish_time.setText("更新失败");
                    }
                    
                });
2.View.postDelay(Runnable , long)/new Handler().postDelayed(Runnable)

在需要更新UI的地方调用该方法，Runnable对象的方法里，直接操作UI；long是指延迟多少秒

//延迟一秒钟出现
        new Handler().postDelayed(new Runnable() {
            
            @Override
            public void run() {
                // TODO Auto-generated method stub
                notify_view_text.setText(String.format(getString(R.string.ss_pattern_update), 10));
                notify_view.setVisibility(View.VISIBLE);
                //延迟两秒钟消失
                new Handler().postDelayed(new Runnable() {
                    
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        notify_view.setVisibility(View.GONE);
                    }
                }, 2000);
            }
        }, 1000);



3.使用Handler


4.使用AsyncTask
