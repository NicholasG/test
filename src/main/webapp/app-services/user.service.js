(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];
    function UserService($http) {
        var service = {};

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.GetCurrent = GetCurrent;
        service.Create = Create;
        service.Register = Register;
        service.Update = Update;
        service.Delete = Delete;

        return service;

        function GetAll() {
            return $http.get('/users').then(handleSuccess, handleError('Error getting all users'));
        }

        function GetById(id) {
            return $http.get('/users/' + id).then(handleSuccess, handleError('Error getting user by id'));
        }

        function GetCurrent() {
            return $http.get('/users/current').then(handleSuccess, handleError('Error getting current user'));
        }

        function Create(user) {
            return $http.post('/users', user).then(handleSuccess, handleError('Error creating user'));
        }

        function Register(user) {
            return $http.post('/auth/register', user).then(handleSuccess, handleError('Error registering user'));
        }

        function Update(user) {
            return $http.put('/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
        }

        function Delete(id) {
            return $http.delete('/users/' + id).then(handleSuccess, handleError('Error deleting user'));
        }

        // private functions

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return {success: false, message: error};
            };
        }
    }

})();
