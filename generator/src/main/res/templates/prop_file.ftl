<#if needDebug>
isDebug=true

</#if>
<#list properties as property>
<#if property.notes?has_content>
# ${property.notes}
</#if>
${property.annotationName}=<#if property.defVal?has_content>${property.defVal}</#if>
</#list>
<#if needDebug>

<#list properties as property>
<#if property.notes?has_content>
# ${property.notes} when debug is true
</#if>
debug.${property.annotationName}=<#if property.defVal?has_content>${property.defVal}</#if>
</#list>
</#if>