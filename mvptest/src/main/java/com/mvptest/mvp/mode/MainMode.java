package com.mvptest.mvp.mode;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Administrator on 2017/12/11.
 */

public class MainMode {
    
    public void requestSomething(final Context context, final Listener listener){
        listener.onLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                    ((Activity)context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.onError(e);
                        }
                    });
                }finally {
                    ((Activity)context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String s = "返回的结果";
                            listener.onSuccess(s);
                        }
                    });
                }
            }
        }).start();
    }
    
    public interface Listener {
        void onLoading();
        void onSuccess(String s);
        void onError(Exception e);
            
    } 
}
