package com.longtask.tea.freemarker;


public interface BulidTemplet {

	public final static String MAIN_PATH = "/src/main/java";
	public final static String VM_CONFIG_PATH = "/config/";
	public final static String VM_VELOCITY_PATH = "/velocity/content/";
	public final static String MAIN_CONF = "/src/main/resources";
	public final static String TEST_PATH = "/src/test/java";
	public final static String TEST_CONF = "/src/test/resources";



	public abstract boolean buildSourceFile(String tableName);

}