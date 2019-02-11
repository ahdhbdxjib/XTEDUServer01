package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;

public interface CmsPageControllerApi {
//根据提供的葉面和大小查詢，统一使用QueryResponseResult来接受数据的结果
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

}
