/**
 * Created by mak on 9/26/16.
 */
(() => {

  const app = angular.module('client', ['ngRoute']);

  const UserFactory = () => {
    return {
      users : [
        {id: 1, username: 'Max'},
        {id: 2, username: 'Bax'}
      ]
    };
  };

  const MainCtrl = ($scope, UserFactory) => {
    $scope.users = UserFactory.users;
  };

  return app.factory('UserFactory', UserFactory)
            .controller('MainCtrl', ['$scope', 'UserFactory', MainCtrl]);
})();
