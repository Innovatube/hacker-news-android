package com.innovatube.hackernews.utils;

import com.innovatube.hackernews.consts.Consts;
import com.innovatube.hackernews.data.model.ApiError;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.ConnectException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Thanh on 13/09/2016.
 */
public class HackerNewsUtils {
    public static String getError(Throwable e, Retrofit retrofit) {
        String errorMessage = Consts.GENERIC_ERROR;
        if (e instanceof HttpException) {
            ResponseBody body = ((HttpException) e).response().errorBody();

            Converter<ResponseBody, ApiError> responseBodyObjectConverter =
                    retrofit.responseBodyConverter(ApiError.class, new Annotation[0]);
            try {
                ApiError error = responseBodyObjectConverter.convert(body);
                if (error != null) {
                    errorMessage = error.getMessage();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (e instanceof ConnectException) {
            errorMessage = Consts.SERVER_ERROR;
        } else {
            e.printStackTrace();
        }
        return errorMessage;
    }
}
