# Lab04 Building a Recipe Book App with Jetpack Compose

## Goal
This lab gives you the possibility to practice Jetpack Compose and become more familiar with different UI elements, layouts, and modifiers.
There are many tasks and requirements described below. You do not need to complete all of them—pick the ones that seem useful for you. Focus first on the UI structure and styling.

## Task description
You will create a screen for a recipe book (see Figure 1).

The app should display a headline and a scrollable list of recipes.
Each recipe item should contain:

- An image.
- A recipe name.
- A short description.

You can add additional elements like tags (e.g., “Vegan”, “Quick”) or ratings (e.g., stars or numbers) later.

As making lists scrollable has not been covered in class, you will have to research how to do this to successfully solve the task, and it should be relatively straightforward to figure out. This is also the case for image loading. Some sources for how to load images have been added to the end of the assignment.

## Requirements
### 1. Structure & Reuse
- Organize your UI into reusable composable functions (e.g., RecipeCard, TagRow, RatingBar).
- Write preview functions with dummy data to test your components.
- Test with different text lengths to find solutions for overflowing texts.

### 2. Styling
- Use different fonts, weights, and sizes for the various text elements.
- Apply proper margins, paddings, and spacing.
- Select a theme and apply consistent colors.


### 3. Interactivity
- Add a simple filter mechanism (e.g., filter by recipe name or by tag).
- Add a floating “Add” button that opens a dialog to enter a new recipe.
