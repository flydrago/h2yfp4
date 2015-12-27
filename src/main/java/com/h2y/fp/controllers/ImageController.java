package com.h2y.fp.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.h2y.fp.basic.BaseController;
import com.h2y.fp.entity.FileDownMode;
import com.h2y.fp.services.IFileDownService;
import com.h2y.spring.IocUtil;
import com.h2y.util.MatcherUtil;
import com.h2y.util.QRCodeUtil;

/**
 * 提供图片服务
 * 
 * @author：段晓刚
 * 
 * @update：2014年10月31日 上午10:52:32
 * 
 * @Email：
 */
@Controller
@Scope("prototype")
@RequestMapping("/image")
public class ImageController extends BaseController {

	private Logger logger = Logger.getLogger(ImageController.class);

	private static final String GIF = "image/gif;charset=GB2312";
	// 设定输出的类型
	private static final String JPG = "image/jpeg;charset=GB2312";

	private static final String PNG = "image/png;charset=GB2312";

	private static final String BMP = "image/bmp;charset=GB2312";
	
	@RequestMapping(value = "view")
	public void view(HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		showImage(response, request);
	}
	
	
	@RequestMapping(value = "show")
	public void show(HttpServletResponse response, HttpServletRequest request) throws IOException {

		showImage(response, request);
	}

	private void showImage(HttpServletResponse response, HttpServletRequest request) throws IOException {

		String path = request.getParameter("path");
		
		if (null==path || "".equals(path)) {
			
			//获取图片id
			String _fdid = request.getParameter("fdid");
			String beanName = request.getParameter("bn");
			String id = request.getParameter("id");
			
			//兼容以前版本的商品图片加载
			if (null!=_fdid && !"".equals(_fdid)) {
				beanName = "fileService";
				id = _fdid;
			}
			
			if (null!=beanName && !"".equals(beanName)) {
				IFileDownService fileDownService = IocUtil.getBean(beanName);
				FileDownMode fileDownMode = fileDownService.getFileInfo(request, id);
				if (fileDownMode==null) {
					return;
				}
				path = fileDownMode.getFilePath();
			}
		}

//		logger.debug("\n path" + path);
		long fileSize = -1l;
		
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		String imageName = "";
		if (StringUtils.isNotBlank(path)) {
			if (path.startsWith("http:")) {
				URL url = new URL(path);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				// 此连接的 URL 引用的资源的内容长度，或者如果内容长度未知，则返回 -1。
				fileSize = connection.getContentLength();
				try {
					bis = new BufferedInputStream(connection.getInputStream());

					URL url2 = connection.getURL();
					String path2 = url2.getPath();
					imageName = connection.getHeaderField(path2.substring(path2
							.lastIndexOf("/") + 1));
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			} else {
				File file = new File(path);
				if (!file.exists())
					return;
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				fileSize = file.length();
				imageName = file.getName();
			}
			// 在浏览器界面展示图片
			printImage(response, bis, fileSize, imageName);
			fis.close();
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	/**
	 * 展示图片
	 * 
	 * @param is
	 * @param fis
	 * @param response
	 * @param imagePath
	 * @param imageName
	 * @param fileSize
	 * @throws IOException
	 */
	private void printImage(HttpServletResponse response,
			BufferedInputStream bis, long fileSize, String imageName)
			throws IOException {

		if (bis != null) {
			logger.debug("image print begin");
			// 输出图片的类型的标志　　　　
			// 得到输出流
			OutputStream output = response.getOutputStream();
			// 使用编码处理文件流的情况：
			String imp = imageName.toLowerCase();

			// 设定输出的类型
			if (imp.endsWith(".jpg") || imp.endsWith(".jpeg")) {
				// 设定输出的类型
				response.setContentType(JPG);
			} else if (imp.endsWith(".gif")) {
				response.setContentType(GIF);
			} else if (imp.endsWith(".png")) {
				response.setContentType(PNG);
			} else if (imp.endsWith(".bmp")) {
				response.setContentType(BMP);
			} else {
				response.setContentType(JPG);
			}
			response.setHeader("Cache-Control", "max-age=2592000");//设置三十天，不同服务器端访问图片
			response.setHeader("fileName", imageName);
			response.addHeader("fileLength", fileSize + "");// 设置大小

			// 输入缓冲流
			BufferedOutputStream bos = new BufferedOutputStream(output);
			// 输出缓冲流
			byte data[] = new byte[1024*8];
			// 缓冲字节数
			int size = 0;
			size = bis.read(data);
			while (size != -1) {
				bos.write(data, 0, size);
				size = bis.read(data);
			}

			if (bis != null)
				bis.close();
			// 清空输出缓冲流
			bos.flush();

			if (bos != null)
				bos.close();
			
			// 关闭文件流
			if (bis != null)
				bis.close();
			if (output != null)
				output.close();
			logger.debug("print file end");
		}
	}
	
	
	
	@RequestMapping(value = "getQrCode")
	public void getQrCode(HttpServletResponse response, HttpServletRequest request,String url) throws IOException {
		
		String widthStr = request.getParameter("width");
		String heightStr = request.getParameter("height");
		int width = MatcherUtil.checkNumber(widthStr)?Integer.parseInt(widthStr):300;
		int height = MatcherUtil.checkNumber(heightStr)?Integer.parseInt(heightStr):300;
		
		if (null==url || url.equals("")) {
			out("url不能为空！");
		}else {
			QRCodeUtil.createQrCode(url, width, height, response);
		}
	}
}