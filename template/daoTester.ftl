package ${package};

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ${abstract_spring_dao_test};
import ${default_list_adapter};
import ${list_adapter};

import ${domain};

public class ${className}DaoTest extends AbstractSpringDaoTest<${className}> {

	@Autowired
	private ${className}Dao<${className}> <@lowerFirstChar>${className}</@lowerFirstChar>Dao;

	private Map<String , ${className}> data ;
	@BeforeMethod
	public void setBefore() {
		String pg = this.getClass().getPackage().getName().replace(".", "/");
		data = super.getYamlData( pg + "/${className}DaoTest.yaml");
	}
	
	 @Test
	public void testInsert${className}() {
		${className} <@lowerFirstChar>${className}</@lowerFirstChar> = data.get("insert");
		<@lowerFirstChar>${className}</@lowerFirstChar>Dao.insert(<@lowerFirstChar>${className}</@lowerFirstChar>);	
		<@lowerFirstChar>${className}</@lowerFirstChar> = <@lowerFirstChar>${className}</@lowerFirstChar>Dao.find(<@lowerFirstChar>${className}</@lowerFirstChar> , null);
		Assert.assertNotNull(<@lowerFirstChar>${className}</@lowerFirstChar>);	
	}
	
    @Test
	public void testUpdate${className}() {
		${className} <@lowerFirstChar>${className}</@lowerFirstChar> = data.get("update");
		<@lowerFirstChar>${className}</@lowerFirstChar>Dao.update(<@lowerFirstChar>${className}</@lowerFirstChar>);
	}
	
	 @Test
	public void testFind${className}() {
		${className} <@lowerFirstChar>${className}</@lowerFirstChar> = data.get("find");
		<@lowerFirstChar>${className}</@lowerFirstChar> = <@lowerFirstChar>${className}</@lowerFirstChar>Dao.find(<@lowerFirstChar>${className}</@lowerFirstChar> , null);
		Assert.assertNotNull(<@lowerFirstChar>${className}</@lowerFirstChar>);
	}
	
    @Test
	public void testList${className}() {
		${className} <@lowerFirstChar>${className}</@lowerFirstChar> = data.get("list");
		ListAdapter<${className}> adapter = new DefaultListAdapter<${className}>();
		//adapter.setOrderItem("id");
		adapter.setOrderType("desc");
		List<${className}>  <@lowerFirstChar>${className}</@lowerFirstChar>s = <@lowerFirstChar>${className}</@lowerFirstChar>Dao.list(<@lowerFirstChar>${className}</@lowerFirstChar> , adapter);
		for(${className} <@lowerFirstChar>${className}</@lowerFirstChar>tmp :  <@lowerFirstChar>${className}</@lowerFirstChar>s) {
			Assert.assertNotNull(<@lowerFirstChar>${className}</@lowerFirstChar>tmp);
		}
		Assert.assertTrue(<@lowerFirstChar>${className}</@lowerFirstChar>s.size() > 0);
		
		Integer count = <@lowerFirstChar>${className}</@lowerFirstChar>Dao.listCount(<@lowerFirstChar>${className}</@lowerFirstChar> , adapter);
		Assert.assertTrue(count > 0);
	}
	
	
    @Test
	public void testDelete${className}() {
		${className} <@lowerFirstChar>${className}</@lowerFirstChar> = data.get("find");
		<@lowerFirstChar>${className}</@lowerFirstChar> = <@lowerFirstChar>${className}</@lowerFirstChar>Dao.find(<@lowerFirstChar>${className}</@lowerFirstChar> , null);
		${className} del${className} = new ${className}();
		//TODO set id
		<@lowerFirstChar>${className}</@lowerFirstChar>Dao.delete(del${className});
		<@lowerFirstChar>${className}</@lowerFirstChar> = <@lowerFirstChar>${className}</@lowerFirstChar>Dao.find(<@lowerFirstChar>${className}</@lowerFirstChar> , null);
		Assert.assertNull(<@lowerFirstChar>${className}</@lowerFirstChar>);
	}

}

