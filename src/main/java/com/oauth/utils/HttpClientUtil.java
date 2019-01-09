package com.oauth.utils;

//import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @comment 提供http访问
 * @author xhl
 * @version V1.0
 * @date 2015年8月7日  下午4:20:59
 */
public class HttpClientUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    private static RequestConfig requestConfig;

    private static String encoding = "UTF-8";
    static CloseableHttpClient httpClient;

    static {
        //服务器响应超时:读取超时
        Integer socketTimeout = 60 * 1000;
        //服务器请求超时:连接超时
        Integer connectTimeout = 5 * 1000;
        Integer connectionRequestTimeout = 3 * 1000;
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setDefaultMaxPerRoute(20);
        cm.setMaxTotal(200);
        httpClient = HttpClients.custom().setConnectionManager(cm).build();
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionRequestTimeout).build();
    }

    /**
     * @param url      请求url
     * @param postData POST请求时form表单封装的数据 没有时传null
     * @return response返回的文本数据
     * @throws Exception
     */
    public static String doPostRequest(String url, Map<String, Object> postData) throws Exception {

        return doPostRequest(url, postData, null);
    }

    /**
     * @param url      请求url
     * @param postData POST请求时form表单封装的数据 没有时传null
     * @param header   头信息,没有时传null
     * @return response返回的文本数据
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public static String doPostRequest(String url, Map<String, Object> postData, Map header) throws IOException {
        HttpResponse response = null;
        try {

            //Fix by meng
            if (null == postData) {
                postData = new HashMap<String, Object>();
            }
            //post参数传递
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            for (Map.Entry<String, Object> entry : postData.entrySet()) {
                if (null != entry.getValue()) {
                    params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                }
            }
            // 目标地址
            HttpPost httpPost = new HttpPost(url);

            //httpPost.setConfig(requestConfig);
            httpPost.setEntity(new UrlEncodedFormEntity(params, encoding)); // 设置参数给Post

            // 得到返回的response.
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return getResult(entity);
        } catch (Exception e) {
            logger.info("--------HttpClient请求失败-----");
            e.printStackTrace();
        } finally {
            if (response != null) {
                EntityUtils.consume(response.getEntity()); //会自动释放连接
            }
        }
        return null;
    }

    /**
     * @param url 请求url
     * @return response返回的文本数据
     * @throws Exception
     */
    public static String doGetRequest(String url) throws Exception {
        return doGetRequest(url, null);
    }

    /**
     * @param url    请求url
     * @param header 头信息,没有时传null
     * @return response返回的文本数据
     * @throws Exception
     */
    public static String doGetRequest(String url, Map<String,Object> header) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        // 得到返回的response.
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        return getResult(entity);
    }

    /**
     * @param url 请求url
     * @return response返回的文本数据
     * @throws Exception
     */
    public static HttpEntity doGetEntity(String url) throws Exception {
        return doGetEntity(url, null);
    }


    /**
     * @param url    请求url
     * @param header 头信息,没有时传null
     * @return response返回的文本数据
     * @throws Exception
     */
    public static HttpEntity doGetEntity(String url, Map<String,Object> header) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        // 得到返回的response.
        HttpResponse response = httpClient.execute(httpGet);
        return response.getEntity();
    }

    /**
     * 获取远程的图片到指定路劲
     *
     * @param url      请求url
     * @param des      保存目录,不含“//”后缀
     * @param fileName 文件名称
     * @return bo
     */
    public static boolean doGetRemoteImg(String url, String des, String fileName) {
        boolean bo = false;
        InputStream is = null;
        FileOutputStream output = null;
        try {
            is = doGetEntity(url).getContent();
            String fullFileName = des + "//" + fileName;
            File file = new File(fullFileName);
//            output = org.apache.commons.io.FileUtils.openOutputStream(file);
            IOUtils.copy(is, output);
            bo = true;
        } catch (Exception e) {
            bo = false;
        } finally {
            if (null != output) {
                IOUtils.closeQuietly(output);
            }
            if (null != is) {
                IOUtils.closeQuietly(is);
            }
        }
        return bo;
    }

    /**
     * 返回结果转String
     *
     * @param entity HttpEntity
     * @return String
     */
    private static String getResult(HttpEntity entity) {
        StringBuilder sbResult = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    entity.getContent(), encoding));
            String line;
            while ((line = reader.readLine()) != null) {
                sbResult.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sbResult.toString();
    }

    public static String httpPostWithJSON(String urls, String date) throws Exception {
        try {
            // 创建连接
            URL url = new URL(urls);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "text/xml");
            connection.connect();
            // POST请求
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
            out.append(date);
            out.flush();
            out.close();
            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            reader.close();
            return "success";
        } catch (Exception e) {
            throw new Exception("执行HttpClient发生异常");
        }
    }

    public static String httpPostWithJSONOld(String urls, String date) throws Exception {
        try {
            // 创建连接
            URL url = new URL(urls);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "text/xml");
            connection.connect();
            // POST请求
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
            out.append(date);
            out.flush();
            out.close();
            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            reader.close();
            // 断开连接
            connection.disconnect();
            return sb.toString();
        } catch (Exception e) {
            throw new Exception("执行HttpClient发生异常");
        }
    }

    /**
     * 返回处理结果
     * @param data
     * @throws IOException
     */
    public static void backToWX(String data, HttpServletResponse response) throws Exception  {
        //输出结果
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print(data);
        out.flush();
        out.close();
    }


    /**
     * 开始post提交参数到接口
     * 并接受返回
     * @param url
     * @param xml
     * @param method
     * @param contentType
     * @return
     */
    public static String xmlHttpProxy(String url,String xml,String method, String contentType){
        InputStream is = null;
        OutputStreamWriter os = null;

        try {
            URL _url = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-type", "text/xml");
            conn.setRequestProperty("Pragma:", "no-cache");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestMethod("POST");
            os = new OutputStreamWriter(conn.getOutputStream());
            os.write(new String(xml.getBytes(contentType)));
            os.flush();

            //返回值
            is = conn.getInputStream();
            return getContent(is, "utf-8");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                if(os!=null){os.close();}
                if(is!=null){is.close();}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 解析返回的值
     * @param is
     * @param charset
     * @return
     */
    public static String getContent(InputStream is, String charset) {
        String pageString = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuffer sb = null;
        try {
            isr = new InputStreamReader(is, charset);
            br = new BufferedReader(isr);
            sb = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            pageString = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null){
                    is.close();
                }
                if(isr!=null){
                    isr.close();
                }
                if(br!=null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb = null;
        }
        return pageString;
    }
    
}
