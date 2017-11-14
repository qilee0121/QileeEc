package com.qile.net;

import android.content.Context;

import com.qile.net.callback.IError;
import com.qile.net.callback.IFailure;
import com.qile.net.callback.IRequest;
import com.qile.net.callback.ISuccess;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import com.qile.ui.LoaderStyle;

/**
 * Created by qilee on 2017/11/6.
 */

public class RestClientBuilder {
    private String mUrl = null;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private ISuccess mSuccess = null;
    private IFailure mFailure = null;
    private IError mError = null;
    private IRequest mRequest = null;
    private RequestBody mBody = null;
    private File mFile = null;
    private Context mContext = null;
    private LoaderStyle mStyle = null;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String mUrl) {
        this.mUrl = mUrl;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> mParams) {
        PARAMS.putAll(mParams);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest request) {
        this.mRequest = request;
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset = UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess success) {
        this.mSuccess = success;
        return this;
    }

    public final RestClientBuilder failure(IFailure failure) {
        this.mFailure = failure;
        return this;
    }

    public final RestClientBuilder error(IError error) {
        this.mError = error;
        return this;
    }

    public final RestClientBuilder loader(Context context,LoaderStyle style){
        this.mContext = context;
        this.mStyle = style;
        return this;
    }
    public final RestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }
    public final RestClientBuilder file(String file){
        this.mFile = new File(file);
        return this;
    }
    public final RestClientBuilder loader(Context context){
        this.mContext = context;
        this.mStyle = LoaderStyle.BallClipRotateIndicator;
        return this;
    }

    public final RestClient bulid() {
        return new RestClient(mUrl, PARAMS, mSuccess, mFailure, mError, mRequest, mBody,mFile,mContext,mStyle);
    }
}
