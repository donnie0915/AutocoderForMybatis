/* 
 * @(#) ${className}QueryBiz.java ${createTime}
 * Copyright (c) 2015 okhqb Technologies, Inc. All rights reserved.
 */
package ${package} ;

import com.hqb360.common.page.Paging;
import com.hqb360.common.page.PagingRequest;

import ${domainPackage}.${className};

/**
 * 查询接口
 *
 * @author ${author}
 * @version ${version}
 *
 * Modification History:
 * Date 		Author 	 Description
 * -----------------------------------------------------------------------------------
 *
 * -----------------------------------------------------------------------------------
 */
public interface ${className}QueryBiz {
    
	/**
     * 根据主键查找
     *  
     * @param <@lowerFirstChar>${keyColumn.javaName}</@lowerFirstChar> 主键
     * @return ${className} 业务实体
     */
    ${className} find(${keyColumn.javaType} <@lowerFirstChar>${keyColumn.javaName}</@lowerFirstChar>);

    /**
     * 列表查询
     * 
     * @param <@lowerFirstChar>${className}</@lowerFirstChar> 业务实体
     * @param pagingRequest 分页信息
     * @return Paging<${className}Do> 查询结果
     */
    Paging<${className}> pageList(${className} <@lowerFirstChar>${className}</@lowerFirstChar>,PagingRequest pagingRequest);

} 
