package io.nanonews.nanonews;

/**
 * Created by louistsai on 25.08.17.
 */
public interface DataFetchingCallback<T> {
    void onDataFetched(T result);
    void onFail(Throwable exception);
}
