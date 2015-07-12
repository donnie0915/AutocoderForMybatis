<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- Always ensure to use the correct XML header as above! -->
<mapper namespace="${package}.${className}Dao">

<resultMap id="<@lowerFirstChar>${className}</@lowerFirstChar>_result" type="<@lowerFirstChar>${className}</@lowerFirstChar>">
	<#list columns as column>
    <result property="${column.javaName}" column="${column.columnName}"/>
    </#list>
</resultMap>

<sql id="${className}_Query_List_Sharing">
    <where>
	<#list columns as column>
    <#if column.dateType=="datetime" && column.columnName?starts_with("gmt_created")>
        <if test="dynamicFileds_startTime != null ">
            and	${tableName}.${column.columnName} &gt;= #${boxBracket}dynamicFileds_startTime}
        </if>
         <if test="dynamicFileds_endTime != null ">
            and	${tableName}.${column.columnName} &lt;= #${boxBracket}dynamicFileds_endTime}
        </if>
    <#else>
        <if test="${column.javaName} != null ">
            and ${tableName}.${column.columnName} = #${boxBracket}${column.javaName}}
        </if>
    </#if>
	</#list>
	</where>
</sql>

<sql id="${className}_List_Paramter">
    <if test="orderItem != null ">
          ORDER BY ${tableName}.$${boxBracket}orderItem} $${boxBracket}orderType}
    </if>
</sql>

<sql id="${className}_column">
<#list columns as column>
	<#if column_has_next>
        ${tableName}.${column.columnName},
    <#else>
        ${tableName}.${column.columnName}
	</#if>
</#list>
</sql>

<sql id="${className}_By_${keyColumn.javaName}">
    WHERE ${tableName}.${keyColumn.columnName} = #${boxBracket}${keyColumn.javaName}}
</sql>

 <insert id="insert" parameterType="<@lowerFirstChar>${className}</@lowerFirstChar>" >
    INSERT INTO ${tableName} (
    <include refid="${className}_column" />
    ) VALUES (
            <#list columns as column>
                <#if column_has_next>
                    <#if column.dateType=="datetime" && column.columnName?starts_with("gmt")>
                    now(),
                    <#else>
                        #${boxBracket}${column.javaName}},
                    </#if>
                <#else>
                    <#if column.dateType=="datetime" && column.columnName?starts_with("gmt")>
                    now()
                    <#else>
                        #${boxBracket}${column.javaName}}
                    </#if>
                </#if>
			</#list>
    )
    <#assign firstColumn>
    <#list columns as column>
		<#if column_index ==0>${column.javaName}<#break></#if>
		</#list>
    </#assign>
    <!--  需要返回自增id，取消此注释即可
     <selectKey resultType="long" keyProperty="${firstColumn?trim}" order="AFTER">
         SELECT LAST_INSERT_ID()
     </selectKey>
     -->
</insert>  

<update id="update" parameterType="<@lowerFirstChar>${className}</@lowerFirstChar>">
    UPDATE ${tableName} ${tableName}
    <set>
    <#if modifiedColumn??>
         gmt_modified=now(),
    <#else>
    </#if>
    <#list columns as column>
    <#if (column.columnName == "gmt_modified") ||column.columnName == keyColumn.columnName || column.columnName?starts_with("gmt_created")>
    <#else>
        <if test="${column.javaName} != null ">
             ${tableName}.${column.columnName} = #${boxBracket}${column.javaName}},
        </if>
    </#if>
    </#list>
     </set>

    <include refid="${className}_By_${keyColumn.javaName}" />
</update> 

<select id="find" resultMap="<@lowerFirstChar>${className}</@lowerFirstChar>_result" parameterType="java.util.HashMap">
    SELECT
     	<include refid="${className}_column" />      
    FROM ${tableName} ${tableName}
   <include refid="${className}_Query_List_Sharing" />
</select>

<select id="findById"
        resultMap="<@lowerFirstChar>${className}</@lowerFirstChar>_result" parameterType="${keyColumn.javaType}">
    SELECT
     	<include refid="${className}_column" />      
    FROM ${tableName} ${tableName}
    <include refid="${className}_By_${keyColumn.javaName}" />
</select>

<select id="list" resultMap="<@lowerFirstChar>${className}</@lowerFirstChar>_result" parameterType="java.util.HashMap">
    SELECT
         <include refid="${className}_column" />  
    FROM ${tableName} ${tableName}
	<include refid="${className}_Query_List_Sharing" />
	<include refid="${className}_List_Paramter" />
	limit #${boxBracket}begin},#${boxBracket}pageSize}
</select>

<select id="listCount" resultType="java.lang.Integer" parameterType="java.util.HashMap">
    SELECT
		COUNT(*)
   	FROM ${tableName} ${tableName}
	<include refid="${className}_Query_List_Sharing" />
</select>

<delete id="delete" parameterType="<@lowerFirstChar>${className}</@lowerFirstChar>">
    DELETE  FROM  ${tableName} 
    <include refid="${className}_By_${keyColumn.javaName}" />
</delete>

</mapper>
