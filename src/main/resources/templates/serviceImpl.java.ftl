package ${package.ServiceImpl};

    import ${package.Entity}.${entity};
    import ${package.Mapper}.${table.mapperName};
    import ${package.Service}.${table.serviceName};
    import ${superServiceImplClassPackage};
    import org.springframework.stereotype.Service;

    /**
    * <p>
    * ${table.comment!} 服务实现类
    * </p>
    *
    * @author ${author}
    * @since ${date}
    */
    @Service
    @@Transactional
    @Slf4j
<#if kotlin>
    open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

    }
<#else>
    public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Autowired
    private ${table.mapperName} mapper;
    @Override
    public Map<String, Object> getUserList(Integer pageNum, Integer pageSize,String userName) {

    QueryWrapper<${entity}> wrapper = new QueryWrapper<>();
    if(StringUtils.isNotBlank(userName)){
    //wrapper.likeRight("SA008",userName);
    }
    // wrapper.eq("SA007",1);
    //wrapper.orderByDesc("SA003");
    IPage<${entity}> iPage = st4SysSaMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);


    Map<String, Object> result = new HashMap<>();
    result.put("rows", iPage.getRecords());
    result.put("total", iPage.getTotal());

    return result;
}

    @Override
    public void delete(List<Integer> ids) {
    //具体逻辑

        }

    @Override
    public void insert(${entity} entity) {
    //具体逻辑

    }

    }

    @Override
    public void editUser(${entity} entity) {
        //具体逻辑
    }

    }


    </#if>
