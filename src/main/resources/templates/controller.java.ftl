package ${package.Controller};


    import org.springframework.web.bind.annotation.*;
    import org.springframework.beans.factory.annotation.Autowired;
    import com.ycj.oe.service.${table.serviceName};
    import com.ycj.oe.entity.${entity};
    import io.swagger.annotations.Api;
    import io.swagger.annotations.ApiOperation;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
    import base.ResponseObj;
    import java.util.List;
<#if restControllerStyle>
    import org.springframework.web.bind.annotation.RestController;
<#else>
    import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>

    /**
    * <p>
    * ${table.comment!} 前端控制器
    * </p>
    *
    * @author ${author}
    * @since ${date}
    * @version v1.0
    */
<#if restControllerStyle>

    @RestController
<#else>
    @Controller
</#if>
    @RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
    class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
        public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
        public class ${table.controllerName} {
    </#if>
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ${table.serviceName} service;

    @PostMapping("/list")
    public ResultVO getUserList(@RequestBody Map<String, Object> params) {

    Integer pageNum = (Integer) params.get("pageNum");
    Integer pageSize = (Integer) params.get("pageSize");
    String name = (String)params.get("name");
    if (pageNum == null) {
    pageNum = 1;
    }

    if (pageSize == null) {
    pageSize = 10;
    }

    Map<String, Object> result = service.list(pageNum, pageSize,name);
    return ResultVOUtil.success(result);
    }


    @PostMapping("/detail")
    public ResultVO getById(@RequestBody Map<String, Object> params) {
        Integer id = (Integer) params.get("id");
        if (id == null || id < 0) {
        return ResultVOUtil.error(ResultEnum.ERROR.getCode(), "id不能为空");
        }
      ${entity} entity = service.getById(id);
        return ResultVOUtil.success(entity);
    }


@RequestMapping(value = "/add", method = RequestMethod.POST)
public ResultVO add(@RequestBody ${entity} entity, BindingResult bindingResult){
    if (bindingResult.hasErrors()) {
    return ResultVOUtil.error(ResultEnum.ERROR.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage());
    }
    //判断添加人是否为空
    service.insert(entity)
return  ResultVOUtil.success();
}


@RequestMapping(value = "/del")
public ResponseWeb<String> delete(@RequestBody Map<String, Object> params){
    List<Integer> id = (List<Integer>) params.get("ids");
    if (ListUtil.isNull(id)) {
    return ResultVOUtil.error(ResultEnum.ERROR.getCode(), "id不能为空");
    }
    service.delete(id);
    return ResultVOUtil.success();
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public ResponseWeb<${entity}> update(@RequestBody ${entity} entity,BindingResult bindingResult){
if (bindingResult.hasErrors()) {
return ResultVOUtil.error(ResultEnum.ERROR.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage());
}
//判断更新人加人是否为空
service.edit(entity);
return ResultVOUtil.success();
}

}
</#if>