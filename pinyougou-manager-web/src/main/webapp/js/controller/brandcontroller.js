app.controller('brandcontroller' ,function($scope,brandService){


    $scope.reloadList = function () {
        $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
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
    <!--执行分页方法-->
    $scope.findPage = function (page, rows) {
        brandService.findPage().success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;
            }
        );
    }
    //保存
    $scope.save = function () {
        var object = null;
        if ($scope.entity.id != null) {
            object = brandService.update($scope.entity);
        }else {
            object = brandService.add($scope.entity);
        }
        object.success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();
                } else {
                    alert(response.message);
                }
            }
        );
    }

    $scope.findOne = function (id) {
        brandService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    }



    //删除
    $scope.delete = function () {
        brandService.delete($scope.selectIds).success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();
                } else {
                    alert(response.message);
                }
            }
        );
    }

    $scope.searchEntity = {};
    $scope.search = function (page, rows) {
        brandService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;
            }
        );
    }

});