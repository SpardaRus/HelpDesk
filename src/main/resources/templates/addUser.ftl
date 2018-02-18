<html>
   <body>
      <h2>Add user</h2>
      <form:form method = "POST" action = "/addu">
         <table>
            <tr>
               <td><form:label path = "name">Name</form:label></td>
               <td><form:input path = "name" /></td>
            </tr>
            <tr>
               <td><form:label path = "address">Address</form:label></td>
               <td><form:input path = "address" /></td>
            </tr>

            <tr>
               <td colspan = "2">
                  <input type = "submit" value = "Submit"/>
               </td>
            </tr>
         </table>
      </form:form>
   </body>

</html>