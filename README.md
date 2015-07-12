如果你有遇到什么问题可以联系我：hjp22222@gmail.com <br/>

此项目最初出自龙浩的[autocoder](https://github.com/hoorace/autocoder),<br/>
他的只支持到ibatis，未有支持mybatis，我在autocoder做了一下修改和简化，<br/>
目前已支持MyBatis<br/>

### 注意
1.必须配置JAVA_HOME与MAVEN_HOME<br/>
2.在windows下运行run.bat ,在liunx系统下运行run.sh<br/>
3.运行前需在baseconfig.yaml文件中配置数据库和要生成代码的表<br/>
4.目前仅支持mysql数据库<br/>
5.生成mybatis<br/>
6.没有生成daoImpl是应因为mybatis加个配置就自动实现<br/>

```xml
   <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
		<property name="basePackage" value="com.okhqb.business" />
	</bean>
```
	

7.mybatis的alias也通过配置自动别名<br/>
```xml
  <typeAliases>
  		<package name="com.okhqb.business.usermgr.domain"/>
  </typeAliases>
  ```
8.更改了BaseDao<br/>
```
package com.okhqb.common.dao;


import java.util.List;
import java.util.Map;

/**
 * 提供Ibatis数据库的基本操作
 *
 * Date: 15-6-16
 * @param <T>
 */
public interface BaseDao<T> {

	/**
	 * 添加数据
	 * @param obj
	 * @return
	 */
	public void insert(T obj) ;

	/**
	 * 删除数据
	 * @param id
	 */
	public void delete(Long id) ;

	/**
	 * 修改数据
	 * @param obj
	 */
	public void update(T obj) ;

	/**
	 * 根据条件查询
	 * @param params
	 * @return
	 */
	public T find(Map<String, Object> params) ;

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public T findById(long id) ;

	/**
	 * 根据条件查询
	 * @param params
	 * @return
	 */
	public List<T> list(Map<String, Object> params) ;

	/**
	 * 根据条件统计
	 * @param params
	 * @return
	 */
	public Integer listCount(Map<String, Object> params) ;
}

```

***************************************maven依赖置***************************************

1：在maven的配置文件中增加apache的mirror：<br/>
```xml
    <mirror>
        <id>apache</id>
        <mirrorOf>central</mirrorOf>
        <url>http://repo1.maven.org/maven2</url>
    </mirror>
```    
2: 在应用的pom.xml文件中添加依赖<br/>
```xml
    <dependency>
        <groupId>org.yaml</groupId>
        <artifactId>snakeyaml</artifactId>
        <version>1.9</version>
    </dependency>
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>6.1.1</version>
        <scope>test</scope>
    </dependency>
 ```
<br/>
***************************************代码相关说明***************************************<br/>
1：分页，排序需要设置ListAdapter中的属性：<br/>
```
    ListAdapter<PvsToday> adapter = new DefaultListAdapter<PvsToday>();
    adapter.adapter.setPageNo(1).setPageSize(20);//分页
    adapter.setOrderItem("xx").setOrderType("ASC");//排序字段
```

2：为了解决DO和VO的导致JavaBean内容重复的问题，设计上考虑使用动态字段来传值，提高系统可拓展性。
    set动态字段：<br/>
    
```
    adapter.setFiled("startTime", startTime).setFiled("endTime", endTime);
    
```
    在xml配置中调用<br/>
    
```
    
    <if test="dynamicFileds_startTime != null ">
          and  ${tableName}.${column.columnName} &gt;= #${boxBracket}dynamicFileds_startTime}
    </if>
    <if test="dynamicFileds_endTime != null ">
       and	${tableName}.${column.columnName} &lt;= #${boxBracket}dynamicFileds_endTime}
    </if>
    
```
3：update都是需要直接修改xml来完成，生成的内容仅供参考。<br/>


### 版本：
**************************** 1.0.1功能列表[2015-7-2]：****************************<br/>

1.支持生成mybatis<br/>
2.支持生成dao<br/>
3.支持生成domain<br/>
4.支持生成分页<br/>
