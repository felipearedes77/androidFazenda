package com.example.projeto.ApiService;
import com.example.projeto.model.Usuario;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
public class NullOnEmptyConverterFactory extends Converter.Factory {
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
        return new Converter<ResponseBody, Usuario>() {
            @Override
            public Usuario convert(ResponseBody body) throws IOException {
                if (body.contentLength() == 0) return null;
                return (Usuario) delegate.convert(body);
            }
        };
    }
}
