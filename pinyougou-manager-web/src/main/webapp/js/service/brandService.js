//品牌的服务层
app.service('brandService',function ($http) {
    this.findPage=function (page,rows) {
        return  $http.get('../brand/findPage.do?page='+page+'&rows='+rows);
    }

    this.search=function (page,rows,searchEntity) {
        return $http.post("../brand/search.do?page="+page+"&&rows="+rows,searchEntity);
    }

    this.save=function (methodName,entity) {
        return $http.post("../brand/"+methodName+".do",entity);
    }

    this.findOne= function (id) {
        return $http.get("../brand/findOne.do?id="+id);
    }

    this.delete = function (ids) {
        return $http.get('../brand/delete.do?ids='+ids);
    }
});