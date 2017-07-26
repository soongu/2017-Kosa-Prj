package com.coderby.myapp.upload.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderby.myapp.upload.dao.IUploadFileRepository;
import com.coderby.myapp.upload.model.UploadFileVO;

@Service
public class UploadFileService implements IUploadFileService {

	@Autowired
	IUploadFileRepository uploadFileRepository;
	
	@Override
	public void uploadFile(UploadFileVO file) {
		file.setFileId(uploadFileRepository.getMaxFileId()+1);
		uploadFileRepository.uploadFile(file);
	}

	@Override
	public List<UploadFileVO> getFileList(String dir) {
		return uploadFileRepository.getFileList(dir);
	}

	@Override
	public List<UploadFileVO> getImageList(String dir) {
		return uploadFileRepository.getImageList(dir);
	}
	
	@Override
	public List<UploadFileVO> getAllFileList() {
		return uploadFileRepository.getAllFileList();
	}
	
	@Override
	public UploadFileVO getFile(int fileId) {
		return uploadFileRepository.getFile(fileId);
	}

	@Override
	public void deleteFile(int fileId) {
		uploadFileRepository.deleteFile(fileId);
	}

	@Override
	public String getDirectoryName(int fileId) {
		return uploadFileRepository.getDirectoryName(fileId);
	}

	@Override
	public void updateDirectory(int[] fileIds, String directoryName) {
		for(int fileId : fileIds) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("fileId", fileId);
			map.put("directoryName", directoryName);
			uploadFileRepository.updateDirectory(map);
		}
	}

	@Override
	public void updateFile(UploadFileVO file) {
		uploadFileRepository.updateFile(file);
	}

}
