package com.h2y.fp.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.h2y.util.JSONUtil;
import com.h2y.util.PropertiesUtil;

/**
 * 基础Controller

 * @author：段晓刚

 * @update：2015年4月9日 上午9:15:01

 * @Email：
 */
@Controller
public class BaseController {
	
    protected DecimalFormat format = new DecimalFormat("0.00");
    protected HttpServletResponse response;
    protected HttpServletRequest request;
    protected HttpSession session;

    //url通知
    private Map<String, String> urlMap = new HashMap<String, String>();

    protected Charset utf_8 = Charset.forName("UTF-8");
    

    //protected MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();

    /**
     * 获取用户信息
     *
     * @return
     */
    protected Map<String, Object> getCurrentUser() {

        return null;
    }

    /**
     * 获取URL配置信息
     *
     * @return
     * @throws java.io.IOException
     * @update：2014年8月4日 下午8:42:09
     */
    protected Map<String, String> getURLConfing() throws IOException {

        if (urlMap == null || urlMap.isEmpty())
            urlMap = PropertiesUtil.getInstance("/webconfig.properties").getMap();
        return urlMap;
    }

    /**
     * @ModelAttribute放置在方法上面：表示请求该类的每个Action前都会首先执行它， 你可以将一些准备数据的操作放置在该方法里面
     */
    @ModelAttribute
    public void setReqAndResp(HttpServletResponse response, HttpServletRequest request) {
        this.response = response;
        this.request = request;
        this.session = request.getSession();
    }

    protected void outJson(Object obj) {
        out(JSONUtil.getJson(obj));
    }

    /**
     * 输出数据
     *
     * @param value
     */
    protected void out(String value) {
        PrintWriter out = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            out.println(value);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}