<%-- 
    Document   : Success
    Created on : Mar 8, 2020, 11:53:18 AM
    Author     : Neal Valdez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Success</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--BOOTSTRAP 4-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <!--FONTS-->
        <link href="https://fonts.googleapis.com/css?family=Englebert&display=swap" rel="stylesheet">
    <!--STYLESHEETS-->
        <link rel="stylesheet" href="styles/login_signup.css">
    <!--ICONS-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    </head>
    <body>
        <h1>You have successfully created an account!</h1>
        <h4 style="text-align: center">${c1.getFname()},you may now continue shopping or proceed to checkout</h4>
        
        <div class="row">
    <div class="col-sm-12 text-center">
        <a href="index.jsp"><button id="shopping" class="btn btn-default btn-md center-block" Style="width: 25%; background-color: lightgray;">Continue Shopping</button></a>
        <button id="checkout" class="btn btn-default btn-md center-block" Style="width: 25%; background-color: lightgray;">Checkout</button>
     </div>
    </body>
</html>
