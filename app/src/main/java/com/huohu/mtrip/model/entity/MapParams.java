package com.huohu.mtrip.model.entity;

import java.util.HashMap;

/**
 * Created by admin on 2017/2/10.
 */

public class MapParams
{
    private HashMap<String, Object> params = new HashMap<>();

    public MapParams()
    {
        params = new HashMap<>();
    }

    public MapParams addParam(String key, Object value)
    {
        params.put(key, value);
        return this;
    }

    public HashMap<String, Object> create() {
        return params;
    }
}
