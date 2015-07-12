package ${package};

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.IHookCallBack;
import org.testng.ITestResult;
import org.yaml.snakeyaml.Yaml;

@ContextConfiguration(locations = { "classpath:spring*.xml","classpath:spring-dao-config.xml" })
public abstract class AbstractSpringDaoTest<T> extends AbstractTestNGSpringContextTests {
	private static final transient Logger logger = LoggerFactory.getLogger(AbstractSpringDaoTest.class);
	public void run(IHookCallBack callBack, ITestResult testResult) {
		super.run(callBack, testResult);
		this.hook(callBack, testResult);
	}
	/**
	 * 子类需要hook内容的时候参考
	 * @param callBack
	 * @param testResult
	 */
	protected void hook(IHookCallBack callBack, ITestResult testResult){
		
	}
	/**
	 * 把yaml 定义的值 set 到对象
	 * @param name
	 * @return
	 */
	public Map<String , T> getYamlData(String name){
		try {
			Yaml yaml = new Yaml();
			@SuppressWarnings("unchecked")
			Map<String , T> data = (Map<String , T>)yaml.load(this.getClass()
					.getResourceAsStream("/" + name));
			return data;
		} catch (Exception e) {
			logger.error("init yaml error : " , e);
			throw new RuntimeException("init yaml error : " , e);
		}
	}
}

