package qilee.cn.fastec;

import android.os.Bundle;
import android.os.TokenWatcher;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import net.RestClient;
import net.callback.IError;
import net.callback.IFailure;
import net.callback.ISuccess;

import delegates.LatteDelegate;

/**
 * Created by qilee on 2017/11/6.
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestCliet();
    }

    private void testRestCliet(){

        RestClient.builder()
                .url("http://www.baidu.com")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .bulid()
                .get();
    }
}
