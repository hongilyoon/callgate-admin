package com.hellowd.callgate.core.util.pageUtil;

import lombok.Data;

@Data
public class PageUtil {

    private Boolean isPrevious = false;
    private Boolean isNext = false;
    private int currentPage = 1;
    private int[] arrPage;
    private static int CNT_PER_PAGE = 10;

    public PageUtil(int totalPage, int currentPage) {

        // 지역변수
        int currentPageSize = ((currentPage - 1) / CNT_PER_PAGE) + 1;
        int totalPageSize = ((totalPage - 1) / CNT_PER_PAGE) + 1;

        // 현재 페이지
        this.currentPage = currentPage;

        // 이전 페이지 영역이 있는지 여부(이전 화살표)
        if (currentPageSize > 1) {
            this.isPrevious = true;
        }

        // 이후 페이지 영역이 있는지 여부(이후 화살표)
        if (currentPageSize < totalPageSize) {
            this.isNext = true;
        }

        // 현재 페이지 영역에 보여줄 페이지 목록을 생성한다.
        arrPage = new int[CNT_PER_PAGE];
        for (int i = 0; i < CNT_PER_PAGE; i++) {
            arrPage[i] = ((currentPageSize - 1) * CNT_PER_PAGE) + i + 1;
        }
    }
}
