package com.aliouswang.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;

import java.util.Random;

import rx.Observable;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "rxjava-test";

    private Button btn_rx_create;
    private Button btn_just;
    private Button btn_rx_flat_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
    }

    private void initView() {
        btn_rx_create = (Button) findViewById(R.id.btn_rx_create);
        btn_rx_create.setOnClickListener(listener ->
            createObserver().subscribe(new Subscriber<Integer>() {
                @Override
                public void onCompleted() {
                    log("onCmpleted");
                }

                @Override
                public void onError(Throwable e) {
                    log("onError:" + e);
                }

                @Override
                public void onNext(Integer integer) {
                    log("onNext:" + integer);
                }
            })
        );

        btn_just = (Button) findViewById(R.id.btn_just);
        btn_just.setOnClickListener(listener -> Observable.defer(()-> Observable.just(3, 5))
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        log("onCmpleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        log("onNext:" + integer);
                    }
                }));

        btn_rx_flat_map = (Button) findViewById(R.id.btn_rx_flat_map);
        Integer [] arrys = {1,2,3};
        btn_rx_flat_map.setOnClickListener(
                view -> Observable.just("Hello map").map(s -> s + "_Dan").subscribe(s -> log(s))
        );
    }

    public void log(String logStr) {
        Log.e(TAG, logStr);
    }

    private Observable<Integer> createObserver() {
        return Observable.create(new Observable.OnSubscribe<Integer>(){

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    for (int i = 0; i < 5; i++) {
                        int temp = new Random().nextInt(10);
                        if (temp > 8) {
                            //if value>8, we make an error
                            subscriber.onError(new Throwable("value >8"));
                            break;
                        } else {
                            subscriber.onNext(temp);
                        }
                        // on error,complete the job
                        if (i == 4) {
                            subscriber.onCompleted();
                        }
                    }
                }
            }
        });
    }

}
