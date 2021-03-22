package com.example.demo.basepage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: xiaozhu13539
 * @Date: 2021/3/22
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BaseRequest implements Serializable {
    private static final long serialVersionUID = 9099214017305593522L;

    private String token;

    private String clientId;

    private String something;
}
