# test

##_**API:**_
###_/users_:
```
GET                 -> returns all of users
POST                -> registers a new user; consumes entity like CustomUser
PUT                 -> updates an existing user; consumes entity like CustomUser
DELETE              -> activates or deactivates an user; consumes {id}
/{id}               -> GET; returns a user with {id}
/current            -> GET; returns a currently logged in user
/change-password    -> POST; changes a password; consumes oldPass and newPass
```
