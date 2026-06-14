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

### System Constraints

- The program is console-based with no graphical user interface

- The system must support two users roles only __ Rider and Admin

- The system must run continuously and display menu options until the user selects exit

- The system must handle invalid input and display error messages without crashing

- The system must correctly update data when journeys are edited or deleted

- Zones are numbered 1 to 5 only 

- Time bands are Peak and Off-peak only 

- Passenger types are Adult, Student, Child, and Senior Citizen only

- The system must follow fare rules and constraints from the supplied CityRideDataset.java

- The system must correctly  process fare calculations including zone crossed, discounts, and daily caps

- The system must ensure daily caps are never exceeded for any passenger type

- The system must read and write JSON files for profiles and configuration

- The system must read and write CSV files for journeys and reports

- If the configuration file is missing on starup, the system must use default values 

- All admin changes must be validated before being saved 

### Input Process Output Table

<style>
</style>

![](/Users/toenandaraung/Desktop/Screenshot%202026-06-13%20at%2016.14.32.png)![](/Users/toenandaraung/Desktop/Screenshot%202026-06-13%20at%2016.14.59.png)

### Algorithm Design

##### Main program role selection

![](/Users/toenandaraung/Downloads/Untitled-4.jpg)

##### Rider Menu

![](/Users/toenandaraung/Downloads/Untitled-5.jpg)

##### Add Journey

![](/Users/toenandaraung/Downloads/Untitled-6.jpg)

##### Manage profile

![](/Users/toenandaraung/Downloads/Untitled-7.jpg)

##### Edit journeys

![](/Users/toenandaraung/Downloads/Untitled-8.jpg)

##### Delete journeys

![](/Users/toenandaraung/Downloads/Untitled-9.jpg)

##### Admin Menu

![](/Users/toenandaraung/Downloads/Untitled-10.jpg)

### Research

Research 1: Name of program : Transport for NSW Opal Card System

Reference : Transport for NSW.(n.d). Plan your trip. Retrieved from: [https://transportnsw.info](https://transportnsw.info) [Accessed 13 June 2026]

What it does well: 

- Calcualtes fares automatically based on zones and peak or off-peak time 

- Stores a persistent passenger profile across multiple sessions

- Applies daily fare caps and shows savings compared to uncapped fares 

What it does poorly:

- Does not show a breakdown of how each fare was calculated 

Key Design ideas to reuse:

- Persistent profile storage using files between sessions 

- Daily cap tracking with saving display 

- Seperate admin control for updating fare rules 

![](/Users/toenandaraung/Desktop/Screenshot%202026-06-14%20at%2013.44.56.png)



Research 2 : Name of program: Citymapper 

Reference (link):Citymapper.(n.d).Journey planner . Retrieved from:[https://citymapper.com](https://citymapper.com) [Accessed 13 June 2026]

What it does well :

- Provides journey information in a clear and includes fare estimates , zone crossed, and travel time 

- Displays several routes options enabling users to choose the most suitable journey 

- Supports different transport methods and passenger journeys

What it does poorly : 

- Does not provide tracking daily spending totals or apply fare caps .That mean user cannot moniter their daily transport costs in one place. 

Key design ideas you could reuse (e.g., layout, navigation, input/output, program structure):

- Clear journey and fare summaries 

- Saved places and profile feature 

- Simple and clear menu navigation that guides the user step by step 

Screenshot (showing the interface/output):

![](/Users/toenandaraung/Desktop/Screenshot%202026-06-14%20at%2013.56.32.png)

### Gantt Chart

![](/Users/toenandaraung/Desktop/Screenshot%202026-06-14%20at%2014.22.12.png)

### Diary Entries

###### Diary Entry 1 - 12 June 2026

Today I started part 2 specification by reading through it carefully. I compared it to part 1 to understand what was new and what carried over. I then began writing the purpose of the program and the core functionality section. I also reviewd my part 1 feedback so I could plan how to fix those issues.

The main challenge today was understanding the full of part 2 , as it is much more complex than part 1. However, breaking it down section by section made it more manageable.



###### Diary Entry 2 - 13 June 2026

Today I focused on completeing the system constraints, IPO tables and starting fowcharts. I went through each Part 2 requirement carefully to make sure every bullet point in the core functionality matched a specific requirement. i then started drawing the flowcharts in miro , beginning with the rider menu and add jounrey flowcharts. 

The hardest part was makeing sure i used the correct flowcharts symbols, especially after the feedback from part 1. I made sure to use parallelograms for inputs and outputs and predefined process shapes for sub- flowcharts.



###### Diary Entry 3 - 14 June 2026

Today i finished the remaining flowcharts and compeleted the research section. I looked at three existing transport systems - opak card, and city mapper and identified usefuyl design ideas for part 2. I  focusing on finishing everything together and checked.

The main problem i faced was finding the extensions of part 1 because I found out the difficult of extensions between part 1 and part 2 so I got confusions a little as well as the research a.
