package com.github.qing.multtypeimagelayout.data;

import java.util.List;

public class RecommendList {

    List<RecommendData> recommendDataList;

    public RecommendList(List<RecommendData> recommendDataList) {
        this.recommendDataList = recommendDataList;
    }

    public List<RecommendData> getRecommendDataList() {
        return recommendDataList;
    }
}
