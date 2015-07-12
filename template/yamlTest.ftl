insert: !!${domain}
<#list columns as column>
  ${column.javaName}: 
</#list>
update: !!${domain}
<#list columns as column>
  ${column.javaName}: 
</#list>
find: !!${domain}
<#list columns as column>
  ${column.javaName}: 
</#list>
list: !!${domain}
<#list columns as column>
  ${column.javaName}: 
</#list>
  
#--delete findById set in domain;
#--createdTime: 2011-09-23 15:03:17
#--yaml tyep in here http://yaml.org/type/index.html