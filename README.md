# hotel room reservation
Facilitates the booking of hotel rooms, managing room availability, reservations, and cancellations.


The project is about reserving hotel rooms: user can see rooms, book them, cancel reservations & update them. It simplifies the booking by program's simple interface.

# User can do basic actions, like:
1. see all rooms
2. book a room
3. view reservations
4. cancel reservation
5. update reservation name
6. view reports
7. save all & exit

   ![image](https://github.com/user-attachments/assets/9a7d2b1f-e6a2-415c-864f-385610220ea4)

# What's doing each of these functions?
1. show all rooms

   Displays all rooms from the list. It shows room number, type, price, and if it’s free or booked
   
2. book a room

   Asks the user for their name and the room number. If the room is free, it books it. If not, it shows a message
   
3. view reservations

   Prints all people who booked a room and their room number
   
4. cancel reservation

   Asks for the user's name. If a reservation is found with that name, it removes it and makes the room available again.
   
5. update reservation

   Changes the name on a reservation. It asks for the old name and the new name
   
6. show report

   Prints how many reservations there are and how many rooms are still free
   
7. save all & exit
   
   Saves all data into rooms.csv and reservations.csv and exits the program.

   # there's csv files , program is reading info from those files and when the program is closed again it saves new info there

   ![image](https://github.com/user-attachments/assets/6532ce3c-5fe6-469d-9520-9a7fc80c342f)

   ![image](https://github.com/user-attachments/assets/8301aca4-e1ec-4632-bbb0-8bc04044a888)

   Here the run of booking a room :

   ![image](https://github.com/user-attachments/assets/827bc108-17ed-45e8-8cf0-508d6482ff60)

   # Input Validation :
   
  1. the program checks if the room is already booked
  2. it does not allow empty names
  3. it shows errors if the room number is not found.

  # This project helped me better understand how to work with file storage, object-oriented programming
  
  
  subanova milena EEAIR24
  
  
  thanks 

