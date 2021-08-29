# shaadiAssignment
This is the demo application for shaadi.com interview assignment.

Application Flow :
1. One every app open it fetches 10 new users from API and store them into the db.
2. All the previouse records will still be there in the application, you can find them after these 10 new users.
3. Every time you accept/decline the matches, it will be store as match status in the db and will be updated on the UI using LiveData.

Database Entities :
1. User: it is used to store user details
2. Location : It is used to store location related info of user. it has one-on-one relationship with user table and store uuid of user as foriegn key.
3. MatchStatus : it is used to store the match status(Accept/decline) status, it has one-on-one relationship with user table and store uuid of user as foreign key.

Architecture :
It uses MVVM architecture.
1. Model : Repositories(UserRepository) acts as the model.
2. View : MainActivity acts as View.
3. ViewModel : UserViewModel is the ViewModel for this app.
