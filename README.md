# 24 Crafts Routes Test - With Params

## /adduser - User Creation - POST 

### req: body 

Everything!! (User Creation)

Example: 

+ name: VPR
+ password: 123
+ dob: 1988-07-24
+ isClient: true
+ category: Director
+ gender: male
+ email: rakave@gmail.com

(Check schema to see what is required and what is not)

### res
+ Registration successful!

(email sent)

## /confirmEmail/:email/:emailhash - Email confirmation - GET

### req: NONE

### res
+ Email confirmed! 

## /login - POST

### req: body

Example:

+ username: rakave@gmail.com
+ password: 123

### res: body
+ {
    "token": "eyJhbGciLiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IlZlbHVwQG5kaWFuLmNvbSIsImlkIjoiNWEzMjdlMjM4ZjJhZGYyYWM2NTBkNzc0IiwiaXNDbGllbnQiOnRydWUsImNhdGVnb3J5IjoiQ0xJRU5UIiwiaWF0IjoxNTEzMjU5MDQ1LCJleHAiOjE1MTMzNDU0NDV9.CNwC8ElRxJ0cRSu4soNoB5W_1wUSxjboVw5AXIKToMg"
}

## /user - Obtain User information - GET

### req: header

+ authorization: (token from above)

### res: body

{
    "_id": "5a327e238f2adf2ac650d774",
    "name": "VPR",
    "dob": "1988-07-24T00:00:00.000Z",
    "gender": "male",
    "email": "rakave@gmail.com",
    "__v": 0,
    "createdAuditions": [],
    "experienceName": [],
    "previousExperience": [],
    "languagesSpoken": []
}


## /user/delete - Delete User - GET

### req: header

+ authorization: (token from above)

### res: body

+ User deleted message

## /user/view/all/:databaseName - Obtain all users from that Craft - GET

### req : header + params

+ authorization (header)
+ databseNames : Actor, Actress, etc

### res : body

{ 
    ALL USER INFO FOR THAT CRAFT
}

## /user/audition/view - Obtian all auditions applied by User - GET

### req: header

+ authorization: (token from above)

### res : body

{
    All applied Auditions
}


# TODO

+ Confirm Email : Showing error for Clients
+ Backup Mongo Directory from 24crafts.tk



