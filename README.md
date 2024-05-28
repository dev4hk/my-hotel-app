# Hotel Reservation

This project is for users to make reservation for hotel rooms

## Features

- User can login in and out
- User can view / reserve rooms
- Admin can manage rooms / bookings

## Tech Stack

- Spring Boot
- Gradle
- Spring Data JPA
- Lombok
- MySQL
- Spring Security
- JWT
- React
- Axios
- Bootstrap

## Entity Relationship Diagram
![ERD](images/ERD.png)

## Screenshots
- Main page
![main](images/main.png)
- Signup page
![signup](images/signup.png)
- Login page
![login](images/login.png)
- Room select
![roomSelect](images/roomSelect.png)
- Reservation form
![reservationFOrm](images/reservationForm.png)
- Confirmation
![confirmation](images/confirmation.png)
- Search booking
![search](images/findBooking.png)
- Admin room management
![roomManage](images/adminManageRoom.png)
- Admin add room
![addRoom](images/adminAddRoom.png)


## Lessons Learned

- JWT token can be stored in browser's local storage, and can be used for API calls whenever needed
- Photo can be received as Multipart in the backend, and can be converted to BLOB to store image as a large object
- We can set photo as byte array in the response, or Base64 encoded so that the client application can render
