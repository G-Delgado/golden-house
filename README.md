# Golden House - GUI Menu
## Table of contents
* [General Info](#general-info)
* [Functionalities](#functionalities)
* [Second Delivery Changes](#second-delivery-changes)
* [Technologies](#technologies)
* [Setup](#setup)
* [Credits](#credits)

### General Info
Golden House - GUI Menu is a desktop program based on the restaurant Golden House. It works on Java and Javafx using Scene builder to build the .fxmls.  
The program is able to manage (add, edit, enable / disable, delete) Users, Employees, Clients, Products, Ingredients, Types of products and Orders.  
You can also import data from .csv files to test these functionalities. Finally, you can generate reports of Clients, Products and Orders, everything between a range of dates.
### Functionalities
The program functionalities are:
* Manage users
* Manage employees
* Manage clients
* Manage products
* Manage ingredients
* Manage type of products
* Manage orders
* List users, employees, clients, products, ingredients, types of products and orders of the system
* Allow the log in the application
* Allow the register in the application
* Allow the change of the password
* Generate reports between a range of date and hour

### Second Delivery Changes
For the second delivery the next changes have been made:
* Error at editing clients and employees fixed.
* Adding, Editing, Deleting, Enabling and Disabling objects now throw confirmation alerts.
* Time interval included at the generate reports functionality.
* Binary search for the client given a name and last name added.
* Two algorithms implemented. Bubble sort for ingredients and Insertion sort for types.
* Import functionality added.
* Internationalization partially added (So far).
* Time and Date added with Threads.
* Class diagram cardinality, direction and visibility fixed.
* Class diagram association fixed.
* Class diagram dependency with .fxml added.
* Requirements are now more specific.

### Technologies
This program was made using:
* Java 
* Java jdk 1.8
* Java jre 1.8
* JavaFX
* Scene Builder

### Setup
To initialize the program, do:

```
javac -cp src src/ui/*.java -d bin
javac -cp src src/model/*.java -d bin
java -cp bin ui.Main
```
This way, we can compile the whole program and run it.
### Credits
The author of this program is **Gabriel Delgado**. (2021-1)