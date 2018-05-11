package com.main;

/**
 * Listing 1.1  of <i>Netty in Action</i>
 *
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
public class CallbackFetcherExample {

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.doWork();
    }

    public static interface Fetcher {
        void fetchData(FetchCallback callback);
    }

    public static class FetcherImpl implements Fetcher {

        @Override
        public void fetchData(FetchCallback callback) {
            try {
                callback.onData(new Data());
            } catch (Exception e) {
                callback.onError(e);
            }
        }
    }

    public static interface FetchCallback {
        void onData(Data data);
        void onError(Throwable cause);
    }

    public static class Worker {
        public void doWork() {
            Fetcher fetcher = new FetcherImpl(); // obtain Fetcher instance
            fetcher.fetchData(new FetchCallback() {
                @Override
                public void onData(Data data) {
                    System.out.println("Data received: " + data);
                }

                @Override
                public void onError(Throwable cause) {
                    System.err.println("An error accour: " + cause.getMessage());
                }
            });

        }
    }

    public static class Data {
        private int age;

        @Override
        public String toString() {
            return "Data{" +
                    "age=" + age +
                    '}';
        }
    }
}
