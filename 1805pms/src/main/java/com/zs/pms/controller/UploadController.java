package com.zs.pms.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Administrator
 *
 */
@Controller
public class UploadController {

	/**
	 * ��ͨ�ļ��ϴ�
	 * @param file ���ļ���
	 * @param req	�ϴ����ļ� ��input��name��ͬ
	 * @return
	 */
	@RequestMapping("/upload/common.do")
	@ResponseBody
	public String uploadFile(MultipartFile file,HttpServletRequest req){
		//���upload�ļ��е�����·��
		String path=req.getRealPath("/upload");
		//�������ļ��� Ŀ���ļ�
		//����uuid�㷨 �������32λ��
		/**
		 * uuid�㷨
		 * ����������ʱ�䡢IP��ַ����Ϣ
		 * �Զ����ɾ����ظ���32λ��
		 */
		
		UUID uuid=UUID.randomUUID();
		//Ŀ���ļ��� 32λ��+�ļ���׺
		String destfilename=uuid.toString()+file.getOriginalFilename();
		//
		//
		File destfile=new File(path+File.separator+destfilename);
		
		/**
		 * 
		 */
		
		try {
			file.transferTo(destfile);
			return destfilename;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
		} 
		
		
	}
}
