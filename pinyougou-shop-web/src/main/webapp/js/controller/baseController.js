app.controller('baseController',function ($scope) {

    $scope.reloadList = function () {
        $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    }

    //更新复选框
    $scope.selectIds = [];
    $scope.updateSelection = function ($event, id) {
        if ($event.target.checked) {
            $scope.selectIds.push(id);
        } else {
            var index = $scope.selectIds.indexOf(id);
            $scope.selectIds.splice(index);
        }

    }
//分页控件配置
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function () {
            $scope.reloadList();
        }
    };

    //在json中提取某个元素组成新的字符串
    $scope.jsonToString=function (jsonString ,key) {
        var json = JSON.parse(jsonString);
        var value="";
        for (var i =0;i<json.length;i++){
            //这里的写法，因为数组中第一个元素，然后{}中取元素可以是a[text]的方式
            if (i>0){
                value+=",";
            }
            value+=json[i][key];
        }
        return value;
    }


    //从集合中根据key查找对象
    $scope.searchObjectByKey=function(list,key,keyValue){
        for (var i=0;i<list.length;i++){
            if(list[i][key]==keyValue){
                return list[i];
            }
        }
        return null;
    }

})