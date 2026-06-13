# IY4113 Milestone 1 Part 2

| Assessment Details | Please Complete All Details                                          |
| ------------------ | -------------------------------------------------------------------- |
| Group              | 4113 A                                                               |
| Module Title       | Applied Software Engineering using object Orientated<br/>Programming |
| Assessment Type    | Java fundamental's part 2                                            |
| Module Tutor Name  | Jonathan Shore                                                       |
| Student ID Number  | P512757                                                              |
| Date of Submission | 14/06/2026                                                           |
| Word Count         |                                                                      |
| GitHub Link        |                                                                      |

- [x] *I confirm that this assignment is my own work. Where I have referred to academic sources, I have provided in-text citations and included the sources in
  the final reference list.*

- [x] *Where I have used AI, I have cited and referenced appropriately.

------------------------------------------------------------------------------------------------------------------------------

### Purpose of the Program

The purpose of this program is to develop an extended console-based java application called CityRide Lite, building on the foundation of part 1. This report focus on adding persistent storage,user roles, and adminstrative control.

 As a Rider, the user is to create a personal profile, add and manage journeys throughout the day, calculating running fare totals and summaries and also generate a detailed end-of -day summary saved in both CSV and human-readable text formats. 

As a Admin , the user can access a password-protected menu to view and manage the system's configuration.All changes must be validated before being saved to ensure the system remains reliable and consistant. The program will provide clear menu options to guide user and ensure correct input throughout.



### Core program Functionality

Select User Role

- Display role selection menu on launch 

- Allow user to select Rider or Admin

- Load system configuration from file on starup

- Use safe default values if configuration file is missing 

Rider Profile Management 

- Allow rider to create a personal profile including name,passenger type, default payment option

- Save and load profile to a JSON file

Display Menu Options

- Show rider menu options (add journey, edit journey, delete journey, import journey, export journey , calculate fares, view summary, save/exit )

- Show Admin menu options ( view config, add/update / delete fare rules, discounts, caps, peak windows, exit )

Add journey Details 

- Capture user input regarding date , start zone, destination zone , passenger type , and time band 

- Assign a unique journey ID

- Import journeys from a CSV file

System Validations 

- Validate all user input

- Prompt user to re-enter invalid input

- Reject invalid journey IDs with clear error messages

- Validate all admin input before saving 

Fare Calculations 

- Calculate zones crossed and determine base fare from dataset 

- Apply peak or off-peak rules based on time band

- Apply passenger discounts based on passenger type 

Apply Daily Cap 

- Track running total per passenger type 

- Adjust fare when cap is reached 

Edit and Delete journeys

- Edit and Delete journeys by ID

- Recalculate totals after changes 

Display Summary 

- Show total journeys , total cost, average cost , most expensive journey 

- Display cap status, savings , peak vs off-peak counts, and zone pair counts 

Export Reports 

- Export journeys to CSV file 

- Save end-of-day summary as CSV and human-readable text file 

- Store in reports folder with date and rider name

Admin configuration Management

- Password-protected admin menu

- View,add,update, and delete base fares, discounts, caps, and peak windows

- Validate all changes before saving

System Exit 

- Prompt user to save data before exiting

- Save profile and journeys if comfirmed 



System Constraints 

- The program is console-based with no graphical user interface

- The system supports two roles only - Rider and Admin

- Zones are numbered 1 to 5 only 

- Time bands are Peak and Off-peak only 

- Passenger types are 

------------------------------------------------------------------------------------------------------------------------------

*Add purpose of the program, identify core program functionality and and system contraints.*

------------------------------------------------------------------------------------------------------------------------------

### Input Process Output Table

------------------------------------------------------------------------------------------------------------------------------

*Add IPO table (It maybe easier to create the table in Word and paste as an image!)*

------------------------------------------------------------------------------------------------------------------------------

### Algorithm Design

---

- *Add **images** for the design of your algorithm. Choose either Flowchart or JSP diagrams to demonstrate the functional elements of the algorithm. There should be multiple images for this part as you are decomposing the problem into smaller elements.*

- *Include a class diagram to demonstrate the class structure of the proposed program design.

------------------------------------------------------------------------------------------------------------------------------

### Research (minimum of 1 required, preferrebly 2)

---

*Research existing programs that solve a similar problem. The program does not have to be written in java or object orientated in nature - just solve a similar type of problem.*

*Use the strucutre below to capture your evidence:*

------------------------------------------------------------------------------------------------------------------------------Name of program:

Reference (link):

What it does well (2-3 features that work effectively):

What it does poorly (at least 1 feature):

Key design ideas you could reuse (e.g., layout, navigation, input/output, program structure):

Screenshot (showing the interface/output):

------------------------------------------------------------------------------------------------------------------------------

### Gantt Chart

------------------------------------------------------------------------------------------------------------------------------

*Add Gantt Chart (it maybe easier to create chart in Excel and paste as an image!)*

------------------------------------------------------------------------------------------------------------------------------

### Diary Entries

------------------------------------------------------------------------------------------------------------------------------

*Add diary entries here detailing what you have done, wny you have done it, and any problems encountered.*

------------------------------------------------------------------------------------------------------------------------------
