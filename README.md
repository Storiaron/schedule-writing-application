# Shift scheduler
## Table of Contents
- [Description](#description)
- [Project Description](#project-description)
- [How To Use](#how-to-use)
- [Technologies Used](#technologies-used)
- [Coming Soon](#coming-soon)

## Description
Shift scheduler is a full stack application that can write multiple different kind of schedules and track employee work hours. 
## Project Description
With this project I aimed to create an application that can manage shift scheduling for workplaces, where schedule writing is non-trivial. (e.g.: retail, hospitality-industry, etc.) Scheduling employees based on pre-specified minimal employee count for each day, and taking into account individual day-off requests, where possible.
On the backend I have used the factory pattern to make the codebase easily expandable for any kind-of schedule type. Currently two placeholder, basic types are done for demo purposes, more complex and different types will be added later. The main focus of the project was practicing software-architecture and creating a codebase that is easy to adapt to numerous different requirements.
The server is secured using spring security, each request is authenticated and if needed checked for proper authority. (e.g.: regular employees can't generate schedules, non-logged-in users can't see employee names)
The project is currently in development, with only the base functionality and code-architecture finished, styling/design and more complex schedule options coming soon.
### Current Schedule Types:
* Default: Employees work 8 hours a day, each day has only one shift. No limit on the amount of days someone can work in a row. Only schedules enough people to meet minimum count.
* 12h, single shift: Employees work 12 hours a day, each day has only one shift. Limited to maximum 4 days in a row. Only schedules enough people to meet minimum count.
## How To Use
(placeholder for the installation process)
1. Add new employees, at least one should be "Admin". The corresponding endpoint allows free access for demo and testing purposes.
2. Login and send day-off requests if you wish.
3. Log into an "Admin" employee's account, navigate to the "daily requirements" page and submit how many shifts, min employees, etc. each day should have and submit.
4. As an "Admin" you can navigate to the "generate schedule" page, select a type and generate the schedule. The schedule will only be generated for days that have been submitted in the previous step. Work hours are automatically subtracted from monthly work hours for each employee, when a schedule is generated.
5. You can use the "/api/employee/reset" endpoint to reset work hours back to their maximum (the number given at employee creation)
## Technologies Used
* React.js
* PostgreSQL
* Spring Boot
## Coming Soon
1. More complex schedule types, like schedules with multiple shifts, a utility score based schedule and many more.
2. Proper design and styling for the entire frontend.
3. Application build and dockerisation.
