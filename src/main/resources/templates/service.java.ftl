package ${package.Service};

    import ${package.Entity}.${entity};
    import ${superServiceClassPackage};

    /**
    * <p>
    * ${table.comment!} 服务类
    * </p>
    *
    * @author ${author}
    * @since ${date}
    */
<#if kotlin>
    interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
    public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {


    Map<String, Object> list(Integer pageNum,Integer pageSize,String Name);

    void delete(List<Integer> id);

    void insert(${entity} entity);

    void edit(${entity} entity);
    }
</#if>