app.controller("brandController", function ($scope,$controller, brandService) {

    $controller('baseController',{$scope:$scope});

    <!--执行分页方法-->
    $scope.findPage = function (page, rows) {
        brandservice.findPage().success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;
            }
        );
    }
    //保存
    $scope.save = function () {
        var methodName = 'add';
        if ($scope.entity.id != null) {
            methodName = "update";
        }
        brandservice.save(methodName, $scope.entity).success(
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
        brandservice.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    }



    //删除
    $scope.delete = function () {
        brandservice.delete($scope.selectIds).success(
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
        brandservice.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;
            }
        );
    }

});