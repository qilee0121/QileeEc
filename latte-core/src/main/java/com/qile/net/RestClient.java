package com.qile.net;

import android.content.Context;

import com.qile.net.callback.IError;
import com.qile.net.callback.IFailure;
import com.qile.net.callback.IRequest;
import com.qile.net.callback.ISuccess;
import com.qile.net.callback.RequestCallbacks;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Multipart;

import com.qile.ui.LatteLoader;
import com.qile.ui.LoaderStyle;


/**
 * Created by qilee on 2017/11/6.
 */

public class RestClient {

    private final String URL;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final IRequest REQUEST;
    private final RequestBody BODY;
    private final File FILE;
    private final Context CONTEXT;
    private final LoaderStyle LOADER_STYLE;

    public RestClient(String url,
                      Map<String, Object> params,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      IRequest request,
                      RequestBody body,
                      File file,
                      Context cotext,
                      LoaderStyle style) {
        this.URL = url;
        this.PARAMS.putAll(params);
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.REQUEST = request;
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = cotext;
        this.LOADER_STYLE = style;
    }

    public static RestClientBuilder builder() {

        return new RestClientBuilder();
    }

    private void request(HttpMethod httpMethod) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (httpMethod) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT_RAW:
                call = service.putRaw(URL,BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType
                        .parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                call = RestCreator.getRestService().upload(URL,body);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(SUCCESS, FAILURE, ERROR, REQUEST, LOADER_STYLE);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAW);
        }

    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
}
