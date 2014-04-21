# Aesthetic Issues #

Mark's list of aesthetic issues:

- Don't let the user set their font, it can look ugly.
	- Fix: CSS3 font files
- Do something about the default bootstrap bar. Everybody who know's web development knows what it is, and it takes away from the uniqueness of our site.
- Titles need to be per page (having itinerate as the title for every page is not helpful when people come back to the tab after doing other stuff, and the current URI parsing is also non helpful).
- Our site's colors should probably match the logo.

## Landing Page ##
- Set background size to the same as the remaining screen size (i.e. window size - navbar)
	- Fix: Javascript window.resize and window.load function with a delayed callback
- Prevent the background from fading to blue before switching to another picture
	- Fix: Implement the JS from my J-Pop site

## Nav Bar ##
- Center the login button
- Widen the box so the text fields don't touch the edges
	- Fix: CSS padding

## Date Range Page ##
- Resolve the differences between Chrome and Firefox when it comes to rendering the choose dates page.
- Make the start and end bar align perfectly, or change it entirely; there is no middle.
- *Make the page fade to the itinerary, rather than being a dedicated page entirely, or rewrite/animate the landing page to also set the dates; let our users enjoy our beautiful images longer.*

## Sign In Page ##
- There appear to be some margins set on the form bars. Get rid of them.
- Add a margin between the form and the navbar.

## Itinerary Page ##
- Your Itinerary should be on a single line
- The calendar needs a smoother theme
- The Events Box is disgusting. It needs to be bordered, needs a more consistent scroll, and less space between results. Also pictures.
- The search should be some sort of click or hover to expand feature; it takes up too much space.
- Needs a way to specify the name of the itinerary
- Needs to store the itinerary in some variable, and read and save to it.

## Show Itineraries Page ##
- It's pretty damn good
