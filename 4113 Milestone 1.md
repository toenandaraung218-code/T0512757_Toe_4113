# IY4113 Milestone 1

| Assessment Details | Please Complete All Details                                      |
| ------------------ | ---------------------------------------------------------------- |
| Group              | 4113 A                                                           |
| Module Title       | Applied Software Engineering using object Orientated Programming |
| Assessment Type    | Java fundamental's part 1                                        |
| Module Tutor Name  | Jonathan Shore                                                   |
| Student ID Number  | P512757                                                          |
| Date of Submission | 03/05/26                                                         |
| Word Count         |                                                                  |

- [x] *I confirm that this assignment is my own work. Where I have referred to academic sources, I have provided in-text citations and included the sources in
  the final reference list.*

- [x] *Where I have used AI, I have cited and referenced appropriately.

------------------------------------------------------------------------------------------------------------------------------

### Purpose of the Program

*Add purpose of the program, identify core program functionality and and system contraints.

The purpose of this program is to develop a console-based Java application called City Ride Lite. THe goal is to assist a user in observing and tracking their public transport journeys for a single day. The program must store detailed information by asking the user to input data. Based on the dails , the fate and discounts will be calculated according to the zone crossed. It is also necessary to apply a daily cap and adjust the cost accordingly . The program is required to store all the journeys to allow the user to review and filter them after confirmation, as well as remove them if needed. A summary will be presented, including the total number of journeys, total amount spend, average cost per journey, and the most expensive journey.In addition, the program will provide clear menu options to guide the user and ensure correct input. 

### Core program functionality

Display the menu options***

- Show the options details(add journey, list journeys, filter journeys, view daily summary, view totals by passenger type, undo/remove journeys , reset day and exit)

Add journeys details***

- Capture the user input regarding start zone, destination zone, passenger types, and time band

- Assign a unique journey ID

System Validations

- Validate user input

- Prompt user to re-enter invalid input

Make fare calculations of each journey***

- Calculate the number of zones crossed and determine the base fate based on dataset

- Apply passenger discount : Adult (0%), Student(25%), Child(50%), Senior (30%)

Apply daily cap***

- Track running total per passenger type 

- Ensure total does not exceed daily cap

- Adjust fare if cap is reached

- Maintain separate running totals for each passenger type

- Round all calculated fares to 2 decimal places 

Display the summary of journeys details***

- Show the totals of daily summary

- Display the totals by passenger types

- Present the category counts

- Adjust the average cost 

- Generate the most expensive journey

Manage journeys details***

- Display all recorded journeys

- Allow user to filter journeys based on criteria 

- Allow user to remove a journey by ID

Reset system***

- Clear all stored journeys and totals

- Ask for confirmation before resetting

### System constraints

- The program must be console-based only, using menu options for user interaction

- The system must store all journey data in memory during execution only, meaning data will be lost when the program is reset or closed

- Each journey must have a unique ID to support viewing and removing journeys

- The system must run continuouslu and disply menu options until the user selects exit

- The system must handle invalid input and display error messeages without crashing

- The system must correctly update data when journeys are removed or reset

- The system must maintain separate running totals for each passenger type

- The system must ensure all monetary values are rounded to 2 decimal places

- The system must correctly process fare calculations, including zones, discounts, and daily caps.

### Input Process Output Table

*Add IPO table (It maybe easier to create the table in Word and paste as an image!)*

![](/Users/toenandaraung/IdeaProjects/T0512757_Toe_4113/IPO.png)

### Gantt Chart

------------------------------------------------------------------------------------------------------------------------------

*Add Gantt Chart (it maybe easier to create chart in Excel and paste as an image!)*

![](/Users/toenandaraung/IdeaProjects/T0512757_Toe_4113/gantt%20chart.png)

### Diary Entries

*Add diary entries here detailing what you have done, wny you have done it, and any problems encountered.*

### **27/01/2026 - Daily Entry 1- Understanding the assignment brief , writing program purpose and functionality

Today I reviewed the assignment brief to understand the requirements of the program. I identified the main features of the program neatly. This helped me understand what the system is expected to do. I did read the brief repeatedly to get the main idea. In addition , I wrote the purpose of the program breakingit down into each section, such as highlighting, collecting facts and analysing,. I want to make sure which information I should put for each stage. After that, I defined the core functionaliy of the program . I wrote it down based on the brief and included the short under each subheading.

THe problem I encountered was analysing the long breif ass well as the details. It took me few time reading that and I foundi t challenging to rewrite the purpose and the functionality in my own words without copying directly from the scenario. For the functionality, I initially found it difficult to understand how the daily caps should be applied. Well, I have not completed funtionality fully and constraints today . I also missed some features in functionality, and I need some requirements to fix it correctly.

### **29/01/2026 - Daily Entry 2 - Working on Gantt Chart, functionality and constraints

Today, I continued working by focusing on the Gantt chart and reviewing my functionality again. I did some research for the Gantt chart template and what information need to be included based on the milestone requirements . I also started creating my own Gantt chart . In addition,  I made some small improvements to ensure the functionality section to finish. I also started thinking about the system constraints and how they are different from functionality . I realised that functionality explains what the systen dies, while constraints describe the rules and limitation of the system. This helped me understand the difference clearly. 

The problem I encountered today was finding a suitable Gantt chart template, as I found it quite difficult to choose the correct format. I also found it difficult to choise the correct format. I also found it a bit confusing to clearly separate functionality and constraints at first, but after reviewing my work , I started to understand it better.

Overall, today's work was ligher compared to the first day, but still helped me make progress and improve my understanding of the assignment.



### 01/05/26 - Daily Entry 3  - IPO table and Gantt Chart

Today I continued working by focusing on the IPO table and the gantt chart. I created the IPO table by identifying the inputs , processes , and outputs of the system based on the functionality I had written . This helped me understand how the system works step by step. I also started wroking on the gantt chart using Excel. 

The problem I encountered was structuring the IPO correctly and deciding how to organise the tasks in the Gantt Chart. At first, I was not sure how detailed it shoulbe. However, after reviewing some examples, I was able to improve my understaning and make it clear. 

### 02/05/26 - Daily Entry 4 - Finalising Milestone 1

Today I focused on reviewing and finalising . I checked my purpose of the program , functionality , constraints , IPO table , and Gantt chart to make sure everything is consistent and matches the requirements. 

I made some small improvements to my work and ensured that all sections are clearly written and connected properly. I also reviewed my diary entries to make sure they reflet the progree I have made during this timeline. 

The main problem I faved was making sure all sections were aligned and not repeated , especialy  between functionality and constraints .For example, I checked the fare calculations were clearly explained in functionality and not repeated in the constraints section.This is my first time completing this type of assignment , and although there were many requirements, I have done my best carefully complete and review all parts of the milestone.
