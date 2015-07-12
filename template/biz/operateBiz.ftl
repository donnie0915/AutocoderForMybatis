/* 
 * @(#) ${className}OperateBiz.java ${createTime}
 * Copyright (c) 2015 okhqb Technologies, Inc. All rights reserved.
 */
package ${package} ;

import ${domainPackage}.${className};

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
public interface ${className}OperateBiz {

    /**
     * 新增
     * 
     * @param <@lowerFirstChar>${className}</@lowerFirstChar>
     */
    void insert(${className}  <@lowerFirstChar>${className}</@lowerFirstChar>);

    /**
     * 修改
     * 
     * @param <@lowerFirstChar>${className}</@lowerFirstChar>
     */
    void update(${className}  <@lowerFirstChar>${className}</@lowerFirstChar>);
    
} 