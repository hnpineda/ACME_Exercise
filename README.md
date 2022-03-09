# ACME_Exercise

## Methodology and approach

I used the OOP paradigm. The data of each text file line is converted to an object of type Worker. That object has as parameters the name and its schedule information. And the logic of the program is written in the Main method of the Main class.

The design pattern I approached to solve the problem is the Iterator Design Pattern. Mostly because I use an iterator object to go through some maps (TreeMaps). On those maps, the worker-related data is stored, such as the name and the schedule. I chose to work with the Map framework, which stores the worker's information because it allows associating the workers' names with their schedules. Using a map to store schedules allowed me to better associate the day with their respective working hours.

## Overview of the Solution

After executing the Java program and passing the txt file path as an argument, the worker name and their schedule are stored in a TreeMap, as key values, respectively. But the schedule is stored as a HashMap with the days as the key and the hours as the value. 

Then, with the help of an iterator object, an element of the TreeMap is selected, their K-V is stored in a variable and will be used as a reference value. Then the TreeMap selected element is removed. After that, another iterator object selects the remaining elements of the TreeMap and each of these elements will be compared with the referenced value previously stored. 

Later, with the help of two more iterator objects, the schedule data (day and hours) of the referenced values is compared with the schedule data of the remaining elements of the TreeMap.

If two workers have a coincidence on the workday and an overlapping of the working hours, the names of both workers and the number of times they coincided in a week will be stored as a K-V on a Treemap respectively.

## Execution

Run with Java 17.

- Open the shell or command line in the directory where the repository was downloaded.

- Execute the command: javac *.java.

- Execute the command: java MainClass -txt filename-
