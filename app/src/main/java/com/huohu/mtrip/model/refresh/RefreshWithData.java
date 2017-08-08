package com.huohu.mtrip.model.refresh;

public interface RefreshWithData extends RefreshListener {
    void onRefreshWithData(int key, Object data);
}