package ${package} ;
/**
 * 
 * @author ${author}
 * @version ${version}
 *
 */
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ${className} implements Serializable {
	
	private static final long serialVersionUID = ${UUID}L;

<#list columns as column>
    /** ${column.columnCommit} */
    private ${column.javaType} ${column.javaName} ;

</#list>

<#list columns as column>
    public ${column.javaType}  get<@upperFirstChar>${column.javaName}</@upperFirstChar>(){
        return ${column.javaName} ;
    }
    public ${className} set<@upperFirstChar>${column.javaName}</@upperFirstChar>(${column.javaType} ${column.javaName}){
        this.${column.javaName} = ${column.javaName} ;
        return this;
    }

</#list>


} 
