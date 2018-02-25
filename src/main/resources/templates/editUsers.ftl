<#import "spring.ftl" as spring/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <style>
    .prokrutka {
    margin: auto;
            height: 200px; /* высота нашего блока */
            width: 500px; /* ширина нашего блока */
    overflow-y: scroll; /* прокрутка по вертикали */
    }
    </style>
    <style>
        .size {
        margin: auto;
        height: 130px; /* высота нашего блока */
        width: 500px; /* ширина нашего блока */
        }
       </style>


</head>
    <body>
<div align="center" style="margin: auto;
                    height: 50px;
                    width: 500px;">
                <h1>Edit users</h1>
</div>

   <div style="width: 500px;
           margin: auto;">
   <div align="left" >
    <table border="1" >
                              <tr>
                    <th width="40">id</th>
                    <th width="200">name</th>
                    <th width="200">address</th>
                </tr>

            </table>
             </div>

            <div align="left" class="prokrutka">
            <table border="1" >
            <#list users as users>
                                <tr><td width="40">${users.id}
                                <td width="200">${users.name}
                                <td width="200">${users.address}
                              </#list>
            </table>
            </div>
            </div>


<div class="size">
          <fieldset>
              <legend>Add user</legend>
              <form name="addUser" action="add_user" method="POST">
              <table>
                                      <tr>
                                      <td>Name   :</td><td>Address:</td>
                                      </tr>
                                      <tr>
                                      <td><@spring.formInput "user.name" "" "text"/></td>
                                      <td><@spring.formInput "user.address" "" "text"/></td>
                                      </tr>
                                      <tr>
                                      <td><input type="submit" value="Create" /></td>
                                     </tr>
                                  </table>
              </form>
          </fieldset>
      </div>
      <div class="size">
                <fieldset>
                    <legend>Edit/Delete users</legend>
                    <form name="e/d" action="" method="POST">
                    <table>
                        <tr>
                        <td>Id     :</td><td>Name   :</td><td>Address:</td>
                        </tr>
                        <tr>
                        <td><@spring.formInput "userF.id" "" "text"/></td><td><@spring.formInput "userF.name" "" "text"/></td><td><@spring.formInput "userF.address" "" "text"/></td>
                        </tr>
                        <tr>
                        <td><input type="submit" value="Find" formaction="find_user"/></td>
                        <td><input type="submit" value="Edit" formaction="edit_user"/></td>
                        <td><input type="submit" value="Delete" formaction="delete_user" /></td>
                        </tr>
                    </table>

                    </form>
                </fieldset>
            </div>
    </body>
</html>