/* 
 * @(#) ${className}OperateBizImpl.java ${createTime}
 * Copyright (c) 2015 okhqb Technologies, Inc. All rights reserved.
 */
package ${package} ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${domainPackage}.${className};
import ${daoPackage}.${className}Dao;
import ${servicePackage}.${className}OperateBiz;

/**
 * 操作接口
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
@Service("<@lowerFirstChar>${className}</@lowerFirstChar>OperateBiz")
public class ${className}OperateBizImpl implements ${className}OperateBiz{
    
    /**
     * 新增
     * 
     * @param <@lowerFirstChar>${className}</@lowerFirstChar>
     */
    public void insert(${className}  <@lowerFirstChar>${className}</@lowerFirstChar>){

        <@lowerFirstChar>${className}</@lowerFirstChar>Dao.insert(<@lowerFirstChar>${className}</@lowerFirstChar>);
    }

    /**
     * 修改
     * 
     * @param <@lowerFirstChar>${className}</@lowerFirstChar>
     */
    public void update(${className}  <@lowerFirstChar>${className}</@lowerFirstChar>){

         <@lowerFirstChar>${className}</@lowerFirstChar>Dao.update(<@lowerFirstChar>${className}</@lowerFirstChar>);
    }
    
    /** slf4j*/
    private static final Logger                         LOGGER = LoggerFactory.getLogger(${className}OperateBizImpl.class);
 	
 	/** 数据访问层接口*/
    @Autowired
    private ${className}Dao <@lowerFirstChar>${className}</@lowerFirstChar>Dao;
} 
