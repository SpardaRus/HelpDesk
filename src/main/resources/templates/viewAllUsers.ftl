<#import "spring.ftl" as spring/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
    <body>
        <table style="margin: auto;" border="1">
            <caption><h1>Edit users</h1></caption>
            <thead>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>address</th>
            </tr>
            </thead>
            <tbody>
                ${users}
             </tbody>
        </table>
<div>
          <fieldset>
              <legend>Add user</legend>
              <form name="addUser" action="view" method="POST">
                  Name   : <@spring.formInput "user.name" "" "text"/>    <br/>
                  Address: <@spring.formInput "user.address" "" "text"/>    <br/>
                  <input type="submit" value="new User" />
              </form>
          </fieldset>
      </div>
    </body>
</html>
