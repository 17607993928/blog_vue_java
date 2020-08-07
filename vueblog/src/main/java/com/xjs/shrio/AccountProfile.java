package com.xjs.shrio;

import lombok.Data;

import java.io.Serializable;

/**
 * @创建人 xjs
 * @类描述 封装的实体类展示信息
 * @创建时间 2020/8/5 0005
 */
@Data
public class AccountProfile implements Serializable {
    private static final long serialVersionUID = 8106680558350357387L;

    private Long id;

    private String username;

    private String avatar;

    private String email;

}
