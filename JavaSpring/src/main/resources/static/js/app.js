
(function () {

    var app = angular.module('Store', []);

    app.controller('StoreController', function ($scope,$http) {
        $http.get('http://localhost:8080/api/product/detail/2').
        then(function(response) {
            $scope.productDetail = response.data.data;
        });
    });

    app.controller('PanelController', function () {
        this.tab = 'description';

        this.setTab = function (tab) {
            this.tab = tab;
        };

        this.isSelected = function (tab) {
            return this.tab === tab;
        }
    });



})();