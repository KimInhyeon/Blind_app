package com.ksinfo.blind.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HttpClient {
    private static final String WWW_FORM = "application/x-www-form-urlencoded";

    private int httpStatusCode;
    private String body;

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getBody() {
        return body;
    }

    private Builder builder;

    private void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public void request() {
        HttpURLConnection conn = getConnection();
        setHeader(conn);
        setBody(conn);
        httpStatusCode = getStatusCode(conn);
        body = readStream(conn);
        conn.disconnect();
    }

    private HttpURLConnection getConnection() {
        try {
            URL url = new URL(builder.getUrl());
            URLConnection uc = null;

            try {
                uc = url.openConnection();
            } catch (IOException e) {

            }

            uc.setDoOutput(false);  // 스트림 출력 설정
            uc.setDoInput(true);    // 스트림 입력 설정 -- 기본적으로 입력 스트림

            return (HttpURLConnection) uc;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setHeader(HttpURLConnection connection) {
        setContentType(connection);
        setRequestMethod(connection);

        connection.setConnectTimeout(5000);
        connection.setDoOutput(true);
        connection.setDoInput(true);
    }

    private void setContentType(HttpURLConnection connection) {
        connection.setRequestProperty("Content-Type", WWW_FORM);
    }

    private void setRequestMethod(HttpURLConnection connection) {
        try {
            connection.setRequestMethod(builder.getMethod());
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
    }

    private void setBody(HttpURLConnection connection) {

        String parameter = builder.getParameters();
        if ( parameter != null && parameter.length() > 0 ) {
            OutputStream outputStream = null;
            try {
                outputStream = connection.getOutputStream();
                outputStream.write(parameter.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if ( outputStream != null )
                        outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }        }

    }

    private int getStatusCode(HttpURLConnection connection) {
        try {

            InputStream inputStream;
            int status = connection.getResponseCode();

            if (status != HttpURLConnection.HTTP_OK)  {
                inputStream = connection.getErrorStream();
            }
            else  {
                inputStream = connection.getInputStream();
            }

            return connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return -10;
    }

    private String readStream(HttpURLConnection connection) {
        String result = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            while ( (line = reader.readLine()) != null ) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader != null)
                    reader.close();
            } catch (IOException e) {}
        }

        return result;
    }

    public static class Builder {

        private final Map<String, String> parameters;
        private final String method;
        private final String url;

        public String getMethod() {
            return method;
        }

        public String getUrl() {
            return url;
        }

        public Builder(String method, String url) {
            if(method == null) {
                method = "GET";
            }
            this.method = method;
            this.url = url;
            this.parameters = new HashMap<String, String>();
        }

        public void addOrReplace(String key, String value) {
            this.parameters.put(key, value);
        }

        public void addAllParameters(Map<String, String> param) {
            this.parameters.putAll(param);
        }

        public String getParameters() {
            return generateParameters();
        }

        public String getParameter(String key) {
            return this.parameters.get(key);
        }

        private String generateParameters() {
            StringBuffer parameters = new StringBuffer();

            Iterator<String> keys = getKeys();

            String key = "";
            while ( keys.hasNext() ) {
                key = keys.next();
                parameters.append(String.format("%s=%s", key, this.parameters.get(key)));
                parameters.append("&");
            }

            String params = parameters.toString();
            if ( params.length() > 0 ) {
                params = params.substring( 0, params.length() - 1 );
            }

            return params;
        }

        private Iterator<String> getKeys() {
            return this.parameters.keySet().iterator();
        }

        public HttpClient create() {
            HttpClient client = new HttpClient();
            client.setBuilder(this);
            return client;
        }
    }
}
