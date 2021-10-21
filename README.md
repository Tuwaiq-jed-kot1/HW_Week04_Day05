# Fragment Navigation and Dialog Project
---
The assignment will add a further condition to the Dialog Project that we applied together in the lab at the end of the day. Thus, the starting point will be from the last thing that we did in Dialog Project.

> Note: If you miss some piece of the code through following the lab, you can find the latest version of the Dialog Project in this assignment.

## Part One - Modify on Register screen
- You must add TextView in Register screen for gender where when the user click on TextView the Dialog shown and ask user to select one of two choice `male/female` and show the item selected in the same TextView.


## Part Two - Modify on Click Send
- When the user click on send button will go to another fragment that will display all info that the user entered in the register screen instead of showing in the same screen.
- To do this part you need to modify:

    1. create two fragments named `MainFragment` and `InfoFragment`. `MainFragment` represent Register screen, that means all code in `MainActivity` and `activity_main.xml` will be in this fragment with it’s layout. `InfoFragment` is for displaying the entered information. 
    2. change code in `MainActiviy` and it’s layout to can add/replace fragment. 
    3. When click on `send button` in `MainFragment` It will send all info that user entered to `InfoFragment` to display it.
