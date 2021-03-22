package com.example.demo.basepage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageQueryDTO<T> implements Serializable {

    private static final long serialVersionUID = 3149784268665936020L;

    private T queryParam;

    private Page page = new Page();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Page implements Serializable {

        private static final long serialVersionUID = 467971471256953710L;

        /**
         * 每页数据
         */
        private int pageSize = 10;

        /**
         * 当前页号
         */
        private int pageNum = 1;
    }

}
