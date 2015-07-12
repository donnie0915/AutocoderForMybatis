package com.longtask.tea.yaml;

public class PackageConfigUtil {
	
	public static String getKey(String key,boolean isPath){
		return getPackage(ParseYamlConfig.parseYaml().getCodePackageMap(key),isPath);
	}
	
	
	private static String getPackage(String pg,boolean isPath){
		String base = ParseYamlConfig.parseYaml().getCodePackageMap("base");
		if(isPath){
			return replace(base,isPath) + replace(pg,isPath);
		}else{
			return base + "." + pg;
		}
	}


	private static String replace(String str,boolean isPath){
		if(isPath){
			return "/" + str.replace(".", "/");
		}else{
			return str;
		}
	}

    public static String getIgnorePrefix(){
        return ParseYamlConfig.parseYaml().getCodePackageMap("ignorePrefix");
    }

    public static String getIgnoreSuffix () {
        return ParseYamlConfig.parseYaml().getCodePackageMap("ignoreSuffix");
    }

    public static String getTargetDir() {
        return ParseYamlConfig.parseYaml().getCodePackageMap("targetPath");
    }



    public static String getConfigValue(String key) {
        if (key == null) {
            return "";
        }

        return ParseYamlConfig.parseYaml().getCodePackageMap(key);
    }
}
