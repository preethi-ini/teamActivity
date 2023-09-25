<!DOCTYPE html>
<html>
<head> 
  <title>Team Engagement activity</title>
  <link rel="stylesheet" type="text/css" href="/css/styles.css">
</head>
<body>
  <h1>DineDecide: Your Team's Lunch Picker!!</h1>
  <p>
	Here's how it works:
	
	Submit Your Preference: Every team member can access our web page and input their preferred restaurant.
	
	Get a Suggestion: At any time, users can request a recommendation. The application will then randomly select a restaurant from the list of submitted choices, ensuring fairness and variety.
	With DineDecide, gone are the days of lengthy debates and indecision. Let's make lunch time fun and spontaneous again!
  </p>
  <br />
  <div id="form_elements">
    <button onclick="location.href='/addChoice'">I would like to suggest a restaurant for the team retreat</button>
    <br />
    <button onclick="location.href='/getChoice'">I would like to know the selected choice of restaurant for the team retreat</button>
  </div>
  </body>
</html>