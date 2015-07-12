/* 
 * @(#) ${className}QueryBizImpl.java ${createTime}
 * Copyright (c) 2015 okhqb Technologies, Inc. All rights reserved.
 */
package ${package} ;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqb360.common.dao.adapter.ListAdapter;
import com.hqb360.common.dao.adapter.ListAdapterUtil;

import com.hqb360.common.page.DefaultPaging;
import com.hqb360.common.page.Paging;
import com.hqb360.common.page.PagingRequest;

import ${domainPackage}.${className};
import ${daoPackage}.${className}Dao;
import ${servicePackage}.${className}QueryBiz;


/**
 * 查询实现类
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
@Service("<@lowerFirstChar>${className}</@lowerFirstChar>QueryBiz")
public class ${className}QueryBizImpl implements ${className}QueryBiz{

	/**
     * 根据主键查找
     *  
     * @param <@lowerFirstChar>${keyColumn.javaName}</@lowerFirstChar> 主键
     * @return ${className} 业务实体
     */
    public ${className} find(${keyColumn.javaType} <@lowerFirstChar>${keyColumn.javaName}</@lowerFirstChar>){

    	${className} <@lowerFirstChar>${className}</@lowerFirstChar> =<@lowerFirstChar>${className}</@lowerFirstChar>Dao.findById(<@lowerFirstChar>${keyColumn.javaName}</@lowerFirstChar>);
        return <@lowerFirstChar>${className}</@lowerFirstChar>;
    }

    /**
     * 列表查询
     * 
     * @param  <@lowerFirstChar>${className}</@lowerFirstChar> 业务实体
     * @param  pagingRequest 分页信息
     * @return Paging<${className}> 查询结果
     */
    public Paging<${className}> pageList(${className} <@lowerFirstChar>${className}</@lowerFirstChar>,PagingRequest pagingRequest){
    	ListAdapter<${className}> adapter = ListAdapterUtil.<${className}> convert2ListAdapter(pagingRequest);

        int listCount = <@lowerFirstChar>${className}</@lowerFirstChar>Dao.listCount(adapter.convert(<@lowerFirstChar>${className}</@lowerFirstChar>));
        DefaultPaging<${className}> paging = new DefaultPaging<${className}>(
            pagingRequest.getPageNo(), pagingRequest.getPageSize(), listCount);
        adapter.setPageNo(paging.getCurrentPageNo());
        List<${className}> list${className} = <@lowerFirstChar>${className}</@lowerFirstChar>Dao.list(adapter.convert(<@lowerFirstChar>${className}</@lowerFirstChar>));
        paging.setResult(list${className});
        return paging;
    }
    
    /** slf4j*/
    private static final Logger                         LOGGER = LoggerFactory.getLogger(${className}QueryBizImpl.class);
 	
 	/** 数据访问层接口*/
    @Autowired
    private ${className}Dao <@lowerFirstChar>${className}</@lowerFirstChar>Dao;

} 
