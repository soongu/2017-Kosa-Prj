package com.coderby.myapp.analytics.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.RList;
import org.rosuda.JRI.Rengine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderby.myapp.analytics.model.IrisVO;
import com.coderby.myapp.analytics.model.SampleVO;
import com.coderby.myapp.analytics.model.SummaryVO;
import com.coderby.myapp.upload.model.UploadFileVO;
import com.coderby.myapp.upload.service.IUploadFileService;

@Service
public class AnalyticsService implements IAnalyticsService {

	private static final Logger logger = LoggerFactory.getLogger(AnalyticsService.class);

	@Autowired
	Rengine rEngine;

	@Autowired
	IUploadFileService fileService;

	@Override
	public ArrayList<IrisVO> getAvgPetalBySpecies() {
		ArrayList<IrisVO> irisList = new ArrayList<IrisVO>();
		try {
			String[] species = {"setosa", "versicolor", "virginica"};
			REXP result = rEngine.eval("tapply(iris$Petal.Length, iris$Species, mean)");
			REXP result2 = rEngine.eval("tapply(iris$Petal.Width, iris$Species, mean)");

			double resultList[] = result.asDoubleArray();
			double resultList2[] = result2.asDoubleArray();
			for(int i=0; i<resultList.length; i++) {
				IrisVO iris = new IrisVO();
				iris.setSpecies(species[i]);
				iris.setPetalLength(resultList[i]);
				iris.setPetalWidth(resultList2[i]);
				irisList.add(iris);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return irisList;

	}

	@Override
	public ArrayList<SampleVO> getAvgPetalBySpecies2() {
		ArrayList<SampleVO> irisList = new ArrayList<SampleVO>();
		try {
			//            String[] species = {"setosa", "versicolor", "virginica"};
			REXP result = rEngine.eval("tapply(iris$Petal.Length, iris$Species, mean)");
			REXP result2 = rEngine.eval("tapply(iris$Petal.Width, iris$Species, mean)");

			SampleVO sample1 = new SampleVO();
			sample1.setName("P.L mean");
			sample1.setType("column");
			sample1.setData(result.asDoubleArray());
			irisList.add(sample1);
			SampleVO sample2 = new SampleVO();
			sample2.setName("P.W mean");
			sample2.setType("column");
			sample2.setData(result2.asDoubleArray());
			irisList.add(sample2);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return irisList;
	}

	@Override
	public ArrayList<SampleVO> analyticsDatabase(int fileId) {
		ArrayList<SampleVO> irisList = new ArrayList<SampleVO>();
		UploadFileVO file = fileService.getFile(fileId);
		byte[] data = file.getFileData();
		try {
			long exp = rEngine.rniPutString(new String(data));
			rEngine.rniAssign("data", exp, 0);

			REXP rdata = rEngine.eval("(data <- read.table(text = data, sep =\",\", header = TRUE, stringsAsFactors = FALSE))");
			logger.info(rdata.toString());
//			
//			RList rdataList = rdata.asList();
//			String[] keys = rdataList.keys();
//			Object[] modelData = new Object[keys.length];
//			for(String key : keys) {
//				System.out.println(key);
//			}
//			System.out.println("----");
//			System.out.println(rdataList.toString());
//			System.out.println("=====");
//			for(int i=0; i<keys.length; i++) {
//				switch(rdataList.at(i).rtype) {
//				case 14:
//					modelData[i] = rdataList.at(i).asDoubleArray();
//					for(double col : rdataList.at(i).asDoubleArray()) {
//						System.out.print(col + "\t");
//					}
//					break;
//				case 16:
//					modelData[i] = rdataList.at(i).asStringArray();
//					for(String col : rdataList.at(i).asStringArray()) {
//						System.out.print(col + "\t");
//					}
//				}
//				System.out.println();
//			}
//			System.out.println("=====");
//			System.out.println();
//			double[] col1 = rdataList.at(1).asDoubleArray();
//			for(double col : col1) {
//				System.out.print(col + "\t");
//			}
//			System.out.println();
//			
//			REXP names = rEngine.eval("names(data)");
//			for(String colName : names.asStringArray()) {
//				System.out.println(colName);
//			}
			REXP result = rEngine.eval("tapply(data$Petal.Length, data$Species, mean)");
			REXP result2 = rEngine.eval("tapply(data$Petal.Width, data$Species, mean)");

			SampleVO sample1 = new SampleVO();
			sample1.setName("꽃잎 길이 평균");
			sample1.setType("column");
			sample1.setData(result.asDoubleArray());
			irisList.add(sample1);
			SampleVO sample2 = new SampleVO();
			sample2.setName("꽃잎 너비 평균");
			sample2.setType("column");
			sample2.setData(result2.asDoubleArray());
			irisList.add(sample2);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return irisList;
	}

	
	@Override
	public Map<String, Object> analyticsDatabaseInfo(int fileId) {
		//rData안에 객체를 매핑시킬때 String, Object 타입으로 put해줘야 한다는 것을 의미.
		Map<String, Object> rData = new Hashtable<String, Object>();
				
		UploadFileVO file = fileService.getFile(fileId);
		byte[] data = file.getFileData();
//		logger.info(new String(data));
		try {
			//DB에 저장된 파일형식을 R이 읽을 수 있도록 변환하는 변수 -> exp
			long exp = rEngine.rniPutString(new String(data, "UTF-8"));
			//R에서 "data"라는 변수를 사용하기 위해 exp를 전역변수형태로 전달하는 메서드(0을 사용하면 전역변수로 지정가능)
			rEngine.rniAssign("data", exp, 0);
			
			//eval은 R에게 스크립트를 실행하라고 전달하는 메서드. setlocale은 언어를 설정해주는 것.
			rEngine.eval("Sys.setlocale(category=\"LC_ALL\", locale=\"English_United States.1252\")");
//			rEngine.eval("Sys.setlocale(category=\"LC_ALL\", locale=\"ko_KR.UTF-8\")");
			
			//R스크립트를 변수로 선언하려면 데이터타입을 REXP로 해야한다. 스크립트 안의 내용은 R의 data변수를 읽어서 dataFrame이라는 변수로 지정한 후 JAVA의 rdataFrame 변수로 선언. ()를 통해 실행까지 시킴
			REXP rdataFrame = rEngine.eval("(dataFrame <- read.table(text=data, sep=\",\", fill=TRUE, header=TRUE, stringsAsFactors=FALSE, skipNul=TRUE, encoding=\"UTF-8\", fileEncoding=\"UTF-8\"))");
			
			//R에서 규정한 data를 list형식으로 바꿔주고 JAVA에서 RList로 사용하겠다.
			RList rdataList = rdataFrame.asList();
//			System.out.println(rdataList.toString());
			
			//R데이터의 리스트의 key를 String배열타입의 keys객체에 저장. keys는 열의 개수(40)
			String[] keys = rdataList.keys();
			
			//rData 변수에 String, Object 타입으로 put.
			rData.put("colNames", keys);
			
			//Object[]로 만들어야 여러 데이터 타입들을 가져올 수 있다.
			Object[] modelData = new Object[keys.length];
			//열 이름들을 출력하기 위해 향상된 for 반복문으로 keys의 길이만큼 key값을 반복시킨다.
			for(String key : keys) {
				System.out.print(key + "\t");
			}
			System.out.println();	
//			System.out.print(keys.length);
			
			//행 정보들을 출력하기 위해 반복문을 사용.
			for(int i=0; i<keys.length; i++) {
				
				//행 정보의 데이터 타입에 따라 정보를 가져오기 위한 조건문.
				switch(rdataList.at(i).rtype) {
				//rtype 은 https://github.com/s-u/rJava/blob/master/jri/REXP.java 참고
				case REXP.LGLSXP: //logical vectors, 10
					modelData[i] = rdataList.at(i).asBool();
					break;
				case REXP.INTSXP: //integer vectors, 13
					modelData[i] = rdataList.at(i).asIntArray();
					break;
				case REXP.REALSXP: //real variables, 14
					modelData[i] = rdataList.at(i).asDoubleArray();
					break;
				case REXP.STRSXP: //string vectors, 16
					modelData[i] = rdataList.at(i).asStringArray();
					break;
				}
				rData.put("data", modelData);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return rData;
	}

	@Override
	public Map<String, Object> getSummary(int fileId) {
		Map<String, Object> rData = new Hashtable<String, Object>();

		UploadFileVO file = fileService.getFile(fileId);
		byte[] data = file.getFileData();

		long exp;
		try {
			exp = rEngine.rniPutString(new String(data, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		rEngine.rniAssign("data", exp, 0);
		
		rEngine.eval("Sys.setlocale(category=\"LC_ALL\", locale=\"English_United States.1252\")");
		rEngine.eval("dataFrame <- read.table(text=data, sep=\",\", fill=TRUE, header=TRUE, stringsAsFactors=FALSE, skipNul=TRUE, encoding=\"UTF-8\", fileEncoding=\"UTF-8\")");
		
		//R에서 dataFrame을 summary 해서 dfSummary에 저장, REXP 클래스 타입의 summary 객체 생성 
		REXP summary = rEngine.eval("(dfSummary <- summary(dataFrame))");
		if(summary == null)	//summary 할 데이터가 없을 경우 Runtime 예외발생
			throw new RuntimeException("No data for summary");
		
		//rData라는 맵에 summary한 내용으로 모델(key+value)을 만든다.
		rData.put("summary", summary.asStringArray());
		
		//R에서 dfSummary의 열이름을 뽑아내서 맵의 colNames 객체를 만든다.
		REXP colNames = rEngine.eval("colnames(dfSummary)");
		//rData라는 맵에 colNames한 내용으로 모델(key+value)을 만든다.
		rData.put("colNames", colNames.asStringArray());
		
		//R에서 dfSummary의 행의 수를 뽑아내서 맵의 nrow 객체를 만든다.
		REXP nrow = rEngine.eval("nrow(dfSummary)");
		//rData라는 맵에 nrow한 내용으로 모델(key+value)을 만든다.
		rData.put("nrow", nrow.asInt());
		
		return rData;	//rData 맵 리턴
	}

	
	@Override
	public SummaryVO getSummaryList(int fileId) {
		
		UploadFileVO file = fileService.getFile(fileId);
		byte[] data = file.getFileData();

		long exp;
		try {
			exp = rEngine.rniPutString(new String(data, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		rEngine.rniAssign("data", exp, 0);
		
		rEngine.eval("Sys.setlocale(category=\"LC_ALL\", locale=\"English_United States.1252\")");
		rEngine.eval("dataFrame <- read.table(text=data, sep=\",\", fill=TRUE, header=TRUE, stringsAsFactors=FALSE, skipNul=TRUE, encoding=\"UTF-8\", fileEncoding=\"UTF-8\")");

		REXP rsummary = rEngine.eval("(dfSummary <- summary(dataFrame))");
		if(rsummary == null)
			throw new RuntimeException("No data for summary");
		
		SummaryVO summary = new SummaryVO();
		
		REXP rcolNames = rEngine.eval("colnames(dfSummary)");
		summary.setColNames(rcolNames.asStringArray());
		summary.setValues(rsummary.asStringArray());
		System.out.println(summary);
		return summary;
	}
}
