package com.coderby.myapp.analytics.service;

import java.util.ArrayList;
import java.util.Map;

import com.coderby.myapp.analytics.model.IrisVO;
import com.coderby.myapp.analytics.model.SampleVO;
import com.coderby.myapp.analytics.model.SummaryVO;

public interface IAnalyticsService {
	ArrayList<IrisVO> getAvgPetalBySpecies();
	ArrayList<SampleVO> getAvgPetalBySpecies2();
	ArrayList<SampleVO> analyticsDatabase(int fileId);
	Map<String, Object> analyticsDatabaseInfo(int fileId);
	Map<String, Object> getSummary(int fileId);
	SummaryVO getSummaryList(int fileId);
}
