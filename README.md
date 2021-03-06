# test

##_**API:**_
###_/users_:
```
GET                 -> returns all of users
POST                -> registers a new user; consumes entity like [CustomUser]
PUT                 -> updates an existing user; consumes entity like [CustomUser]
DELETE              -> activates or deactivates an user; consumes {id}
/{id}               -> GET; returns a user with {id}
/current            -> GET; returns a currently logged in user
/change-password    -> POST; changes a password; consumes oldPass and newPass
```
###_/goods_:
```
GET                 -> returns all of goods; may consume {name} and {categoryId}
POST                -> adds a new good; consumes entity like [Good]
PUT                 -> updates an existing good; consumes entity like [Good]
DELETE              -> deletes a good; consumes {id}
/{id}               -> returns a good with {id}
/pictures           -> GET; returns all pictures of good; consumes {id}
/pictures           -> POST; adds a picture for good; consumes {id} of good and {file}
/pictures           -> DELETE; deletes a picture of good; consumes {id} of picture
```
###_/categories_:
```
GET                 -> returns all of categories
POST                -> adds a new category; consumes entity like [Category]
PUT                 -> updates an existing category; consumes entity like [Category]
DELETE              -> deletes a category; consumes {id}
/{id}               -> returns a category with {id}
```
###_/cart_:
```
GET                 -> returns a cart of currently logged in user
POST                -> adds a good to a cart; cunsumes {goodId}
DELETE              -> deletes a good from a cart; cunsumes {goodId}
```
* [CustomUser]
* [Good]
* [Picture]
* [Category]

[Good]: https://github.com/NicholasG/test/blob/master/src/main/java/com/sombra/shop/good/domain/Good.java
[Picture]: https://github.com/NicholasG/test/blob/master/src/main/java/com/sombra/shop/picture/domain/Picture.java
[CustomUser]: https://github.com/NicholasG/test/blob/master/src/main/java/com/sombra/shop/user/domain/CustomUser.java
[Category]: https://github.com/NicholasG/test/blob/master/src/main/java/com/sombra/shop/category/domain/Category.java
