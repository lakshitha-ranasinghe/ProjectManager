<%-- 
    Document   : addMenu
    Created on : May 26, 2016, 11:00:51 PM
    Author     : Lakshitha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<div class="breadcrumbs">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-sm-4">
                <h1>
                    Menus
                </h1>
            </div>
            <div class="col-lg-8 col-sm-8">
                <ol class="breadcrumb pull-right">
                    <li>
                        <a href="#">
                            Home
                        </a>
                    </li>
                    <li class="active">
                        Add Menus
                    </li>
                </ol>
            </div>
        </div>
    </div>
</div>

<div class="component-bg container">
    <div class="bs-docs-section mar-b-30">
        <div class="bs-example">
            <form class="form-horizontal" role="form" method="POST">
                <label for="menuTypes" class="col-sm-1 control-label">Type</label>
                <div class="form-group col-sm-3">                  
                    <form:select path="menuTypes" items="${menuTypes}" class="form-control" name="menuType"/>
                </div> 
                
                <div class="form-group">
                    <label for="areaName" class="col-sm-1 control-label">Name</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="name" name="areaName">
                    </div>
                    <div class="col-sm-2">
                        <button class="btn btn btn-login btn-primary" type="submit">Add</button>
                    </div>
                </div>               
            </form>
        </div>
    </div>
</div>
