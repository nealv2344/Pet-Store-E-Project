<%-- 
    Document   : addAdmin
    Created on : Apr 5, 2020, 1:29:04 PM
    Author     : Neal Valdez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="businessObjs.Administrator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <title>Admin Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--BOOTSTRAP 4-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <!--FONTS-->
        <link href="https://fonts.googleapis.com/css?family=Englebert&display=swap" rel="stylesheet">
    <!--STYLESHEETS-->
        <link rel="stylesheet" href="styles/style.css">
    <!--ICONS-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    
        <style>
        #header-nav {
            border-bottom: 2px solid #e6e6e6;
            padding-bottom: 50px;
            padding-top: 50px;
        }
        .jumbotron {
            background: rgba(71, 144, 71, 0.82);
            /*background-image: url(pictures/logo_opacity.png);*/
            background-repeat: no-repeat;
            background-size: 10px;
            background-origin: content-box;
            background-position: center;
            box-shadow: 0px 0px 30px rgba(0, 0, 0, 0.54);
            border: 1px solid rgba(0, 0, 0, 0.1);
        }
        .container {
            background: rgba(71, 144, 71, 0.82);
            box-shadow: 0px 0px 30px rgba(0, 0, 0, 0.54);
            border: 1px solid rgba(0, 0, 0, 0.1);
        }
        .navbar {
            box-shadow: 0px 0px 30px rgba(0, 0, 0, 0.54);
        }
         .nav-link {
        color: white;
        font-weight: bold;
        font-size: 20px;
        letter-spacing: 2px;
    }
        @media screen and (max-width: 600px) {
            .navbar {
                box-shadow: 0px 0px 0px;
                justify-content: center;
            }

        }


    </style>
    </head>
    <body>
        <div class="jumbotron jumbotron-fluid">
        <div class="container-fluid">
            <h1>Animal <img src="pictures/logo_cutout_transparent.png" class="logo-img"> Avenue</h1>
        </div>
    </div>
    <div class="container">
        <div class="row" id="header-nav">
            <div class="col-sm-3">
                
            </div>
            <div class="col-sm-6">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
                        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                            <li class="nav-item">
                                <a class="nav-link" href="index.html">Home <span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">About Us</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Contact</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">FAQ</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
            <div class="col-sm-3">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
                        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                            <li class="nav-item">
                                <a class="nav-link" href="index.html">Sign Out</a>
                            </li>
                            
                        </ul>
                    </div>
                </nav>
        </div>
        </div>
        <div class="row" id="admin-header">
            <div class="col-sm-12">
                <h1>Orders</h1>
            </div>
        </div>
        <div class="row" id="order-lookup">
            <form id="order-lookup-form" action="adminHandler?action=orderLookup" method="POST">
            <label>Look up Order:</label>
            <input type="text" name="orderNum"id="lookupOrder" placeholder="order num">
            <input type="submit" name="submit" id="submit" value="Lookup">
          </form>
        </div>   
        
        <div class="row" id="cancelOrder">
            <form id="order-lookup-form" action="adminHandler?action=candelOrder" method="POST">
            <label>Cancel Order:</label>
            <input type="text" name="cancelOrder"id="cancelOrder" placeholder="order num">
            <input type="submit" name="submit" id="submit" value="Cancel">
          </form>
        </div>
        
        <div class="row" id="admin-header">
            <div class="col-sm-12">
                <h1>Products</h1>
            </div>
        </div>
        
        <div class="row" id="Product">
            <form id="order-lookup-form" action="adminHandler?action=productInfo" method="POST">
            <label>Product Info:</label>
            <input type="text" name="productSku"id="productInfo" placeholder="sku number">
            <input type="submit" name="submit" id="submit" value="Lookup">
          </form>
        </div>
        
        <div class="row" id="addProduct">
            <form id="order-lookup-form" action="adminHandler?action=addProduct" method="POST">
                <label>Add new Product:</label><br>
            <input type="text" name="productSku"id="productSku" placeholder="number"><br>
            <input type="text" name="productName"id="productName" placeholder="name"><br>
            <input type="text" name="productPrice"id="productPrice" placeholder="price"><br>
            <input type="text" name="productStock"id="productStock" placeholder="stock"><br>
            <input type="submit" name="submit" id="submit" value="Add">
          </form>
        </div>
        
        <div class="row" id="deleteProduct">
            <form id="order-lookup-form" action="adminHandler?action=deleteProduct" method="POST">
            <label>Delete Product:</label>
            <input type="text" name="productSku"id="deleteProduct" placeholder="sku number">
            <input type="submit" name="submit" id="submit" value="Delete">
          </form>
        </div>
        
        <div class="row" id="admin-header">
            <div class="col-sm-12">
                <h1>Customers</h1>
            </div>
        </div>
        
        <div class="row" id="deleteCustomer">
            <form id="order-lookup-form" action="adminHandler?action=deleteCustomer" method="POST">
            <label>Delete Customer:</label>
            <input type="text" name="customerId"id="deleteCustomer" placeholder="customer id">
            <input type="submit" name="submit" id="submit" value="Delete">
          </form>
        </div>
        
        <div class="row" id="customerHistory">
            <form id="order-lookup-form" action="adminHandler?action=customerHistory" method="POST">
            <label>Customer History:</label>
            <input type="text" name="customerId"id="customerHistory" placeholder="customer id">
            <input type="submit" name="submit" id="submit" value="Search">
          </form>            
        </div>
        
        <div class="row" id="admin-header">
            <div class="col-sm-12">
                <h1>Administrators</h1>
            </div>
        </div>
        
        <div class="row" id="customerHistory">
            <form id="order-lookup-form" action="adminHandler?action=addAdmin" method="POST">
                <label>Add new Admin:</label><br>
            <input type="text" name="adminId"id="customerHistory" placeholder="new Id"><br>
            <input type="text" name="fName"id="customerHistory" placeholder="First Name"><br>
            <input type="text" name="lName"id="customerHistory" placeholder="Last Name"><br>
            <input type="text" name="address"id="customerHistory" placeholder="Address"><br>
            <input type="text" name="password"id="customerHistory" placeholder="Password"><br>
            <input type="submit" name="submit" id="submit" value="Add">
          </form>            
        </div>
        
        <p style="text-align:center; font-size: 30px;">Administrator ${a1.getFname()} successfully added!</p>
        
        <div class="row" id="customerHistory">
            <form id="order-lookup-form" action="adminHandler?action=deleteAdmin" method="POST">
            <label>Delete Admin:</label>
            <input type="text" name="adminId"id="customerHistory" placeholder="adminId">
            <input type="submit" name="submit" id="submit" value="Delete">
          </form>            
        </div>
    </div>       
    </body>
</html>

