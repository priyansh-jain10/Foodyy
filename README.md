## Inspiration
We often have materials in our kitchen store but are limited with ideas of dishes to make. Why would one even compromise when you can get the most popular dishes. 

## What it does
This app takes data from Spoonacular API and displays different recipes with key highlights of types such as Vegetarian,Non-vegetarian,Vegan, Main-course, Snacks, side-dish etc . Users can filter the recipes based on their preferences and view ingredients on how to make it.
Users can also save their recipes for further view without internet.

## How we built it

We built Using most reliable architectue MVVM. We distinguished our logics in repository to remote and local data store, we parsed the Json array from API called once and passed into respective fragments so not to load at each stage, We used Room database to save Recipes and also datastore repository to save filter checks .

## Challenges we ran into

Most complicated challenge was working on MVVM architecture style and understanding the flow.Understanding the API in best way to deliver its functionalities in the app. Creating Databases and Aceesing them with Room databses. Using annotations of dagger-hilt library properly  

## Accomplishments that we're proud of
Not just making the app connecting the dots but we tried to make it upto most reliable tools and architectures we can use . It made us realise the use of such revolutionary technologies and impact it can make on big industry level projects

## What we learned

## What's next for Foody 
Probably we would like to make more good UI features actions,animations implement dark modes and make this code shippable to user needs.
## APK FILE 
The apk file can be found in codebase by name app-debug.apk
