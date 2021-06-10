package com.wust.spring.boot.multi.tenant.bean.constant.page;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageResult<T> {

    protected List<T> records;

    protected long total;

    protected long pageSize;

    protected long page;

}
