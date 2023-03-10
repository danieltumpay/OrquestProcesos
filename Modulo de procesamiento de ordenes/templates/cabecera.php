<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tienda de Útiles FISI</title>
    <!-- <link rel="shortcut icon" href="downloadIcon.ico" type="image/x-icon"/> -->
    <!-- <link rel="shortcut icon" href="" type="image/x-icon"> -->
    <link rel="icon" href="img/utiles.png" type="image/x-icon">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    <!--iconos-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
</head>
<body class="container">
<nav class="navbar navbar-expand-sm navbar-light justify-content-between" style="background-color: #8556AF;">
    <!-- Brand/logo -->
    <a class="navbar-brand" href="index.php">
        <img src="img/utiles_escolares_2022.png" alt="logo" style="height:80px; margin-left:20px; border-radius:10px;">
    </a>
    
    <!-- Links -->
    <ul class="navbar-nav">
        <li class="nav-item">
        <a class="nav-link" href="index.php">Inicio</a>
        </li>
        <li class="nav-item">
        <a class="nav-link" href="index.php">Nosotros</a>
        </li>
        <li class="nav-item" style="margin-right:20px;">
        <a class="nav-link" href="detalles_carrito.php">Carrito (<?php 
            echo (empty($_SESSION['detalles']))?0:count($_SESSION['detalles']);
        ?>)</a> 
        </li>
    </ul>
    </nav>
    <br>
    
    <div class="container">