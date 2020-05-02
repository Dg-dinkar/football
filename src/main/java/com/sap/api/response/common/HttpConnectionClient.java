package com.sap.api.response.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Field;

public class HttpConnectionClient {

    private final OkHttpClient httpClient = new OkHttpClient();

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public <T> T sendHttpPostRequest(String url, String payload, Class<T> responseClazz) throws Exception {
        String response = sendPost(url, payload);
        return (T) mapJsonToObject(response, responseClazz);
    }

    private String sendPost(String url, String payload) throws Exception {
        //RequestBody formBody = getRequestBody(clazz);
        RequestBody formBody = RequestBody.create(JSON, payload);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .post(formBody)
                .build();
        Response response = null;
        StringBuilder result = new StringBuilder("");
        try  {
            response = httpClient.newCall(request).execute();

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            result.append(response.body().string());
            System.out.println(result.toString());
        } catch (Exception ex) {

        }
        return result.toString();
    }

    public String sendGET(String url, String queryParams) throws IOException {

        Request request = new Request.Builder()
                .url(url+queryParams)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .build();

        System.out.println("Request : " + request);
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
            //System.out.println("Response : " + response.body().string());
            return response.body().string();
            //return (T) mapJsonToObject(response.body().string(), responseClazz);
        } catch (Exception ex) {
            System.out.println("GET EXP : "+ex);
        }
        return null;

//
//        Request request = new Request.Builder()
//                .url(url+queryParams)
//                .build();
//
//        httpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                try (ResponseBody responseBody = response.body()) {
//                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//                    // Get response headers
//                    Headers responseHeaders = response.headers();
//                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
//                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//                    }
//                    result.append(responseBody.toString());
//                    // Get response body
//                    System.out.println(responseBody.string());
//                }
//            }
//        });

    }

    private RequestBody getRequestBody(Object clazz) throws IllegalAccessException {
        FormBody.Builder builder = new FormBody.Builder();
        Class<?> objClass = clazz.getClass();
        Field[] fields = objClass.getFields();
        for(Field field : fields) {
            String name = field.getName();
            Object value = field.get(clazz);
            System.out.println(name + ": " + value.toString());
            builder.add(name, (String) value);
        }
        RequestBody body = builder.build();
        return builder.build();
    }

    public static <T> T mapJsonToObject(String jsonObject, Class<T> clazz) throws RuntimeException {
        try {
            T returnValue = objectMapper.readValue(jsonObject, clazz);
            return returnValue;
        } catch (Exception var3) {
            System.out.println("Map: " + var3);
            throw new RuntimeException(var3);
        }
    }

}
