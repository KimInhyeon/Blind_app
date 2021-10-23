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
    //application/x-www-form-urlencoded : 서버에 데이터를 전송하기 위해 사용하는 방식.
    //                                     이름1=값1&이름2=값2&이름3=값3&이름4=값4 식으로 이름과 값이 한쌍으로 구성된다.
    //                                      ( & : 쌍을 분리한다. / = :이름과 값을 분리한다.)

    private int httpStatusCode;  //httpStatusCode : http상태값을 보유. 400(문법에러) 404(찾기불가)같은 상태가 발생시
                                 // httpClientAccessor클래스의 throw new Exception(new Throwable());가 실행.

    private String body;         //body : GSON(JSON문자열을 자바객체로 변환)데이터를 받는 변수. GSON형태면 자바에서 데이터를 바로 사용할 수 있다.

    public int getHttpStatusCode() {    //getHttpStatusCode : HttpStatusCode를 받는다. 404와 같은 에러 코드를 받는거 같다.
        return httpStatusCode;          //                    만약 값이 400이상이면 httpClientAccessor클래스의 throw new Exception(new Throwable());가 실행.
    }

    public String getBody() {
        return body;
    }

    private Builder builder;    //Builder : 본 java파일애서 선언된 오리지널 클래스.
                                //          하단에 있는 public static class Builder {...} 참고.

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

    private HttpURLConnection getConnection() { //HttpURLConnection : http 통신을 수행 객체를 생성할 수 있음.
        try {
            URL url = new URL(builder.getUrl()); // URL : URL객체는 connection(연결할 URL주소)을 만든다.
                                                 //       
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
        connection.setRequestProperty("Content-Type", WWW_FORM); //connection.setRequestProperty : Request Header값 설정
                                                                 //사용법 : 변수명.setRequestProperty(String key, String value );
    }

    private void setRequestMethod(HttpURLConnection connection) {
        try {
            connection.setRequestMethod(builder.getMethod());  //요청방식을 선택(GET/POST 중 택1). 미설정(null)인 경우 get방식으로 설정된다.
        } catch (ProtocolException e) {
            e.printStackTrace();//예외처리실시.
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

    public static class Builder { //Builder : 본 httpClint.java에서 만든 오리지널 클래스.
                                  //          httpClientAccessor 클래스에서 생성시 요청방식(POST/GET)과 url을 설정한다.

        private final Map<String, String> parameters;
        private final String method;                     // method : 요청방식(POST/GET)을 결정&리턴해주는 역할.
        private final String url;                        // url :

        public String getMethod() {                      //
            return method;          // POST/GET
        }

        public String getUrl() {
            return url;
        }

        public Builder(String method, String url) {
            if(method == null) {
                method = "GET";     //전송
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
