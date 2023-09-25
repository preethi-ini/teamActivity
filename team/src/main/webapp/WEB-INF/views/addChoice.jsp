<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Add A Restaurant Choice</title>
   <link rel="stylesheet" type="text/css" href="/css/addChoice.css">
</head>

<body>
  <h3>Add A Restaurant Choice</h3>

  <div id="addNewChoice">
  <form action="/addNewChoice" method="post">
    <p>
      <label>Enter Employee Id</label>
      <input type="number" name="employeeId" min="1" required />
    </p>
    <p>
      <label>Enter your Choice of Restaurant</label>
      <input type="text" name="choice" required />
    </p>
    <input type="submit" value="Submit" />
  </form>
</div>
</body>
</html>