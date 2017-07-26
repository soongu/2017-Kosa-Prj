package com.coderby.myapp.upload.controller;

import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coderby.myapp.upload.model.UploadFileVO;
import com.coderby.myapp.upload.service.IUploadFileService;


@Controller
public class UploadFileController {
	static final Logger logger = Logger.getLogger(UploadFileController.class);

	@Autowired
	IUploadFileService imageService;
	
	@RequestMapping(value="/upload", method=RequestMethod.GET)
	public String home() {
		return "/upload/index";
	}
	
	@RequestMapping(value="/upload/new", method=RequestMethod.GET)
	public String uploadImage(Model model) {
		model.addAttribute("dir", "/");
		return "/upload/form";
	}
	
	@RequestMapping(value="/upload/new", method=RequestMethod.POST)
	public String uploadImage(@RequestParam(value="dir", required=false, defaultValue="/") String dir, @RequestParam MultipartFile file, RedirectAttributes redirectAttrs) {
		logger.info(file.getOriginalFilename());
		try{
			if(file!=null && !file.isEmpty()) {
				logger.info("/upload : " + file.getOriginalFilename());
				UploadFileVO image = new UploadFileVO();
				image.setDirectoryName(dir);
				image.setFileName(file.getOriginalFilename());
				image.setFileSize(file.getSize());
				image.setFileContentType(file.getContentType());
				image.setFileData(file.getBytes());
				logger.info("/upload : " + image.toString());

				imageService.uploadFile(image);
			}
//			redirectAttrs.addFlashAttribute("dir", dir);
		}catch(Exception e){
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/upload/list";//+dir;
	}
	
	@RequestMapping("/upload/gallery")
	public String getFileList(@RequestParam(value="dir", required=false, defaultValue="/images")String dir, Model model) {
		model.addAttribute("fileList", imageService.getImageList(dir));
		return "/upload/gallery";
	}
	
	@RequestMapping("/upload/list")
	public String getImageList(Model model) {
		model.addAttribute("fileList", imageService.getAllFileList());
		return "/upload/list";
	}

	@RequestMapping("/upload/list/{dir}")
	public String getFileListByDir(@PathVariable String dir, Model model) {
		model.addAttribute("fileList", imageService.getFileList("/"+dir));
		return "/upload/list";
	}
	
	@RequestMapping("/img/{fileId}")
	public ResponseEntity<byte[]> getImageFile(@PathVariable int fileId) {
		UploadFileVO file = imageService.getFile(fileId);
		final HttpHeaders headers = new HttpHeaders();
		if(file != null) {
			logger.info("getFile " + file.toString());
			String[] mtypes = file.getFileContentType().split("/");
			headers.setContentType(new MediaType(mtypes[0], mtypes[1]));
			headers.setContentDispositionFormData("attachment", file.getFileName(), Charset.forName("UTF-8")); //4.3.7 이후 charset 지정 가능
			headers.setContentLength(file.getFileSize());
			return new ResponseEntity<byte[]>(file.getFileData(), headers, HttpStatus.OK);
		}else {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping("/pds/{fileId}")
	public ResponseEntity<byte[]> getBinaryFile(@PathVariable int fileId) {
		UploadFileVO file = imageService.getFile(fileId);
		final HttpHeaders headers = new HttpHeaders();
		if(file != null) {
			logger.info("getFile " + file.toString());
			String[] mtypes = file.getFileContentType().split("/");
			headers.setContentType(new MediaType(mtypes[0], mtypes[1]));
			headers.setContentDispositionFormData("attachment", file.getFileName(), Charset.forName("UTF-8")); //4.3.7 이후 charset 지정 가능
			headers.setContentLength(file.getFileSize());
			return new ResponseEntity<byte[]>(file.getFileData(), headers, HttpStatus.OK);
		}else {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping("/upload/delete/{fileId}")
	public String deleteFile(@PathVariable int fileId) {
//		String dir = imageService.getDirectoryName(fileId);
		imageService.deleteFile(fileId);
		return "redirect:/upload/list";// + dir;
	}
	
	@RequestMapping("/upload/updateDir")
	public String updateDirectory(@RequestParam int[] fileIds, @RequestParam String directoryName) {
//		String dir = imageService.getDirectoryName(fileId);
		imageService.updateDirectory(fileIds, directoryName);
		return "redirect:/upload/list";
	}
}
