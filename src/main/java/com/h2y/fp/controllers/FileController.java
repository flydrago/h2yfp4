package com.h2y.fp.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.h2y.fp.basic.BaseController;
import com.h2y.fp.enums.FileKeys.FUPKey;
import com.h2y.fp.enums.FileKeys.FUPResult;
import com.h2y.fp.enums.FileKeys.FUPVResult;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;

/**
 * 文件处理

 * @author：段晓刚

 * @update：2015年4月9日 上午8:41:26

 * @Email：
 */
@Controller
@Scope("prototype")
@RequestMapping("/fp")
public class FileController extends BaseController {

	private Logger logger = Logger.getLogger(FileController.class);

	private String bp = "D://upload";

	@RequestMapping(value = "upload")
	public void upload2(MultipartFile file, HttpServletRequest request) {
		//String path = request.getSession().getServletContext().getRealPath("upload");
		saveFile(file);
		//model.addAttribute("fileUrl", request.getContextPath() + "/upload/"+ fileName);
		out(file.getName());
	}

	@RequestMapping("uploads")
	public void filesUpload(MultipartFile[] files) {
		//判断file数组不能为空并且长度大于0
		if(files!=null&&files.length>0){
			//循环获取file数组中得文件
			for(int i = 0;i<files.length;i++){
				MultipartFile file = files[i];
				//保存文件
				saveFile(file);
			}
		}
	}
	
	/***
	 * 保存文件
	 * @param file
	 * @return
	 */
	private boolean saveFile(MultipartFile file) {
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 文件保存路径
				String savePath = bp + File.separator + DateUtil.getDatePath()+file.getOriginalFilename();
				// 转存文件
				file.transferTo(new File(savePath));
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text;charset=UTF-8");// 内容类型

		@SuppressWarnings("unused")
		String sessionId = request.getParameter(FUPKey.session.value()) + "";

		// System.out.println("POST= ");
		PrintWriter out = response.getWriter();
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String fileName = "";
		String saveName = "";
		// 根目录
		String sp = bp;

		String savePath = bp + File.separator + DateUtil.getDatePath();

		File file = new File(savePath);
		if (!file.exists())
			file.mkdirs();
		/**
		 * 在isMultipartContent方法中同时检测了是否是post提交 如果不是post提交则返回false
		 */
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			try {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				@SuppressWarnings("unchecked")
				List<FileItem> items = upload.parseRequest(request);
				// 从request中解析出若干表单项
				Iterator<FileItem> iter = items.iterator();

				String oldFileName = "";
				while (iter.hasNext()) {// 遍历每一个表单项
					FileItem item = iter.next();
					if (item.isFormField()) {// 如果是普通字段
						String fieldName = item.getFieldName();// 获取字段名(name)
						String value = item.getString();// 获取字段值(value)
						// 为解决httmime实现上传中文乱码问题
						if (fieldName != null
								&& fieldName.equals("httpmime_fileName")) {
							oldFileName = value;
						}
					} else { // 不是普通字段, 就是上传文件
						// 为解决httmime实现上传中文乱码问题
						if (oldFileName.equals(""))
							fileName = new File(item.getName()).getName();// 获取上传文件的文件名(有些浏览器可能会带着路径)
						else {
							fileName = oldFileName;
							oldFileName = "";
						}
						saveName = getNewFileName(fileName);
						File uploadedFile = new File(savePath, saveName);// 在Upload文件夹中创建文件
						item.write(uploadedFile);// 写出数据
					}
				}

				resultMap.put(FUPResult.rtflg.value(),FUPVResult.success.value());
				resultMap.put(FUPResult.rtfilename.value(), fileName);
				resultMap.put(FUPResult.rtsavepath.value(),savePath.replace(sp, "").replace(saveName, ""));
				resultMap.put(FUPResult.rtsavename.value(), saveName);
				String resultStr = JSONUtil.getJson(resultMap);
				out.println(resultStr);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug(e.getMessage(), e);
			}
		}
		resultMap.put(FUPResult.rtflg.value(), FUPVResult.fail.value());
		resultMap.put(FUPResult.rtfilename.value(), fileName);
		String resultStr = JSONUtil.getJson(resultMap);
		out.println(resultStr);
	}

	private String getNewFileName(String fileName) {

		String fn;

		fn = System.currentTimeMillis() + "";
		if (fileName.contains("."))
			fn = fn
					+ fileName.substring(fileName.lastIndexOf("."),
							fileName.length());
		else {
		}

		return fn;
	}
}
