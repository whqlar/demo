package com.demo.java.concurrent;

import com.demo.AbstractDemoTestBase;
import org.junit.Test;
import rx.*;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Created by wu on 16/8/25.
 */
public class RxJavaTest extends AbstractDemoTestBase {


    @Test
    public void test() {
        Observable.just("jim", "tom").reduce(new Func2<String, String, String>() {
            @Override
            public String call(String s, String s2) {
                return null;
            }
        });


        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
//                subscriber.on
            }
        });
    }


    @Test
    public void testSimple() {
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                print("Item: " + s);
            }

            @Override
            public void onCompleted() {
                print("Completed!");
            }

            @Override
            public void onError(Throwable e) {
                print("Error!");
            }
        };

        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
                throw new RuntimeException();
            }
        });

        observable.subscribe(subscriber);
    }

    @Test
    public void testRange() {
        Observable.range(2, 5).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer age) {
                print(age);
            }
        });
    }

    @Test
    public void testJust() {
        Observable.just(2, 3).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer age) {
                print(age);
            }
        });
    }

    @Test
    public void testDefer() {
    }

    @Test
    public void testFrom() {
        Observable.from(new String[]{"jim", "tom"}).subscribe(new Action1<String>() {
            @Override
            public void call(String name) {
                print(name);
            }
        });
    }
}
