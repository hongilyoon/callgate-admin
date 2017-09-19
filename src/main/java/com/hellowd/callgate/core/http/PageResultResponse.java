package com.hellowd.callgate.core.http;

import com.hellowd.callgate.core.util.pageUtil.PageUtil;
import lombok.Data;

@Data
public class PageResultResponse<T> extends ResultResponse {

    private PageUtil pageUtil;

    public PageResultResponse() {

    }

    public PageResultResponse(T data, PageUtil pageUtil) {
        this.setData(data);
        this.setPageUtil(pageUtil);
    }
}
