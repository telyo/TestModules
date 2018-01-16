package com.telyo.mvp.mvp.mode;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/12/7.
 */

public class MainMode {
    
    public void getDate(final CallBack callBack){
        Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            subscriber.onNext("返回的数据");
                        }
                    }
                }).start();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e);
                    }

                    @Override
                    public void onNext(String s) {
                        callBack.onSuccess(s);
                    }
                });
    }
    
    public interface CallBack{
        void onError(Throwable e);
        void onSuccess(String s);
    }
    
    public void interruptHttp(){
        
    }
}
