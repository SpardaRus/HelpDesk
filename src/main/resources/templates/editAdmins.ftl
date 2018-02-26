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
                <h1>Edit admins</h1>
</div>

   <div style="width: 500px;
           margin: auto;">
   <div align="left" >
    <table border="1" >
                              <tr>
                    <th width="40">id</th>
                    <th width="200">name</th>
                </tr>

            </table>
             </div>

            <div align="left" class="prokrutka">
            <table border="1" >
            <#list admins as admins>
                                <tr><td width="40">${admins.id}
                                <td width="200">${admins.name}
                              </#list>
            </table>
            </div>
            </div>


<div class="size">
          <fieldset>
              <legend>Add admin</legend>
              <form name="addAdmin" action="add_admin" method="POST">
              <table>
                                      <tr>
                                      <td>Name   :</td>
                                      </tr>
                                      <tr>
                                      <td><@spring.formInput "admin.name" "" "text"/></td>
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
                    <legend>Edit/Delete admins</legend>
                    <form name="e/d" action="" method="POST">
                    <table>
                        <tr>
                        <td>Id     :</td><td>Name   :</td>
                        </tr>
                        <tr>
                        <td><@spring.formInput "adminF.id" "" "text"/></td><td><@spring.formInput "adminF.name" "" "text"/></td>
                        </tr>
                        <tr>
                        <td><input type="submit" value="Find" formaction="find_admin"/></td>
                        <td><input type="submit" value="Edit" formaction="edit_admin"/></td>
                        <td><input type="submit" value="Delete" formaction="delete_admin" /></td>
                        </tr>
                    </table>

                    </form>
                </fieldset>
            </div>
            <div class="size">
            <a href="/"><button>Menu</button>
            </div>


    </body>
</html>