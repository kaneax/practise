package com.example.demo.basepage;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页相关的参数
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagination<T> {

    /**
     * 返回的数据
     */
    private List<T> data;
    /**
     * 总页数
     */
    private int pageCount = 0;

    /**
     * 总记录数
     */
    private long recordsCount = 0L;

    /**
     * 每页数据
     */
    private int pageSize = 0;

    /**
     * 当前页号
     */
    private int pageNum = 1;

    public static <T> Pagination<T> buildPageWithData(PageQueryDTO.Page page, List<T> data, Long count) {
        Pagination<T> result = new Pagination<>();
        result.setPageNum(page.getPageNum());
        result.setPageSize(page.getPageSize());
        result.setData(data);
        result.setPageCount(getPageCount(page, count));
        result.setRecordsCount(count);
        return result;
    }


    public static <T> Pagination<T> buildPageWithData(Integer pageNum, Integer pageSize, Long count, List<T> data) {
        Pagination<T> result = new Pagination<>();
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        result.setData(data);
        result.setPageCount(getPageCount(new PageQueryDTO.Page(pageSize, pageNum), count));
        result.setRecordsCount(count);
        return result;
    }

    private static int getPageCount(PageQueryDTO.Page page, Long count) {
        if (page.getPageSize() == 0) {
            return 0;
        }
        return (count.intValue() - 1) / page.getPageSize() + 1;
    }

}

