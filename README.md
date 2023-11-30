# My Personal Project

## Physics Calculator

My application will allow users to create physics equations, enter all
**known** parameters, and compute the **unknown** parameter(s) if possible. This 
application will be useful for students that need to solve physics equations quickly.
Note that the program does not actually solve entire problems for students; it simply
allows students that have already determined how to solve the problem to skip tedious
computation and arrive straight to the answer. This project is interest of me because
I am currently taking a physics course and I believe that a program like this would 
allow students to worry less about the computational aspect of problems and focus more on
conceptual aspect.

## User Stories

- As a user, I want to be able to add a new equation to my list of equations.
- As a user, I want to be able to specify the unknown values and enter the known values 
for an equation, and either get the result or be notified that I did not enter sufficient
information.
- As a user, I want to be able to view my entire list of equations, and see the status
of each equation.
- As a user, I want to be able to delete any equation from my list of equations.
- As a user, I want to be able to modify the input values for an equation from my list
and see the new result.

### Phase 2: User Stories

- As a user, I want to be able to save the entire list of equations to file.
- As a user, I want to be able to load my list of equations from file.

### Phase 3: Instructions To Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by 
clicking "New Equation".
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by 
clicking "Delete Equation".
- You can locate my visual component by clicking "Help".
- You can save the state of my application by clicking "Save Data".
- You can reload the state of my application by clicking "Load Data".

### Phase 4: Task 2
Tue Nov 28 17:53:37 PST 2023

Added equation of type Force to equations list!

Tue Nov 28 17:53:39 PST 2023

Added equation of type Flow Rate to equations list!

Tue Nov 28 17:53:41 PST 2023

Removed equation at index: 1

Tue Nov 28 17:53:44 PST 2023

Added equation of type Density to equations list!

Tue Nov 28 17:53:46 PST 2023

Added equation of type Force to equations list!

Tue Nov 28 17:53:47 PST 2023

Removed equation at index: 0

Tue Nov 28 17:53:49 PST 2023

Removed equation at index: 1

Tue Nov 28 17:53:50 PST 2023

Removed equation at index: 0

### Phase 4: Task 3

Given more time, one thing I could do to refactor the structure of my code is to introduce a new abstract class along 
the lines of "PopupWindow", and have HelpWindow and NewEqWindow extend this abstract class. I would then have 
PhysicsWindow contain a list of PopupWindow.

The reason for doing this is that HelpWindow and NewEqWindow already share some functionality - they are new windows
that pop up upon certain user actions. By abstracting their behaviour into a new abstract class, it would be easier to
make similar pop-up windows if we wanted to add new functionality (such as a congratulations window that pops up when 
the user solves an equation). However, one drawback is that NewEqWindow has some different functionality; it has a 
bidirectional relationship so that it can tell PhysicsWindow to create specific equations. Deciding whether this
bidirectionality should be part of the abstract class's behaviour or just unique to NewEqWindow would require more 
planning about the types of new pop-up windows that we would want to create in the future.