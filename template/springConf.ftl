<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context" xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-2.5.xsd">

    <context:annotation-config/>
    <context:component-scan base-package="${daoImplPackage}"/>


    <!-- 声明使用注解式事务 -->
    <tx:annotation-driven transaction-manager="transactionManager"/>
	<bean id="propertyConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
		<property name="locations">
			<list>
				<value>classpath:jdbc.properties</value>
			</list>
		</property>
		<property name="systemPropertiesMode" value="1" /> 
        <property name="searchSystemEnvironment" value = "true" /> 
        <property name="ignoreUnresolvablePlaceholders" value = "true" /> 
	</bean>

	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
		<property name="testOnBorrow" value="true" />
		<property name="validationQuery" value="SELECT 1 FROM DUAL" />
		<property name="driverClassName" value="${r"${db.source.driver}"}" />
		<property name="url" value="${r"${db.source.url}"}" />
		<property name="username" value="${r"${db.source.username}"}" />
		<property name="password" value="${r"${db.source.password}"}" />
		<property name="minIdle" value="${r"${db.source.min.idle}"}" />
       	<property name="maxActive" value="${r"${db.source.max.active}"}" />
      	<property name="maxIdle" value="${r"${db.source.max.idle}"}" />
		<property name="initialSize" value="30" />
		<property name="defaultAutoCommit" value="true" />
	</bean>
	
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource" />
	</bean>
	
	<bean id="transactionTemplate" class="org.springframework.transaction.support.TransactionTemplate">
		<property name="transactionManager" ref="transactionManager" />
	</bean>
    
	<bean id="sqlMapClient" class="org.springframework.orm.ibatis.SqlMapClientFactoryBean">
		<property name="configLocation"  value="classpath:${daoConf}"/>
		<property name="dataSource" ref="dataSource"/>
	</bean>
	
	<bean id="sqlMapClientOperations" class="org.springframework.orm.ibatis.SqlMapClientTemplate">
		<property name="sqlMapClient" ref="sqlMapClient" />
	</bean>

</beans>
