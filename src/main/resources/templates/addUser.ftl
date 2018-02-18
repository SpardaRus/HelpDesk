<#import "spring.ftl" as spring/>
<html>
   <body>
         <div>
          <fieldset>
              <legend>Add user</legend>
              <form name="person" action="" method="POST">
                  Name   : <@spring.formInput "user.name" "" "text"/>    <br/>
                  Address: <@spring.formInput "user.address" "" "text"/>    <br/>
                  <input type="submit" value="Create" />
              </form>
          </fieldset>
      </div>
      <p><a href="/view">View</p>
   </body>
</html>