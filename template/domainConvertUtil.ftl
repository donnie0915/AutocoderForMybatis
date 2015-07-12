package ${package} ;
/**
 * 
 * @author ${author}
 * @version ${version}
 *
 */


public class ${className}Util  {

    /**
    * SQL Entity 转换为业务Domain
    *
    * @param  <@lowerFirstChar>${className}</@lowerFirstChar>
    * @return
    */
    public static ${className}Do convertEntityToDomain(${className} <@lowerFirstChar>${className}</@lowerFirstChar>) {

        if (<@lowerFirstChar>${className}</@lowerFirstChar> == null) {
            return null;
        }
        ${className}Do  <@lowerFirstChar>${className}</@lowerFirstChar>Do= new ${className}Do();
        <#list columns as column>
        <@lowerFirstChar>${className}</@lowerFirstChar>Do.set<@upperFirstChar>${column.javaName}</@upperFirstChar>(<@lowerFirstChar>${className}</@lowerFirstChar>.get<@upperFirstChar>${column.javaName}</@upperFirstChar>());
        </#list>

        return <@lowerFirstChar>${className}</@lowerFirstChar>Do;
    }


    /**
    * Domain  转换为业务 SQL Entity
    *
    * @param <@lowerFirstChar>${className}</@lowerFirstChar>Do
    * @return
    */
    public static ${className} convertDomainToEntity(${className}Do <@lowerFirstChar>${className}</@lowerFirstChar>Do) {

        if (<@lowerFirstChar>${className}</@lowerFirstChar>Do == null) {
           return null;
        }
        ${className}  <@lowerFirstChar>${className}</@lowerFirstChar>= new ${className}();
        <#list columns as column>
        <@lowerFirstChar>${className}</@lowerFirstChar>.set<@upperFirstChar>${column.javaName}</@upperFirstChar>(<@lowerFirstChar>${className}</@lowerFirstChar>Do.get<@upperFirstChar>${column.javaName}</@upperFirstChar>());
        </#list>

       return <@lowerFirstChar>${className}</@lowerFirstChar>;
    }


} 
