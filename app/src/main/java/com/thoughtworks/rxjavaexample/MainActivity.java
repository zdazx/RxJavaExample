package com.thoughtworks.rxjavaexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {
    private Button rxBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rxBtn = findViewById(R.id.rx_btn);
        rxBtn.setOnClickListener(view -> changeNumber());
    }

    private void changeNumber() {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(10)
                .map(this::numberMapString)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onStart() {
                        rxBtn.setEnabled(false);
                    }

                    @Override
                    public void onCompleted() {
                        rxBtn.setEnabled(true);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        rxBtn.setText(s);
                    }
                });
    }

    private String numberMapString(Long aLong) {
        return "The number is " + aLong;
    }
}